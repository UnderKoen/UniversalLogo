package nl.underkoen.universallogo.programs;

import nl.underkoen.universallogo.*;
import nl.underkoen.universallogo.Canvas;

import java.awt.*;

/**
 * Created by Under_Koen on 31/03/2018.
 */
public class PerfectCircle implements Program, VariableListener {
    Variable[] variables = {new Variable("Width", 100, 249, 1)};
    public Canvas canvas;

    @Override
    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

    @Override
    public String getName() {
        return "Perfect circle";
    }

    @Override
    public void init() {
        canvas.setPixelRounded(true);
    }

    @Override
    public void run() {
        for (int i = 0; i < 360 * variables[0].getValue(); i++) {
            canvas.setPenDown(false);
            canvas.goToLocation(0, 0);
            canvas.rotate(((double) i) / variables[0].getValue());
            canvas.forward(variables[0].getValue());
            canvas.setPenDown(true);
            canvas.goToRelativeLocation(0, 0);
        }
    }

    @Override
    public Variable[] getVariables() {
        return variables;
    }

    @Override
    public void variableChanged(Variable variable) {
        canvas.redraw();
    }
}
