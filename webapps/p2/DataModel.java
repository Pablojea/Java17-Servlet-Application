package p2;

import java.util.ArrayList;

public abstract class DataModel {

    static ArrayList<String> getQ2Langs(){

        ArrayList<String> langs = new ArrayList<>();
        langs.add("es");
        langs.add("en");
        langs.add("fr");
        langs.add("it");
        langs.add("de");
        langs.add("gl");

        return langs;

    }

    // ArrayList<Song> getQ2Song(String lang){

    // }

    // ArrayList<Album> getQ2Albums(String lang, String sid){

    // }

}