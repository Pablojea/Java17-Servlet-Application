package p2;

import java.util.ArrayList;

public class Song {

    private String title;
    private int duration;
    private ArrayList<String> genres;
    private String composer;
    private ArrayList<String> mumls;
    private String sid;
    private String lang;

    public Song(String title, int duration, ArrayList<String> genres, String composer, ArrayList<String> mumls, String sid, String lang){

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

    ArrayList<String> getGenres(){
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

    void setTitle(String title){
        this.title = title;
    }

    void setDuration(int duration){
        this.duration = duration;
    }

    void setGenres(ArrayList<String> genres){
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