package Vue;

import App.MonApplication;
import Controler.ControlerListToQCM;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PanellistQCM extends AbstractPanel{
    private JList liste;
    private final JPanel sousPanelJlist = new JPanel();

    private final ControlerListToQCM controleur = new ControlerListToQCM(app);

    public PanellistQCM(MonApplication app, Mainwindow mainwindow) {
        super(app,mainwindow);
        this.setSize(150,mainwindow.getHeight());
        this.setLayout(null);
        setListeQCM();
    }

    public void setListeQCM(){

        liste = new JList();

        sousPanelJlist.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        sousPanelJlist.setLayout(new BorderLayout());
        sousPanelJlist.setBounds(0,0,this.getWidth(),this.getHeight());

        liste.setListData(app.getListAllQCm().toArray());
        liste.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        final JScrollPane scroll = new JScrollPane(liste);
        scroll.setSize(sousPanelJlist.getSize()); // coords du scroll

        sousPanelJlist.add(scroll);

        sousPanelJlist.setVisible(true);

        this.add(sousPanelJlist);

        // le double clic pour changer de qcm
        liste.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    int index = liste.locationToIndex(evt.getPoint()); // on récupère l'index dans la Jliste de l'étudiant qui a été sélectionner
                    mainwindow.currentQCM = (app.getListAllQCm().get(index));
                    mainwindow.currentQCM.setIndexQuestion(0);
                    mainwindow.currentQCM.startTime(); // on lance le chrono
                    controleur.getQCm(app.getListAllQCm().get(index).toString());
                }
            }
        });
    }
}
