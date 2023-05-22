package p2;

import java.util.ArrayList;

public class Song {

    private String title;
    private int duration;
    private String genres;
    private String composer;
    private ArrayList<String> mumls;
    private String sid;
    private String album;
    private String lang;

    public Song(){
        
    }

    public Song(String title, int duration, String genres, String composer, ArrayList<String> mumls, String sid, String lang){

        this.title = title;
        this.duration = duration;
        this.genres = genres;
        this.composer = composer;
        this.mumls = mumls;
        this.sid = sid;
        this.lang = lang;
    }

    public Song(String title, String lang, String sid){
        
        this.title = title;
        this.lang = lang;
        this.sid = sid;
    }

    String getTitle(){
        return this.title;
    }

    int getDuration(){
        return this.duration;
    }

    String getGenres(){
        return this.genres;
    }

    String getComposer(){
        return this.composer;
    }

    ArrayList<String> getMumls(){
        return this.mumls;
    }

    String getSid(){
        return this.sid;
    }

    String getLang(){
        return this.lang;
    }

    String getAlbum(){
        return this.album;
    }

    void setAlbum(String album){
        this.album = album;
    }

    void setTitle(String title){
        this.title = title;
    }

    void setDuration(int duration){
        this.duration = duration;
    }

    void setGenres(String genres){
        this.genres = genres;
    }

    void setComposer(String composer){
        this.composer = composer;
    }

    void setMumls(ArrayList<String> mumls){
        this.mumls = mumls;
    }

    void setSid(String sid){
        this.sid = sid;
    }

    void setLang(String lang){
        this.lang = lang;
    }

}