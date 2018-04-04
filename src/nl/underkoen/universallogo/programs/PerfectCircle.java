package nl.underkoen.universallogo.programs;

import nl.underkoen.universallogo.*;

import java.awt.*;

/**
 * Created by Under_Koen on 31/03/2018.
 */
public class PerfectCircle extends Program implements VariableListener {
    Variable[] variables = {new Variable("Width", 100, 249, 1)};

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
