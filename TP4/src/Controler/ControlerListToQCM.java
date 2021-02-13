package Controler;

import App.MonApplication;

import java.util.HashMap;

public class ControlerListToQCM extends AbstractControleur {
    private final HashMap<String, String> list = new HashMap<>();

    public ControlerListToQCM(MonApplication app) {
        super(app);
    }

    public void getQCm(String data){
        setHashmap();
        String url = "http://perso.univ-lyon1.fr/lionel.buathier/qcm/qcm.php?file=";

        for(String i : list.keySet()){
            if(i.equals(data)){
                url += list.get(data);
                app.setId(data);
            }
        }
        app.setListAllQCm(url);
    }

    public void setHashmap(){
        list.put("java 1 bases","java-01");
        list.put("java 2 avance","java-02");
        list.put("java 3 expert","java-03");

        list.put("PHP Niveau 1","php-01");
        list.put("PHP Niveau 2","php-02");

        list.put("Android Niveau 1","android-01");
        list.put("Android Niveau 2","android-02");
    }

}
