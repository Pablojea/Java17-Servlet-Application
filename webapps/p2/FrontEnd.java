package p2;

import java.io.*;
import java.util.ArrayList;

public abstract class FrontEnd {

    static void printScreen01(PrintWriter out, String p, String auto){

        if(auto.equals("true")){

            out.println("""
            <?xml version='1.0' encoding='utf-8'?>
            <service>
                <status>OK</status>
            </service>
            """);

        }
        else{

            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Screen 01</h1>");
            out.println("<p><a style='text-decoration: none' href='?pphase=02&p="+ p + "&auto=" + auto + "'>Ver los ficheros erroneos</a><p>");
            out.println("<p><a style='text-decoration: none' href='?pphase=21&p="+ p + "&auto=" + auto + "'>Consulta2: álbumes de una compañia con canciones en un idioma.</a><p>");
            out.println("</body>");
            out.println("</html>");

        }
    }

    static void printScreen02(PrintWriter out, String p, String auto){

        if(auto.equals("true")){

            if(auto.equals("true")){

                out.println("""
                <?xml version='1.0' encoding='utf-8'?>
                <wrongDocs>
                    <warnings>
                    </warnings>
                    <errors>
                    </errors>
                    <fatalErrors>
                    </fatalErrors>
                </wrongDocs>
                """);
            }

        }
        else{

            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Screen 02</h1>");
            out.println("<p><a style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Atrás</a><p>");        
            out.println("</body>");
            out.println("</html>");
        }
    }

    static void printScreen03(PrintWriter out, String auto,boolean noPassword){

        if(auto.equals("true")){

            if(noPassword){

                out.println("""
                <?xml version='1.0' encoding='utf-8'?>
                <wrongRequest>no passwd</wrongRequest>
                """);
            }
            else{

                out.println("""
                <?xml version='1.0' encoding='utf-8'?>
                <wrongRequest>bad passwd</wrongRequest>
                """);
            }

            // falta comprobar los el resto de parámetros

        }
        else{

            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");       
            out.println("<html>");
            out.println("<head>");
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");

            if(noPassword){
                out.println("<h1> FALTA EL PARÁMETRO P </h1>");
            }
            else{
                out.println("<h1> PARÁMETRO P INCORRECTO </h1>");
            }

            out.println("<p><a style='text-decoration: none' href='?pphase=01'> Inicio </a><p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    static void printScreen21(PrintWriter out, String p, String auto, ArrayList<String> langs){

        if(auto.equals("true")){

            out.println("""
            <?xml version='1.0' encoding='utf-8'?>
            <langs>
            """);

            for(String lang: langs){                      
                out.println("<lang>" + lang + "</lang>");                    
            } 

            out.println("</langs>");

        }
        else{

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
                out.println("<li><a style='text-decoration: none' href='?pphase=22&p="+ p + "&auto=" + auto + "&plang=" + lang + "'" + ">"+ lang + "</a> </li>");                    
            } 

            out.println("</ul>");    
            out.println("<p><a style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Inicio</a><p>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    static void printScreen22(PrintWriter out, String p, String auto, String lang, ArrayList<Song> songs){

        if(auto.equals("true")){

            out.println("""
            <?xml version='1.0' encoding='utf-8'?>
            <songs>""");

            for(Song song: songs){                      
                out.println("<song album='null' duration='null' genres='null'>" + song.getTitle() + "</song>");                    
            } 

            out.println("</songs>");

        }
        else{
        
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
                out.println("<li><a style='text-decoration: none' href='?pphase=23&p=" + p + "&auto=" + auto +"&plang=" + lang + "&psid=" + song.getSid() + "'"+">"+ "  " + song.getTitle() + "</a>" + songInfo+ "</li>");                    
            }

            out.println("</ol>");
            out.println("<p><a style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Inicio</a><p>");
            out.println("<p><a style='text-decoration: none' href='?pphase=21&p=" + p + "&auto=" + auto + "'>Atrás</a></p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    static void printScreen23(PrintWriter out, String p, String auto, String lang, String sid, ArrayList<Album> albums){

        if(auto.equals("true")){

            out.println("""
            <?xml version='1.0' encoding='utf-8'?>
            <albums>
            """);

            for(Album album: albums){  

                out.println("<album acountry='null' company='null' review='null'>" + album.getName() + "</album>");                    
            } 

            out.println("</albums>");

        }
        else{

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
            out.println("<p><a style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Inicio</a><p>");
            out.println("<p><a style='text-decoration: none' href='?pphase=22&p=" + p + "&auto=" + auto +"&plang=" + lang + "'>Atrás</a><p>");
            out.println("</body>");
            out.println("</html>");
        }
    }
 
    static void printEmpty(PrintWriter out, String p, String auto){

        if(auto.equals("true")){

        }
        else{
        
            out.println("<!DOCTYPE html>");
            out.println("<html lang='es'>");       
            out.println("<html>");
            out.println("<head>");
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> EMPY ATM </h1>");
            out.println("<p><a style='text-decoration: none' href='?pphase=01'> Inicio </a><p>");
            out.println("<p><a style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Inicio</a><p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}