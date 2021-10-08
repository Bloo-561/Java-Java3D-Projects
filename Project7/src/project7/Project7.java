package project7;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsConfiguration;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Material;
import javax.media.j3d.PointAttributes;
import javax.media.j3d.PointLight;
import javax.media.j3d.PolygonAttributes;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class Project7 extends Applet {
	public static void main(String[] args) {
		new MainFrame(new Project7(), 2000, 600);
	}

	public void init() {
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D cv = new Canvas3D(gc);
		setLayout(new BorderLayout());
		add(cv, BorderLayout.CENTER);
		BranchGroup bg = createShape();
		bg.compile();
		SimpleUniverse su = new SimpleUniverse(cv);
		su.getViewingPlatform().setNominalViewingTransform();
		su.addBranchGraph(bg);
	}

	private BranchGroup createShape() {
		BranchGroup obj = new BranchGroup();
		Shape3D shape = new Shape3D();
		shape.setGeometry(Mobius().getIndexedGeometryArray());
		Transform3D tr = new Transform3D();
		tr.setScale(.5);

		TransformGroup spin = new TransformGroup();
		spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		obj.addChild(spin);

		Appearance ap = new Appearance();
		PointAttributes pa = new PointAttributes(10, true);
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

		Alpha alpha = new Alpha(-1, 6000);
		RotationInterpolator rotate = new RotationInterpolator(alpha, spin);
		BoundingSphere bounds = new BoundingSphere();
		rotate.setSchedulingBounds(bounds);
		spin.addChild(rotate);
		
		// Background //
		Background background = new Background(1.0f, 1.0f, 1.0f);
		background.setApplicationBounds(bounds);
		obj.addChild(background);
		
		// Natural Light //
		AmbientLight light = new AmbientLight(true, new Color3f(Color.BLACK));
		light.setInfluencingBounds(bounds);
		obj.addChild(light);
		
		// Artificial Lighting //
		PointLight ptlight = new PointLight(new Color3f(Color.white), 	
											new Point3f(0.5f, 0.5f, 1f),
											new Point3f(1f, 0.2f, 0f));
		ptlight.setInfluencingBounds(bounds);
		obj.addChild(ptlight);

		return obj;
	}

	// Mobius Class //
	private GeometryInfo Mobius() {
		int m = 100;
		int n = 100;
		int p = 4 * ((m - 1) * (n - 1));

		IndexedQuadArray iqa = new IndexedQuadArray(m * n, GeometryArray.COORDINATES, p);
		Point3d[] vertices = new Point3d[m * n];
		int index = 0;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				double u = i * (4 * (Math.PI)) / (m - 1);
				double v = -0.3 + (j * (0.6 / (n - 1)));
				double x = (1 + v * Math.cos(u / 2)) * Math.cos(u);
				double y = (1 + v * Math.cos(u / 2)) * Math.sin(u);
				double z = v * Math.sin(u / 2);
				vertices[index] = new Point3d(x, y, z);
				index++;
			}
		}

		iqa.setCoordinates(0, vertices);
		index = 0;

		for (int i = 0; i < m - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				iqa.setCoordinateIndex(index, i * m + j);
				index++;
				iqa.setCoordinateIndex(index, i * m + j + 1);
				index++;
				iqa.setCoordinateIndex(index, (i + 1) * m + j + 1);
				index++;
				iqa.setCoordinateIndex(index, (i + 1) * m + j);
				index++;
			}
		}
		GeometryInfo gi = new GeometryInfo(iqa);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		return gi;
	}
}
