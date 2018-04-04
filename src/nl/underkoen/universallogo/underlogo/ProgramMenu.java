package nl.underkoen.universallogo.underlogo;

import nl.underkoen.universallogo.Program;

import java.awt.*;

/**
 * Created by Under_Koen on 04/04/2018.
 */
public class ProgramMenu extends EventMenu {
    public Program program;

    public ProgramMenu(Component event_handler, String label, Program program) {
        super(event_handler, label);
        this.program = program;
    }
}
