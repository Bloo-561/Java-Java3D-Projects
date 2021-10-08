package project9;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.GridLayout;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Material;
import javax.media.j3d.PhysicalBody;
import javax.media.j3d.PhysicalEnvironment;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.View;
import javax.media.j3d.ViewPlatform;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Project9 extends Applet {
	
	public static void main(String[] args) {
		new MainFrame(new Project9(), 2000, 600);
	}
	
	public void init() {
		this.setLayout(new GridLayout(1,2));
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		
		// Normal View //
		Canvas3D cv = new Canvas3D(gc);
		add(cv);
		SimpleUniverse su = new SimpleUniverse(cv);
		su.getViewingPlatform().setNominalViewingTransform();
		
		// Angled View //
		cv = new Canvas3D(gc);
	    add(cv);
	    BranchGroup bgView = createView(cv, new Point3d(1,1,1), 
	    	      new Point3d(0,0,0), new Vector3d(0,1,1));
	    su.addBranchGraph(bgView);
		
		// content //
		BranchGroup bg = createShape();
		bg.compile();
		su.addBranchGraph(bg);
	}
	
	private BranchGroup createView(Canvas3D cv, Point3d eye,
		    Point3d center, Vector3d vup) {
		View view = new View();
		view.setFrontClipDistance(0.01f);
	    view.setProjectionPolicy(View.PARALLEL_PROJECTION);
	    ViewPlatform vp = new ViewPlatform();
	    view.addCanvas3D(cv);
	    view.attachViewPlatform(vp);
	    view.setPhysicalBody(new PhysicalBody());
	    view.setPhysicalEnvironment(new PhysicalEnvironment());
	    Transform3D tf = new Transform3D();
	    tf.lookAt(eye, center, vup);
	    tf.invert();
		TransformGroup tg = new TransformGroup(tf);
	    tg.addChild(vp);
		BranchGroup bgView = new BranchGroup();
	    bgView.addChild(tg);
		return bgView;
	}

	private BranchGroup createShape() {
		BranchGroup root = new BranchGroup();
		Shape3D shape = new Shape3D();
	    TransformGroup spin = new TransformGroup();
	    Transform3D tr = new Transform3D();
	    tr.setScale(.25);
	    shape.setGeometry(Hex().getIndexedGeometryArray());
	    
	    // Spin //
	    spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    root.addChild(spin);
	    
	    
	    // object 
	    Appearance ap = new Appearance();
		PointAttributes pa = new PointAttributes(6, true);
		ap.setPointAttributes(pa);
		ap.setPolygonAttributes(new PolygonAttributes(PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_BACK, 0));

		Material mat = new Material();
		mat.setLightingEnable(true);
		mat.setShininess(50);
		ap.setMaterial(mat);

		TransformGroup tg = new TransformGroup(tr);
		tg.addChild(shape);
		spin.addChild(tg);
		shape.setAppearance(ap);
		
	    // rotator
	    Alpha alpha = new Alpha(-1, 6000);
	    RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
	    BoundingSphere bounds = new BoundingSphere();
	    rotator.setSchedulingBounds(bounds);
	    spin.addChild(rotator);
	    
	    // background and light
	    Background background = new Background(1.0f, 1.0f, 1.0f);
	    background.setApplicationBounds(bounds);
	    root.addChild(background);
	    
	    AmbientLight light = new AmbientLight(true, new Color3f(Color.gray));
	    light.setInfluencingBounds(bounds);
	    root.addChild(light);
	    
	    PointLight ptlight = new PointLight(new Color3f(Color.white), 
	      new Point3f(.5f,.5f,.5f), new Point3f(1f,0.2f,0f));
	    ptlight.setInfluencingBounds(bounds);
	    root.addChild(ptlight);
	    
	    return root;
	}
	
	private GeometryInfo Hex() {
		IndexedQuadArray iqa = new IndexedQuadArray(20, GeometryArray.COORDINATES, 80);
		
		// Positive Z Group //
        iqa.setCoordinate(0, new Point3f(2f, 0f, 0.25f));
        iqa.setCoordinate(1, new Point3f(1f, 2f, 0.25f));
        iqa.setCoordinate(2, new Point3f(-1f, 2f, 0.25f));
        iqa.setCoordinate(3, new Point3f(-2f, 0f, 0.25f));
        iqa.setCoordinate(4, new Point3f(-1f, -2f, 0.25f));
        iqa.setCoordinate(5, new Point3f(1f, -2f, 0.25f));
        
        // Negative Z Group //
        iqa.setCoordinate(6, new Point3f(2f, 0f, -0.25f));
        iqa.setCoordinate(7, new Point3f(1f, 2f, -0.25f));
        iqa.setCoordinate(8, new Point3f(-1f, 2f, -0.25f));
        iqa.setCoordinate(9, new Point3f(-2f, 0f, -0.25f));
        iqa.setCoordinate(10, new Point3f(-1f, -2f, -0.25f));
        iqa.setCoordinate(11, new Point3f(1f, -2f, -0.25f));
        
        
        int[] coord = {0,1,4,5, 5,4,1,0, 1,2,3,4, 4,3,2,1, 6,7,10,11, 11,10,7,6, 7,8,9,10, 10,9,8,7, 
        		0,1,6,7, 7,6,1,0, 1,2,7,8, 8,7,2,1, 2,3,8,9, 9,8,3,2, 3,4,9,10, 10,9,4,3, 4,5,10,11, 11,10,5,4,
        		0,5,6,11, 11,6,5,0};
       
        	
        iqa.setCoordinateIndices(0, coord);
		GeometryInfo gi = new GeometryInfo(iqa);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		return gi;
	}
	
	
}
