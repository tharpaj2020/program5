/* Camera.java
 *
 * This class provides a "movie camera" that will repaint the stage (a JPanel)
 * being pointed at when the camera is told to roll once every SHUTTER_INTERVAL 
 * milliseconds. See the roll method below.
 *
 * The cut method will stop the camera.
 *
 * K. Weber
 * weberk@mountunion.edu
 * April 13-14, 2017.
 */

package csc220.drawing;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.JPanel;

public class Camera  {
    
    public int SHUTTER_INTERVAL = 1000/24;        //  Gives 24 frames/sec 
    
    private ScheduledFuture rollFuture = null;
    
    public void roll(JPanel stage){
        if (rollFuture != null) {
            rollFuture.cancel(true);
        }
        class Shutter implements Runnable {
            @Override
            public void run() {
                java.awt.EventQueue.invokeLater(() -> stage.repaint());
            }     
        }
        rollFuture = AnimatedDrawable.TIMER.scheduleAtFixedRate(new Shutter(), 
                         0, SHUTTER_INTERVAL, TimeUnit.MILLISECONDS);
    }
    
    public void cut() {
        if (rollFuture != null) {
            rollFuture.cancel(true);
            rollFuture = null;
        }
    }
    
}
