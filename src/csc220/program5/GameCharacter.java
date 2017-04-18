/* GameCharacter.java
 *
 * Any character in the game must extend this abstract class, which handles
 * all the animation.  The implementor of the character needs to provide a
 * draw method (see csc220.drawing.Drawable), and a list of points for the
 * character to visit. Many other customizations are possible.
 * 
 * The player will have the opportunity to add points to the list when the game 
 * is paused; this  occurs via the add method below.
 *
 * When characters are moving, the player will have the opportunity to remove a
 * point from the list; this occurs via the overridden clickAction method below.
 *
 * K. Weber
 * April 13-14, 2017.
 *
 */

package csc220.program5;

import csc220.drawing.AnimatedDrawable;
import csc220.list.AddIterator;
import csc220.list.List;
import java.awt.Point;

public abstract class GameCharacter extends AnimatedDrawable {

    //  These variables are available to subclasses.
    protected List<Point> animationPath;
    protected AddIterator<Point> animationIterator;
    protected boolean isVisible;

    public GameCharacter(int over, int down, int width, int height, 
                           int moveInterval, List<Point> animationPath) {
        super(over, down, width, height, moveInterval);
        this.animationPath = animationPath;
        this.animationIterator = null;
        isVisible = true;
    }
    
    @Override
     protected void move() {
        if (animationIterator == null || !animationIterator.hasNext()) {
            animationIterator = animationPath.addIterator();           
        }
        Point p = animationIterator.next();
        setOver(p.x);
        setDown(p.y);
    }
    
    public void add(Point p){
        if (animationIterator == null) {
            animationIterator = animationPath.addIterator();
        }
        animationIterator.addBeforeNext(p);
    }
    
    @Override
    public void clickAction() {
        if (animationIterator == null) {
            animationIterator = animationPath.addIterator();
        }
        animationIterator.remove();
        isVisible = animationPath.iterator().hasNext();
    }
    
    public boolean isVisible(){return isVisible;}
            
}
