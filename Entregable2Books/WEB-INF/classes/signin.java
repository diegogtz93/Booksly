import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class signin extends HttpServlet {
    Connection connection;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Booksly";
            connection=DriverManager.getConnection(url); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
	
        if(req.getParameter("user")=="" || req.getParameter("name")=="" || req.getParameter("appel1")=="" || req.getParameter("apell2")=="" || req.getParameter("domicilio")=="" || req.getParameter("CP")=="" || req.getParameter("tel")=="" || req.getParameter("pass")=="" || req.getParameter("c_pass")=="" || req.getParameter("mail")=="" || req.getParameter("c_mail")=="") {
			PrintWriter out = null;
			out=res.getWriter();
			out.println("<html>");
				out.println("<head>");
					
					out.println("<title>LOG IN</title>");
					
				out.println("</head>");
				out.println("<body>");
				out.println("<form>");
					out.println("<br><br><br><br><br><br><br><br><br><br><br><br>");
					
						out.println("<h1><IMG SRC=\"Imagenes/Books.jpg\"></h1><br>");
							out.println("<form method=\"get\" action=\"login\">");
							out.println("<input type=\"text\" name=\"user\" Placeholder=\"NombreUsuario\">");
							out.println("<input type=\"password\" name=\"passw\" Placeholder=\"Password\">");
							out.println("<CENTER><h1><FONT COLOR=\"BLACK\">Error in register</font></h1></CENTER>");
							out.println("<input type=\"submit\" value=\"Enviar\">");
							out.println("</form>");
			
							out.println("<a href=\"Registro.html\">Registro</a> ");
							out.println("/ ");
						out.println("<a href=\"forgetpassword.html\">Olvidaste el password?</a> ");
						out.println("</div>");
					out.println("</div>");
				 
				out.println("</body>");
			out.println("</html>");
			out.flush();
			out.close();
        }
		else {
        int tipo = 1;
        
        String sql = "INSERT INTO Usuarios (Usuario, Nombre, PrimerApellido, SegundoApellido, Domicilio, CodigoPostal, Telefono, Passw, Passe, Email, Emaile, tipo1) VALUES (";	           
		
		sql+= "'" + req.getParameter("user") + "', '" + req.getParameter("name") + "', '" + req.getParameter("apell1") + "', '" + req.getParameter("apell2") + "', '" + req.getParameter("domicilio") + "', '" + req.getParameter("CP") + "', '" + req.getParameter("tel") + "', '" + req.getParameter("pass") + "', '" + req.getParameter("c_pass") + "', '" + req.getParameter("mail") + "', '" + req.getParameter("c_mail") + "','" + tipo + "' )";
		
        System.out.println("Insert sql: " + sql);
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Resulset: " + sql + " Exception: " + e);
        }

        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.println("<!DOCTYPE HTML>");
        toClient.println("<html>");
		
        toClient.println("<head>");
		toClient.println("<title>User Added</title>");
		
		
		
		toClient.println("</head>");
		toClient.println("<body>");
		
		
		toClient.println("<P><FONT size=+2><B>Bienvenido: </B>");
		
        toClient.println(req.getParameter("user"));
		toClient.println("<BR>");
		toClient.println("<BR>");
	
        toClient.println("</body>");
		toClient.println("<a href=\"PaginaInico.html\"><img src=\"Imagenes/Books.jpg\"></a>");
        toClient.println("</html>");
        toClient.close();
		}
	}
}
