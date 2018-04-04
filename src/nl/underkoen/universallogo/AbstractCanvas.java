package nl.underkoen.universallogo;

import java.awt.*;

/**
 * Created by Under_Koen on 04/04/2018.
 */
public abstract class AbstractCanvas implements Canvas {
    private Point relativePoint = Point.CENTER;
    private double rotation = 0;
    private boolean penActive = true;
    private Location centerLocation = new Location(0, 0);
    private Location location = new Location(0, 0);
    private Color currentColor = Color.BLACK;
    private Color currentBackGroundColor = Color.WHITE;
    private boolean checkingPath = false;
    private Path path;
    private boolean roundingPixels = false;

    /**
     * @return if the program is in init
     */
    public abstract boolean isInitializing();

    @Override
    public void setPixelRounded(boolean rounded) {
        if (!isInitializing()) return;
        roundingPixels = rounded;
    }

    @Override
    public boolean isPixelRound() {
        return roundingPixels;
    }

    @Override
    public void setRelativePoint(Point point) {
        if (!isInitializing()) return;
        Location loc = getRelativeCoords(point);
        location.setX(centerLocation.getX() + loc.getX());
        location.setY(centerLocation.getY() - loc.getY());
        relativePoint = point;
    }

    @Override
    public Point getRelativePoint() {
        return relativePoint;
    }

    @Override
    public Location getRelativeLocation(Location original, Point relative) {
        return getRelativeLocation(original, relative, relativePoint);
    }

    @Override
    public Location getRelativeLocation(Location original, Point from, Point to) {
        Location loc = getRelativeCoords(from);
        double centerX = original.getX() - loc.getX();
        double centerY = original.getY() + loc.getY();
        Location loc2 = getRelativeCoords(to);
        return new Location(centerX + loc2.getX(), centerY - loc2.getY());
    }

    private Location getRelativeCoords(Point point) {
        double x = 0;
        double y = 0;

        double rX = getSize().getWidth() / 2;
        double rY = getSize().getHeight() / 2;
        switch (point) {
            case TOP_LEFT:
                x = rX;
                y = rY;
                break;
            case TOP_CENTER:
                y = rY;
                break;
            case TOP_RIGHT:
                x = -rX;
                y = rY;
                break;
            case LEFT_CENTER:
                x = rX;
                break;
            case CENTER:
                break;
            case RIGHT_CENTER:
                x = -rX;
                break;
            case BOTTOM_LEFT:
                x = rX;
                y = -rY;
                break;
            case BOTTOM_CENTER:
                y = -rY;
                break;
            case BOTTOM_RIGHT:
                x = -rX;
                y = -rY;
                break;
        }

        return new Location(x, y);
    }

    @Override
    public void reset() {
        rotation = 0;
        penActive = true;
        relativePoint = Point.CENTER;
        centerLocation = new Location(0, 0);
        location = new Location(0, 0);
        currentColor = Color.BLACK;
        activePen(0,0,0);
        currentBackGroundColor = Color.WHITE;
        changeBackgroundColor(255,255,255);
        if (checkingPath) {
            stopCheckingPath();
        }
        checkingPath = false;
    }

    @Override
    public Location getLocation() {
        return location.clone();
    }

    @Override
    public void goToLocation(double x, double y) {
        if (!canDraw()) return;
        goToRelativeLocation(x - location.getX(), y - location.getY());
    }

    @Override
    public void goToRelativeLocation(double x, double y) {
        if (!canDraw()) return;
        if (roundingPixels) {
            x = Math.round(x);
            y = Math.round(y);
        }
        goToRelative(x,y);
        location.addX(x);
        centerLocation.addX(x);
        location.addY(y);
        centerLocation.addY(y);
        if (checkingPath) path.addPoint(location);
    }

    public abstract void goToRelative(double x, double y);

    @Override
    public void forward(double d) {
        if (!canDraw()) return;
        if (rotation == 0 || rotation == 180) {
            goToRelativeLocation(0, (rotation == 0) ? d : -d);
        } else if (rotation == 90 || rotation == 270) {
            goToRelativeLocation((rotation == 90) ? d : -d, 0);
        } else {
            double A = Math.toRadians(90 - rotation);
            double B = Math.toRadians(rotation);
            double C = Math.toRadians(90);
            double x = (Math.sin(B) * d) / (Math.sin(C));
            double y = (Math.sin(A) * d) / (Math.sin(C));
            goToRelativeLocation(x, y);
        }
    }

    @Override
    public void rotate(double deg) {
        if (!canDraw()) return;
        rotateRelative(rotation, false);
        rotateRelative(deg, true);
    }

    @Override
    public double getRotation() {
        return rotation;
    }

    @Override
    public void rotateRelative(double deg, boolean right) {
        if (!canDraw()) return;
        double tempRotation = rotation + (right ? deg : -deg);
        if (tempRotation >= 360 || tempRotation < 0) {
            tempRotation = tempRotation - ((int) Math.floor(tempRotation / 360.0) * 360);
        }
        rotation = tempRotation;
    }

    @Override
    public void setPenDown(boolean active) {
        if (!canDraw()) return;
        this.penActive = active;
        if (active) {
            activePen(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
        } else {
            disablePen();
        }
    }

    public abstract void activePen(int r, int g, int b);
    public abstract void disablePen();

    @Override
    public boolean getPenDown() {
        return penActive;
    }

    @Override
    public void setColor(Color color) {
        if (!canDraw()) return;
        currentColor = color;
        if (!penActive) return;
        setPenDown(true);
    }

    @Override
    public Color getColor() {
        return currentColor;
    }

    @Override
    public void setBackgroundColor(Color color) {
        currentBackGroundColor = color;
        changeBackgroundColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public abstract void changeBackgroundColor(int r, int g, int b);

    @Override
    public Color getBackgroundColor() {
        return currentBackGroundColor;
    }

    @Override
    public void checkPath(boolean check) {
        if (!canDraw()) return;
        if (check) {
            startCheckingPath(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
            path = new Path(location);
        } else {
            stopCheckingPath();
            path = null;
        }
        checkingPath = check;
    }

    public abstract void startCheckingPath(int r, int g, int b);
    public abstract void stopCheckingPath();

    @Override
    public boolean isCheckingPath() {
        return checkingPath;
    }

    @Override
    public void resetPath() {
        if (!canDraw()) return;
        if (checkingPath) {
            stopCheckingPath();
            startCheckingPath(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
            path = new Path(location);
        }
    }

    @Override
    public Path getCurrentPath() {
        return path;
    }

    @Override
    public void fillInCurrentPath() {
        if (!canDraw()) return;
        fillIn();
        stopCheckingPath();
        startCheckingPath(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue());
        path = new Path(location);
    }

    public abstract void fillIn();

    @Override
    public void write(String text) {
        if (!canDraw()) return;
        type(text);
    }

    @Override
    public void write(String text, Font font) {
        if (!canDraw()) return;
        type(text, font);
    }

    public abstract void type(String text);
    public abstract void type(String text, Font font);
}