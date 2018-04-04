package nl.underkoen.universallogo.programs;

import nl.underkoen.universallogo.*;
import nl.underkoen.universallogo.Point;

import java.awt.*;

/**
 * Created by Under_Koen on 03/04/2018.
 */
public class AnimatedTest extends AnimatedProgram implements MouseListener {
    public int deg;

    @Override
    public String getName() {
        return "Animation";
    }

    @Override
    public void init() {
        canvas.setPixelRounded(true);
        canvas.setRelativePoint(Point.TOP_LEFT);
    }

    @Override
    public void run() {
        drawAnimation();
    }

    @Override
    public void drawAnimation() {
        drawButtons();
        deg++;
        if (deg == 360) deg = 0;
        canvas.rotateRelative(deg, true);
        canvas.forward(100);
    }

    public Path start;
    public boolean hoverStart = false;

    public Path stop;
    public boolean hoverStop = false;

    public void drawButtons() {
        Location loc = canvas.getLocation().clone();

        //START
        canvas.setPenDown(false);
        canvas.goToLocation(10, -10);
        canvas.setPenDown(true);
        canvas.setColor((hoverStart) ? Color.ORANGE : Color.GREEN);
        canvas.checkPath(true);
        canvas.setColor(Color.BLACK);
        canvas.rotate(90);
        canvas.forward(100);
        canvas.rotateRelative(90, true);
        canvas.forward(25);
        canvas.rotateRelative(90, true);
        canvas.forward(100);
        canvas.rotateRelative(90, true);
        canvas.forward(25);
        start = canvas.getCurrentPath();
        canvas.fillInCurrentPath();
        canvas.rotate(0);
        canvas.setPenDown(false);
        canvas.goToRelativeLocation(20, -22);
        canvas.setPenDown(true);
        canvas.write("Start", new Font(Font.SANS_SERIF, Font.BOLD, 25));

        //STOP
        canvas.setPenDown(false);
        canvas.goToLocation(120, -10);
        canvas.setPenDown(true);
        canvas.setColor((hoverStop) ? Color.ORANGE : Color.RED);
        canvas.checkPath(true);
        canvas.setColor(Color.BLACK);
        canvas.rotate(90);
        canvas.forward(100);
        canvas.rotateRelative(90, true);
        canvas.forward(25);
        canvas.rotateRelative(90, true);
        canvas.forward(100);
        canvas.rotateRelative(90, true);
        canvas.forward(25);
        stop = canvas.getCurrentPath();
        canvas.fillInCurrentPath();
        canvas.rotate(0);
        canvas.setPenDown(false);
        canvas.goToRelativeLocation(22, -22);
        canvas.setPenDown(true);
        canvas.write("Stop", new Font(Font.SANS_SERIF, Font.BOLD, 25));

        canvas.setPenDown(false);
        canvas.goToLocation(loc.getX(), loc.getY());
        canvas.setPenDown(true);
    }

    @Override
    public void mouseIn(Location mouse, Location mousePress) {
    }

    @Override
    public void mouseOut(Location mouse, Location mousePress) {
        if (start.inside(mouse)) {
            startAnimation(0);
        }

        if (stop.inside(mouse)) {
            stopAnimation();
        }
    }

    @Override
    public void mouseDrag(Location mouse, Location mousePress, Location deltaMouseDrag) {
    }

    @Override
    public void mouseMove(Location mouse) {
        hoverStart = start.inside(mouse);
        hoverStop = stop.inside(mouse);
        if (!isAnimationActive()) canvas.redraw();
    }
}
