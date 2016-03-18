import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

// names from http://deron.meranda.us/data/census-dist-female-first.txt
@SuppressWarnings("serial")
public class Conversaciones extends HttpServlet {
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
        resp.setContentType("text/html");
        PrintWriter out=null;
        try {
            out=resp.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }

        String letters = req.getParameter("letters");

        String sql = "SELECT IDConversacion FROM Conversaciones WHERE Usuario1 Like '" + letters +  "' or  Usuario2 Like '" +letters + "%'";
        System.out.println(sql);
        try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            String resString = "[";
            boolean first = true;
            while(result.next()) {
                if (!first) {
                    resString += ",";
                } else {
                    first = false;
                }
                resString += "\"" + result.getString("IDConversacion");
            }
            resString += "]";
            out.println(resString);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Resulset: " + sql + " Exception: " + e);
        }
        out.close();
    } 
}
