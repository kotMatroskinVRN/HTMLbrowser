package org.md121.HTMLbrowser;

import org.md121.HTMLbrowser.controllers.Controller;
import org.md121.HTMLbrowser.controllers.WebController;
import org.md121.HTMLbrowser.models.Engine;
import org.md121.HTMLbrowser.models.WebEngine;
import org.md121.HTMLbrowser.view.SwingView;
import org.md121.HTMLbrowser.view.ViewInterface;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

 //       if(args.length>0) Browser.setStartPage( args[0] );

//        Browser browser = new Browser();
//        browser.setVisible(true);


        Engine engine = new WebEngine();
        Controller controllerGUI = new WebController(engine);
        ViewInterface view = new SwingView(controllerGUI);

        if(args.length>0) controllerGUI.setStartPage(args[0]);

        SwingUtilities.invokeLater(  view::init );

    }
}
