package p2;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.ArrayList;

// this.log("se imprime en tomcat/logs/localhost.fechadehoy.log");

public class Sint500P2 extends HttpServlet {

    String password;
    DataModel dm;

    // este método se ejecuta una única vez al arrancar el tomcat
    public void init(){        

        try{

            password = "1234";
            String rutaServlet = getServletContext().getRealPath("/");
            String urlInicial = "http://alberto.gil.webs.uvigo.es/SINT/22-23/muml2001.xml";  
            dm = new DataModel(rutaServlet, urlInicial);             
            dm.leerFicheros();

        }
        catch(Exception e){

            System.out.println("Error: " + e.getMessage());
            e.printStackTrace(System.out);
        }

        // esta linea sólo sirve para separar visiblemente las distintas ejecuciones en catalina.out
        System.out.println("-".repeat(100));

    }

    //este método se llama cada vez que hay una solicitud al servlet (se invoca una pantalla)
    public void doGet(HttpServletRequest req, HttpServletResponse res){

        try {

            System.out.println("Log line: Peticion HTTP con método GET.");           

            //get all possible parameters from the request url
            String param_p = req.getParameter("p") == null ? null : req.getParameter("p");            
            String param_auto = req.getParameter("auto") == null ? "false" : req.getParameter("auto");
            String param_phase = req.getParameter("pphase") == null ? "null" : req.getParameter("pphase");        
            String param_lang = req.getParameter("plang") == null ? "null" : req.getParameter("plang");
            String param_sid = req.getParameter("psid") == null ? "null" : req.getParameter("psid");


            // set content and encoding for http response            
            if(param_auto.equals("false")){ 
                res.setCharacterEncoding("utf-8");    
                res.setContentType("text/html");
            }

            // este bloque sólo sirve para debugear el programa logeando las requests a catalina.out
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
            

            //validación de la contraseña
            if(param_p == null){          

                FrontEnd.printScreen03(out, param_auto, true);
            }
            else if(!param_p.equals(password)){

                FrontEnd.printScreen03(out, param_auto, false);
            }
            else{
                //en función del parámetro phase se mostrará una pantalla u otra
                switch(param_phase) {
                    case "01", "null" -> FrontEnd.printScreen01(out, param_p, param_auto);
                    case "02" -> FrontEnd.printScreen02(out, param_p, param_auto);
                    case "21" -> FrontEnd.printScreen21(out, param_p, param_auto, DataModel.getQ2Langs());
                    case "22" -> FrontEnd.printScreen22(out, param_p, param_auto, param_lang, DataModel.getQ2Songs(param_lang));
                    case "23" -> FrontEnd.printScreen23(out, param_p, param_auto, param_lang, param_sid, DataModel.getQ2Albums(param_lang, param_sid));
                    default -> FrontEnd.printEmpty(out, param_p, param_auto);
                }
            }            

        }
        catch (Exception ex) {
            System.out.println("Algo fue mal: " + ex.toString());
        }
    }
}