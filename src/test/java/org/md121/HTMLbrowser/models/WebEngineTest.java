package org.md121.HTMLbrowser.models;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

class WebEngineTest {

    String[] links = { "0" , "1" , "2" , "3" , "4" , "5"};
    WebEngine engine ;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        engine = new WebEngine();

    }

    @org.junit.jupiter.api.Test
    void goForward() {
    }

    @org.junit.jupiter.api.Test
    void goBack() {
    }

    @org.junit.jupiter.api.Test
    void go() {
        URL url;

        url = engine.verifyAddress(links[0]);
        engine.go(url);
        assertEquals( 0 , engine.getForward().size() );
        assertEquals( 0 , engine.getBack().size() );
        assertThrows( EmptyStackException.class , () -> engine.getForward().peek() );
        assertThrows( EmptyStackException.class , () -> engine.getBack().peek() );
        System.out.println( engine );

        url = engine.verifyAddress(links[1]);
        engine.go(url);
        assertEquals( 0 , engine.getForward().size() );
        assertEquals( 1 , engine.getBack().size() );
        assertEquals( engine.verifyAddress(links[0]) , engine.getBack().peek() );
        System.out.println( engine );

        engine.goBack();
        assertEquals( 1 , engine.getForward().size() );
        assertEquals( 0 , engine.getBack().size() );
        assertEquals( engine.verifyAddress(links[1]) , engine.getForward().peek() );
        System.out.println( engine );

        url = engine.verifyAddress(links[2]);
        engine.go(url);
        assertEquals( 0 , engine.getForward().size() );
        assertEquals( 1 , engine.getBack().size() );
        assertEquals( engine.verifyAddress(links[0]) , engine.getBack().peek() );
        System.out.println( engine );

        url = engine.verifyAddress(links[3]);
        engine.go(url);
        assertEquals( 0 , engine.getForward().size() );
        assertEquals( 2 , engine.getBack().size() );
        assertEquals( engine.verifyAddress(links[2]) , engine.getBack().peek() );
        System.out.println( engine );

        url = engine.verifyAddress(links[4]);
        engine.go(url);
        assertEquals( 0 , engine.getForward().size() );
        assertEquals( 3 , engine.getBack().size() );
        assertThrows( EmptyStackException.class , () -> engine.getForward().peek() );
        assertEquals( engine.verifyAddress(links[3]) , engine.getBack().peek() );
        System.out.println( engine );

        url = engine.verifyAddress(links[5]);
        engine.go(url);
        assertEquals( 0 , engine.getForward().size() );
        assertEquals( 4 , engine.getBack().size() );
        assertEquals( engine.verifyAddress(links[4]) , engine.getBack().peek() );
        System.out.println( engine );

        engine.goBack();
        assertEquals( 1 , engine.getForward().size() );
        assertEquals( 3 , engine.getBack().size() );
        assertEquals( engine.verifyAddress(links[5]) , engine.getForward().peek() );
        assertEquals( engine.verifyAddress(links[3]) , engine.getBack().peek() );
        System.out.println( engine );

        engine.goForward();
        assertEquals( 0 , engine.getForward().size() );
        assertEquals( 4 , engine.getBack().size() );
        assertEquals( engine.verifyAddress(links[4]) , engine.getBack().peek() );
        System.out.println( engine );

        engine.goBack();
        assertEquals( 1 , engine.getForward().size() );
        assertEquals( 3 , engine.getBack().size() );
        assertEquals( engine.verifyAddress(links[5]) , engine.getForward().peek() );
        assertEquals( engine.verifyAddress(links[3]) , engine.getBack().peek() );
        System.out.println( engine );

        url = engine.verifyAddress(links[3]);
        engine.go(url);
        assertEquals( 0 , engine.getForward().size() );
        assertEquals( 4 , engine.getBack().size() );
        assertEquals( engine.verifyAddress(links[4]) , engine.getBack().peek() );
        System.out.println( engine );

    }

    @org.junit.jupiter.api.Test
    void verifyAddress() {
    }
}