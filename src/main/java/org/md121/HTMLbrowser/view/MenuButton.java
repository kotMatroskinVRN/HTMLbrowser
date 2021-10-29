package org.md121.HTMLbrowser.view;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    private int height = 25;

    MenuButton(Picture buttonImage){
        super(buttonImage.getImageIcon());
        setPreferredSize(new Dimension(height,height));

        setIcon(resizeIcon((ImageIcon) getIcon(), height , height ));

        setBorderPainted(true);
        setOpaque(false);
        setContentAreaFilled(false);

    }

    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }


}
