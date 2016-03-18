import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class MostrarOpinion extends HttpServlet {
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

    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException  {
        
        res.setContentType("text/html");
        PrintWriter toClient = res.getWriter();
        toClient.println("<!DOCTYPE HTML>");
        toClient.println("<html>");
        toClient.println("<head><title>Opiniones</title></head>");
        toClient.println("<body>");
        toClient.println("<a href=\"PaginaInico.html\">Pagina Inicial</A>");
        toClient.println("<h2>Lista de opiniones</h2>");
        toClient.println("<table border=\"1\">");
		toClient.println("<tr>");
		toClient.println("<TH>");
		toClient.println ("Usuario");
		toClient.println ("</TH>");
		toClient.println ("<TH>");
		toClient.println ("Titulo");
		toClient.println ("</TH>");
		toClient.println ("<TH>");
		toClient.println ("Puntuacion");
		toClient.println ("</TH>");
		toClient.println ("<TH>");
		toClient.println ("Opinion");
		toClient.println ("</TH>");
		toClient.println ("</TR>");
        String sql = "SELECT Usuario, Titulo, Puntuacion, Opinion FROM Comentarios";
        System.out.println(sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                toClient.println("<tr>");            
                String usuarioStr = result.getString("Usuario");
                String tituloStr = result.getString("Titulo");
				String puntuacionStr = result.getString("Puntuacion");
				String opinionStr = result.getString("Opinion");
                toClient.println("<td>" + usuarioStr + "</td>");
                toClient.println("<td>" + tituloStr + "</td>");
				toClient.println("<td>" + puntuacionStr + "</td>");
				toClient.println("<td>" + opinionStr + "</td>");
                toClient.println("</tr>");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Resulset: " + sql + " Exception: " + e);
        }
        toClient.println("</table>");
        toClient.println("</body>");
        toClient.println("</html>");
        toClient.close();
    }
}
