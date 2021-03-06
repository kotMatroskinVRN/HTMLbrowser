package org.md121.HTMLbrowser.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public enum Picture {
    BACK("buttons/back.png"){},
    FORWARD("buttons/forward.png"){},
    GO("buttons/play.png"){},
    REFRESH("buttons/square_refresh.png"){},
    ZOOMIN("buttons/round_zoomIn.png"){},
    ZOOMOUT("buttons/round_zoomOut.png"){},
    ICON("iconXFAB.png")
    ;

    ImageIcon imageIcon ;

    Picture(String resourceImage){
        this.imageIcon = setImage(resourceImage);

    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    private ImageIcon setImage(String resourceName){
        InputStream is = ClassLoader.getSystemResourceAsStream(resourceName);

        try {
            Image image = ImageIO.read(is);
            return  new ImageIcon(image) ;
        } catch (IOException e) {
            e.printStackTrace();
            return  null ;
        }




    }

}
