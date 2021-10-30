package org.md121.HTMLbrowser.controllers;

import java.net.URL;

public interface Controller {

    URL go(String address);
    URL back();
    URL forward();
    URL getCurrentUrl();
    void setStartPage(String address);
    String getStartPage();
}
