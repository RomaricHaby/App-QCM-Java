package Controler;

import App.MonApplication;

public abstract class AbstractControleur {
    protected  MonApplication app;

    public AbstractControleur(MonApplication app) {
        this.app = app;
    }
}
