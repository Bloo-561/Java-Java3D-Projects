import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.Timer;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.*;
import java.util.*;

public class Breakout extends Applet implements ActionListener, KeyListener {

	// Play button to start game //
	private Button play = new Button("Play");

	// Timer that paces the game //
	private Timer timer;

	// Places the Bounds for the objects //
	private BoundingSphere bounds;

	// Global Transform Groups //
	private TransformGroup tg;
	
	// TransformGroup for all my Bricks //
	private TransformGroup obj_0;
	private TransformGroup obj_1;
	private TransformGroup obj_2;
	private TransformGroup obj_3;
	private TransformGroup obj_4;
	private TransformGroup obj_5;
	private TransformGroup obj_6;
	private TransformGroup obj_7;
	private TransformGroup obj_8;
	private TransformGroup obj_9;
	private TransformGroup obj_10;
	private TransformGroup obj_11;
	private TransformGroup obj_12;
	private TransformGroup obj_13;
	private TransformGroup obj_14;
	private TransformGroup obj_15;
	private TransformGroup obj_16;
	private TransformGroup obj_17;
	private TransformGroup obj_18;
	private TransformGroup obj_19;
	private TransformGroup obj_20;
	private TransformGroup obj_21;
	private TransformGroup obj_22;
	private TransformGroup obj_23;
	private TransformGroup obj_24;
	private TransformGroup obj_25;
	private TransformGroup obj_26;
	private TransformGroup obj_27;

	// Paddle Transform Variables //
	private TransformGroup pobjtr;
	private Transform3D paddleLocation = new Transform3D();

	// Ball Transform Variables //
	private TransformGroup bobjtr;
	private Transform3D ballLocation = new Transform3D();

	// Switcher to make bricks invisible //
	private Switch selector = new Switch(Switch.CHILD_MASK);
	public BitSet flag_i = new BitSet(28);
	private int bitValue = 27;

	// Variables for Game //
	private float width = 0.0f;
	private float height = 0.0f;
	private float hsign = 1.0f;
	private float vsign = 1.0f;
	private float ploc = 0.0f;

	public static void main(String[] args) {
		new MainFrame(new Breakout(), 1280, 720);
	}

	public void init() {
		setLayout(new BorderLayout());
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D cv = new Canvas3D(gc);
		add(cv, BorderLayout.CENTER);
		cv.addKeyListener(this);
		timer = new Timer(250, this);
		Panel top = new Panel();
		top.add(play);
		add(top, BorderLayout.NORTH);
		play.addActionListener(this);

		BranchGroup scene = createSceneGraph();

		SimpleUniverse su = new SimpleUniverse(cv);
		su.getViewingPlatform().setNominalViewingTransform();
		su.addBranchGraph(scene);
	}

	public BranchGroup createSceneGraph() {
		BranchGroup root = new BranchGroup();
		tg = new TransformGroup();

		bounds = new BoundingSphere(new Point3d(), 100.0);
		
		// Bricks //
		obj_0 = new TransformGroup();
		obj_1 = new TransformGroup();
		obj_2 = new TransformGroup();
		obj_3 = new TransformGroup();
		obj_4 = new TransformGroup();
		obj_5 = new TransformGroup();
		obj_6 = new TransformGroup();
		obj_7 = new TransformGroup();
		obj_8 = new TransformGroup();
		obj_9 = new TransformGroup();
		obj_10 = new TransformGroup();
		obj_11 = new TransformGroup();
		obj_12 = new TransformGroup();
		obj_13 = new TransformGroup();
		obj_14 = new TransformGroup();
		obj_15 = new TransformGroup();
		obj_16 = new TransformGroup();
		obj_17 = new TransformGroup();
		obj_18 = new TransformGroup();
		obj_19 = new TransformGroup();
		obj_20 = new TransformGroup();
		obj_21 = new TransformGroup();
		obj_22 = new TransformGroup();
		obj_23 = new TransformGroup();
		obj_24 = new TransformGroup();
		obj_25 = new TransformGroup();
		obj_26 = new TransformGroup();
		obj_27 = new TransformGroup();
		
		pobjtr = new TransformGroup();
		bobjtr = new TransformGroup();

		selector.setCapability(Switch.ALLOW_SWITCH_WRITE);
		pobjtr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		bobjtr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		root.addChild(pobjtr);
		root.addChild(bobjtr);

		// Making the Ball //
		Sphere ball = new Sphere(0.03f);
		bobjtr = new TransformGroup();
		bobjtr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D bpos = new Transform3D();
		bpos.setTranslation(new Vector3f(0.0f, 0.0f, 0.0f));
		bobjtr.setTransform(bpos);
		bobjtr.addChild(ball);

		CollisionDectorGroup ballGroup = new CollisionDectorGroup(bobjtr);
		ballGroup.setSchedulingBounds(bounds);

		tg.addChild(bobjtr);
		tg.addChild(ballGroup);

		root.addChild(tg);

		// Making the Paddle //
		Appearance ap = new Appearance();
		Color3f color = new Color3f(Color.WHITE);
		Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
		Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
		ap.setMaterial(new Material(color, black, color, white, 70f));
		Box paddle = new Box(.2f, .01f, .2f, ap);
		pobjtr = new TransformGroup();
		pobjtr.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D playerpos = new Transform3D();
		playerpos.setTranslation(new Vector3f(0.0f, -0.4f, 0.0f));
		pobjtr.setTransform(playerpos);
		pobjtr.addChild(paddle);
		root.addChild(pobjtr);

		// Yellow Brick 1 //
		Appearance apY = new Appearance();
		color = new Color3f(Color.YELLOW);
		black = new Color3f(0.0f, 0.0f, 0.0f);
		white = new Color3f(1.0f, 1.0f, 1.0f);
		apY.setMaterial(new Material(color, black, color, white, 70f));
		Box yBrick = new Box(.1f, .03f, .1f, apY);
		obj_0 = new TransformGroup();
		obj_0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yBrickpos = new Transform3D();
		yBrickpos.setTranslation(new Vector3f(-0.75f, 0.4f, 0.0f));
		obj_0.setTransform(yBrickpos);
		obj_0.addChild(yBrick);
		
		selector.addChild(obj_0);

		flag_i.set(0);
		selector.setChildMask(flag_i);

		// Yellow Brick 2 //
		Box yBrick2 = new Box(.1f, .03f, .1f, apY);
		obj_1 = new TransformGroup();
		obj_1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yBrickpos2 = new Transform3D();
		yBrickpos2.setTranslation(new Vector3f(-0.50f, 0.4f, 0.0f));
		obj_1.setTransform(yBrickpos2);
		obj_1.addChild(yBrick2);
		
		selector.addChild(obj_1);

		flag_i.set(1);
		selector.setChildMask(flag_i);

		// Yellow Brick 3 //
		Box yBrick3 = new Box(.1f, .03f, .1f, apY);
		obj_2 = new TransformGroup();
		obj_2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yBrickpos3 = new Transform3D();
		yBrickpos3.setTranslation(new Vector3f(-0.25f, 0.4f, 0.0f));
		obj_2.setTransform(yBrickpos3);
		obj_2.addChild(yBrick3);
		
		selector.addChild(obj_2);

		flag_i.set(2);
		selector.setChildMask(flag_i);

		// Yellow Brick 4 //
		Box yBrick4 = new Box(.1f, .03f, .1f, apY);
		obj_3 = new TransformGroup();
		obj_3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yBrickpos4 = new Transform3D();
		yBrickpos4.setTranslation(new Vector3f(0f, 0.4f, 0.0f));
		obj_3.setTransform(yBrickpos4);
		obj_3.addChild(yBrick4);
		
		selector.addChild(obj_3);

		flag_i.set(3);
		selector.setChildMask(flag_i);

		// Yellow Brick 5 //
		Box yBrick5 = new Box(.1f, .03f, .1f, apY);
		obj_4 = new TransformGroup();
		obj_4.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yBrickpos5 = new Transform3D();
		yBrickpos5.setTranslation(new Vector3f(0.25f, 0.4f, 0.0f));
		obj_4.setTransform(yBrickpos5);
		obj_4.addChild(yBrick5);
		
		selector.addChild(obj_4);

		flag_i.set(4);
		selector.setChildMask(flag_i);

		// Yellow Brick 6 //
		Box yBrick6 = new Box(.1f, .03f, .1f, apY);
		obj_5 = new TransformGroup();
		obj_5.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yBrickpos6 = new Transform3D();
		yBrickpos6.setTranslation(new Vector3f(0.5f, 0.4f, 0.0f));
		obj_5.setTransform(yBrickpos6);
		obj_5.addChild(yBrick6);
		
		selector.addChild(obj_5);

		flag_i.set(5);
		selector.setChildMask(flag_i);

		// Yellow Brick 7 //
		Box yBrick7 = new Box(.1f, .03f, .1f, apY);
		obj_6 = new TransformGroup();
		obj_6.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D yBrickpos7 = new Transform3D();
		yBrickpos7.setTranslation(new Vector3f(0.75f, 0.4f, 0.0f));
		obj_6.setTransform(yBrickpos7);
		obj_6.addChild(yBrick7);
		
		selector.addChild(obj_6);

		flag_i.set(6);
		selector.setChildMask(flag_i);

		// Orange Brick 1 //
		Appearance apO = new Appearance();
		color = new Color3f(Color.ORANGE);
		black = new Color3f(0.0f, 0.0f, 0.0f);
		white = new Color3f(1.0f, 1.0f, 1.0f);
		apO.setMaterial(new Material(color, black, color, white, 70f));
		Box oBrick = new Box(.1f, .03f, .1f, apO);
		obj_7 = new TransformGroup();
		obj_7.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D oBrickpos = new Transform3D();
		oBrickpos.setTranslation(new Vector3f(-0.75f, 0.3f, 0.0f));
		obj_7.setTransform(oBrickpos);
		obj_7.addChild(oBrick);
		
		selector.addChild(obj_7);

		flag_i.set(7);
		selector.setChildMask(flag_i);

		// Orange Brick 2 //
		Box oBrick2 = new Box(.1f, .03f, .1f, apO);
		obj_8 = new TransformGroup();
		obj_8.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D oBrickpos2 = new Transform3D();
		oBrickpos2.setTranslation(new Vector3f(-0.50f, 0.3f, 0.0f));
		obj_8.setTransform(oBrickpos2);
		obj_8.addChild(oBrick2);
		
		selector.addChild(obj_8);

		flag_i.set(8);
		selector.setChildMask(flag_i);

		// Orange Brick 3 //
		Box oBrick3 = new Box(.1f, .03f, .1f, apO);
		obj_9 = new TransformGroup();
		obj_9.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D oBrickpos3 = new Transform3D();
		oBrickpos3.setTranslation(new Vector3f(-0.25f, 0.3f, 0.0f));
		obj_9.setTransform(oBrickpos3);
		obj_9.addChild(oBrick3);
		
		selector.addChild(obj_9);

		flag_i.set(9);
		selector.setChildMask(flag_i);

		// Orange Brick 4 //
		Box oBrick4 = new Box(.1f, .03f, .1f, apO);
		obj_10 = new TransformGroup();
		obj_10.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D oBrickpos4 = new Transform3D();
		oBrickpos4.setTranslation(new Vector3f(0f, 0.3f, 0.0f));
		obj_10.setTransform(oBrickpos4);
		obj_10.addChild(oBrick4);
		
		selector.addChild(obj_10);

		flag_i.set(10);
		selector.setChildMask(flag_i);

		// Orange Brick 5 //
		Box oBrick5 = new Box(.1f, .03f, .1f, apO);
		obj_11 = new TransformGroup();
		obj_11.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D oBrickpos5 = new Transform3D();
		oBrickpos5.setTranslation(new Vector3f(0.25f, 0.3f, 0.0f));
		obj_11.setTransform(oBrickpos5);
		obj_11.addChild(oBrick5);
		
		selector.addChild(obj_11);

		flag_i.set(11);
		selector.setChildMask(flag_i);

		// Orange Brick 6 //
		Box oBrick6 = new Box(.1f, .03f, .1f, apO);
		obj_12 = new TransformGroup();
		obj_12.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D oBrickpos6 = new Transform3D();
		oBrickpos6.setTranslation(new Vector3f(0.5f, 0.3f, 0.0f));
		obj_12.setTransform(oBrickpos6);
		obj_12.addChild(oBrick6);
		
		selector.addChild(obj_12);

		flag_i.set(12);
		selector.setChildMask(flag_i);

		// Orange Brick 7 //
		Box oBrick7 = new Box(.1f, .03f, .1f, apO);
		obj_13 = new TransformGroup();
		obj_13.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D oBrickpos7 = new Transform3D();
		oBrickpos7.setTranslation(new Vector3f(0.75f, 0.3f, 0.0f));
		obj_13.setTransform(oBrickpos7);
		obj_13.addChild(oBrick7);
		
		selector.addChild(obj_13);

		flag_i.set(13);
		selector.setChildMask(flag_i);

		// Green Brick 1 //
		Appearance apG = new Appearance();
		color = new Color3f(Color.GREEN);
		black = new Color3f(0.0f, 0.0f, 0.0f);
		white = new Color3f(1.0f, 1.0f, 1.0f);
		apG.setMaterial(new Material(color, black, color, white, 70f));
		Box gBrick = new Box(.1f, .03f, .1f, apG);
		obj_14 = new TransformGroup();
		obj_14.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D gBrickpos = new Transform3D();
		gBrickpos.setTranslation(new Vector3f(-0.75f, 0.2f, 0.0f));
		obj_14.setTransform(gBrickpos);
		obj_14.addChild(gBrick);
		
		selector.addChild(obj_14);

		flag_i.set(14);
		selector.setChildMask(flag_i);

		// Green Brick 2 //
		Box gBrick2 = new Box(.1f, .03f, .1f, apG);
		obj_15 = new TransformGroup();
		obj_15.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D gBrickpos2 = new Transform3D();
		gBrickpos2.setTranslation(new Vector3f(-0.50f, 0.2f, 0.0f));
		obj_15.setTransform(gBrickpos2);
		obj_15.addChild(gBrick2);
		
		selector.addChild(obj_15);

		flag_i.set(15);
		selector.setChildMask(flag_i);

		// Green Brick 3 //
		Box gBrick3 = new Box(.1f, .03f, .1f, apG);
		obj_16 = new TransformGroup();
		obj_16.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D gBrickpos3 = new Transform3D();
		gBrickpos3.setTranslation(new Vector3f(-0.25f, 0.2f, 0.0f));
		obj_16.setTransform(gBrickpos3);
		obj_16.addChild(gBrick3);
		
		selector.addChild(obj_16);

		flag_i.set(16);
		selector.setChildMask(flag_i);

		// Green Brick 4 //
		Box gBrick4 = new Box(.1f, .03f, .1f, apG);
		obj_17 = new TransformGroup();
		obj_17.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D gBrickpos4 = new Transform3D();
		gBrickpos4.setTranslation(new Vector3f(0.0f, 0.2f, 0.0f));
		obj_17.setTransform(gBrickpos4);
		obj_17.addChild(gBrick4);
		
		selector.addChild(obj_17);

		flag_i.set(17);
		selector.setChildMask(flag_i);

		// Green Brick 5 //
		Box gBrick5 = new Box(.1f, .03f, .1f, apG);
		obj_18 = new TransformGroup();
		obj_18.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D gBrickpos5 = new Transform3D();
		gBrickpos5.setTranslation(new Vector3f(0.25f, 0.2f, 0.0f));
		obj_18.setTransform(gBrickpos5);
		obj_18.addChild(gBrick5);
		
		selector.addChild(obj_18);

		flag_i.set(18);
		selector.setChildMask(flag_i);

		// Green Brick 6 //
		Box gBrick6 = new Box(.1f, .03f, .1f, apG);
		obj_19 = new TransformGroup();
		obj_19.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D gBrickpos6 = new Transform3D();
		gBrickpos6.setTranslation(new Vector3f(0.50f, 0.2f, 0.0f));
		obj_19.setTransform(gBrickpos6);
		obj_19.addChild(gBrick6);
		
		selector.addChild(obj_19);

		flag_i.set(19);
		selector.setChildMask(flag_i);

		// Green Brick 7 //
		Box gBrick7 = new Box(.1f, .03f, .1f, apG);
		obj_20 = new TransformGroup();
		obj_20.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D gBrickpos7 = new Transform3D();
		gBrickpos7.setTranslation(new Vector3f(0.75f, 0.2f, 0.0f));
		obj_20.setTransform(gBrickpos7);
		obj_20.addChild(gBrick7);
		
		selector.addChild(obj_20);

		flag_i.set(20);
		selector.setChildMask(flag_i);

		// Blue Brick 1 //
		Appearance apB = new Appearance();
		color = new Color3f(Color.BLUE);
		black = new Color3f(0.0f, 0.0f, 0.0f);
		white = new Color3f(1.0f, 1.0f, 1.0f);
		apB.setMaterial(new Material(color, black, color, white, 70f));
		Box bBrick = new Box(.1f, .03f, .1f, apB);
		obj_21 = new TransformGroup();
		obj_21.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D bBrickpos = new Transform3D();
		bBrickpos.setTranslation(new Vector3f(-0.75f, 0.1f, 0.0f));
		obj_21.setTransform(bBrickpos);
		obj_21.addChild(bBrick);
		
		selector.addChild(obj_21);

		flag_i.set(21);
		selector.setChildMask(flag_i);

		// Blue Brick 2 //
		Box bBrick2 = new Box(.1f, .03f, .1f, apB);
		obj_22 = new TransformGroup();
		obj_22.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D bBrickpos2 = new Transform3D();
		bBrickpos2.setTranslation(new Vector3f(-0.50f, 0.1f, 0.0f));
		obj_22.setTransform(bBrickpos2);
		obj_22.addChild(bBrick2);
		
		selector.addChild(obj_22);

		flag_i.set(22);
		selector.setChildMask(flag_i);

		// Blue Brick 3 //
		Box bBrick3 = new Box(.1f, .03f, .1f, apB);
		obj_23 = new TransformGroup();
		obj_23.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D bBrickpos3 = new Transform3D();
		bBrickpos3.setTranslation(new Vector3f(-0.25f, 0.1f, 0.0f));
		obj_23.setTransform(bBrickpos3);
		obj_23.addChild(bBrick3);
		
		selector.addChild(obj_23);

		flag_i.set(23);
		selector.setChildMask(flag_i);

		// Blue Brick 4 //
		Box bBrick4 = new Box(.1f, .03f, .1f, apB);
		obj_24 = new TransformGroup();
		obj_24.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D bBrickpos4 = new Transform3D();
		bBrickpos4.setTranslation(new Vector3f(0.0f, 0.1f, 0.0f));
		obj_24.setTransform(bBrickpos4);
		obj_24.addChild(bBrick4);
		
		selector.addChild(obj_24);

		flag_i.set(24);
		selector.setChildMask(flag_i);

		// Blue Brick 5 //
		Box bBrick5 = new Box(.1f, .03f, .1f, apB);
		obj_25 = new TransformGroup();
		obj_25.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D bBrickpos5 = new Transform3D();
		bBrickpos5.setTranslation(new Vector3f(0.25f, 0.1f, 0.0f));
		obj_25.setTransform(bBrickpos5);
		obj_25.addChild(bBrick5);
		
		selector.addChild(obj_25);

		flag_i.set(25);
		selector.setChildMask(flag_i);

		// Blue Brick 6 //
		Box bBrick6 = new Box(.1f, .03f, .1f, apB);
		obj_26 = new TransformGroup();
		obj_26.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D bBrickpos6 = new Transform3D();
		bBrickpos6.setTranslation(new Vector3f(0.50f, 0.1f, 0.0f));
		obj_26.setTransform(bBrickpos6);
		obj_26.addChild(bBrick6);
		
		selector.addChild(obj_26);

		flag_i.set(26);
		selector.setChildMask(flag_i);

		// Blue Brick 7 //
		Box bBrick7 = new Box(.1f, .03f, .1f, apB);
		obj_27 = new TransformGroup();
		obj_27.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D bBrickpos7 = new Transform3D();
		bBrickpos7.setTranslation(new Vector3f(0.75f, 0.1f, 0.0f));
		obj_27.setTransform(bBrickpos7);
		obj_27.addChild(bBrick7);
		
		selector.addChild(obj_27);

		flag_i.set(27);
		selector.setChildMask(flag_i);
		
		root.addChild(selector);

		Color3f light1Color = new Color3f(1.0f, 0.0f, 0.2f);
		Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
		DirectionalLight light1 = new DirectionalLight(light1Color, light1Direction);
		light1.setInfluencingBounds(bounds);
		root.addChild(light1);

		// Set up the ambient light
		Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
		AmbientLight ambientLightNode = new AmbientLight(ambientColor);
		ambientLightNode.setInfluencingBounds(bounds);
		root.addChild(ambientLightNode);

		return root;
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (ploc > -0.8f) {
				ploc -= 0.1f;
			}
		}
		if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (ploc < 0.8f) {
				ploc += 0.1f;
			}
		}

	}

	public void keyReleased(KeyEvent e) {

	}

	public void keyTyped(KeyEvent e) {

	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == play) {
			if (!timer.isRunning()) {
				timer.start();
			}
		} else {
			// Moving Ball Up/Down //
			height += 0.1f * vsign;
			if (Math.abs(height * 2) >= 1) {
				vsign = -1.0f * vsign;
			}

			// Moving Ball Sideways and hit Borders //
			width += 0.1f * hsign;
			if (Math.abs(width) >= 1) {
				hsign = -1.0f * hsign;
			}

			// Ball Position //
			ballLocation.setTranslation(new Vector3f(width, height, 0.0f));
			bobjtr.setTransform(ballLocation);

			// Paddle Position //
			paddleLocation.setTranslation(new Vector3f(ploc, -0.4f, 0.0f));
			pobjtr.setTransform(paddleLocation);
		}
	}

	class CollisionDectorGroup extends Behavior {
		private boolean onCollision = false;
		
		private Group group;

		private WakeupOnCollisionEntry oEnter;
		private WakeupOnCollisionExit oExit;

		public CollisionDectorGroup(TransformGroup bobjtr) {
			group = bobjtr;
			onCollision = false;
		}

		public void initialize() {
			oEnter = new WakeupOnCollisionEntry(group);
			oExit = new WakeupOnCollisionExit(group);
			wakeupOn(oEnter);
		}

		public void processStimulus(Enumeration num) {
			WakeupCriterion criterion = (WakeupCriterion) num.nextElement();
			onCollision = !onCollision;
			if (onCollision) {
				vsign = -1.0f * vsign;
				Node object = ((WakeupOnCollisionEntry) criterion).getTriggeringPath().getObject();
				object.setUserData(flag_i);
				System.out.println(object.getUserData());
				flag_i.clear(bitValue);
				selector.setChildMask(flag_i);
				wakeupOn(oExit);
			} else {
				bitValue--;
				wakeupOn(oEnter);
			}
		}
	}
}
