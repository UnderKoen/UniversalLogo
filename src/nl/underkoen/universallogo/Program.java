package nl.underkoen.universallogo;

/**
 * Created by Under_Koen on 31/03/2018.
 */
public interface Program {
    /**
     * @return the canvas to use to draw
     */
    Canvas getCanvas();

    /**
     * Set the canvas to paint in
     */
    void setCanvas(Canvas canvas);

    /**
     * @return the name of the program
     */
    String getName();

    /**
     * Runs after the canvas is set, you can't draw yet
     */
    void init();

    /**
     * Runs when loading of stuff is done
     */
    void run();
}
