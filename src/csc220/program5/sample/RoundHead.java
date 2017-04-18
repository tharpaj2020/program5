package csc220.program5.sample;
/*
    CS 121 Lab 7
    RoundHead class

    October 13, 2005

    Class definition for a RoundHead character.
    The character can smile or frown, depending
    on the value of isHappy, and can be asked
    to move horizontally. If it moves too close
    to the left or right edge of the window,
    it will "turn around" and start moving in
    the opposite direction.

    Acknowledgements:
    The original version of RoundHead class and this
    lab were created by Blase Cindric.

    Modified for NetBeans October 4, 2005, by John Kirchmeyer
    Modified 9-Mar-2006 by k. weber
    +   Added moveTo method.
    +   Added getLocation method.
    Modified 15-Mar-2006 by k. weber
    +   Added containsPoint method.
    Modified 16-Mar-2015 by k. weber.
    +   Now extends graphical.Graphical abstract class.  Includes following changes.
    + Removed containsPoint method, since contains is implemented in Graphical.
    + changed upperLeftX, upperLeftY to over, down.
    + changed size to width.
    Modified 22-Mar-2016 by k. weber
    +  Replaced use of Graphical with Drawable
    Modified 23-Mar-2017
    +  now uses the new package name csc220.drawing
    Modified 14-Apr-2017 kw
    + extends GameCharacter
*/
import java.awt.*;

public class RoundHead extends csc220.program5.GameCharacter { // RoundHead

    private boolean isHappy;
    private Color hatColor;
    private int horizontalStepSize;	// how far to move
    private int windowWidth;

    public RoundHead(int x, int y, int diameter, int moveInterval) {
        super(x, y, diameter, diameter, 1000, new List<Point>());
        animationPath.add(new Point(x, y));
        animationPath.add(new Point(x + 100, y));
        animationPath.add(new Point(x, y + 100));
        animationPath.add(new Point(x + 100, y + 100));
        isHappy = true;
        hatColor = Color.GREEN;
     } // constructor

    public void draw(Graphics g) {
        // Draw the RoundHead's face.
        int over = getOver();
        int down = getDown();
        int width = getWidth();
        g.setColor(Color.yellow);
        g.fillOval(over, down, width, width);

        // Put a black outline around face.
        g.setColor(Color.black);
        g.drawOval(over, down, width, width);

        // Draw the RoundHead's hat.
        g.setColor(hatColor);
        g.fillRect(over, down, width, width/10);
        g.fillRect(over+width/4, down-width/3, width/2, width/3);

        // Draw the RoundHead's facial features in black, based on mood.
        g.setColor(Color.black);

        if (isHappy)  {
            g.fillOval(over+width/5, down+3*width/10, width/5, width/5);
            g.fillOval(over+3*width/5, down+3*width/10, width/5, width/5);
            g.drawArc(over+3*width/10, down+width/2, 2*width/5, 3*width/10, 190, 160);
        } else {
            // sad
            g.fillRect(over+width/5, down+3*width/10, width/5, width/10);
            g.fillRect(over+3*width/5, down+3*width/10, width/5, width/10);
            g.drawArc(over+3*width/10, down+7*width/10, 2*width/5, 3*width/10, 10, 160);
        }
    } // drawMe

     
    public void changeMood() {
        isHappy = !isHappy;
    } // changeMood

    
} // RoundHead