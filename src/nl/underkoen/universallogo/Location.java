package nl.underkoen.universallogo;

/**
 * Created by Under_Koen on 01/04/2018.
 */
public class Location {
    private double x;
    private double y;

    /**
     * @param x the x-pos for this location
     * @param y the y-pos for this location
     */
    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return the x-pos for this location
     */
    public double getX() {
        return x;
    }

    /**
     * @return the y-pos for this location
     */
    public double getY() {
        return y;
    }

    /**
     * @param x the amount to add to the x-pos
     */
    public void addX(double x) {
        this.x = this.x + x;
    }

    /**
     * @param y the amount to add to the y-pos
     */
    public void addY(double y) {
        this.y = this.y + y;
    }

    /**
     * @param x the amount to set the x-pos
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @param y the amount to set the y-pos
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * @return a new instace of location with the same x and y as this one.
     */
    @Override
    public Location clone() {
        return new Location(x, y);
    }

    /**
     * @return the location in this format "Location: (x, y)"
     */
    @Override
    public String toString() {
        return "Location: (" + x + ", " + y + ")";
    }
}
