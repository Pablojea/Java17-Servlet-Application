package p2;

import java.util.ArrayList;

public abstract class DataModel {

    static void leerFicheros(){
        
    }


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

        for(int i = 0; i < 5; i++){

            Song newSong1 = new Song(
                "song es" + i,
                300,
                null,
                "Sawano",
                null,
                "es" + i,
                "es"
            );

            Song newSong2 = new Song(
                "song en" +i,
                300,
                null,
                "Sawano",
                null,
                "en" + i,
                "en"
            );

            allSongs.add(newSong1);
            allSongs.add(newSong2);
        }


        ArrayList<Song> langSongs = new ArrayList<>();

        for(Song song: allSongs){

            if(song.getLang().equals(lang)){
                langSongs.add(song);
            }
            
        }

        return langSongs;
    }



    static ArrayList<Album> getQ2Albums(String lang, String sid){

        ArrayList<Album> albums = new ArrayList<>();
        String tmpCompany ="";


        for(int i = 0; i < 10; i++){
            albums.add(new Album("album sony "+i, "spain", "sony records", "buena wea"));
        }

        for(int i = 0; i < 10; i++){
            albums.add(new Album("album jony "+i, "spain", "jony records", "bacano"));
        }

        for(int i = 0; i < 10; i++){
            Album newAlbum =new Album("album honey "+i, "spain", "honey records", "buena wea");
            newAlbum.addSong(new Song("megalocumbia", lang, sid));
            albums.add(newAlbum);
        }


        for(Album album: albums){

            if(album.hasSong(lang, sid)){
                tmpCompany = album.getCompany();
                break;
            }

        }

        ArrayList<Album> songAlbums = new ArrayList<>();

        for(Album album: albums){
            
            if(album.getCompany().equals(tmpCompany)){
                songAlbums.add(album);
            }
        }

        return songAlbums;

    }

}