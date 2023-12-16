import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse VerletObject.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class VerletObject extends Actor
{
    
    Vec2 position_current;
    Vec2 position_old;
    Vec2 acceleration;
    
    public double radius=15;
    Color color=new Color(56,96,12);
    public VerletObject(){
        super();
        
    }
    public VerletObject(double radius, Color color,Vec2 initialPosition){
        super();
        
        position_current=initialPosition;
        position_old=new Vec2(position_current);
        acceleration=new Vec2(0,0);
        
        this.radius=radius;
        this.color=color;
        
        GreenfootImage im = new GreenfootImage((int)radius*2,(int)radius*2);
        im.setColor(color);//Add Background color
        im.fillOval(0,0,(int)radius*2,(int)radius*2);
        setImage(im);
    }
    
    public void updatePosition(double dt){
        Vec2 displacement=position_current.subtract(position_old);
        position_old=new Vec2(position_current);
        position_current=position_current.add(displacement).add(acceleration.time(dt*dt));
        acceleration=new Vec2();
    }
    public void accelerate(Vec2 acc){
        acceleration=acceleration.add(acc);
    }
    void setVelocity(Vec2 v, double dt)
    {
        position_old = position_current.subtract(v.time( dt));
    }
    public void act() 
    {
        setLocation((int)Math.round(position_current.x),(int)Math.round(position_current.y));
    }
}