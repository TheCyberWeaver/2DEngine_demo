import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

import java.util.ArrayList; 
/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Red.
 * 
 * @author TheCyberWeaver 
 * @version 2024.1.13
 */
public class MyWorld extends World
{
    
    public Vec2 gravity=new Vec2(0,3000);
    public double dt=0.01;//Ein vorausgesetzter Wert, Die Zeit zwischen zwei Frames
    public Class<? extends VerletObject> VerletObjectClassRef=new VerletObject().getClass();
    public Class<? extends Constraint> ConstraintClassRef=new Constraint().getClass();
    
    public int subSteps=8;  //Die Anzahl der Iteration jedes Frames
    public MyWorld()
    {    
        // Erstellt eine neue Welt mit 768x768 Zellen und einer Zell-Größe von 1x1 Pixeln.
        super(768, 768, 1);
        initBackground();
        
        addObject(new CircleOuter(),350,350);        //Eine Grenze von außen hinzufügen
    }
    public void initBackground(){
        GreenfootImage background = getBackground();        //Create Image
        background.setColor(Color.BLACK);                   //Add Background color
        background.fillRect(0,0,getWidth(),getHeight());
    }
    public void act()   //Main Loop
    {
        double sub_dt=dt/subSteps;      
        for(int i=0;i<subSteps;i++){    //Jede Iteration
            applyGravity();     //Gravitation anwenden
            applyConstraint();  //Zusammenstoß mit der Grenze
            checkCollisions();  //Zusammenstoß zwischen den Kugeln
            updatePosition(sub_dt); //Die nächste Position berechnen
        }
    }
    
    public void updatePosition(double dt){
        for(VerletObject object : getObjects(VerletObjectClassRef)){    //Iteration durch jeden Kugel
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
    //Ein dumme aber einfache Methode, Kollision zu überprüfen
    //Zeitkomplexität: O(N^2)
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