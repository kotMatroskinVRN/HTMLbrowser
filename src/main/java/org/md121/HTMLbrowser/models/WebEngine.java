package org.md121.HTMLbrowser.models;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;

public class WebEngine implements Engine{

    private final Stack<URL> forward = new Stack<>();
    private final Stack<URL> back    = new Stack<>();

    private URL previous;

    private boolean firstRound = true;

    public Stack<URL> getForward() {
        return forward;
    }

    public Stack<URL> getBack() {
        return back;
    }

    public URL getPrevious() {
        return previous;
    }

    public boolean isFirstRound() {
        return firstRound;
    }

    @Override
    public URL goForward(){
        try {
            URL result = forward.pop();
            back.push(previous);
            previous = result;
            return result;
        }catch (EmptyStackException e){
//            printOperation("FORWARD EXCEPTION");
            return previous;
        }
    }

    @Override
    public URL goBack(){
        try {
            URL result = back.pop();
            forward.push(previous);
            previous = result;
            return result;
        }catch (EmptyStackException e){
//            printOperation("BACK EXCEPTION");
            return previous;
        }
    }

    @Override
    public void go(URL current) {
        if(firstRound) {
            firstRound = false;
        }
        else {
            back.push(previous);
        }

        forward.clear();
        previous = current;

    }




    @Override
    public URL verifyAddress(String address) {

        File file = new File(address);

        if( file.isDirectory()) return loadDirectory(file);

        if (!address.toLowerCase().startsWith("http://")) {
            String result ;
            String userDir = System.getProperty("user.dir");
            String[] addresses = address.split("file:");
            if(addresses.length>0) address = addresses[addresses.length -1];
            result = "file:///" + address;
            if (System.getProperty("os.name").startsWith("Linux") && !address.startsWith("/")) {
                result = "file:///"+ userDir + "/" + address;
            }
            if (System.getProperty("os.name").startsWith("Win") &&
                    !Pattern.compile("^[A-Z]*:").matcher(address).find()) {
                result = "file:///"+ userDir + "/" + address;
            }

//            System.out.println("verifyUrl : " + result);

            try {
                return new URL(result);
            } catch (Exception e) {
                return null;
            }

        }
        return null;
    }

    private URL loadDirectory(File file){

        try {
            List<File> files;
            files = Arrays.asList(Objects.requireNonNull(file.listFiles(f -> f.getName().endsWith("html"))));

//            System.out.println();
//            System.out.println(files);
//            System.out.println();

            if(files.size()>0) {
                String index = "index.html";
                File indexFile = new File(index);
                if (!files.contains(indexFile)) {
                    indexFile = files.stream().min(File::compareTo).get();
                }

                return  indexFile.toURI().toURL();

            }

        } catch ( MalformedURLException  e) {
            e.printStackTrace();
            return null;
            //TODO 404
        }

        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        result.append("\n").append("Back").append("\n");
        back.forEach(e -> result.append(e).append("\n"));

        result.append("\n").append("Forward").append("\n");
        forward.forEach(e -> result.append(e).append("\n"));

        result.append("\n");

        result.append("\n").append("Previous").append("\n");
        result.append(previous).append("\n");

        result.append("\n");

        return result.toString();
    }

    private void printOperation(String operation){
        System.out.println("Opreration : " + operation);
        System.out.println(this);
    }
}
