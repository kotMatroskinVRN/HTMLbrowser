package org.md121.HTMLbrowser.controllers;

import java.net.URL;

public interface Controller {

    URL go(String address);
    URL back();
    URL forward();
    void refresh();
    void zoomIn();
    void zoomOut();
    URL getCurrentUrl();
    void setStartPage(String address);
    String getStartPage();
}
