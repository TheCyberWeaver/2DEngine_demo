import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Red.
 * 
 * @author TheCyberWeaver 
 * @version 2024.1.13
 */
public class Constraint extends Actor
{
    protected double radius;
    protected Color color=new Color(200,200,200);
    protected double response_coef = 0.75;
    public Constraint(){
        
    }
    public Constraint(double r,Color c){
        color=c;
        radius=r;
        GreenfootImage im = new GreenfootImage((int)radius*2,(int)radius*2);
        im.setColor(color);//Add Background color
        im.fillOval(0,0,(int)radius*2,(int)radius*2);
        setImage(im);
    }
    
    public void applyConstraint(VerletObject object){
        
    }
    
}