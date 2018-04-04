package nl.underkoen.universallogo.underlogo;

import nl.underkoen.universallogo.AbstractCanvas;
import nl.underkoen.universallogo.Location;
import nl.underkoen.universallogo.Point;
import nl.underkoen.universallogo.Size;

import java.awt.*;
import java.util.List;

/**
 * Created by Under_Koen on 04/04/2018.
 */
public class UnderLogoCanvas extends AbstractCanvas {
    public Color fillInColor;
    public UnderLogo underLogo;

    public UnderLogoCanvas(UnderLogo underLogo) {
        this.underLogo = underLogo;
    }

    @Override
    public boolean isInitializing() {
        return underLogo.init;
    }

    @Override
    public void goToRelative(double x, double y) {
        if (!getPenDown()) return;
        Location from = getRelativeLocation(getLocation(), getRelativePoint(), nl.underkoen.universallogo.Point.TOP_LEFT);
        Location to = new Location(from.getX() + x, from.getY() + y);
        underLogo.dc.drawLine(from, to);
    }

    @Override
    public void activePen(int r, int g, int b) {
        underLogo.dc.setColor(new Color(r, g, b));
    }

    @Override
    public void disablePen() {

    }

    @Override
    public void changeBackgroundColor(int r, int g, int b) {
        underLogo.back = new Color(r, g, b);
    }

    @Override
    public void startCheckingPath(int r, int g, int b) {
        fillInColor = new Color(r, g, b);
    }

    @Override
    public void stopCheckingPath() {
        fillInColor = null;
    }

    @Override
    public void fillIn() {
        List<Location> points = getCurrentPath().getPoints();
        int[] x = new int[points.size()];
        int[] y = new int[points.size()];
        for (int i = 0; i < points.size(); i++) {
            Location loc = getRelativeLocation(points.get(i), getRelativePoint(), Point.TOP_LEFT);
            x[i] = (int) Math.round(loc.getX());
            y[i] = -(int) Math.round(loc.getY());
        }
        underLogo.dc.fillIn(new Polygon(x, y, points.size()), fillInColor, getPenDown());
    }

    @Override
    public void type(String text) {
        type(text, null);
    }

    @Override
    public void type(String text, Font font) {
        underLogo.dc.print(text, font, getRelativeLocation(getLocation(), getRelativePoint(), Point.TOP_LEFT));
    }

    @Override
    public Size getSize() {
        return new Size(500, 500);
    }

    @Override
    public void redraw() {
        underLogo.redraw();
    }

    @Override
    public boolean canDraw() {
        return true;
    }
}