package nl.underkoen.universallogo.programs;

import nl.underkoen.universallogo.Location;
import nl.underkoen.universallogo.MouseListener;
import nl.underkoen.universallogo.Program;

/**
 * Created by Under_Koen on 03/04/2018.
 */
public class Test extends Program implements MouseListener {
    private Location mouse;

    @Override
    public String getName() {
        return "Test";
    }

    @Override
    public void init() {
        canvas.setPixelRounded(true);
    }

    @Override
    public void run() {
        canvas.checkPath(true);
        canvas.forward(100);
        canvas.rotateRelative(135,true);
        canvas.forward(100);
        canvas.rotateRelative(90,false);
        canvas.forward(100);
        canvas.rotate(180);
        canvas.forward(100);
        canvas.goToLocation(0,0);
        if (mouse == null) return;
        if (canvas.getCurrentPath().inside(mouse)) canvas.fillInCurrentPath();
    }

    @Override
    public void mouseIn(Location mouse, Location mousePress) {
    }

    @Override
    public void mouseOut(Location mouse, Location mousePress) {
    }

    @Override
    public void mouseDrag(Location mouse, Location mousePress, Location deltaMouseDrag) {
    }

    @Override
    public void mouseMove(Location mouse) {
        this.mouse = mouse;
        canvas.redraw();
    }
}
