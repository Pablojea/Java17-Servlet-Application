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

            // set content and encoding for http response
            res.setCharacterEncoding("utf-8");
            res.setContentType("text/html");

            //get all possible parameters from the request url
            String param_p = req.getParameter("p") == null ? "01" : req.getParameter("p");            
            String param_auto = req.getParameter("auto") == null ? "false" : req.getParameter("auto");
            String param_phase = req.getParameter("pphase") == null ? "01" : req.getParameter("pphase");        
            String param_lang = req.getParameter("plang") == null ? "es" : req.getParameter("plang");
            String Param_sid = req.getParameter("ppsid") == null ? "0" : req.getParameter("ppsid");

            PrintWriter out = res.getWriter();
            
            // out.println("<html>");
            // out.println("<head>");
            // out.println("<title>Hello World!</title>");
            // out.println("</head>");
            // out.println("<body>");
            // out.println("<h1>Hello World!</h1>");
            // out.println("</body>");
            // out.println("</html>");

            FrontEnd.printLandingPage(out);
        }
        catch (Exception ex) {
            System.out.println("Algo fue mal: " + ex.toString());
        }
    }
}