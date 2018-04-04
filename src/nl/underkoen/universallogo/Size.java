package nl.underkoen.universallogo;

/**
 * Created by Under_Koen on 02/04/2018.
 */
public class Size {
    private int width;
    private int height;

    /**
     * @return the width of this
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height of this
     */
    public int getHeight() {
        return height;
    }

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }
}
