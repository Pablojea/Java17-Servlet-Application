package p2;

import java.io.*;
import java.util.ArrayList;

public abstract class FrontEnd {

    static String style = """
    <style>

        * {

            margin: 0px;
            padding: 0px;

        }

        body {

            margin: 10px;
            padding: 10px;            
            font-family: Arial, Helvetica, sans-serif;         
            background-color: antiquewhite;

        }        

        h1 {

            color: #333;
            padding-bottom: 40px;
            font-size: 40px;

        }

        h2 {

            color: #333;
            padding-bottom: 20px;
            font-size: 30px;

        }

        h3 {

            color: #333;
            padding-bottom: 20px;
            font-size: 25px;

        }

        ol {            
            padding-left: 40px; 
            padding-right: 0px;  
            padding-bottom: 40px;         
        }   

        ol li {
            
            color: #333;            
            font-size: 20px;
            padding-left: 20px;
        }

        ul {

            list-style: none;

        }

        ul li {

            color: #3541ea;            
            font-size: 20px;
            padding-left: 20px;
            padding-bottom: 20px;

        }

        ul li a {

            color: #3541ea;  

        }

        ol li a {

            color: #3541ea;  

        }

        p {

            color: #3541ea; 
            font-size: 20px;

        }

        .resultado li{

            color: green;

        }

        .btn_ini {

            background: #3D94F6;
            background-image: -webkit-linear-gradient(top, #3D94F6, #1E62D0);
            background-image: -moz-linear-gradient(top, #3D94F6, #1E62D0);
            background-image: -ms-linear-gradient(top, #3D94F6, #1E62D0);
            background-image: -o-linear-gradient(top, #3D94F6, #1E62D0);
            background-image: -webkit-gradient(to bottom, #3D94F6, #1E62D0);
            -webkit-border-radius: 20px;
            -moz-border-radius: 20px;
            border-radius: 20px;
            height: 12px;
            margin-left: 40px;
            margin-right: 10px;
            line-height: 12px;
            color: #FFFFFF;
            width: 60px;
            font-size: 20px;
            font-weight: 100;
            padding: 15px;
            -webkit-box-shadow: 1px 1px 20px 0 #000000;
            -moz-box-shadow: 1px 1px 20px 0 #000000;
            box-shadow: 1px 1px 20px 0 #000000;
            text-shadow: 1px 1px 20px #000000;
            border: solid #337FED 1px;
            text-decoration: none;
            display: inline-block;
            cursor: pointer;
            text-align: center;

        }

        .btn_ini:hover {

            border: solid #337FED 1px;
            background: #1E62D0;
            background-image: -webkit-linear-gradient(top, #1E62D0, #3D94F6);
            background-image: -moz-linear-gradient(top, #1E62D0, #3D94F6);
            background-image: -ms-linear-gradient(top, #1E62D0, #3D94F6);
            background-image: -o-linear-gradient(top, #1E62D0, #3D94F6);
            background-image: -webkit-gradient(to bottom, #1E62D0, #3D94F6);
            -webkit-border-radius: 20px;
            -moz-border-radius: 20px;
            border-radius: 20px;
            text-decoration: none;

        }

        .btn_atr {

            background: #FA0606;
            background-image: -webkit-linear-gradient(top, #FA0606, #905B5B);
            background-image: -moz-linear-gradient(top, #FA0606, #905B5B);
            background-image: -ms-linear-gradient(top, #FA0606, #905B5B);
            background-image: -o-linear-gradient(top, #FA0606, #905B5B);
            background-image: -webkit-gradient(to bottom, #FA0606, #905B5B);
            -webkit-border-radius: 20px;
            -moz-border-radius: 20px;
            border-radius: 20px;
            height: 12px;
            line-height: 12px;
            color: #FFFFFF;
            width: 60px;
            font-size: 20px;
            font-weight: 100;
            padding: 15px;
            -webkit-box-shadow: 1px 1px 20px 0 #000000;
            -moz-box-shadow: 1px 1px 20px 0 #000000;
            box-shadow: 1px 1px 20px 0 #000000;
            text-shadow: 1px 1px 20px #000000;
            border: solid #DD380D 1px;
            text-decoration: none;
            display: inline-block;
            cursor: pointer;
            text-align: center;

        }

        .btn_atr:hover {

            border: solid #337FED 1px;
            background: #C32626;
            background-image: -webkit-linear-gradient(top, #C32626, #804343);
            background-image: -moz-linear-gradient(top, #C32626, #804343);
            background-image: -ms-linear-gradient(top, #C32626, #804343);
            background-image: -o-linear-gradient(top, #C32626, #804343);
            background-image: -webkit-gradient(to bottom, #C32626, #804343);
            -webkit-border-radius: 20px;
            -moz-border-radius: 20px;
            border-radius: 20px;
            text-decoration: none;

        }

        footer {
            position: absolute;
            bottom: 0;
        }

        hr {

            padding: 50px 0;
            border: none;

        }

    </style>
    """;

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
            out.println(style);
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servicio de consulta de información musical</h1>");  
            out.println("<h2>Bienvenido a este servicio</h2>"); 
            out.println("<h3>Selecciona una consulta:</h3>");
            out.println("<ul>");
            out.println("<li><a style='text-decoration: none' href='?pphase=21&p="+ p + "&auto=" + auto + "'>Consulta 2: Álbumes de una compañia con canciones en un idioma.</a></li>");
            out.println("<li><a style='text-decoration: none' href='?pphase=02&p="+ p + "&auto=" + auto + "'>Ver los ficheros erroneos</a></li>");
            out.println("</ul>");
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
            out.println(style);
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servicio de consulta de información musical</h1>"); 

            out.println("<h2>Ficheros con warnings:</h2>");

            out.println("<h2>Ficheros con errores:</h2>"); 

            out.println("<h2>Ficheros con errores fatales:</h2>");  

            out.println("<a class='btn_ini' style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Atrás</a>");        
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
            out.println(style);           
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");

            if(noPassword){
                out.println("<h1> FALTA EL PARÁMETRO P </h1>");
            }
            else{
                out.println("<h1> PARÁMETRO P INCORRECTO </h1>");
            }

            out.println("<a class='btn_ini' style='text-decoration: none' href='?pphase=01'> Inicio </a>");
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
            out.println(style);
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servicio de consulta de información musical</h1>");  
            out.println("<h2>Consulta 2: Fase 1</h2>"); 
            out.println("<h3>Selecciona un idioma:</h3>"); 
            out.println("<ol>");

            for(String lang: langs){                      
                out.println("<li><a style='text-decoration: none' href='?pphase=22&p="+ p + "&auto=" + auto + "&plang=" + lang + "'" + ">"+ lang + "</a> </li>");                    
            } 

            out.println("</ol>");    
            out.println("<a class='btn_ini' style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Inicio</a>");
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
            out.println(style);
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servicio de consulta de información musical</h1>");  
            out.println("<h2>Consulta 2: Fase 2</h2>"); 
            out.println("<h3>Selecciona una canción:</h3>"); 
            out.println("<ol>");

            for(Song song: songs){      

                String songInfo =" --- " +"Álbum = null --- Duración = " + song.getDuration() + " --- Géneros = " + song.getGenres();              
                out.println("<li><a style='text-decoration: none' href='?pphase=23&p=" + p + "&auto=" + auto +"&plang=" + lang + "&psid=" + song.getSid() + "'"+">" + song.getTitle() + "</a>" + songInfo + "</li>");                    
            }

            out.println("</ol>");
            out.println("<a class='btn_ini' style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Inicio</a>");
            out.println("<a class='btn_atr' style='text-decoration: none' href='?pphase=21&p=" + p + "&auto=" + auto + "'>Atrás</a>");
            out.println("""
            <footer>
                <hr>
                <p>Author: Pablojea</p>
            </footer>
            """
            );
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
            out.println(style);
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servicio de consulta de información musical</h1>");  
            out.println("<h2>Consulta 2: Fase 3</h2>"); 
            out.println("<h3>Este es el resultado de la consulta:</h3>"); 
            out.println("<ol class='resultado'>");

            for(Album album: albums){      

                String albumInfo =" Álbum = " + album.getName() + " --- País = " + album.getCountry() + " --- Compañía = " + album.getCompany() + " --- Crítica = " + album.getReview() ;              
                out.println("<li>" + albumInfo + "</li>");    

            }

            out.println("</ol>");  
            out.println("<a class='btn_ini' style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Inicio</a>");
            out.println("<a class='btn_atr' style='text-decoration: none' href='?pphase=22&p=" + p + "&auto=" + auto +"&plang=" + lang + "'>Atrás</a>");
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
            out.println(style);
            out.println("<title> Sint500P2 </title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> EMPY ATM </h1>");
            out.println("<p><a class='btn_ini' style='text-decoration: none' href='?pphase=01'> Inicio </a><p>");
            out.println("<p><a class='btn_ini' style='text-decoration: none' href='?pphase=01&p=" + p + "&auto=" + auto + "'>Inicio</a><p>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}