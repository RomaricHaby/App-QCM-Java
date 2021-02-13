package App;

import Modele.QCM;
import Modele.Question;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Observable;


public class MonApplication extends Observable implements Callback {
    private String id;
    private final ArrayList<QCM> listAllQCm = new ArrayList<>();


    private  ArrayList<Question> questionQCM;

    public void launch() {
        ThreadConnexion t = new ThreadConnexion(this,"http://perso.univ-lyon1.fr/lionel.buathier/qcm/qcm.php?file=liste-qcm");
        t.start();
    }

    public void setListAllQCm(String url) {
       // on att le thread d'avant
        ThreadConnexion t = new ThreadConnexion(this,url);
        t.start();

        setChanged();
        notifyObservers(this);
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public synchronized void onWorkDone(String result) {
        //Todo regarder la classe de retour / sois un int en param
        parstJson(result);
        partsJsonQuestion(result);
    }

    public void parstJson(String data){
        JSONObject jsonObject = new JSONObject(data);
        String[] key = {"java","php","android"};
        try {
            for(int i=0; i < jsonObject.length(); i++){
                String tmp = key[i];
                for(int j = 0; j <jsonObject.getJSONObject(tmp).length(); j++){
                    QCM qcm = new QCM(jsonObject.getJSONObject(tmp).getString(tmp + "-0" + (j + 1)));
                    listAllQCm.add(qcm);
                }
            }
        }
        catch (org.json.JSONException e){
            //System.out.println(e);
        }
    }

    public void partsJsonQuestion(String data){
        JSONObject jsonObject = new JSONObject(data);
        Question question;

            try {
                if(checkError404(data)){ //On check si le fichier est erreur 404
                    if(checkQCMisNull()){ // Cela évite de remettre les questions si le QCM est déjà remplis

                        questionQCM = new ArrayList<>();
                        for (int i = 0; i <= jsonObject.length(); i++) { // on parcour les key
                            JSONObject tmp = (JSONObject) jsonObject.getJSONArray("questions").get(i);

                            ArrayList<String> choixTmp = new ArrayList<>();
                            choixTmp.clear();

                            for (int j = 0; j < tmp.getJSONArray("choix").length(); j++) {
                                choixTmp.add(tmp.getJSONArray("choix").get(j).toString());
                            }

                            question = new Question(tmp.getString("titre"), choixTmp, tmp.getInt("reponse"));
                            questionQCM.add(question);
                        }


                        for (int l = 0; l < listAllQCm.size(); l++) {
                            if (listAllQCm.get(l).getTitre().equals(this.id)) {
                                listAllQCm.get(l).setListQuestion(questionQCM);
                            }
                        }
                    }
                    else{
                        System.out.println("Ce QCM est déja remplie");
                    }
                }
                else {
                    System.out.println("error 404 file");
                }
            }
            catch (org.json.JSONException e){
                //System.out.println(e);
            }
    }


    public boolean checkError404(String data){
        JSONObject jsonObject = new JSONObject(data);
        try {
            int tmp = jsonObject.getInt("code"); // on regarde just si la key code est dans le json
        }
        catch (org.json.JSONException e){
            //System.out.println(e);
            return true;
        }
        return false;
    }

    public boolean checkQCMisNull(){
        for(int i=0; i < listAllQCm.size(); i++){
            if(listAllQCm.get(i).getListQuestion() == null && listAllQCm.get(i).getTitre().equalsIgnoreCase(this.id)){
                return true;
            }
        }
        return false;
    }
    public ArrayList<QCM> getListAllQCm() {
        return listAllQCm;
    }
}
