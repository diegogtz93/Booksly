import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

@SuppressWarnings("serial")
public class MostrarLibros extends HttpServlet {
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
        toClient.println("<head><title>Booksly</title></head>");
        toClient.println("<body background =Imagenes/fondo.jpg>");
        toClient.println("<h2>Lista de libros leidos</h2>");
  
        toClient.println("<table border='1' background= white >");
        
        String sql = "Select ID, TituloLibro, Autor, Editorial,Genero, Descripcion FROM LibrosNuevos";
        String ID = req.getParameter("ID");
		toClient.println("<tr>");
		toClient.println("<TH>");
		toClient.println ("Identificador");
		toClient.println ("</TH>");
		toClient.println ("<TH>");
		toClient.println ("Titulo");
		toClient.println ("</TH>");
		toClient.println ("<TH>");
		toClient.println ("Autor");
		toClient.println ("</TH>");
		toClient.println ("<TH>");
		toClient.println ("Editorial");
		toClient.println ("</TH>");
		toClient.println ("<TH>");
		toClient.println ("Genero");
		toClient.println ("</TH>");
		toClient.println ("<TH>");
		toClient.println ("Descripcion");
		toClient.println ("</TH>");
		toClient.println ("</TR>");
			try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next()) {
                toClient.println("<tr>");
				toClient.println("<td>" + result.getString("ID") + "</td>");
                toClient.println("<td>" + result.getString("TituloLibro") + "</td>");
                toClient.println("<td>" + result.getString("Autor") + "</td>");
				toClient.println("<td>" + result.getString("Editorial") + "</td>");
				toClient.println("<td>" + result.getString("Genero") + "</td>");
				toClient.println("<td>" + result.getString("Descripcion") + "</td>");
				toClient.println("<td><a href=\"EditarLibro.html\">Editar</a> </td>");
                toClient.println("</tr>");
            }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Resulset: " + sql + " Exception: " + e);
        }
        toClient.println("</table>");
		toClient.println("<a href=\"PaginaInico.html\">Ir al menu</a>");
		toClient.println("</body>");
        toClient.println("</html>");
        toClient.close();

  }

  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  } 
}