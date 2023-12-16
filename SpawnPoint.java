import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse SpawnPoint.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class SpawnPoint extends Actor
{
    
    private int timer=0;
    private int counter=0;
    public double shootSpeed=0.2;
    public double shootInitialSpeed=75;
    public double angleSpeed=0.05;
    public double colorChangeSpeed=0.005;
    
    
    private double dt;
    public Vec2 gravity=new Vec2(0,1000);
    
    public SpawnPoint(Vec2 acc,double dt){
        gravity=acc;
        this.dt=dt;
    }
    public SpawnPoint(){
        gravity=new Vec2(0,1000);
        this.dt=0.01;
    }
    public int randomInt(int a,int b){
        return (int) ((b-a+1) * Math.random() + a);
    }
    public int randomInt(int b){
        return (int) ((b+1) * Math.random() );
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
    public static Color getRainbow1(int counter)
    {
        return new Color(counter,(counter+75)%256,(counter+150)%256);
    }
    public void act() 
    {
        timer+=1;
        if(timer%(int)(1.0/shootSpeed)==0){
            counter=(counter+10)%256;
            
            //Color c=getRainbow1(counter);
            Color c=getRainbow(timer*colorChangeSpeed);
            double radius=randomInt(5,10);
            VerletObject ball=new VerletObject(radius,c,new Vec2(this.getX(),this.getY()));
            getWorld().addObject(ball,this.getX(),this.getY());
            
            
            
            double xDelta=Math.sin(timer*angleSpeed);
            double yDelta=Math.cos(timer*angleSpeed);
            ball.setVelocity(new Vec2(shootInitialSpeed*xDelta,shootInitialSpeed*yDelta),dt);
            
        }
    }    
}