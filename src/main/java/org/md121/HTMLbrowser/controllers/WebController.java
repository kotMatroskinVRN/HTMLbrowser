package org.md121.HTMLbrowser.controllers;

import org.md121.HTMLbrowser.models.Engine;

import java.net.URL;

public class WebController implements Controller  {

    private final Engine engine;
    private URL currentURL ;
    private String startPage = "./";

    public WebController(Engine engine){
        this.engine = engine;
    }



    @Override
    public URL go(String address) {
        currentURL = engine.verifyAddress(address);
        engine.go(currentURL);
        return currentURL;
    }

    @Override
    public URL back() {
        currentURL = engine.goBack();
        return currentURL;
    }

    @Override
    public URL forward() {
        currentURL = engine.goForward();
        return currentURL;
    }


    @Override
    public URL getCurrentUrl() {
//        return engine.verifyAddress(currentURL.toString());
        return currentURL;
    }

    @Override
    public void setStartPage(String address){
        this.startPage = startPage;
    }

    @Override
    public String getStartPage() {
        return startPage;
    }

}
