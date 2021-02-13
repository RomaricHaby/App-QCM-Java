package Vue;

import App.MonApplication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class PanelQCM extends AbstractPanel implements Observer {
    private final JList  liste = new JList();

    private final JLabel titre = new JLabel();
    private final JLabel question = new JLabel();

    private final JPanel sousPanelChoix = new JPanel();

    private final JButton valider = new JButton("Valider");


    public PanelQCM(MonApplication app, Mainwindow mainwindow) {
        super(app, mainwindow);

        this.setSize(mainwindow.getWidth()-150,mainwindow.getHeight()); //coords du panel
        this.setLayout(null);

        titre.setFont(super.title);
        titre.setBounds(165,20,250,30);
        this.add(titre);

        question.setBounds(20,100,800,30);
        question.setFont(super.text);

        this.add(question);

        valider.setBounds(210,290,100,30);
        valider.addActionListener(new Ecouter());

        valider.setVisible(true);
        valider.setFocusPainted(false);
        this.add(valider);

        app.addObserver(this);

        setChoix();
    }


    public void setChoix(){
        liste.removeAll();
        sousPanelChoix.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        sousPanelChoix.setLayout(new BorderLayout());
        sousPanelChoix.setBounds(150,150,250,100);

        liste.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        final JScrollPane scroll = new JScrollPane(liste);
        scroll.setSize(sousPanelChoix.getSize()); // coords du scroll

        sousPanelChoix.add(scroll);
        sousPanelChoix.setVisible(false);
        valider.setVisible(false);
        this.add(sousPanelChoix);
    }

    public void ChangedData(){
        mainwindow.currentQCM.addObserver(this::update);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if(mainwindow.currentQCM.getListQuestion() != null){
            if(mainwindow.currentQCM.getIndexQuestion() < mainwindow.currentQCM.getListQuestion().size()){

                titre.setBounds(165,20,250,30);
                titre.setText(mainwindow.currentQCM.getTitre());
                question.setText(mainwindow.currentQCM.getListQuestion().get(mainwindow.currentQCM.getIndexQuestion()).getTitre());
                question.setBounds(20,100,800,30);
                liste.setListData(mainwindow.currentQCM.getListQuestion().get(mainwindow.currentQCM.getIndexQuestion()).getChoix().toArray());

                sousPanelChoix.setVisible(true);
                valider.setVisible(true);

            }
            else{
                mainwindow.currentQCM.stopTime();
                titre.setText("Ce QMC est finis !");
                titre.setLocation(160,165);
                question.setText(" Votre score est de : "+ mainwindow.currentQCM.getScore() + "/"+ mainwindow.currentQCM.getListQuestion().size());
                question.setLocation(100,220);
                sousPanelChoix.setVisible(false);
                valider.setVisible(false);
            }
        }
        else{
            titre.setText("Ce QMC est vide !");
            titre.setLocation(160,165);
            question.setText("");
            sousPanelChoix.setVisible(false);
            valider.setVisible(false);
        }
    }

    public class Ecouter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == valider){
                if (liste.getSelectedValue() != null) {
                    int  reponseIndex = liste.getSelectedIndex();

                    mainwindow.currentQCM.setScore(reponseIndex == mainwindow.currentQCM.getListQuestion().get(mainwindow.currentQCM.getIndexQuestion()).getReponse());
                    mainwindow.currentQCM.nextQuestion();
                }
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        ChangedData();
    }
}
