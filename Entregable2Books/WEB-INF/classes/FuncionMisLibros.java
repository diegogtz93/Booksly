import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FuncionMisLibros extends HttpServlet {
    public static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
     
        HttpSession session = req.getSession(true);
        
        Vector<String> vec = (Vector<String>)session.getAttribute("lista");
        String user = (String)session.getAttribute("user");
    
        if (vec == null) { 
            vec = new Vector<String>();
        } 
        String[] valueArray = req.getParameterValues("libro");
        if (valueArray != null) {
            for (int i=0;i<valueArray.length;i++) {
                String value = valueArray[i];
                vec.addElement(value);
            }
        }
        session.setAttribute("lista", vec);

        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        
        toClient.println("<html>");
        toClient.println("<title>My books</title>");
        toClient.println("<body>");
		toClient.println("<BODY BGCOLOR=\"GREY\">");
        toClient.println("<H2><UL><LI>My books: " + (user == null ? session.getId() : user) + "</UL></H2>");
        toClient.println("<UL>");
        
        for (int i= 0; i < vec.size() ; i++ ) {         
            toClient.println("<LI>" + vec.elementAt(i) + "</LI>");
        }
        toClient.println("</UL>");
        toClient.println("<BR><a href=\"intro.html\">Menu</a>");
        toClient.println("</body>");
        toClient.println("</html>");
        toClient.close();
    }
}