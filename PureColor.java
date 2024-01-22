import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Red.
 * 
 * @author TheCyberWeaver 
 * @version 2024.1.13
 */
public class PureColor extends SpawnPoint
{
    public PureColor(){
        super();
        color=new Color(255,255,255);
    }
    public PureColor(double r){
        super(r);
    }
    public void act(){
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