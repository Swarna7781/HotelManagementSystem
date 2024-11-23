package hotel.com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/editScreen")
public class UpdateServScreen extends HttpServlet {


  private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
   private static final String username = "root";
   private static final String password = "root@123";
   
   private static final String query = "SELECT * FROM reservations WHERE reservation_id= ?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		

out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<style>");
out.println("body { background-color: lightblue; font-family: Arial, sans-serif; margin: 0; padding: 0; }");
out.println("form {");
out.println(" width: 50%;");
out.println("margin: 50px auto;");
out.println("background-color: #fff;");
out.println("padding: 20px;");
out.println("box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);");
out.println("}");
out.println("table { width: 100%; }");
out.println("td {");
out.println("padding: 10px;");
out.println("text-align: left;");
out.println("}");
out.println("td:first-child { font-weight: bold; }");
out.println("input[type='text'] {");
out.println("width: 100%;");
out.println("padding: 8px;");
out.println(" margin: 5px 0;");
out.println(" border: 1px solid #ccc;");
out.println(" border-radius: 5px;");
out.println("font-size: 1em;");
out.println("}");
out.println("input[type='submit'], input[type='reset'] {");
out.println(" width: 48%;");
out.println(" padding: 10px;");
out.println(" margin-top: 15px;");
out.println(" border: none;");
out.println(" border-radius: 5px;");
out.println(" font-size: 1em;");
out.println(" cursor: pointer;");
out.println("}");
out.println("input[type='submit'] { background-color: #5cb85c; color: white; }");
out.println("input[type='reset'] { background-color: #d9534f; color: white; }");
out.println("input[type='submit']:hover, input[type='reset']:hover {");
out.println("    opacity: 0.9;");
out.println("}");
out.println(".button {");
out.println(" display: inline-block;");
out.println(" padding: 8px 8px;");
out.println(" margin-top: 10px;");
out.println(" background-color: #0275d8;");
out.println("color: white;");
out.println(" text-align: center;");
out.println(" text-decoration: none;");
out.println(" border-radius: 5px;");
out.println("}");
out.println(".button:hover {");
out.println(" background-color: #025aa5;");
out.println(" text-decoration: none;");
out.println("  opacity: 0.9;");
out.println("}");
out.println(".center {");
out.println(" text-align: center;");
out.println(" margin-top: 20px;");
out.println("}");
out.println("</style>");
out.println("</head>");
out.println("<body>");
		
		int id = Integer.parseInt(req.getParameter("id")); 
		
		 try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch(ClassNotFoundException e){
	           e.printStackTrace();

	        }
		 
		 
		 try (Connection con = DriverManager.getConnection(url, username, password);
				 PreparedStatement ps = con.prepareStatement(query);){
			ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            out.println("<form action='editurl?id=" + id + "' method='post'>");
            out.println("<table align='center'>");
            out.println("<tr>");
            out.println("<td>Customer Name</td>");
            out.println("<td><input type='text' name='customerName' value='" + rs.getString("guest_name") + "'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Customer Number</td>");
            out.println("<td><input type='text' name='customerNumber' value='" + rs.getString("contact_number") + "'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td>Room Number</td>");
            out.println("<td><input type='text' name='roomNumber' value='" + rs.getInt("room_number") + "'></td>");
            out.println("</tr>");
            out.println("<tr>");
            out.println("<td><input type='submit' value='submit'></td>");
            out.println("<td><input type='reset' value='cancel'></td>");
            out.println("</tr>");
            out.println("</table>");
            out.println("</form>");
            
            
		 }
		 catch(SQLException e) {
			 e.printStackTrace();
			 
		 }
		 out.println("<div class='center'>");
		 out.println("<a href='index.html' class='button'>Home Page</a>");
		 out.println("</body>");
		 out.println("</html>");
		
	}
	 @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	        doGet(req, res);
	    }
}
