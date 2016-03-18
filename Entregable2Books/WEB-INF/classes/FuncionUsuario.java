import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FuncionUsuario extends HttpServlet {
    public static final long serialVersionUID = 1L;
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {   
        HttpSession session = req.getSession(true);       
        
		String user = req.getParameter("user");
		String Apell1 = req.getParameter("Apell1");
		String Apell2 = req.getParameter("Apell2");
		String Domicilio = req.getParameter("Domicilio");
		String CP = req.getParameter("CP");
		String tel = req.getParameter("tel");
		String mail= req.getParameter("mail");
        session.setAttribute("Nombre", user);
		session.setAttribute("Apell1", Apell1);
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter(); 
			toClient.println("<html>");
			toClient.println("<title>User register</title>");
			toClient.println("<body>");
			toClient.println("<BODY background =Imagenes/fondo.jpg>");
			toClient.println("<H2>Bienvenido " + user + "  <U>Id of the session:</U> " + session.getId() + "</H2>");
			
			toClient.println("</body>");
			toClient.println("<BR><a href=\"PaginaInico.html\">Ir al menu</a>");
			toClient.println("</html>");
			toClient.close();
    }
}