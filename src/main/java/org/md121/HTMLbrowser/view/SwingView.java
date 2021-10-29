package org.md121.HTMLbrowser.view;

import org.md121.HTMLbrowser.controllers.Controller;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Document;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLFrameHyperlinkEvent;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;

public class SwingView extends JFrame implements ViewInterface, HyperlinkListener {

    private final Controller controller;

    private final JTextField locationTextField = new JTextField(65);
    private final JEditorPane webView = new JEditorPane();
    private double zoom = 1.0;


    public SwingView(Controller controller){
        this.controller = controller;
    }

    @Override
    public void init() {
        setSize(1280, 720);
//        setSize(1280, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel buttonPanel = new JPanel();
        setIconImage(Picture.ICON.getImageIcon().getImage());

        MenuButton back     = new MenuButton(Picture.BACK);
        MenuButton forward  = new MenuButton(Picture.FORWARD);
        MenuButton zoomIn   = new MenuButton(Picture.ZOOMIN);
        MenuButton zoomOut  = new MenuButton(Picture.ZOOMOUT);
        MenuButton refresh  = new MenuButton(Picture.REFRESH);
        MenuButton goButton = new MenuButton(Picture.GO);

        back.addActionListener(e -> back());
        forward.addActionListener(e->forward());
        refresh.addActionListener(e->refresh());
        zoomIn.addActionListener(e->zoomIn());
        zoomOut.addActionListener(e->zoomOut());
        buttonPanel.add(back);
        buttonPanel.add(forward);

        locationTextField.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    go();
                }
            }
        });
        buttonPanel.add(locationTextField);
        locationTextField.setPreferredSize(new Dimension(600,30));
        locationTextField.setText(controller.getStartPage());
        locationTextField.setFont( new Font( null, Font.PLAIN  , 14 ));


        goButton.addActionListener(e -> go());
        buttonPanel.add(goButton);

        webView.setContentType("text/html");
        webView.setEditable(false);
        webView.addHyperlinkListener(this);
        webView.setEditorKit(new LargeHTMLEditorKit());
//        webView.getDocument().putProperty("ZOOM_FACTOR", new Double(1.5));

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(buttonPanel, BorderLayout.NORTH);
        getContentPane().add(new JScrollPane(webView), BorderLayout.CENTER);

        go();


        buttonPanel.add(refresh);
        buttonPanel.add(zoomIn);
        buttonPanel.add(zoomOut);
        setVisible(true);
    }

    @Override
    public void refresh() {
//        controller.refresh();
//        showPage( controller.getCurrentUrl() );
//        setTextField(controller.getCurrentUrl());
//        webView.repaint();


        try {
            URL url = new URL(controller.getCurrentUrl().toString());
            Document doc = webView.getDocument();
            doc.putProperty(Document.StreamDescriptionProperty, null);
            webView.setPage(url);

        } catch (IOException e) {
            e.printStackTrace();
        }

//        System.out.println("refresh");
    }

    @Override
    public void zoomIn() {
        zoom += 0.25;
        webView.getDocument().putProperty("ZOOM_FACTOR" , zoom);
        webView.repaint();
    }

    @Override
    public void zoomOut() {
        zoom -= 0.25;
        webView.getDocument().putProperty("ZOOM_FACTOR" , zoom);
        webView.repaint();
    }


    @Override
    public void hyperlinkUpdate(HyperlinkEvent event) {
        HyperlinkEvent.EventType eventType = event.getEventType();
        if (eventType == HyperlinkEvent.EventType.ACTIVATED) {
            if (event instanceof HTMLFrameHyperlinkEvent) {
                HTMLFrameHyperlinkEvent linkEvent = (HTMLFrameHyperlinkEvent) event;
                HTMLDocument document = (HTMLDocument) webView.getDocument();
                document.processHTMLFrameHyperlinkEvent(linkEvent);
                controller.go(String.valueOf(linkEvent.getURL()));
            } else {
                URL page =  event.getURL();
                controller.go( page.toString() );
                showPage(event.getURL());

            }
        }
    }


    private void showPage(URL pageUrl) {
        try {
//            System.out.println( "showPage : " + pageUrl);
//            System.out.println( "--------------------------------");
//            System.out.println();

            webView.setPage(pageUrl);
        } catch (Exception e) {
            System.out.println("Unable to load page");
            e.printStackTrace();
        }
    }

    private void go() {
        URL page = controller.go(locationTextField.getText());
        showPage( page );
        setTextField(page);
    }

    private void back() {
        URL page = controller.back();
        showPage( page );
        locationTextField.setText( page.toString().replaceFirst("file:/" , "") );
    }

    private void forward() {
        URL page = controller.forward();
        showPage( page );
        locationTextField.setText( page.toString().replaceFirst("file:/" , "") );
    }

    private void setTextField( URL url ){
        locationTextField.setText( url.toString().replaceFirst("file:/" , "") );
    }

//    public URL getImagePath() {
//        return ClassLoader.getSystemResource(imagePath);
//    }


}
