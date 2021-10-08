import java.awt.*;
import java.applet.*;
import java.awt.event.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.applet.MainFrame;


public class Project5 extends Applet {

	private BranchGroup createName() {
		BranchGroup name = new BranchGroup(); // Name Object //
		Appearance ap = new Appearance();
		ap.setMaterial(new Material());
		Font3D font = new Font3D(new Font("SansSerif", Font.PLAIN, 1), new FontExtrusion()); // Font Type //
		
		Text3D fName = new Text3D(font, "Wyommie"); 	// First Name //
		Shape3D fShape = new Shape3D(fName, ap);	// Create First Name Shape //
		
		Text3D lName = new Text3D(font, "Mack"); // Last Name //
		Shape3D lShape = new Shape3D(lName, ap);	// Create Last Name Shape //
		
		// Transforming Properties for First Name //
		Transform3D tF = new Transform3D();
	    tF.setScale(0.5);
	    tF.setTranslation(new Vector3f(-.55f, .2f, -.5f));
	    
	    // Transforming Properties for Last Name //
	    Transform3D tL = new Transform3D();
	    tL.setScale(0.5);
	    tL.setTranslation(new Vector3f(-.9f,-.5f,0f));
	    
	    // Adding First Name to BranchGroup within the Transform Group //
	    TransformGroup tFirst = new TransformGroup(tF);
	    name.addChild(tFirst);
	    tFirst.addChild(fShape);
	    
	 // Adding Last Name to BranchGroup within a different Transform Group //
	    TransformGroup tLast = new TransformGroup(tL);
	    name.addChild(tLast);
	    tLast.addChild(lShape);
	    
	    //light
	    PointLight light = new PointLight(new Color3f(Color.white),
	                                      new Point3f(1f,1f,1f),
	                                      new Point3f(1f,0.1f,0f));
	    BoundingSphere bounds = new BoundingSphere();
	    light.setInfluencingBounds(bounds);
	    name.addChild(light);
	    
	 // BackGround //
	    Background background = new Background(.68f,.85f,.9f);
	    background.setApplicationBounds(bounds);
	    name.addChild(background);
		return name;
	}
	
	public void init() {
	    GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
	    Canvas3D cv = new Canvas3D(gc);
	    setLayout(new BorderLayout());
	    add(cv, BorderLayout.CENTER);
	    BranchGroup bg = createName();
	    bg.compile();
	    SimpleUniverse su = new SimpleUniverse(cv);
	    su.getViewingPlatform().setNominalViewingTransform();
	    su.addBranchGraph(bg);
	  }
	
	public static void main(String s[]) {
	    System.setProperty("sun.awt.noerasebackground", "true");
	    new MainFrame(new Project5(), 640, 480);
	  }
}
