/**
 * Ergänzen Sie hier eine Beschreibung für die Klasse Vec2.
 * 
 * @author TheCyberWeaver 
 * @version 2024.1.13
 */
public class Vec2  
{
    public double x;
    public double y;

    /**
     * Konstruktor für Objekte der Klasse Vec2
     */
    public Vec2(double x,double y)
    {
        this.x=x;
        this.y=y;
    }
    public Vec2(Vec2 b)
    {
        this.x=b.x;
        this.y=b.y;
    }
    public Vec2()
    {
        this.x=0;
        this.y=0;
    }
    public Vec2 subtract(Vec2 b){
        return new Vec2(this.x-b.x,this.y-b.y);
    }
    public Vec2 add(Vec2 b){
        return new Vec2(this.x+b.x,this.y+b.y);
    }
    public Vec2 time(double b){
        return new Vec2(this.x*b,this.y*b);
    }
    public Vec2 divide(double b){
        return new Vec2(this.x/b,this.y/b);
    }
    public String toString(){
        return "Vec2("+x+","+y+")";
    }
    //The Euclidiean distance to the origin
    public double getDist(){
        double tmp=x*x+y*y;
        return Math.sqrt(tmp);
    }
    public double getDistTo(Vec2 b){
        double tmp=(b.x-this.x)*(b.x-this.x)+(b.y-this.y)*(b.y-this.y);
        return Math.sqrt(tmp);
    }
}