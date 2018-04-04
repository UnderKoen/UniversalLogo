package nl.underkoen.universallogo.underlogo;

import java.awt.*;

/**
 * Created by Under_Koen on 04/04/2018.
 */
public class EventMenu extends MenuItem {
    private Component event_handler;

    public EventMenu(Component event_handler, String label) {
        super(label);
        this.event_handler = event_handler;
    }

    @Override
    public boolean postEvent(Event e) {
        if (event_handler.isValid()) return event_handler.postEvent(e);
        else return false;
    }
}
