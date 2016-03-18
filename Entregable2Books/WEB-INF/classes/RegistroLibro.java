import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RegistroLibro extends HttpServlet {
     
  
  public void init(ServletConfig config) throws ServletException {
    
    super.init(config);
    System.out.println("Iniciando RegistroLibro...");
  }
        
  public void destroy() {
    System.out.println("No hay nada que hacer...");
  } 
          
 
  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	double codigo = Math.random()*10000;
    String strCodigo = String.valueOf((int) codigo);
    String strTitulo = req.getParameter("titulo");
    String strAutor = req.getParameter("Autor");
    String strEditorial = req.getParameter("editorial");
	String strGenero = req.getParameter("genero");
	String strFecha = req.getParameter("fecha");
	String strPuntuacion = req.getParameter("Puntuacion");
	String strDescripcion = req.getParameter("Descripcion");
	String strPV = req.getParameter("PV");
	String strPV2 = req.getParameter("PV2");
	String strPA = req.getParameter("PA");
	String strPA2 = req.getParameter("PA2");
                 
    FileWriter fileWriter = new FileWriter("listanuevos.txt", true);
    PrintWriter toFile = new PrintWriter(fileWriter);
    toFile.println("\n" + strCodigo + "\t" + strTitulo + "\t" + strAutor + "\t" + strEditorial + "\t" + strGenero + "\t" + strPuntuacion + "\t" + strDescripcion + "\t" + strPV + "\t"   + strPA + "\t"  + strFecha + "\t");
    fileWriter.close();
    devolverPaginaHTML(resp, strCodigo, strTitulo, strAutor, strEditorial, strGenero, strPuntuacion, strDescripcion, strPV, strPA, strFecha);
  } 
    
  public void devolverPaginaHTML(HttpServletResponse resp,
    String identificacion, String titulo, String Autor, String editorial, String genero, String Puntuacion, String Descripcion, String PV, String PA, String fecha) {
    resp.setContentType("text/html");

    PrintWriter out = null;
    try {
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("Se ha producido una excepcion");    
    }
        
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Nuevo libro:</TITLE>");
    out.println("</HEAD>");
	 out.println("<BODY background =Imagenes/fondo.jpg>");
    out.println("<B><FONT size=+4>Ha introducido el siguiente libro: </FONT></B>");
    out.println("<P><FONT size=+2><B>Codigo: </B>" + identificacion + "</FONT>");
    out.println("<P><FONT size=+2><B>Titulo: </B>" + titulo + "</FONT>");
    out.println("<P><FONT size=+2><B>Autor: </B>" + Autor + "</FONT>");
	out.println("<P><FONT size=+2><B>Editorial: </B>" + editorial + "</FONT>");
	out.println("<P><FONT size=+2><B>Genero: </B>" + genero + "</FONT>");
	out.println("<P><FONT size=+2><B>Puntuacion: </B>" + Puntuacion + "</FONT>");
	out.println("<P><FONT size=+2><B>Descripcion: </B>" + Descripcion + "</FONT>");
	out.println("<P><FONT size=+2><B>PrecioVenta: </B>" + PV + " </FONT>");
	out.println("<P><FONT size=+2><B>PrecioAlquiles: </B>" + PA + "</FONT>");
	out.println("<P><FONT size=+2><B>Fecha: </B>" + fecha + "</FONT>");
	
    out.println("</BODY>");
    out.println("<BR><a href=\"PaginaInico.html\">Ir al menu</a>");

    out.println("</HTML>");

   
    out.flush();
    out.close();
  } 
    
}
