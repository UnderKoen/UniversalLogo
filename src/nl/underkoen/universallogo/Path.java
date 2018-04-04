package nl.underkoen.universallogo;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 03/04/2018.
 */
public class Path {
    private Location begin;
    private List<Location> points;
    private Polygon polygon;

    /**
     * @param begin the begin location of the path
     */
    public Path(Location begin) {
        this.begin = begin;
        points = new ArrayList<>();
        points.add(begin.clone());
        polygon = new Polygon();
        polygon.addPoint((int)Math.round(begin.getX()), (int)Math.round(begin.getY()));
    }


    protected void addPoint(Location location) {
        points.add(location.clone());
        polygon.addPoint((int)Math.round(location.getX()), (int)Math.round(location.getY()));
    }

    /**
     * @return the begin location
     */
    public Location getBegin() {
        return begin;
    }

    /**
     * @return all the points on the path
     */
    public List<Location> getPoints() {
        return new ArrayList<>(points);
    }

    /**
     * @param location
     * @return <code>true</code> when <code>location</code> is inside the path
     */
    public boolean inside(Location location) {
        return inside(location.getX(), location.getY());
    }

    /**
     * @param x
     * @param y
     * @return <code>true</code> when the location <code>(x,y)</code> is inside the path
     */
    public boolean inside(double x, double y) {
        return polygon.contains(x,y);
    }

    /**
     * @return a string in current format "Path: ({@link Location#toString()}, {@link Location#toString()}, ...)"
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Path: (");
        for (int i = 0; i < points.size(); i++) {
            sb.append(points.get(i));
            if (i < points.size()-1) sb.append(", ");
        }
        sb.append(")");
        return sb.toString();
    }
}
