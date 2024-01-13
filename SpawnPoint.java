import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Red.
 * 
 * @author TheCyberWeaver 
 * @version 2024.1.13
 */
abstract public class SpawnPoint extends Actor
{
    
    protected int timer=0;
    protected Color color=new Color(0,0,0);
    protected double shootSpeed=0.8;
    protected double shootInitialSpeed=50;
    protected double radius=7;
    
    protected Class<? extends MyWorld> MyWorldClassRef=new MyWorld().getClass();
    
    public SpawnPoint(){
        
    }
    public SpawnPoint(double r){
        radius=r;
    }
    public void addedToWorld(World world){
        shootSpeed/=getWorldOfType(MyWorldClassRef).subSteps;
        shootInitialSpeed/=getWorldOfType(MyWorldClassRef).subSteps;
    }
    public void act() 
    {
        timer+=1;
        if(timer%(int)(1.0/shootSpeed)==0){            
            
            VerletObject ball=new VerletObject(radius,color,new Vec2(this.getX(),this.getY()));
            getWorld().addObject(ball,this.getX(),this.getY());
                       
            double xDelta=0;
            double yDelta=1;
            ball.setVelocity(new Vec2(shootInitialSpeed*xDelta,shootInitialSpeed*yDelta),getWorldOfType(MyWorldClassRef).dt);
            
        }
    }    
}