package nl.underkoen.universallogo.javalogo;

import nl.underkoen.universallogo.Program;

import java.awt.*;
import java.awt.Canvas;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 01/04/2018.
 */
public class ProgramVar extends Panel implements MouseListener {
    private Label label;
    private ArrowCanvas arrowCanvas;
    private TextField textField;

    private List<Program> programs = new ArrayList<>();
    private Program currentProgram;
    private int current;

    public Runnable onChange;

    public ProgramVar() {
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        setLayout(gridbag);
        c.gridwidth = 0;
        c.anchor = 17;
        label = new Label("Current Program:");
        gridbag.setConstraints(label, c);
        add(label);
        c.gridwidth = 1;
        c.insets = new Insets(5, 0, 0, 0);
        c.anchor = 13;
        c.fill = 0;
        arrowCanvas = new ArrowCanvas();
        arrowCanvas.addMouseListener(this);
        gridbag.setConstraints(arrowCanvas, c);
        add(arrowCanvas);
        c.anchor = 17;
        textField = new TextField(10);
        textField.setEditable(false);
        gridbag.setConstraints(textField, c);
        add(textField);
    }

    public void addProgram(Program program) {

        boolean empty = programs.isEmpty();
        programs.add(program);
        if (empty) {
            selectProgram(0);
        }
    }

    private void selectProgram(int i) {
        currentProgram = programs.get(i);
        current = i;
        textField.setText(currentProgram.getName());
        setVisible(true);
    }

    public Program getCurrentProgram() {
        return currentProgram;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int dir = arrowCanvas.getArrow(e.getY());
        if (programs.size() <= 1 || dir == 0) return;
        int newProgram = current + dir;
        if (newProgram < 0) newProgram = programs.size() - 1;
        if (newProgram >= programs.size()) newProgram = 0;
        selectProgram(newProgram);
        onChange.run();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        arrowCanvas.setInactive();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}

class ArrowCanvas extends Canvas {
    private Color disabledArrowColor;
    private Color enabledArrowColor;
    private Color activeArrowColor;
    private Dimension dd;
    private Polygon up;
    private boolean upActive;
    private Polygon down;
    private boolean downActive;
    private boolean enabled;

    public ArrowCanvas() {
        this.disabledArrowColor = Color.gray;
        this.enabledArrowColor = Color.red;
        this.activeArrowColor = Color.green;
        this.upActive = false;
        this.downActive = false;
        this.enabled = true;
        this.dd = new Dimension(19, 23);
        this.setSize(this.dd);
        this.up = new Polygon();
        this.up.addPoint(10, 2);
        this.up.addPoint(3, 10);
        this.up.addPoint(17, 10);
        this.down = new Polygon();
        this.down.addPoint(3, 13);
        this.down.addPoint(17, 13);
        this.down.addPoint(10, 21);
    }

    public void paint(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(0, 0, this.dd.width - 1, this.dd.height - 1);
        g.setColor(Color.white);
        g.fillRect(1, 1, this.dd.width - 2, this.dd.height - 2);
        this.paintPolygon(g, this.up, this.upActive);
        this.paintPolygon(g, this.down, this.downActive);
    }

    protected void setInactive() {
        this.upActive = false;
        this.downActive = false;
        this.repaint();
    }

    protected int getArrow(int clicky) {
        int dir = 0;
        if (clicky > 12) {
            this.downActive = true;
            dir = -1;
            this.repaint();
        } else if (clicky < 11) {
            this.upActive = true;
            dir = 1;
            this.repaint();
        }

        return dir;
    }

    public void setEnabled(boolean b) {
        this.enabled = b;
        this.repaint();
    }

    private void paintPolygon(Graphics g, Polygon p, boolean active) {
        if (this.enabled) {
            if (active) {
                g.setColor(this.activeArrowColor);
            } else {
                g.setColor(this.enabledArrowColor);
            }
        } else {
            g.setColor(this.disabledArrowColor);
        }

        g.drawPolygon(p);
        g.fillPolygon(p);
    }
}