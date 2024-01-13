import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Red.
 * 
 * @author TheCyberWeaver 
 * @version 2024.1.13
 */
public class VerletObject extends Actor
{
    
    Vec2 position_current;  //die aktuelle Position
    Vec2 position_old;      //die Position beim letzten Frame
    Vec2 acceleration;      //Die Beschleunigung
    
    public double radius=15;
    Color color=new Color(56,96,12);
    
    public VerletObject(){
        super();
               
        GreenfootImage im = new GreenfootImage((int)radius*2,(int)radius*2);
        im.setColor(color);//Add Background color
        im.fillOval(0,0,(int)radius*2,(int)radius*2);
        setImage(im);        
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
    public void addedToWorld(World world){
        position_current=new Vec2(getX(),getY());
        position_old=new Vec2(position_current);
        acceleration=new Vec2(0,0);
    }
    //Position akualisieren
    //Verlet integration
    //Xn+1=Xn+Vn*dt
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
    //Einen Kreis auf der akuellen Position mahlen
    public void act() 
    {
        setLocation((int)Math.round(position_current.x),(int)Math.round(position_current.y));
    }
}