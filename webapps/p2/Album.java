package p2;

import java.util.ArrayList;

public class Album {

    private String name;
    private String country;
    private String singer;
    private String group;
    private String isbn;
    private String company;
    private ArrayList<Song> songs;
    private String aid;
    private ArrayList<String> formats;
    private int year;

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

    ArrayList<Song> getSongs(){
        return this.songs;
    }

    String getAid(){
        return this.aid;
    }

    ArrayList<String> getFormats(){
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

    void setSongs(ArrayList<Song> songs){
        this.songs = songs;
    }

    void getAid(String aid){
        this.aid = aid;
    }

    void setFormats(ArrayList<String> formats){
        this.formats = formats;
    }

    void getYear(int year){
        this.year = year;
    }


}