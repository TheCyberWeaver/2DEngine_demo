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
        
    }    
}