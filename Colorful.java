import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Colorful.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Colorful extends SpawnPoint
{
    public double angleSpeed=0.1;
    public double colorChangeSpeed=0.02;
    
    public Colorful(){
        super();
        shootInitialSpeed=50;
    }
    public Colorful(double r){
        super();
        radius=r;
        
    }
    public void addedToWorld(World world){
        angleSpeed/=getWorldOfType(MyWorldClassRef).subSteps;
        colorChangeSpeed/=getWorldOfType(MyWorldClassRef).subSteps;
    }
    public int randomInt(int a,int b){
        return (int) ((b-a+1) * Math.random() + a);
    }

    public static Color getRainbow(double t)
    {
        double r = Math.sin(t);
        double g = Math.sin(t + 0.33f * 2.0f * Math.PI);
        double b = Math.sin(t + 0.66f * 2.0f * Math.PI);
        return new Color((int)(255.0f * r * r),
                (int)(255.0f * g * g),
                (int)(255.0f * b * b));
    }
    public void act() 
    {
        timer+=1;
        if(timer%(int)(1.0/shootSpeed)==0){
            
            Color c=getRainbow(timer*colorChangeSpeed);
            double radius=randomInt(5,10);
            VerletObject ball=new VerletObject(radius,c,new Vec2(this.getX(),this.getY()));
            getWorld().addObject(ball,this.getX(),this.getY());
            
            
            
            double xDelta=Math.sin(timer*angleSpeed);
            double yDelta=Math.abs(Math.cos(timer*angleSpeed));
            ball.setVelocity(new Vec2(shootInitialSpeed*xDelta,shootInitialSpeed*yDelta),getWorldOfType(MyWorldClassRef).dt);
            
        }
    }  
}