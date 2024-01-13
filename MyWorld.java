import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

import java.util.ArrayList; 
/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse MyWorld.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class MyWorld extends World
{
    
    public Vec2 gravity=new Vec2(0,2000);
    public double dt=0.01;
    public Class<? extends VerletObject> VerletObjectClassRef=new VerletObject().getClass();
    public Class<? extends Constraint> ConstraintClassRef=new Constraint().getClass();
    
    public int subSteps=8;
    public MyWorld()
    {    
        // Erstellt eine neue Welt mit 600x400 Zellen und einer Zell-Größe von 1x1 Pixeln.
        super(768, 768, 1);
        initBackground();
        addObject(new CircleOuter(300),350,350);
        //addObject(new SpawnPoint(gravity,dt),300,100);
        //for(int i=0;i<5;i++)addObject(new VerletObject(new Vec2(150+i*40,105),gravity),0,0);
    }
    public void initBackground(){
        GreenfootImage background = getBackground();//Create Image
        background.setColor(Color.BLACK);//Add Background color
        background.fillRect(0,0,getWidth(),getHeight());
    }
    public void act()
    {
        
        double sub_dt=dt/subSteps;
        for(int i=0;i<subSteps;i++){
            applyGravity();
            applyConstraint();
            checkCollisions();
            updatePosition(sub_dt);
        }
    }
    
    public void updatePosition(double dt){
        for(VerletObject object : getObjects(VerletObjectClassRef)){
            //System.out.println(object.position_current);
            object.updatePosition(dt);
        }
    }
    public void applyGravity(){
        for(VerletObject object : getObjects(VerletObjectClassRef)){
            object.accelerate(gravity);
        }
    }
    public void applyConstraint(){
        for(VerletObject object : getObjects(VerletObjectClassRef)){
            
            for(Constraint constraint : getObjects(ConstraintClassRef)){
                constraint.applyConstraint(object);
            }
        }
    }
    public void checkCollisions(){
        double response_coef = 0.75;
        ArrayList<VerletObject> allObjects=new ArrayList<VerletObject>();
        for(VerletObject object : getObjects(VerletObjectClassRef)){
            allObjects.add(object);
        }
        for(int i=0;i<allObjects.size();i++){
            VerletObject obj1=allObjects.get(i);
            for(int j=i+1;j<allObjects.size();j++){
                VerletObject obj2=allObjects.get(j);
                Vec2 v=obj1.position_current.subtract(obj2.position_current);
                double dist2=v.x*v.x+v.y*v.y;
                double minDist=obj1.radius+obj2.radius;
                
                if(dist2<minDist*minDist){
                    double dist=Math.sqrt(dist2);
                    Vec2 n=v.divide(dist);
                    
                    double massRatio1=obj1.radius/minDist;
                    double massRatio2=obj2.radius/minDist;
                    double delta=0.5*response_coef*(dist-minDist);
                    obj1.position_current=obj1.position_current.subtract(n.time(massRatio2*delta));
                    obj2.position_current=obj2.position_current.add(n.time(massRatio1*delta));
                }
            }
        }
    }
}