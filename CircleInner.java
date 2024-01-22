import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot und MouseInfo)

/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Red.
 * 
 * @author TheCyberWeaver 
 * @version 2024.1.13
 */
public class CircleInner extends Constraint
{
        
    public CircleInner(double r,Color c){
        super(r,c);
        response_coef = 0.75;
    }
    public CircleInner(){
        super(100,new Color(0,0,0));
        response_coef = 0.75;
    }
    public void applyConstraint(VerletObject object){
        
        Vec2 constraintCenter=new Vec2(getX(),getY());
        Vec2 to_obj=constraintCenter.subtract(object.position_current);
        double dist=to_obj.getDist();
        
        if(dist<(radius+object.radius)){
            Vec2 n=to_obj.divide(dist);
            double delta=0.5*response_coef*(dist-(radius+object.radius));
            object.position_current=object.position_current.add(n.time(1*delta));
        }
    }
}