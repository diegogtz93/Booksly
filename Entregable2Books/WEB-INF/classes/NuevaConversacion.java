import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class NuevaConversacion extends HttpServlet {
     
 
  public void init(ServletConfig config) throws ServletException {
   
    super.init(config);
    System.out.println("Iniciando RegistroComentario...");
  } 
  public void destroy() {
    System.out.println("No hay nada que hacer...");
  } 
          
  
  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    String NombreConversacion = req.getParameter("nombre");
    String Usuario1 = req.getParameter("usuario1"); 
	String Usuario2 = req.getParameter("usuario2");	
    FileWriter fileWriter = new FileWriter("ListaUsuario.txt", true);
    PrintWriter toFile = new PrintWriter(fileWriter);
    toFile.println(NombreConversacion + "\t" + Usuario1 + "\t" + Usuario2);
    fileWriter.close();
   
    devolverPaginaHTML(resp, NombreConversacion, Usuario1, Usuario2);
  } 
    
  public void devolverPaginaHTML(HttpServletResponse resp,
    String NombreConversacion, String Usuario1, String Usuario2) {
    
    resp.setContentType("text/html");


    PrintWriter out = null;
    try {
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("Se ha producido una excepcion");    
    }
        

    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Conversacion</TITLE>");
    out.println("</HEAD>");
    out.println("<BODY background= Imagenes/fondo.jpg>");
    out.println("<H2>Nueva Conversacion </H2>");
    out.println("<P><FONT size=+1><B>Nombre Conversacion: </B>" + NombreConversacion + "</FONT>");
    out.println("<BR><FONT size=+1><B>Usuario 1: </B>" + Usuario1 + "</FONT>");
	out.println("<BR><FONT size=+1><B>Usuario 2: </B>" + Usuario2 + "</FONT>");
	
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
