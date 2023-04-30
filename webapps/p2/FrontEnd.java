package p2;

import java.io.*;

public class FrontEnd {

    static void printScreen01(PrintWriter out, String p){
        out.println("<!DOCTYPE html>");
        out.println("<html lang='es'>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title> Sint500P2 </title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Screen 01</h1>");
        out.println("<p><a href='?pphase=02&p="+p+"'>Ver los ficheros erroneos</a><p>");
        out.println("<p><a href='?pphase=21&p="+p+"'>Consulta2: álbumes de una compañia con canciones en un idioma.</a><p>");
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
        out.println("<p><a href='?pphase=01'> Inicio </a><p>");
        out.println("</body>");
        out.println("</html>");
    }

}