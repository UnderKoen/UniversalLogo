package nl.underkoen.universallogo;

import java.awt.*;

/**
 * Created by Under_Koen on 31/03/2018.
 */
public interface Canvas {
    /**
     * @return the size of the canvas
     */
    Size getSize();

    /**
     * if <code>true</code> rounds the location to no decimals
     * Can only be set in the init.
     * @param rounded enables or disables rounding of pixels
     */
    void setPixelRounded(boolean rounded);

    /**
     * @return of the pixel are being rounded
     */
    boolean isPixelRound();

    /**
     * Set's the point where locations are relative to.
     * Can only be set in the init.
     * @param point the point where
     */
    void setRelativePoint(Point point);

    /**
     * @return the point where locations are relative to.
     */
    Point getRelativePoint();

    /**
     * @param original the location to change
     * @param relative where this location is relative
     * @return the new location relative to {@link #getRelativePoint()}
     */
    Location getRelativeLocation(Location original, Point relative);

    /**
     * @param original the location to change
     * @param from where this location is relative
     * @param to where the result is relative to
     * @return the new location relative <code>to</code>
     */
    Location getRelativeLocation(Location original, Point from, Point to);

    /**
     * Resets all variables and other things
     */
    void reset();

    /**
     * Redraws the canvas
     */
    void redraw();

    /**
     * @return The location of the line
     */
    Location getLocation();

    /**
     * Set a line to (x, y)
     * @param x X coordinate to draw to
     * @param y Y coordinate to draw to
     */
    void goToLocation(double x, double y);

    /**
     * Set a line to (~x, ~y)
     * @param x relative X coordinate to draw to
     * @param y relative Y coordinate to draw to
     */
    void goToRelativeLocation(double x, double y);

    /**
     * Draws a line
     * @param d lenght of the line
     */
    void forward(double d);

    /**
     * rotates to the given degree
     * @param deg the degree to rotate to 0 is up
     */
    void rotate(double deg);

    /**
     * @return current rotation of the line
     */
    double getRotation();

    /**
     * rotates relative to the rotation the line has
     * @param deg the degree to rotate
     * @param right when true to rotate to the right else rotate to the left
     */
    void rotateRelative(double deg, boolean right);

    /**
     * @param active set the pen down or up
     */
    void setPenDown(boolean active);

    /**
     * @return if the pen is down
     */
    boolean getPenDown();

    /**
     * set's the color of the current line
     * @param color the color to set the line
     */
    void setColor(Color color);

    /**
     * @return the current line color
     */
    Color getColor();

    /**
     * set's the color of the background
     * run {@link #redraw()} before it can change
     * @param color the color to set the background
     */
    void setBackgroundColor(Color color);

    /**
     * @return the current background color
     */
    Color getBackgroundColor();

    /**
     * when enabling set's the color for {@link #fillInCurrentPath()} to {@link #getColor()}
     * @param check enables/disables the checking of the path
     */
    void checkPath(boolean check);

    /**
     * @return if checking the path
     */
    boolean isCheckingPath();

    /**
     * Resets the path and set's the color for {@link #fillInCurrentPath()} to {@link #getColor()}
     */
    void resetPath();

    /**
     * @return the path that you lay from the point were you start checking this
     */
    Path getCurrentPath();

    /**
     * Fills in current path and resets the path and set's the color for {@link #fillInCurrentPath()} to {@link #getColor()}
     */
    void fillInCurrentPath();

    /**
     * Writes text on the screen
     * @param text which text to write
     */
    void write(String text);

    /**
     * Writes text on the screen in specied font
     * @param text which text to write
     * @param font the font for the text
     */
    void write(String text, Font font);

    /**
     * @return if you can currently draw
     */
    boolean canDraw();
}