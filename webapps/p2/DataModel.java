package p2;

import java.util.ArrayList;

public abstract class DataModel {


    // ahora mismo se crean manualmente varios idiomas para poder mostrar algo en la primera pantalla
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

    // en este momento see crean algunas canciones falsas para algunos de los idiomas creados
    // de momento sólo contienen título, idioma, y sid, para poder continuar a la última pantalla
    static ArrayList<Song> getQ2Songs(String lang){
        
        ArrayList<Song> allSongs = new ArrayList<>();
        String auxLang = "";        

        for(int i = 0; i < 9; i++){

            if(i % 2 ==0){
                auxLang = "es";

            }else{
                auxLang = "it";
            }
            
            allSongs.add(new Song("song" + i, auxLang, "1"));
        }

        for(int i = 0; i < 9; i++){
            if(i % 2 ==0){
                auxLang = "de";

            }else{
                auxLang = "gl";
            }

            allSongs.add(new Song("song" + i, auxLang, "2"));
        }

        ArrayList<Song> langSongs = new ArrayList<>();

        for(Song song: allSongs){

            if(song.getLang().equals(lang)){
                langSongs.add(song);
            }
            
        }

        return langSongs;
    }



    // ArrayList<Album> getQ2Albums(String lang, String sid){

    // }

}