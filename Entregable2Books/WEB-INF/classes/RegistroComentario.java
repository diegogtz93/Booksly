import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegistroComentario extends HttpServlet {
     
 
  public void init(ServletConfig config) throws ServletException {
   
    super.init(config);
    System.out.println("Iniciando RegistroComentario...");
  } 
  public void destroy() {
    System.out.println("No hay nada que hacer...");
  } 
          
  
  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
    String strusuario = req.getParameter("usuario");
    String strtitulo = req.getParameter("titulo"); 
	String strpuntuacion = req.getParameter("puntuacion");	
	String strdescripcion = req.getParameter("descripcion");	
    FileWriter fileWriter = new FileWriter("listaopinion.txt", true);
    PrintWriter toFile = new PrintWriter(fileWriter);
    toFile.println(strusuario + "\t" + strtitulo + "\t" + strpuntuacion+ "\t" + strdescripcion);
    fileWriter.close();
   
    devolverPaginaHTML(resp, strusuario, strtitulo, strpuntuacion, strdescripcion);
  } 
    
  public void devolverPaginaHTML(HttpServletResponse resp,
    String usuario, String titulo, String puntuacion, String descripcion) {
    
    resp.setContentType("text/html");


    PrintWriter out = null;
    try {
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("Se ha producido una excepcion");    
    }
        

    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Comentario</TITLE>");
    out.println("</HEAD>");
    out.println("<BODY background= Imagenes/fondo.jpg>");
    out.println("<H2>Opinion del usuario: </H2>");
    out.println("<P><FONT size=+1><B>Usuario: </B>" + usuario + "</FONT>");
    out.println("<BR><FONT size=+1><B>Titulo: </B>" + titulo + "</FONT>");
	out.println("<BR><FONT size=+1><B>Puntuacion: </B>" + puntuacion + "</FONT>");
	out.println("<BR><FONT size=+1><B>Descripcion: </B>" + descripcion + "</FONT>");
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
