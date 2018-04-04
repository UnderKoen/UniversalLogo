package nl.underkoen.universallogo.underlogo;

import nl.underkoen.universallogo.Variable;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Under_Koen on 04/04/2018.
 */
public class VariableMenuList extends MenuItem {
    Variable variable;

    public Runnable onChange;

    public VariableMenuList(Variable variable, Runnable onChange) {
        super(variable.getName() + ": " + variable.getValue());
        this.variable = variable;
        this.onChange = onChange;
    }

    @Override
    public boolean postEvent(Event evt) {
        String i = JOptionPane.showInputDialog(null, "Change value for: " + variable.getName(), variable.getValue());
        try {
            int v = Integer.parseInt(i);
            if (v > variable.getMaxValue()) v = variable.getMaxValue();
            if (v < variable.getMinValue()) v = variable.getMinValue();
            variable.setValue(v);
            setLabel(variable.getName() + ": " + variable.getValue());
            onChange.run();
        } catch (Exception e) {
        }
        return super.postEvent(evt);
    }
}
