import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class EditarLibro extends HttpServlet {
     
  Connection connection;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Books";
            connection=DriverManager.getConnection(url); 
        } catch(Exception e) {
            System.out.println("Problem creating connection");
            e.printStackTrace();
        }
    }

    public void destroy () {
        super.destroy();
        System.out.print("Closing connection ...");
        try {
            connection.close();
            System.out.println("Connection closed");
        } catch(SQLException ex){
            System.out.println("Problem closing connection");
            System.out.println(ex.getMessage());
        }
    }

 
	public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String strID = req.getParameter("ID");
		int codInt = Integer.parseInt(strID);
		
		String strTituloLibro = req.getParameter("TituloLibro");
		String strAutor = req.getParameter("Autor");
		String strEditorial = req.getParameter("Editorial");
		String strGenero = req.getParameter("Genero");
		String strDescripcion = req.getParameter("Descripcion");
		Statement stmt= null;
		String url="jdbc:odbc:Books";
		try {
			Connection connection=DriverManager.getConnection(url);
            stmt=connection.createStatement();
			
			
            
			String sql= "UPDATE LibrosNuevos SET TituloLibro='" + strTituloLibro + "' , Autor='" + strAutor + "' , Genero='" + strGenero + "' ,Editorial='" + strEditorial + "' , Descripcion='" + strDescripcion + "' WHERE ID = " + codInt;
            System.out.println(sql);
            stmt.executeUpdate(sql);
			System.out.println("hace el sql");
			connection.close();
		} catch(SQLException sql){
					System.out.println(sql.getMessage());
					return;
		} finally {
				if(stmt!=null) {
					try{
						stmt.close();
					} catch(SQLException e) {
						System.out.println("Error closing Statement");
						System.out.println(e.getMessage());
						return;
					}
				}
		}

		
	
		devolverPaginaHTML(resp, strTituloLibro, strAutor, strEditorial, strGenero, strDescripcion);
	} 
    
	public void devolverPaginaHTML(HttpServletResponse resp,
		String TituloLibro, String Autor, String Editorial, String Genero, String Descripcion) {
		resp.setContentType("text/html");

		PrintWriter out = null;
		try {
			out=resp.getWriter();
		} catch (IOException io) {
		System.out.println("Se ha producido una excepcion");    
		}
        
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Nuevos datos:</TITLE>");
		out.println("</HEAD>");
		out.println("<BODY background =Imagenes/fondo.jpg>");
		out.println("<B><FONT size=+4>Ha introducido los siguientes cambios: </FONT></B>");
		out.println("<P><FONT size=+2><B>Titulo: </B>" + TituloLibro + "</FONT>");
		out.println("<P><FONT size=+2><B>Autor: </B>" + Autor + "</FONT>");
		out.println("<P><FONT size=+2><B>Editorial: </B>" + Editorial + "</FONT>");
		out.println("<P><FONT size=+2><B>Genero: </B>" + Genero + "</FONT>");
		out.println("<P><FONT size=+2><B>Descripcion: </B>" + Descripcion + "</FONT>");

		out.println("</BODY>");
		out.println("<BR><a href=\"MostrarLibros\">Ir al menu</a>");

		out.println("</HTML>");

   
		out.flush();
		out.close();
	} 
    
}
