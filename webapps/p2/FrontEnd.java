package p2;

import java.io.*;
import java.util.ArrayList;

public abstract class FrontEnd {

    static void printScreen01(PrintWriter out, String p, String auto){

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Sint500P2 </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Screen 01</h1>");
        out.println("<p><a style='text-decoration: none' href='?pphase=02&p="+p+"'>Ver los ficheros erroneos</a><p>");
        out.println("<p><a style='text-decoration: none' href='?pphase=21&p="+p+"'>Consulta2: álbumes de una compañia con canciones en un idioma.</a><p>");
        out.println("</body>");
        out.println("</html>");
    }

    static void printScreen02(PrintWriter out, String p, String auto){

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Sint500P2 </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Screen 02</h1>");
        out.println("<p><a style='text-decoration: none' href='?pphase=01&p="+p+"'>Atrás</a><p>");        
        out.println("</body>");
        out.println("</html>");
    }

    static void printScreen21(PrintWriter out, String p, String auto, ArrayList<String> langs){

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Sint500P2 </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Screen 21</h1>");        

        out.println("<ul style='list-style-type:none'>");

        for(String lang: langs){                      
            out.println("<li><a style='text-decoration: none' href='?pphase=22&p="+p+"&plang="+lang+"'"+">"+ lang + "</a> </li>");                    
        } 

        out.println("</ul>");     

        out.println("<p><a style='text-decoration: none' href='?pphase=22&p="+p+"'>Siguiente</a><p>");
        out.println("<p><a style='text-decoration: none' href='?pphase=01&p="+p+"'>Atrás</a><p>");
        out.println("</body>");
        out.println("</html>");
    } 

    static void printScreen22(PrintWriter out, String p, String auto, String lang, ArrayList<Song> songs){

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Sint500P2 </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Screen 22</h1>");

        out.println("<ol>");

        for(Song song: songs){      

            String songInfo =" --- " +"Álbum = null --- Duración = " + song.getDuration() + " --- Géneros = " + song.getGenres();              
            out.println("<li><a style='text-decoration: none' href='?pphase=23&p="+p+"&plang="+lang+  "&psid=" + song.getSid() + "'"+">"+ "  " + song.getTitle() + "</a>" + songInfo+ "</li>");                    
        }

        out.println("</ol>");

        out.println("<p><a style='text-decoration: none' href='?pphase=23&p="+p+"'>Siguiente</a></p>");
        out.println("<p><a style='text-decoration: none' href='?pphase=21&p="+p+"'>Atrás</a></p>");
        out.println("</body>");
        out.println("</html>");
    }

    static void printScreen23(PrintWriter out, String p, String auto, String lang, String sid, ArrayList<Album> albums){

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Sint500P2 </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Screen 23</h1>");  

        out.println("<ol>");

        for(Album album: albums){      

            String albumInfo =" Álbum = " + album.getName() + " --- País = " + album.getCountry() + " --- Compañía = " + album.getCompany() + " --- Crítica = " + album.getReview() ;              
            out.println("<li>" + albumInfo + "</li>");    

        }

        out.println("</ol>");       

        out.println("<p><a style='text-decoration: none' href='?pphase=22&p="+p+ "&plang=" + lang + "'>Atrás</a><p>");
        out.println("</body>");
        out.println("</html>");
    }

    static void printEmpty(PrintWriter out){

        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");       
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Sint500P2 </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1> EMPY ATM </h1>");
        out.println("<p><a style='text-decoration: none' href='?pphase=01'> Inicio </a><p>");
        out.println("</body>");
        out.println("</html>");
    }

}