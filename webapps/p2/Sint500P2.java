package p2;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Sint500P2 extends HttpServlet {

    public void init(){

        //this.log("se imprime en tomcat/logs/localhost.fechadehoy.log");
        //System.out.println("se imprime en tomcat/logs/catalina.out");

        System.out.println("-".repeat(500));

    }

    public void doGet(HttpServletRequest req, HttpServletResponse res){

        try {

            System.out.println("Log line: Peticion HTTP con método GET.");

            // set content and encoding for http response
            res.setCharacterEncoding("utf-8");
            res.setContentType("text/html");

            //get all possible parameters from the request url
            String param_p = req.getParameter("p") == null ? "01" : req.getParameter("p");            
            String param_auto = req.getParameter("auto") == null ? "false" : req.getParameter("auto");
            String param_phase = req.getParameter("pphase") == null ? "01" : req.getParameter("pphase");        
            String param_lang = req.getParameter("plang") == null ? "es" : req.getParameter("plang");
            String param_sid = req.getParameter("ppsid") == null ? "0" : req.getParameter("ppsid");


            // este bloque sólo sirve para debugear el programa logeando las reqests a catalina.out
            String logString = """

            Log line: Lista de parámetros de la petición ->
            param_p: %s,
            param_auto: %s,
            param_phase: %s,
            param_lang = %s,
            param_sid: %s

            """;

            System.out.println(String.format(logString, param_p, param_auto, param_phase, param_lang, param_sid));
            // fin del bloque de debug

            PrintWriter out = res.getWriter();
            
            switch(param_phase) {
                case "01", "null" -> FrontEnd.printScreen01(out,param_p, param_auto);
                case "02" -> FrontEnd.printScreen02(out,param_p, param_auto);
                case "21" -> FrontEnd.printScreen21(out,param_p, param_auto);
                case "22" -> FrontEnd.printScreen22(out,param_p, param_auto);
                case "23" -> FrontEnd.printScreen23(out,param_p, param_auto);
                default -> FrontEnd.printEmpty(out);
            }

            
        }
        catch (Exception ex) {
            System.out.println("Algo fue mal: " + ex.toString());
        }
    }
}