package org.md121.HTMLbrowser.models;

import java.net.URL;

public interface Engine {
    URL verifyAddress(String address);
    URL goForward();
    URL goBack();
    void go(URL current);

}
