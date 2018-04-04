package nl.underkoen.universallogo.underlogo;

import nl.underkoen.universallogo.*;
import nl.underkoen.universallogo.Canvas;
import nl.underkoen.universallogo.programs.AnimatedTest;
import nl.underkoen.universallogo.programs.PerfectCircle;
import nl.underkoen.universallogo.programs.Test;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by Under_Koen on 04/04/2018.
 */
public class UnderLogo extends Applet implements MouseListener, MouseMotionListener {
    public final int WIDTH = 500;
    public final int HEIGHT = 500;

    Canvas canvas;
    DrawingCanvas dc;
    boolean init;

    Program[] programs = {new PerfectCircle(), new Test(), new AnimatedTest()};
    Program program;
    Color back = Color.white;

    Menu vars;

    @Override
    public void init() {
        super.init();
        Object f = getParent ();
        while (! (f instanceof Frame)) {
            f = ((Component) f).getParent();
        }
        Frame frame = (Frame) f;
        frame.setMenuBar(setupBar());

        dc = new DrawingCanvas();
        dc.setSize(WIDTH,HEIGHT);
        canvas = new UnderLogoCanvas(this);
        dc.addMouseListener(this);
        dc.addMouseMotionListener(this);
        add(dc);
        if (programs.length != 0) program = programs[0];
    }

    public MenuBar setupBar() {
        MenuBar bar = new MenuBar();
        Menu programs = new Menu("Programs");
        for (Program program : this.programs) {
            programs.add(new ProgramMenu(this, program.getName(), program));
        }
        bar.add(programs);
        vars = new Menu("Variables");
        bar.add(vars);
        return bar;
    }

    @Override
    public void start() {
        super.start();
        dc.paint(dc.getGraphics());
        redraw();
    }

    public void loadProgram(Program program) {
        if (program == null) return;
        init = true;
        vars.removeAll();
        if (program instanceof VariableListener) {
            VariableListener vl = (VariableListener) program;
            for (Variable variable : vl.getVariables()) {
                vars.add(new VariableMenuList(variable, () -> {
                    vl.variableChanged(variable);
                }));
            }
        }
        canvas.reset();
        program.setCanvas(canvas);
        program.init();
        init = false;
    }

    public void redraw() {
        dc.setBackground(back);
        dc.redraw();
        loadProgram(program);
        if (program instanceof AnimatedProgram) {
            ((AnimatedProgram) program).drawAnimation();
        } else {
            program.run();
        }
        dc.paint(dc.getGraphics());
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    public Location in;

    @Override
    public void mousePressed(MouseEvent e) {
        if (program instanceof nl.underkoen.universallogo.MouseListener) {
            nl.underkoen.universallogo.MouseListener ml = (nl.underkoen.universallogo.MouseListener) program;

            in = canvas.getRelativeLocation(new Location(e.getX(), -e.getY()), nl.underkoen.universallogo.Point.TOP_LEFT);
            ml.mouseIn(canvas.getRelativeLocation(new Location(e.getX(), -e.getY()), nl.underkoen.universallogo.Point.TOP_LEFT), in.clone());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (program instanceof nl.underkoen.universallogo.MouseListener) {
            nl.underkoen.universallogo.MouseListener ml = (nl.underkoen.universallogo.MouseListener) program;

            ml.mouseOut(canvas.getRelativeLocation(new Location(e.getX(), -e.getY()), nl.underkoen.universallogo.Point.TOP_LEFT), in.clone());
            in = null;
            oldDrag = new Location(0,0);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Location oldDrag = new Location(0,0);

    @Override
    public void mouseDragged(MouseEvent e) {
        if (program instanceof nl.underkoen.universallogo.MouseListener) {
            nl.underkoen.universallogo.MouseListener ml = (nl.underkoen.universallogo.MouseListener) program;

            Location mouse = canvas.getRelativeLocation(new Location(e.getX(), -e.getY()), nl.underkoen.universallogo.Point.TOP_LEFT);
            Location delta = mouse.clone();
            delta.addX(-oldDrag.getX());
            delta.addY(-oldDrag.getY());
            ml.mouseDrag(mouse.clone(), in.clone(), delta);
            oldDrag = mouse.clone();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (program instanceof nl.underkoen.universallogo.MouseListener) {
            nl.underkoen.universallogo.MouseListener ml = (nl.underkoen.universallogo.MouseListener) program;

            ml.mouseMove(canvas.getRelativeLocation(new Location(e.getX(), -e.getY()), nl.underkoen.universallogo.Point.TOP_LEFT));
        }
    }

    @Override
    public boolean action(Event e, Object arg) {
        if (e.target instanceof ProgramMenu) {
            ProgramMenu programMenu = (ProgramMenu) e.target;
            program = programMenu.program;
            redraw();
        }
        return super.action(e,arg);
    }
}