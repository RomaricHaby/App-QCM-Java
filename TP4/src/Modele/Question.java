package Modele;

import java.util.ArrayList;

public class Question {
    private final String titre;
    private final ArrayList<String > choix;
    private final int reponse;

    public Question(String titre, ArrayList<String> choix, int reponse) {
        this.titre = titre;
        this.choix = choix;
        this.reponse = reponse;
    }

    public String getTitre() {
        return titre;
    }

    public ArrayList<String> getChoix() {
        return choix;
    }

    public int getReponse() {
        return reponse;
    }

    @Override
    public String toString() {
        String tmp = "";
        tmp += titre + " : \n";

        for (int i=0 ; i < choix.size(); i++){
            tmp +="     - "+ choix.get(i) + "\n";
        }

        return tmp;
    }
}
