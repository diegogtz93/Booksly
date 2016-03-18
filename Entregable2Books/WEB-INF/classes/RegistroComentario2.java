import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class RegistroComentario2 extends HttpServlet {
    Connection connection;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Books";
            connection=DriverManager.getConnection(url); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    String strusuario = req.getParameter("usuario");
    String strtitulo = req.getParameter("titulo"); 
	String strpuntuacion = req.getParameter("puntuacion");	
	String strdescripcion = req.getParameter("descripcion");	
  

   
    
		String sql2= "Select Usuario FROM Usuarios";
        String sql = "INSERT INTO Comentarios (Usuario, Titulo, Puntuacion, Opinion) VALUES (";
        sql +=  "'" + strusuario + "'";
        sql +=  ", '" + strtitulo + "'";
        sql +=  ", '" + strpuntuacion + "'";
		sql +=  ", '" + strdescripcion + "')";
        System.out.println("Insert sql: " + sql);
		
		int contador=0;
		try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql2);   
				while(result.next()) {
					String strUsuario = result.getString("Usuario");
					if (strUsuario.equals(""+ strusuario +"")){
						contador ++;
					}	
				} 
			} catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Resulset: " + sql2 + " Exception: " + e);
				
			}
			
			if (contador ==0){		
				resp.setContentType("text/html");
				PrintWriter out = resp.getWriter();
				out.println("<option value=\"0\" selected=\"selected\">El usuario no esta registrado</option>");
				out.println("<BR><a href=\"RegistroUsuario.html\">Registre su usuario</a>");
			}
			else {					
			
		
			
        try{
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            statement.close();
            Statement statementSelect=connection.createStatement();
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Exception in insertBook: " + e);
        }
		resp.setContentType("text/html");
        PrintWriter toClient = resp.getWriter();
		  
        toClient.println("<!DOCTYPE HTML>");
		
        toClient.println("<html>");
		toClient.println("<LINK REL=StyleSheet HREF=Diseño.css TYPE=text/css>");
		
        toClient.println("<head><title>Opiniones</title></head>");
        toClient.println("<body>");
        toClient.println("<h1 ALIGN=\"left\"> <FONT face=\"arial\" COLOR=\"black\" SIZE=\"3\"><a href=\"IntroducirComentari.html\">Click Here</A> Inserte un nuevo comentario </font></h1>");
        toClient.println("<h2 ALIGN=\"center\"> <FONT face=\"arial\" COLOR=\"Green\" SIZE=\"10\"> Se ha registrado tu opinion</Font></h2>");
        toClient.println("<h2 ALIGN=\"center\"> <FONT face=\"arial\" COLOR=\"white\" SIZE=\"8\"><a href=\"MostrarOpinion\"> Todas las opiniones</a></Font></h2>");
        toClient.println("</body>");
        toClient.println("</body>");
        toClient.println("</html>");
        toClient.close();    
	}	
	}
}
