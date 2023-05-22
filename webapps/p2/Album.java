package p2;

import java.util.ArrayList;

public class Album {

    private String name;
    private String country;
    private String singer;
    private String group;
    private String isbn;
    private String company;
    private String review;
    private ArrayList<Song> songs;
    private String aid;
    private String formats;
    private int year;


    public Album(){        
        
        this.songs = new ArrayList<Song>();

    };

    public Album(String name, String country, String company, String review){

        this.name = name;
        this.country = country;
        this.company = company;
        this.review = review;
        this.songs = new ArrayList<Song>();

    };


    String getName(){
        return this.name;
    }

    String getCountry(){
        return this.country;
    }

    String getSinger(){
        return this.singer;
    }

    String getGroup(){
        return this.group;
    }

    String getIsbn(){
        return this.isbn;
    }

    String getCompany(){
        return this.company;
    }

    String getReview(){
        return this.review;
    }

    ArrayList<Song> getSongs(){
        return this.songs;
    }

    String getAid(){
        return this.aid;
    }

    String getFormats(){
        return this.formats;
    }

    int getYear(){
        return this.year;
    }

    void setName(String name){
        this.name = name;
    }

    void setCountry(String country){
        this.country = country;
    }

    void setSinger(String singer){
        this.singer = singer;
    }

    void setGroup(String group){
        this.group = group;
    }

    void setIsbn(String isbn){
        this.isbn = isbn;
    }

    void setCompany(String company){
        this.company = company;
    }

    void setReview(String review){
        this.review = review;
    }

    void setSongs(ArrayList<Song> songs){
        this.songs = songs;
    }

    void setAid(String aid){
        this.aid = aid;
    }

    void setFormats(String formats){
        this.formats = formats;
    }

    void setYear(int year){
        this.year = year;
    }

    void addSong(Song song){
        songs.add(song);
    }

    boolean hasSong(String sid){

        for(Song song: songs){

            if(sid.equals(song.getSid())){
                return true;
            }
        }

        return false;
    }

    boolean hasSongLang(String lang){

        for(Song song: songs){

            if(lang.equals(song.getLang())){
                return true;
            }
        }

        return false;
    }

    ArrayList<String> getAlbumLangs(){

        ArrayList<String> langs = new ArrayList<String>();

        for(Song song: songs){

            if(!langs.contains(song.getLang()))
            langs.add(song.getLang());

        }

        System.out.println(langs);
        return langs;

    }



}