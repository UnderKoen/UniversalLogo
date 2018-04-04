package nl.underkoen.universallogo.underlogo;

import nl.underkoen.universallogo.Location;

import java.awt.*;

/**
 * Created by Under_Koen on 04/04/2018.
 */
public class DrawingCanvas extends Canvas {
    public Image im;
    private Color color = Color.BLACK;
    private Color back = Color.WHITE;

    public void setBackground(Color color) {
        back = color;
    }

    public void print(String text, Font font, Location where) {
        Graphics imG = im.getGraphics();
        if (font != null)imG.setFont(font);
        imG.drawString(text, (int)where.getX(), (int)-where.getY());
    }

    public void fillIn(Polygon polygon, Color color, boolean outline) {
        Graphics imG = im.getGraphics();
        imG.setColor(color);
        imG.fillPolygon(polygon);
        imG.setColor(this.color);
        if (outline) imG.drawPolygon(polygon);
    }

    public void setColor(Color color) {
        im.getGraphics().setColor(color);
        this.color = color;
    }

    public void drawLine(Location from, Location to) {
        im.getGraphics().drawLine((int)Math.round(from.getX()), -(int)Math.round(from.getY()), (int)Math.round(to.getX()), -(int)Math.round(to.getY()));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (im == null) im = createImage(500,500);
        g.drawImage(im, 0,0, null);
    }

    public void redraw() {
        Graphics imG = im.getGraphics();
        imG.setColor(back);
        imG.fillRect(0,0, this.getWidth(), this.getHeight());
        imG.setColor(color);
    }
}
