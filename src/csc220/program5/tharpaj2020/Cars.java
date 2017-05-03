/* Aaron Tharp Cars class
 * 5/3/2017
 * Description: The purpose of this subclass is to create animated cars that are displayed in the stagePanel.
 * Acknowledgements: My 120 pa 3
 */
package csc220.program5.tharpaj2020;
import java.awt.*;
import csc220.program5.tharpaj2020.List;
import csc220.drawing.Drawable;
import java.awt.Color;
import static java.awt.Color.BLACK;
import java.awt.Graphics;
import java.awt.Point;

/**
 *
 * @author tharpaj2020
 */
public class Cars extends csc220.program5.GameCharacter{
    private Integer over;
    private Integer down;
    private Color   thingColor;


    public Cars(int over, int down, int width, int height, int moveInterval, Color color) {
        super(over, down, width, height, moveInterval, new List<Point>());
        this.over = over;
        this.down = down;
        thingColor = color;
        this.animationPath.add(new Point(over, down));
        this.animationPath.add(new Point(over, down + 100));
        this.animationPath.add(new Point(over + 100, down + 100));
        this.animationPath.add(new Point(over + 100, down));
    }

    @Override
    public void draw(Graphics g) {
        // body of the car
        g.setColor(thingColor);
        g.fillRect(getOver(),      getDown(), 150, 40);
        g.fillRect(getOver() + 40, getDown() - 30, 75, 50);
        
        // the tires
        g.setColor(Color.DARK_GRAY);
        g.fillOval(getOver() + 25, getDown() + 30, 30, 30);
        g.fillOval(getOver() + 95, getDown() + 30, 30, 30);
        
        // the windows
        g.drawRect(getOver() + 45, getDown() - 25, 25, 25);
        g.drawRect(getOver() + 80, getDown() - 25, 25, 25);
        
        // the anttena
        g.drawLine(getOver() + 145, getDown(), getOver() + 145, getDown() - 30);
    }
    
   
    
}
