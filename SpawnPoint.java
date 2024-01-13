import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse SpawnPoint.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
abstract public class SpawnPoint extends Actor
{
    
    protected int timer=0;
    protected Color color=new Color(0,0,0);
    public double shootSpeed=0.2;
    public double shootInitialSpeed=15;
    public double radius=7;
    
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