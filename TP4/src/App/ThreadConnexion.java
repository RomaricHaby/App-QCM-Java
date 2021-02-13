package App;

public class ThreadConnexion extends Thread {
    private final MonApplication call;
    private final String url;
    public ThreadConnexion(MonApplication call, String url) {
        this.call = call;
        this.url = url;
    }

    @Override
    public void run() {
        ConnexionHTTP connexionHTTP = new ConnexionHTTP(url);
        call.onWorkDone(connexionHTTP.getRequest());
    }
}
