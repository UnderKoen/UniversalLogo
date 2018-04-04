package nl.underkoen.universallogo;

/**
 * Created by Under_Koen on 03/04/2018.
 */
public interface VariableListener {
    /**
     * @return the variables your program should use
     */
    Variable[] getVariables();

    /**
     * Run's when a variable from {@link #getVariables()} changes
     * @param variable the variable that changes
     */
    void variableChanged(Variable variable);
}
