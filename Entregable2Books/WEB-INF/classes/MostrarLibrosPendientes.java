import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MostrarLibrosPendientes extends HttpServlet {
 
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("Iniciando MostrarLibros...");
  }
        
  public void destroy() {
    System.out.println("No hay nada que hacer...");
  }
  
  
  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    devolverPaginaHTML(resp);
  }
    
  public void devolverPaginaHTML(HttpServletResponse resp) 
      throws IOException {
    resp.setContentType("text/html");
    PrintWriter out = null;
    try {
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("Se ha producido una excepcion");    
    }
        
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Valores recogidos en el formulario</TITLE>");
    out.println("</HEAD>");
    out.println("<BODY background =Imagenes/fondo.jpg>");
    File file = new File("listapendientes.txt");
    Scanner scanner = new Scanner(file);

    String line = null;
    String strout = "";
	out.println(strout);
	strout +="<P align=\"center\" font size=24 bgcolor=white>";
	strout +="<B>";
	strout +="Mis libros en Books.ly";
	strout +="</B>";
	strout +="</font>";
	strout +="</P>";
	strout += "<P ALIGN=\"left\">";
	strout +="<B>";
	strout +="Pendientes";
	strout +="</B>";
	strout +="</P>";
    strout += "<TABLE BGCOLOR=\"WHITE\" border=3 >";
    strout += "<TR>";
    strout += "<TH>";
    strout += "Identificador";
    strout += "</TH>";
    strout += "<TH>";
    strout += "Titulo";
    strout += "</TH>";
    strout += "<TH>";
    strout += "Autor";
    strout += "</TH>";
    strout += "<TH>";
    strout += "Editorial";
    strout += "</TH>";
    strout += "<TH>";
    strout += "Genero";
    strout += "</TH>";
	strout += "<TH>";
    strout += "Puntuacion";
    strout += "</TH>";
	strout += "<TH>";
    strout += "Descripcion";
    strout += "</TH>";
	strout += "<TH>";
    strout += "PrecioVenta";
    strout += "</TH>";
	strout += "<TH>";
    strout += "PrecioAlquiler";
    strout += "</TH>";
	 strout += "<TH>";
    strout += "Fecha";
    strout += "</TH>";
    strout += "</TR>";
    while (scanner.hasNext()) {
        line = scanner.nextLine();
        Scanner lineSc = new Scanner(line);
        lineSc.useDelimiter("\t");
        try {
			String identificacion = lineSc.next();
            String titulo = lineSc.next();
            String autor = lineSc.next();
            String editorial = lineSc.next();
            String genero = lineSc.next();
			String Puntuacion = lineSc.next();
			String Descripcion = lineSc.next();
			String PV = lineSc.next();
			String PA = lineSc.next();
			String fecha = lineSc.next();
			
            strout += "<TR>";
            strout += "<TD>";
            strout += identificacion;
            strout += "</TD>";
            strout += "<TD>";
            strout += titulo;
            strout += "</TD>";
            strout += "<TD>";
            strout += autor;
            strout += "</TD>";
            strout += "<TD>";
            strout += editorial;
            strout += "</TD>";
            strout += "<TD>";
            strout += genero;
            strout += "</TD>";
        
			strout += "<TD>";
            strout += Puntuacion;
            strout += "</TD>";
			strout += "<TD>";
            strout += Descripcion;
            strout += "</TD>";
			strout += "<TD>";
            strout += PV;
            strout += "</TD>";
			strout += "<TD>";
            strout += PA;
            strout += "</TD>";
			 strout += "<TD>";
            strout += fecha;
            strout += "</TD>";
			
            strout += "</TR>";
        } catch (NoSuchElementException ex) {
            System.out.println("Error en MostrarLibros " + ex);
        }

    }
    strout += "</TABLE>";
	out.println(strout);
	
	
    
	strout += "</P>";
    out.println("</BODY>");
	    out.println("<BR><a href=\"PaginaInico.html\">Ir al menu</a>");

    out.println("</HTML>");

    out.flush();
    out.close();
  }
     
  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  } 
}