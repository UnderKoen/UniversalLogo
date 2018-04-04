package nl.underkoen.universallogo.javalogo;

import nl.underkoen.universallogo.AbstractCanvas;
import nl.underkoen.universallogo.Size;

import java.awt.*;

/**
 * Created by Under_Koen on 04/04/2018.
 */
public class JavaLogoCanvas extends AbstractCanvas {
    JavaLogo javaLogo;

    public JavaLogoCanvas(JavaLogo javaLogo) {
        this.javaLogo = javaLogo;
    }

    @Override
    public boolean isInitializing() {
        return javaLogo.init;
    }

    @Override
    public void goToRelative(double x, double y) {
        javaLogo.stap(x,y);
    }

    @Override
    public void activePen(int r, int g, int b) {
        javaLogo.penAan(r, g, b);
    }

    @Override
    public void disablePen() {
        javaLogo.penUit();

    }

    @Override
    public void changeBackgroundColor(int r, int g, int b) {
        javaLogo.achtergrondkleur(r, g, b);
    }

    @Override
    public void startCheckingPath(int r, int g, int b) {
        javaLogo.vulAan(r, g, b);
    }

    @Override
    public void stopCheckingPath() {
        javaLogo.vulAan();
        javaLogo.vulUit();
    }

    @Override
    public void fillIn() {
        javaLogo.vulUit();
    }

    @Override
    public void type(String text) {
        javaLogo.schrijf(text);
    }

    @Override
    public void type(String text, Font font) {
        javaLogo.schrijf(text, font);
    }

    @Override
    public Size getSize() {
        return new Size(javaLogo.WIDTH, javaLogo.HEIGHT);
    }

    @Override
    public void redraw() {
        javaLogo.tekenOpnieuw();
    }

    @Override
    public boolean canDraw() {
        return javaLogo.canDraw;
    }
}
