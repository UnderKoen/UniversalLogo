package nl.underkoen.universallogo;

/**
 * Created by Under_Koen on 31/03/2018.
 */
public abstract class Program {
    /**
     * The canvas to use when to draw stuff
     */
    protected Canvas canvas;

    /**
     * Set the canvas to paint in
     */
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    /**
     * @return the name of the program
     */
    public abstract String getName();

    /**
     * Runs after the canvas is set, you can't draw yet
     */
    public abstract void init();

    /**
     * Runs when loading of stuff is done
     */
    public abstract void run();
}
