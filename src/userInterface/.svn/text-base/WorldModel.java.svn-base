package userInterface;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import businesslogic.*;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.GeometryArray;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.PositionPathInterpolator;
import javax.media.j3d.QuadArray;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Texture2D;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.View;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Quat4f;
import javax.vecmath.TexCoord2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;

import javax.media.j3d.LinearFog;
import javax.media.j3d.Fog;

import playground.*;

/**
 * WorldModel.java
 * 
 * Klasse zum Erzeugen und Verwalten des 3D-Modells der Welt. Mithilfe
 * von Klassen und Methoden aus der j3d-Library wird die Spielwelt gebaut
 * und entsprechend bei Aenderungen in der Logic-Schicht angepasst.
 * 
 * 
 * @author Soeren Kroell, Stephanie Scholl, Mario Sigel, Ersin Yilmiz
 */
public class WorldModel extends Observable implements Observer{

	private RotationInterpolator rotator;
	private PositionPathInterpolator positionInterpolator;
	private PositionPathInterpolator positionInterpolatorShoot;
	private Alpha alpha;
	private Alpha alphaTrans;
	
	private Alpha alphaShoot;
	
	private Area areas[][][];
	private float[] animKnots={0.0f, 1.0f};;
	private Point3f[] points={ new Point3f(0.0f, 0.0f, 0.0f),  new Point3f(0.0f, 0.0f, 0.0f)};

	Point3f defaultPosS = new Point3f(0.0f, -10.0f, 0.0f);		//WartePosition der Sphere(Shoot) in der Spielwelt
	private float[] animKnotsS={0.0f, 1.0f};
	private Point3f[] pointsS={ defaultPosS,  defaultPosS};

	float orientation = 0.0f;									//aktueller Winkel des Views im Bezug zur z-Achse auf der xz-Ebene 
	final float DELTAPHI= 0.1f;		
	
	Point3f cPos = new Point3f(0.0f, 0.0f, 0.0f);		//aktuelle Position in der Spielwelt
//	final float DELTAPOS = 0.2f;
	
	final int ALPHA_DURATION_TRANS = 60;
	final int ALPHA_DURATION_ROT = 120;
	final int ALPHA_DURATION_SHOOT = 1500;
	final int ALPHA_DURATION_FALLING = 400;
	
	final int DELAY =100;	

	private NPC[] npcs;
	private HashMap<String, ImageComponent2D> textures = new HashMap<String, ImageComponent2D>();

	private Color3f bgColour = new Color3f(0.03f, 0.05f, 0.1f);
	
	private GameEngine ge;

	private Alpha alphaBusy =  new Alpha(1, 10000);;
	private PositionPathInterpolator interpolatorBusy ;
	
	public WorldModel(WorldCanvas c3d, MainFrame frame, GameEngine ge, MapBuilder mb){

		this.ge=ge;
		ge.addObserver(this);
		ge.getHero().addObserver(this);
		areas = mb.getMap().getAreas();
 			
		BranchGroup objRoot = new BranchGroup();
		SimpleUniverse u = new SimpleUniverse(c3d);

		TransformGroup objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		TransformGroup objSpin = new TransformGroup();
		objSpin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
		objRoot.addChild(objSpin);
		objSpin.addChild(objTrans);

		ImageComponent2D imageTexture;

		// NPC
		npcs = mb.getNPCs();
		for(NPC npc : npcs){

			if (npc!=null){
				npc.addObserver(this);
				imageTexture = createTexture(frame, npc.getFile());
					
				QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
						| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);
				
				QuadArray plane2 = new QuadArray(4, GeometryArray.COORDINATES
						| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);

				Shape3D planeObj = createPlane(imageTexture, plane, true);
				Shape3D planeObj2 = createPlane(imageTexture, plane2, true);
				
				Transform3D trans = new Transform3D();
				trans.setTranslation(new Vector3f(0.0f+npc.getPosX()*2.0f, -0.03f, -0.0f-npc.getPosZ()*2.0f));
				TransformGroup objTransJ = new TransformGroup(trans);
				
				Transform3D rotate = new Transform3D();
				rotate.rotY(-Math.PI/2*npc.getRotation());
				TransformGroup objRot = new TransformGroup(rotate);
				objRot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				
				Transform3D rotate2 = new Transform3D();
				rotate2.rotY(-Math.PI-Math.PI/2*npc.getRotation());
				TransformGroup objRot2 = new TransformGroup(rotate2);
				objRot2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			
				TransformGroup objRotBoth = new TransformGroup();
				objRotBoth.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				
				Quat4f q=new Quat4f();
				q.set(new AxisAngle4f(-1*(float)Math.sin(Math.PI/2*npc.getRotation()), 0, 1*(float)Math.cos(Math.PI/2*npc.getRotation()), (float)(Math.PI/2)));
				Transform3D axis = new Transform3D(q, new Vector3d(0d, -1.0d, 0d), 1.0d);
				
				Alpha alphaJawa = new Alpha(1, ALPHA_DURATION_FALLING);
				rotator = new RotationInterpolator(alphaJawa, objRotBoth, axis, 0.0f, 0.0f);
		        BoundingSphere boundsRot = new BoundingSphere();		
				rotator.setSchedulingBounds(boundsRot);		
				objRotBoth.addChild(rotator);
				
				npc.setRotator(rotator);
				npc.setAlpha(alphaJawa);
				
				objRot.addChild(planeObj);				
				objRotBoth.addChild(objRot);		
				
				objRot2.addChild(planeObj2);				
				objRotBoth.addChild(objRot2);
				
				objTransJ.addChild(objRotBoth);
				objTrans.addChild(objTransJ);
			}
			

				
		}
		//Sky
		Transform3D scale = new Transform3D();
		scale.setScale(25.0);
		TransformGroup objScale = new TransformGroup(scale);
		imageTexture = createTexture(frame, "image/sky.png");
		TransformGroup sky = createSky(imageTexture, 0, 0, -0.86f, false);
		objScale.addChild(sky);
		objSpin.addChild(objScale);		
		
		Transform3D scale2 = new Transform3D();
		scale.setScale(25.0);
		TransformGroup objScale2 = new TransformGroup(scale);
		imageTexture = createTexture(frame, "image/floorDefault.png");
		TransformGroup floor = createFloor(imageTexture, 0, 0, 1f-1f/25f-0.001f, false);
		objScale2.addChild(floor);
		objSpin.addChild(objScale2);		


		//Create World by Map
				
		for(int i=0; i<200; i++){
			for (int j=0; j<200; j++){
				for (int k=0; k<2; k++){
					if(areas[i][k][j]!=null){
						if(areas[i][k][j].getSky().getFile()!=null){
							imageTexture = createTexture(frame, areas[i][k][j].getSky().getFile());
							TransformGroup objRotSky = createSky(imageTexture, (i-100)*2, (j-100)*2, k*2, areas[i][k][j].getSky().isTransparency());
							objTrans.addChild(objRotSky);
						}
	
						if(areas[i][k][j].getFloor().getFile()!=null){
							imageTexture = createTexture(frame, areas[i][k][j].getFloor().getFile());
							TransformGroup objRotFloor = createFloor(imageTexture, (i-100)*2, (j-100)*2, k*2, areas[i][k][j].getFloor().isTransparency());
							objTrans.addChild(objRotFloor);
						}
	
						if(areas[i][k][j].getFront().getFile()!=null){
							imageTexture = createTexture(frame, areas[i][k][j].getFront().getFile());
							TransformGroup objTransFront = createFront(imageTexture, (i-100)*2, (j-100)*2, k*2, areas[i][k][j].getFront().isTransparency());
							objTrans.addChild(objTransFront);
						}
	
						if(areas[i][k][j].getRight().getFile()!=null){
							imageTexture = createTexture(frame, areas[i][k][j].getRight().getFile());
							TransformGroup objRotRight = createRight(imageTexture, (i-100)*2, (j-100)*2, k*2, areas[i][k][j].getRight().isTransparency());
							objTrans.addChild(objRotRight);
						}
						
						if(areas[i][k][j].getLeft().getFile()!=null){
							imageTexture = createTexture(frame, areas[i][k][j].getLeft().getFile());
							TransformGroup objRotLeft = createLeft(imageTexture, (i-100)*2, (j-100)*2, k*2, areas[i][k][j].getLeft().isTransparency());
							objTrans.addChild(objRotLeft);
						}
						
						if(areas[i][k][j].getBack().getFile()!=null){
							imageTexture = createTexture(frame, areas[i][k][j].getBack().getFile());
							TransformGroup objRotBack = createBack(imageTexture, (i-100)*2, (j-100)*2, k*2, areas[i][k][j].getBack().isTransparency());
							objTrans.addChild(objRotBack);	
						}				
											
						if(areas[i][k][j].getMapObject()!=null){
							if(areas[i][k][j].getMapObject() instanceof Item){
							MapObject mapObject = areas[i][k][j].getMapObject(); 
							String file = mapObject.getFile();
							imageTexture = createTexture(frame, file);

							QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
									| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);
							
							QuadArray plane2 = new QuadArray(4, GeometryArray.COORDINATES
									| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);

							Shape3D planeObj = createPlane(imageTexture, plane, true);
							Shape3D planeObj2 = createPlane(imageTexture, plane2, true);
							
							Transform3D trans = new Transform3D();
							trans.setTranslation(new Vector3f(0, 0, 0));
							TransformGroup objTransJ = new TransformGroup(trans);
							
							Transform3D rotate = new Transform3D();
							rotate.rotY(-Math.PI/2*mapObject.getRotation());
							TransformGroup objRot = new TransformGroup(rotate);
													
							Transform3D rotate2 = new Transform3D();
							rotate2.rotY(-Math.PI-Math.PI/2*mapObject.getRotation());
							TransformGroup objRot2 = new TransformGroup(rotate2);


							Transform3D transMapObject = new Transform3D();
							TransformGroup objTransMapObject = new TransformGroup(transMapObject);
							objTransMapObject.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
							
					        Transform3D axisMapObject = new Transform3D();
							Alpha alphaMapObject = new Alpha(1, 50);
					        PositionPathInterpolator positionInterpolatorMapObject = new PositionPathInterpolator(alphaMapObject, objTransMapObject, axisMapObject, animKnotsS, pointsS);
			
							mapObject.setPositionPathInterpolator(positionInterpolatorMapObject);
							mapObject.setAlphaMapObject(alphaMapObject);
							
							BoundingSphere boundsTransMapObject = new BoundingSphere();				
							positionInterpolatorMapObject.setSchedulingBounds(boundsTransMapObject);
							objTransMapObject.addChild(positionInterpolatorMapObject);
							

							TransformGroup objRotBoth = new TransformGroup();
							
							objRot.addChild(planeObj);				
							objRotBoth.addChild(objRot);		
							
							objRot2.addChild(planeObj2);				
							objRotBoth.addChild(objRot2);
							
							objTransJ.addChild(objRotBoth);

							
							objTransMapObject.addChild(objTransJ);
							objTrans.addChild(objTransMapObject);	
							
							
							positionInterpolatorMapObject.setPosition(0, cPos);
							positionInterpolatorMapObject.setPosition(1, new Point3f((i-100)*2, 0.3f, -((j-100)*2)));
							alphaMapObject.setStartTime(System.currentTimeMillis());
							}
							
						}						
						
						
					}
				}
			}
		}
		
		//Rotation und Translation der World bei Bewegung des Views
		Transform3D yAxis = new Transform3D();
		alpha = new Alpha(1, ALPHA_DURATION_ROT);
		rotator = new RotationInterpolator(alpha, objSpin, yAxis, 0.0f,
				(float) Math.PI * 0.0f);
	
        Transform3D axis = new Transform3D();
		alphaTrans = new Alpha(1, ALPHA_DURATION_TRANS);
        positionInterpolator = new PositionPathInterpolator(alphaTrans, objTrans, axis, animKnots, points);
		
        BoundingSphere boundsRot = new BoundingSphere();		
		rotator.setSchedulingBounds(boundsRot);		
		objSpin.addChild(rotator);

		BoundingSphere boundsTrans = new BoundingSphere();				
		positionInterpolator.setSchedulingBounds(boundsTrans);
		objTrans.addChild(positionInterpolator);

		//Background
		Background background = new Background();
		background.setColor(bgColour);
		background.setApplicationBounds(new BoundingSphere());
		objRoot.addChild(background);

		
		//Create lights
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),100.0);
		Color3f ambLightColour = new Color3f(0.6f, 0.6f, 0.6f);
		AmbientLight ambLight = new AmbientLight(ambLightColour);
		ambLight.setInfluencingBounds(bounds);
		Color3f dirLightColour = new Color3f(0.8f, 0.8f, 0.8f);
		Vector3f dirLightDir = new Vector3f(-1.0f, -1.0f, -1.0f);
		DirectionalLight dirLight = new DirectionalLight(dirLightColour,
				dirLightDir);
		dirLight.setInfluencingBounds(bounds);
		objSpin.addChild(ambLight);
		objSpin.addChild(dirLight);      

	        
	    //BusyObject
		Transform3D transB = new Transform3D();		
		TransformGroup objTransB = new TransformGroup(transB);		
		Appearance appB = new Appearance();
		objTransB.addChild(new Sphere(0.2f, Sphere.GENERATE_NORMALS, 120, appB));
		objTrans.addChild(objTransB);

		objTransB.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);		
        Transform3D axisB = new Transform3D();
        interpolatorBusy = new PositionPathInterpolator(alphaBusy, objTransB, axisB, animKnotsS, pointsS);		

		BoundingSphere boundsTransB = new BoundingSphere();				
		interpolatorBusy.setSchedulingBounds(boundsTransB);
		objTransB.addChild(interpolatorBusy);
		
		//Sphere
		Appearance app = new Appearance();

		Color3f ambientColour = new Color3f(0.1f, 0.2f, 0.5f);
		Color3f diffuseColour = new Color3f(1.0f, 0.0f, 0.0f);
		Color3f specularColour = new Color3f(1.0f, 1.0f, 0.5f);
		Color3f emissiveColour = new Color3f(0.0f, 0.0f, 0.0f);

		float shininess = 20.0f;

	    app.setMaterial(new Material(ambientColour, emissiveColour, diffuseColour, specularColour, shininess));

		Transform3D transS = new Transform3D();
		//trans.setTranslation(new Vector3f(0.0f, 0.0f, -5.0f));
		TransformGroup objTransS = new TransformGroup(transS);

		objTransS.addChild(new Sphere(0.2f, Sphere.GENERATE_NORMALS, 120, app));
		objTrans.addChild(objTransS);

		objTransS.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		
        Transform3D axisShoot = new Transform3D();
		alphaShoot = new Alpha(1, ALPHA_DURATION_SHOOT);
        positionInterpolatorShoot = new PositionPathInterpolator(alphaShoot, objTransS, axisShoot, animKnotsS, pointsS);

		BoundingSphere boundsTransS = new BoundingSphere();				
		positionInterpolatorShoot.setSchedulingBounds(boundsTransS);
		objTransS.addChild(positionInterpolatorShoot);
		
		//Fog
		LinearFog fog = new LinearFog();
		Color3f color = bgColour;
		fog.setColor(color);
		fog.setFrontDistance(3.0);
		fog.setBackDistance(25.0);
		fog.setCapability(Fog.ALLOW_COLOR_WRITE);
		fog.setCapability(LinearFog.ALLOW_DISTANCE_WRITE);
		fog.setInfluencingBounds(bounds);
		objRoot.addChild(fog);	
		
		//Settings View
		objRoot.compile();

		u.getViewingPlatform().setNominalViewingTransform();
		u.addBranchGraph(objRoot);

		View view = u.getViewer().getView();
		view.setSceneAntialiasingEnable(true);
		view.setMinimumFrameCycleTime(25);
		view.setTransparencySortingPolicy(View.TRANSPARENCY_SORT_GEOMETRY);
				
	}
	
	/**
	 * 
	 * Erzeugen eines 3d-Texturobjekts, wenn nicht bereits ein entsprechendes in
	 * der HashMap 'textures' vorhanden ist.
	 * 
	 * @param frame
	 * @param filename
	 * @return
	 */
	private ImageComponent2D createTexture(MainFrame frame, String filename) {
		
		if(textures.containsKey(filename)){
			return textures.get(filename);
		}else{
			TextureLoader loader = new TextureLoader(filename, frame);
			ImageComponent2D imageTexture = loader.getImage();
			
			textures.put(filename, imageTexture);
			
			return imageTexture;
		}
	}
	
	/**
	 * Erzeugen eines j3D-Objekts fuer die in Ausgangsposition vor dem Spieler platzierte Wand.
	 * 
	 * @param imageTexture
	 * @param x
	 * @param z
	 * @param y
	 * @param transparency
	 * @return
	 */
	private TransformGroup createFront(ImageComponent2D imageTexture, float x, float z, float y, boolean transparency) {
		QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
				| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);

		Shape3D planeObj = createPlane(imageTexture, plane, transparency);
		
		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3f(0.0f+x, 0.0f+y, -1.0f-z));

		TransformGroup objTrans = new TransformGroup(trans);
		
		objTrans.addChild(planeObj);
		
		return objTrans;
	}
	
	/**
	 * Erzeugen eines j3D-Objekts fuer die in Ausgangsposition rechts neben dem Spieler platzierte Wand.
	 * 
	 * @param imageTexture
	 * @param x
	 * @param z
	 * @param y
	 * @param transparency
	 * @return
	 */
	private TransformGroup createRight(ImageComponent2D imageTexture, float x, float z, float y,  boolean transparency) {
		QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
				| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);

		Shape3D planeObj = createPlane(imageTexture, plane, transparency);
		
		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3f(0.0f-z, 0.0f+y, -1.0f-x));
		TransformGroup objTrans = new TransformGroup(trans);
		
		objTrans.addChild(planeObj);
		
		Transform3D rotate = new Transform3D();
		rotate.rotY(-Math.PI / 2.0d);
		TransformGroup objRot = new TransformGroup(rotate);
		
		objRot.addChild(objTrans);
		
		return objRot;
	}
	
	/**
	 * Erzeugen eines j3D-Objekts fuer die in Ausgangsposition links neben dem Spieler platzierte Wand.
	 * 
	 * @param imageTexture
	 * @param x
	 * @param z
	 * @param y
	 * @param transparency
	 * @return
	 */
	private TransformGroup createLeft(ImageComponent2D imageTexture, float x, float z, float y,  boolean transparency) {
		QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
				| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);

		Shape3D planeObj = createPlane(imageTexture, plane, transparency);
		
		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3f(0.0f+z, 0.0f+y, -1.0f+x));
		TransformGroup objTrans = new TransformGroup(trans);
		
		objTrans.addChild(planeObj);
		
		Transform3D rotate = new Transform3D();
		rotate.rotY(Math.PI / 2.0d);
		TransformGroup objRot = new TransformGroup(rotate);
		
		objRot.addChild(objTrans);
		
		return objRot;
	}
	
	/**
	 * Erzeugen eines j3D-Objekts fuer die in Ausgangsposition hinter dem Spieler platzierte Wand.
	 * 
	 * @param imageTexture
	 * @param x
	 * @param z
	 * @param y
	 * @param transparency
	 * @return
	 */
	private TransformGroup createBack(ImageComponent2D imageTexture, float x, float z, float y,  boolean transparency) {
		QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
				| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);

		Shape3D planeObj = createPlane(imageTexture, plane, transparency);
		
		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3f(0.0f-x, 0.0f+y, -1.0f+z));
		TransformGroup objTrans = new TransformGroup(trans);
		
		objTrans.addChild(planeObj);
		
		Transform3D rotate = new Transform3D();
		rotate.rotY(Math.PI / 1.0d);
		TransformGroup objRot = new TransformGroup(rotate);
		
		objRot.addChild(objTrans);
		
		return objRot;
	}
	

	/**
	 * 
	 * Erzeugen eines j3D-Objekts fuer die ueber dem Spieler platzierte Wand.
	 * 
	 * @param imageTexture
	 * @param x
	 * @param z
	 * @param y
	 * @param transparency
	 * @return
	 */
	private TransformGroup createSky(ImageComponent2D imageTexture, float x, float z, float y,  boolean transparency) {
		QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
				| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);

		Shape3D planeObj = createPlane(imageTexture, plane, transparency);
		
		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3f(0.0f+x, 0.0f-z, -1.0f-y));
		TransformGroup objTrans = new TransformGroup(trans);
		
		objTrans.addChild(planeObj);
		
		Transform3D rotate = new Transform3D();
		rotate.rotX(Math.PI / 2.0d);
		TransformGroup objRot = new TransformGroup(rotate);

		Vector3f polygonNormal = new Vector3f(0f,0f,1f);
		plane.setNormal(0,polygonNormal);
		plane.setNormal(1,polygonNormal);
		plane.setNormal(2,polygonNormal);
		plane.setNormal(3,polygonNormal);
		
		
		objRot.addChild(objTrans);
		
		return objRot;
	}
	
	/**
	 * Erzeugen eines j3D-Objekts fuer die unter dem Spieler platzierte Wand.
	 * 
	 * @param imageTexture
	 * @param x
	 * @param z
	 * @param y
	 * @param transparency
	 * @return
	 */
	private TransformGroup createFloor(ImageComponent2D imageTexture, float x, float z, float y,  boolean transparency) {
		QuadArray plane = new QuadArray(4, GeometryArray.COORDINATES
				| GeometryArray.TEXTURE_COORDINATE_2 | QuadArray.NORMALS);

		Shape3D planeObj = createPlane(imageTexture, plane, transparency);
		
		Transform3D trans = new Transform3D();
		trans.setTranslation(new Vector3f(0.0f+x, 0.0f+z, -1.0f+y));
		TransformGroup objTrans = new TransformGroup(trans);
		
		objTrans.addChild(planeObj);
		
		Transform3D rotate = new Transform3D();
		rotate.rotX(-Math.PI / 2.0d);
		TransformGroup objRot = new TransformGroup(rotate);
	
		Vector3f polygonNormal = new Vector3f(-1.1f,-1f,-0.5f);
		plane.setNormal(0,polygonNormal);
		plane.setNormal(1,polygonNormal);
		plane.setNormal(2,polygonNormal);
		plane.setNormal(3,polygonNormal);
		
		objRot.addChild(objTrans);
		
		return objRot;
	}
	
	
	/**
	 * 
	 * Erzeugen einer Plane, also einer quadratischen Flaeche aus welchen die Welt zusammen- 
	 * gestzt wird. 
	 * 
	 * @param imageTexture
	 * @param plane
	 * @param transparency
	 * @return
	 */
	private Shape3D createPlane(ImageComponent2D imageTexture, QuadArray plane, boolean transparency) {
		Point3f p = new Point3f(-1.0f, 1.0f, 0.0f);
		plane.setCoordinate(0, p);
		p.set(-1.0f, -1.0f, 0.0f);
		plane.setCoordinate(1, p);
		p.set(1.0f, -1.0f, 0.0f);
		plane.setCoordinate(2, p);
		p.set(1.0f, 1.0f, 0.0f);
		plane.setCoordinate(3, p);

		TexCoord2f q = new TexCoord2f(0.0f, 1.0f);
		plane.setTextureCoordinate(0, 0, q);
		q.set(0.0f, 0.0f);
		plane.setTextureCoordinate(0, 1, q);
		q.set(1.0f, 0.0f);
		plane.setTextureCoordinate(0, 2, q);
		q.set(1.0f, 1.0f);
		plane.setTextureCoordinate(0, 3, q);

		Appearance appear = new Appearance();
		
		if(transparency){
			appear.setTransparencyAttributes(new TransparencyAttributes(
			TransparencyAttributes.NICEST, 0.1f));			
		}else{
			appear.setTransparencyAttributes(new TransparencyAttributes(
					TransparencyAttributes.NONE, 0.1f));				
		}
		
		Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
				imageTexture.getWidth(), imageTexture.getHeight());
		texture.setImage(0, imageTexture);
		//texture.setEnable(false);
		
	    TextureAttributes texAttr = new TextureAttributes();
	    texAttr.setTextureMode(TextureAttributes.MODULATE);
	    appear.setTextureAttributes(texAttr);

	    Color3f ambientColour = new Color3f(1.0f, 1.0f, 1.0f);
	    Color3f emissiveColour = new Color3f(1.0f, 0.9f, 0.8f);
	    Color3f specularColour = new Color3f(0.0f, 0.0f, 0.0f);
	    Color3f diffuseColour = new Color3f(0.0f, 0.0f, 0.0f);
	    float shininess = 0.02f;
	    appear.setMaterial(new Material(ambientColour, emissiveColour,
	            diffuseColour, specularColour, shininess));
	    
		appear.setTexture(texture);

		Shape3D planeObj = new Shape3D(plane, appear);
		return planeObj;
	}



	/**
	 * 
	 * Drehen entsprechend der aktuellen Orientierung in der GameEngine.
	 * 
	 */
	public void rotate() {

		float orientationNew;
		
		if(ge.getOrientation()==0 && orientation>(float)Math.PI)
			orientationNew=(float)Math.PI*2.0f;
		else if(ge.getOrientation()==0 && orientation<(float)-Math.PI)
			orientationNew=(float)Math.PI*-2.0f;
		else
			orientationNew=ge.getOrientation();
			
		rotator.setMinimumAngle(orientation);
		rotator.setMaximumAngle(orientationNew);
		alpha.setStartTime(System.currentTimeMillis());
		orientation = ge.getOrientation();
	}
	

	
	/**
	 * Bewegung zur aktuellen Position posX, posZ in aus der GameEngine.
	 */
	public void move (){
				
			Point3f newPos=new Point3f(ge.getPosX(), cPos.y, ge.getPosZ());	
			
			positionInterpolator.setPosition(0, cPos);
			positionInterpolator.setPosition(1, newPos);
			alphaTrans.setStartTime(System.currentTimeMillis());
			
			cPos=new Point3f(newPos.x, newPos.y, newPos.z);
		

	}

	/**
	 * Methode wird zum Anstossen der automatischen PostRender-Methode im WorldCanvas genutzt.
	 */
	public void busyWorld(){
		
		Point3f newPos1=new Point3f(0, -10, 0);
		Point3f newPos2=new Point3f(0, -20, 0);	
		
		interpolatorBusy.setPosition(0, newPos1);
		interpolatorBusy.setPosition(1, newPos2);

		alphaBusy.setStartTime(System.currentTimeMillis());
	}
	
	/**
	 * Schiesse.
	 */
	public void shoot(){
	
		Point3f newPos1=new Point3f(-cPos.x, cPos.y-0.4f, -cPos.z);
		Point3f newPos2=new Point3f(ge.getPosXSphere(), cPos.y-0.4f, ge.getPosZSphere());	
		
		positionInterpolatorShoot.setPosition(0, newPos1);
		positionInterpolatorShoot.setPosition(1, newPos2);

		alphaShoot.setStartTime(System.currentTimeMillis());
		//positionInterpolatorShoot.setPosition(1, defaultPosS);
	}
	
	public void shootStop(){
		positionInterpolatorShoot.setPosition(1, defaultPosS);
	}
	
	/**
	 * Uebergebener npc faellt um.
	 * 
	 * @param npc
	 */
	public void goDown(NPC npc){
		RotationInterpolator rotator = npc.getRotator();
		rotator.setMinimumAngle(0.0f);
		rotator.setMaximumAngle((float)Math.PI/2.0f);
		npc.getAlpha().setStartTime(System.currentTimeMillis());		
	}

	/**
	 * Item (aus dem Rucksack) wird an der aktuellen Position auf der Map platziert.
	 * 
	 * @param item
	 */
	public void setItemOnMap(Item item){
		PositionPathInterpolator positionInterpolatorMapObject = item.getPositionPathInterpolator();
		Alpha alphaMapObject = item.getAlphaMapObject();
		
		Point3f pos1Item=new Point3f(0, 0, 0);
		Point3f pos2Item=new Point3f((ge.getMap().translateX(ge.getPosX())-100)*2, 0.3f, -(ge.getMap().translateZ(ge.getPosZ())-100)*2);
		
		positionInterpolatorMapObject.setPosition(0, pos1Item);
		positionInterpolatorMapObject.setPosition(1, pos2Item);
		alphaMapObject.setStartTime(System.currentTimeMillis());
				
	}
	
	/**
	 * Alle besiegten Jawas stehen wieder auf.
	 */
	public void wakeJawas(){
		for(NPC npc : npcs){
			if (npc!=null){
				if(!npc.getAlive() && npc.getFile().equals("image/jawa.png")){
					RotationInterpolator rotator = npc.getRotator();
					rotator.setMinimumAngle((float)Math.PI/2.0f);
					rotator.setMaximumAngle(0.0f);
					npc.getAlpha().setStartTime(System.currentTimeMillis());					
					npc.setAlive(true);
				}
			}
		}
	}
	
	
	@Override
	public void update(Observable obj, Object arg) {
		if(arg instanceof String){
			if("move".equals((String)arg))
				move();
			if("rotate".equals((String)arg))
				rotate();
			if("shoot".equals((String)arg))
				shoot();
			if("shootStop".equals((String)arg))
				shootStop();
			if("wakeJawas".equals((String)arg))
				wakeJawas();
		}
		if(arg instanceof Item){
			setItemOnMap((Item)arg);
		}		
		if(arg instanceof NPC){
			goDown((NPC)arg);
			setChanged();
			notifyObservers((NPC)arg);
		}
		if(arg instanceof Area){
			Area area = (Area)arg;

			PositionPathInterpolator positionInterpolatorMapObject = area.getMapObject().getPositionPathInterpolator();
			Alpha alphaMapObject = area.getMapObject().getAlphaMapObject();
			
			Point3f pos1Item=new Point3f(0, 0, 0);
			Point3f pos2Item=new Point3f(0, -1.6f, 0);
			
			positionInterpolatorMapObject.setPosition(0, pos1Item);
			positionInterpolatorMapObject.setPosition(1, pos2Item);
			alphaMapObject.setStartTime(System.currentTimeMillis());
			
		}
		if(arg instanceof NPC[]){
			
			NPC[] npcsNeu = (NPC[])arg;
			for(NPC npc : npcsNeu){
				if(npc!=null)
					npc.addObserver(this);
			}
		}
	}
}
