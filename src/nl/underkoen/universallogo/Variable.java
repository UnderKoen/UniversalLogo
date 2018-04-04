package nl.underkoen.universallogo;

/**
 * Created by Under_Koen on 03/04/2018.
 */
public class Variable {
    private String name;
    private int value;
    private int maxValue;
    private int minValue;

    /**
     * @return the name from the variable
     */
    public String getName() {
        return name;
    }

    /**
     * @return the value of the variable
     */
    public int getValue() {
        return value;
    }

    /**
     * @return the max-value of the variable
     */
    public int getMaxValue() {
        return maxValue;
    }

    /**
     * @return the min-value of the variable
     */
    public int getMinValue() {
        return minValue;
    }

    /**
     * Set's the value of the variable
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Creates a variable with only a name
     * @param name the for the variable
     */
    public Variable(String name) {
        this(name, 0);
    }

    /**
     * Creates a variable with only a name and default value
     * @param name the for the variable
     * @param value the default value for the variable
     */
    public Variable(String name, int value) {
        this(name, value, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }

    /**
     * Creates a variable with name, default value, max value and min value
     * @param name the for the variable
     * @param value the default value for the variable
     * @param maxValue the maximum value for the variable
     * @param minValue the minimum value for the variable
     */
    public Variable(String name, int value, int maxValue, int minValue) {
        this.name = name;
        this.value = value;
        this.maxValue = maxValue;
        this.minValue = minValue;
    }

    /**
     * @return the location in this format "Variable: (name: "name", Value: value, Min Value: minValue, Max Value: maxValue)"
     */
    @Override
    public String toString() {
        return "Variable: (name: \"" + name + "\", Value: " + value + ", Min Value: " + minValue + ", Max Value: " + maxValue + ")";
    }
}
