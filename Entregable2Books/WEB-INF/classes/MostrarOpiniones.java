import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MostrarOpiniones extends HttpServlet {
     

  public void init(ServletConfig config) throws ServletException {

    super.init(config);
    System.out.println("Iniciando MostrarOpiniones...");
  } 
  public void destroy() {
    System.out.println("Adios...");
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
    out.println("<TITLE>Opiniones</TITLE>");
    out.println("</HEAD>");
	out.println("<BODY background= Imagenes/fondo.jpg>");
    out.println("<B><FONT size=+2>Opiniones: </FONT></B>");
    File file = new File("listaopinion.txt");
    Scanner scanner = new Scanner(file);

    String line = null;
    String strout = "";
    strout += "<TABLE BGCOLOR=\"WHITE\" border=3>";
    strout += "<TR>";
	strout +="<B>";
    strout += "<TH>";
    strout += "Usuario";
    strout += "</TH>";
	strout += "<TH>";
    strout += "Titulo";
    strout += "</TH>";
	strout += "<TH>";
    strout += "Puntuacion";
    strout += "</TH>";
	strout += "<TH>";
    strout += "Descripcion";
    strout += "</TH>";	
	strout +="</B>";
    strout += "</TR>";
	
	
    while (scanner.hasNext()) {
        line = scanner.nextLine();
        Scanner lineSc = new Scanner(line);
        lineSc.useDelimiter("\t");
        try {
			String Usuario = lineSc.next();
			String Titulo = lineSc.next();
			String Puntuacion = lineSc.next();
			String Descripcion = lineSc.next();
            strout += "<TR>";
            strout += "<TD>";
            strout += Usuario;
            strout += "</TD>";
			strout += "<TD>";
            strout += Titulo;
            strout += "</TD>";
            strout += "<TD>";
            strout += Puntuacion;
            strout += "</TD>";	
			strout += "<TD>";
            strout += Descripcion;
            strout += "</TD>";			
            strout += "</TR>";
        } catch (NoSuchElementException ex) {
            System.out.println("Error en MostrarMovDisp " + ex);
        }

    }
    strout += "</TABLE>";
	
    out.println(strout);
    
    out.println("</BODY>");
	out.println("<BR><a href=\"PaginaInico.html\">Vuelta al Inicio</a>");
    out.println("</HTML>");
	


    out.flush();
    out.close();
  }
     

  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  }
}
