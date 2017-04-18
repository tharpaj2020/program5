/*
 * The class csc220.drawing.Drawable is an abstract base class that is meant to be extended
 * to subclasses.  The base class provides a contains method that tells when a point is 
 * contained in the Drawable object's area.
 * There is also the abstract method draw, which must be implemented by subclasses.
 * It should provide a means for the Drawable object to be drawn on the Graphics object g.
 * The clickAction method is meant to provide a way to tell the Drawable object to 
 * perform an action when it has been clicked.
 *
 * Getter and setter methods for over, down, width and height are also provided.
 *
 * Ken Weber weberk@mountunion.edu
 * Feb 18, 2016
 * modified on Feb 20, 2017 -- changed package name.
 */
package csc220.drawing;

import java.awt.*;

/**
 *
 * @author weberk
 */
public abstract class Drawable // An abstract class has "missing" pieces.
{
    //  This rectangle keeps track of the basic bounds of the Drawable.
    private Rectangle bounds;
    
    public Drawable(int over, int down, int width, int height) {
        bounds = new Rectangle(over, down, width, height);
    }
    
    public int getOver(){return bounds.x;}
    public int getDown(){return bounds.y;}
    public int getWidth(){return bounds.width;}
    public int getHeight(){return bounds.height;}
    
    public void setOver(int over){bounds.x = over;}
    public void setDown(int down){bounds.y = down;}
    public void setWidth(int width){bounds.width = width;}
    public void setHeight(int height){bounds.height = height;}
        
    //  Returns true if p falls within the boundary of the object and false otherwise.
    //  You can override this in subclasses if you want to.
    public boolean contains(Point p) {return bounds.contains(p);}
    
    public abstract void draw(Graphics g);  //  You must implement this method in subclasses.
    
    public void clickAction() {}  //  Does nothing unless you override it in a subclass.
}