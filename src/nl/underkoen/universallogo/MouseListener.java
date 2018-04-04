package nl.underkoen.universallogo;

/**
 * Created by Under_Koen on 02/04/2018.
 */
public interface MouseListener {
    /**
     * Is run when the mouse is pressed
     * @param mouse where the mouse currently is
     * @param mousePress where the mouse press was
     */
    void mouseIn(Location mouse, Location mousePress);

    /**
     * Is run when the mouse is pressed over
     * @param mouse where the mouse currently is
     * @param mousePress where the mouse press was
     */
    void mouseOut(Location mouse, Location mousePress);

    /**
     * Is run when the mouse is dragged
     * @param mouse where the mouse currently is
     * @param mousePress where the mouse press was
     * @param deltaMouseDrag delta for the location of dragged
     */
    void mouseDrag(Location mouse, Location mousePress, Location deltaMouseDrag);

    /**
     * Is run when the mouse has moved
     * @param mouse where the mouse currently is
     */
    void mouseMove(Location mouse);
}
