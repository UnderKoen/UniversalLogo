package nl.underkoen.universallogo.javalogo;

import logotekenap.InvoerVariabele;
import logotekenap.TekenApplet;
import nl.underkoen.universallogo.*;
import nl.underkoen.universallogo.programs.AnimatedTest;
import nl.underkoen.universallogo.programs.PerfectCircle;
import nl.underkoen.universallogo.programs.Test;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Under_Koen on 31/03/2018.
 */
public class JavaLogo extends TekenApplet {
    public final int WIDTH = 500;
    public final int HEIGHT = 500;

    protected boolean canDraw = false;
    protected boolean init = false;

    private Canvas canvas;
    private ProgramVar programVar;
    private Program[] programs = {new PerfectCircle(), new Test(), new AnimatedTest()};
    private Program program;

    @Override
    public void initialiseer() {
        setSize(WIDTH, HEIGHT);
        canvas = new JavaLogoCanvas(this);

        programVar = new ProgramVar();
        programVar.onChange = () -> {
            if (program instanceof AnimatedProgram) {
                AnimatedProgram animatedProgram = (AnimatedProgram) program;
                animatedProgram.stopAnimation();
            }
            program = programVar.getCurrentProgram();
            load(program);
            tekenOpnieuw();
        };
        for (Program program : programs) {
            programVar.addProgram(program);
        }

        maakMuisActieMogelijk();
    }

    private void load(Program program) {
        if (program == null) return;
        this.program = program;
        init = true;
        program.setCanvas(canvas);
        program.init();
        init = false;
        if (program instanceof VariableListener) {
            VariableListener variableListener = (VariableListener) program;
            Variable[] variables = variableListener.getVariables();
            for (int i = 0; i < variables.length; i++) {
                vars.get(i).setVariable(variables[i]);
                vars.get(i).setEnabled(true);
            }
        }
    }

    private boolean first = true;
    private List<InputVar> vars = new ArrayList<>();

    @Override
    public void tekenprogramma() {
        if (first) {
            getComponent(0).addMouseMotionListener(new MouseMotionListener() {
                @Override
                public void mouseDragged(MouseEvent e) {}

                @Override
                public void mouseMoved(MouseEvent e) {
                    mouseMove(e);
                }
            });
            maakZichtbaar(programVar);
            for (int i = 0; i < 9; i++) {
                InputVar var = new InputVar();
                vars.add(var);
                maakZichtbaar(var);
                var.setEnabled(false);
            }
            setSize(WIDTH + 200, (HEIGHT < 650) ? 650 : HEIGHT);
            first = false;
        }
        vars.forEach(inputVar -> inputVar.setEnabled(false));
        canvas.reset();
        load(programVar.getCurrentProgram());
        canDraw = true;
        if (program != null) {
            if (program instanceof AnimatedProgram && ((AnimatedProgram) program).isAnimationActive()) {
                AnimatedProgram animatedProgram = (AnimatedProgram) program;
                animatedProgram.drawAnimation();
            } else {
                program.run();
            }
        }
        canDraw = false;
    }

    public void mouseMove(MouseEvent e) {
        if (program instanceof MouseListener) {
            MouseListener ml = (MouseListener) program;

            ml.mouseMove(canvas.getRelativeLocation(new Location(e.getX(), -e.getY()), nl.underkoen.universallogo.Point.TOP_LEFT));
        }
    }

    @Override
    public void muisDrukActie() {
        if (program instanceof MouseListener) {
            MouseListener ml = (MouseListener) program;

            ml.mouseIn(canvas.getRelativeLocation(new Location(geefX(), -geefY()), nl.underkoen.universallogo.Point.TOP_LEFT),
                    canvas.getRelativeLocation(new Location(geefDrukx(), -geefDruky()), nl.underkoen.universallogo.Point.TOP_LEFT)
            );
        }
    }

    @Override
    public void muisLosActie() {
        if (program instanceof MouseListener) {
            MouseListener ml = (MouseListener) program;

            ml.mouseOut(canvas.getRelativeLocation(new Location(geefX(), -geefY()), nl.underkoen.universallogo.Point.TOP_LEFT),
                    canvas.getRelativeLocation(new Location(geefDrukx(), -geefDruky()), nl.underkoen.universallogo.Point.TOP_LEFT)
            );
        }
    }

    @Override
    public void muisSleepActie() {
        if (program instanceof MouseListener) {
            MouseListener ml = (MouseListener) program;

            ml.mouseDrag(canvas.getRelativeLocation(new Location(geefX(), -geefY()), nl.underkoen.universallogo.Point.TOP_LEFT),
                    canvas.getRelativeLocation(new Location(geefDrukx(), -geefDruky()), nl.underkoen.universallogo.Point.TOP_LEFT),
                    canvas.getRelativeLocation(new Location(geefSleepdx(), -geefSleepdy()), nl.underkoen.universallogo.Point.TOP_LEFT)
            );
        }
    }

    @Override
    public void invoerVarActie(InvoerVariabele iv) {
        if (program instanceof VariableListener) {
            VariableListener variableListener = (VariableListener) program;
            if (iv instanceof InputVar) {
                InputVar inputVar = (InputVar) iv;
                inputVar.getVariable().setValue(iv.geefWaarde());
                variableListener.variableChanged(inputVar.getVariable());
            }
        }
    }
}