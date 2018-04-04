package nl.underkoen.universallogo.javalogo;

import logotekenap.InvoerVariabele;
import nl.underkoen.universallogo.Variable;

import java.awt.*;

/**
 * Created by Under_Koen on 02/04/2018.
 */
public class InputVar extends InvoerVariabele {
    private Label label;
    private TextField textField;

    private Variable variable;

    public Variable getVariable() {
        return variable;
    }

    public void setVariable(Variable variable) {
        this.variable = variable;
        setMinValue(variable.getMinValue());
        setMaxValue(variable.getMaxValue());
        setValue(variable.getValue());
        setLabelText(variable.getName() + ":");
        label.setSize(133, label.getHeight());
    }

    public InputVar() {
        this("");
    }

    public InputVar(String text) {
        this(text, 0);
    }

    public InputVar(String text, int value) {
        this(text,Integer.MIN_VALUE,Integer.MAX_VALUE,value);
    }

    public InputVar(String text, int minValue, int maxValue, int value) {
        super(text, minValue, maxValue, value);
        label = (Label) getComponent(0);
        textField = (TextField) getComponent(2);
        textField.setColumns(10);
    }

    public void setLabelText(String text) {
        super.naam = text;
        label.setText(text);
    }

    public void setMinValue(int minValue) {
        minw = minValue;
    }

    public void setMaxValue(int maxValue) {
        maxw = maxValue;
    }

    public void setValue(int value) {
        zetWaarde(value);
    }

    private boolean paintNot = false;
    private boolean firstPaint = true;
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (paintNot) {
            setVisible(false);
            paintNot = false;
        }
        firstPaint = false;
    }

    @Override
    public void zetInvoerUit() {
        super.enabled = false;
        if (firstPaint) {
            paintNot = true;
        } else {
            setVisible(false);
        }
    }

    @Override
    public void zetInvoerAan() {
        super.enabled = true;
        if (firstPaint) {
            paintNot = false;
        } else {
            setVisible(true);
            repaint();
        }
    }
}
