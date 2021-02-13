package Vue;

import App.MonApplication;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractPanel extends JPanel {

    protected final Font title = new Font(Font.SERIF, Font.BOLD, 25);
    protected final Font text = new Font(Font.SERIF, Font.PLAIN, 17);
    protected MonApplication app;
    protected Mainwindow mainwindow;

    public AbstractPanel(MonApplication app, Mainwindow mainwindow) {
        this.app = app;
        this.mainwindow = mainwindow;
    }
}
