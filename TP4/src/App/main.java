package App;

import Vue.Mainwindow;

import javax.swing.*;

public class main {
    public static void main(String[] args){
        //thème windows pour l'application
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        MonApplication app = new MonApplication();
        app.launch(); // récup la liste des qcm

        Mainwindow fen = new Mainwindow(app);
    }
}
