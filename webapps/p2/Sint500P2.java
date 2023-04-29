package p2;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class Sint500P2 extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res){

        try {

            this.log("se imprime en tomcat/logs/localhost.fechadehoy.log");
            System.out.println("se imprime en tomcat/logs/catalina.out");
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            String user = req.getParameter("user");

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Hello World!</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Hello " + user + " World!</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        catch (Exception ex) {
            System.out.println("Algo fue mal: " + ex.toString());
        }
    }
}