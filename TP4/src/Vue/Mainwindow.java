package Vue;

import App.MonApplication;
import Modele.QCM;

import javax.swing.*;

public class Mainwindow extends JFrame {
    private final PanellistQCM panellistQCM;
    private final PanelQCM panelQCM;

    private final JMenuBar barre = new JMenuBar();
    private final JMenu menu = new JMenu("QCM");
    private final JMenuItem stats = new JMenuItem("Statistique");

    protected QCM currentQCM;


    public Mainwindow(MonApplication app){
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new JDesktopPane());
        this.setTitle("QCM");

        this.setSize(750,450);

        //Jmenu
        menu.add(stats);
        barre.add(menu);
        this.setJMenuBar(barre);

        //Jlist des qcm
        panellistQCM = new PanellistQCM(app,this);
        this.add(panellistQCM);
        panellistQCM.setVisible(true);
        panellistQCM.setLocation(0,0);

        panelQCM = new PanelQCM(app,this);
        panelQCM.setVisible(true);
        panelQCM.setLocation(panellistQCM.getWidth(),0);
        this.add(panelQCM);

        this.setVisible(true);
    }
}
