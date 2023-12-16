import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse constraint.
 * 
 * @author (Ihr Name) 
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Constraint extends Actor
{
    public double radius;
    public Constraint(){
    }
    public Constraint(double r){
        radius=r;
        GreenfootImage im = new GreenfootImage((int)radius*2,(int)radius*2);
        im.setColor(new Color(12,96,56));//Add Background color
        im.fillOval(0,0,(int)radius*2,(int)radius*2);
        im.setTransparency(200);
        setImage(im);
    }

    public void act() {
    }  
}