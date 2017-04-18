/*  AnimatedDrawable interface.
 *
 *  A class that implements the AnimatedDrawable interface can be drawn 
 *  in a JPanel and animates itself. See below for specific methods.
 *
 *  K. Weber  weberk@mountunion.edu
 *  April  7, 2015.
 *  April 10, 2016: Changed drawMe method to draw.
 *                  Added addPoint, removePoint, and pathIsEmpty methods.
 *                  Removed setPath and getPath methods.
 *                  Changed stopAnimation method to return nothing.
 *                  Made into an abstract subclass of Drawable.
 *                  Changed name from Animatable to AnimatedDrawable.
 *  April  8, 2017: AnimatedDrawable in a new package
 *                  Uses csc220.list.List instead of old SimpleLinkedList.
 *                  Constructor now takes over and down parameters.
 *  April 13, 2017: Replaced plain Thread objects with ScheduledThreadPoolExecutor
 *                  Made move method abstract
 *                  Removed references to path
 * 
 */
package csc220.drawing;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public abstract class AnimatedDrawable extends Drawable
{
    //  TIMER is a class-wide variable and available to other classes in package.
    //  It is  pool of threads that can execute tasks at fixed intervals.
    //  A task is specified as an object that implements the Runnable interface.
    static final ScheduledThreadPoolExecutor TIMER = new ScheduledThreadPoolExecutor(4);
    
    private ScheduledFuture moverFuture;
    private int moveInterval;
    
    public AnimatedDrawable(int over, int down, int width, int height, int moveInterval) {
        super(over, down, width, height);
        this.moveInterval = moveInterval;
        this.moverFuture = null;
    }
    
    protected abstract void move();  //  Has to be defined in subclass.
        
    public boolean isAnimated(){return (moverFuture != null);}
    
    public void startAnimation() {
        class Mover implements Runnable {
            @Override
            public void run() {
                move();
            }         
        }
        if (moverFuture == null) {
            moverFuture = TIMER.scheduleAtFixedRate(new Mover(), 0, moveInterval, TimeUnit.MILLISECONDS);
        }
    }
        
    public void stopAnimation() {
        if (isAnimated()) {
            moverFuture.cancel(true);
            moverFuture = null;
        }
    }
          
}