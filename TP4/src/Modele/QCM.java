package Modele;

import java.util.ArrayList;
import java.util.Observable;

public class QCM extends Observable {
    private final String titre;
    private double score;
    private double time;
    private  ArrayList<Question> listQuestion;
    private int indexQuestion;

    public QCM(String titre) {
        this.titre = titre;
        this.indexQuestion = 0;
    }

    public String getTitre() {
        return titre;
    }

    public double getScore() {
        return score;
    }

    public String getTime() {
        double s, min=0;
        s = this.time /1000;
        if (s >= 60) min++;
        return min +"mn "+ s + "s";
    }

    public ArrayList<Question> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(ArrayList<Question> listQuestion) {
        this.listQuestion = new ArrayList<>();
        this.listQuestion = listQuestion;
    }

    public void setScore(boolean reponse){ // score >0 +1 true -0,5 false
        if(score >= 0){
            if (reponse)score++;
            else{
                if(score >= 0.50){
                    score -= 0.5;
                }
            }
        }
    }

    public int getIndexQuestion() {
        return indexQuestion;
    }

    public void setIndexQuestion(int indexQuestion) {
        this.indexQuestion = indexQuestion;
    }

    public void nextQuestion() {
        this.indexQuestion ++;
        setChanged();
        notifyObservers(this);
    }

    public void startTime() {
       this.time = System.currentTimeMillis();
    }
    public void stopTime(){
        this.time = System.currentTimeMillis() -  this.time;
    }

    @Override
    public String toString() {
        String tmp = "";
        tmp += titre;
      return tmp;
    }
}
