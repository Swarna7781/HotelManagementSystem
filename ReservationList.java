package hotel.com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ReservationList")
public class ReservationList extends HttpServlet {


	 private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
    private static final String username = "root";
    private static final String password = "root@123";
    
    private static final String query = "SELECT * FROM reservations";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	
	
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		

out.println("<!DOCTYPE html>");
out.println("<html>");
out.println("<head>");
out.println("<style>");
out.println("body { background-color: lightblue; font-family: Arial, sans-serif; margin: 0; padding: 0; }");
out.println("table {");
out.println("    width: 80%;");
out.println("    margin: 20px auto;");
out.println("    border-collapse: collapse;");
out.println("    background-color: #fff;");
out.println("    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);");
out.println("}");
out.println("th, td {");
out.println("    padding: 8px;");
out.println("    text-align: center;");
out.println("    border: 1px solid #ddd;");
out.println("}");
out.println("th {");
out.println("    background-color: #5bc0de;");
out.println("    color: white;");
out.println("}");
out.println("td {");
out.println("    background-color: #f9f9f9;");
out.println("    color: #333;");
out.println("}");
out.println("tr:nth-child(even) td {");
out.println("    background-color: #f1f1f1;");
out.println("}");
out.println("a {");
out.println("    color: #0275d8;");
out.println("    text-decoration: none;");
out.println("}");
out.println("a:hover {");
out.println("    text-decoration: underline;");
out.println("}");
out.println(".button {");
out.println("    display: inline-block;");
out.println("    padding: 10px 20px;");
out.println("    margin-top: 20px;");
out.println("    background-color: #5cb85c;");
out.println("    color: white;");
out.println("    text-align: center;");
out.println("text-decoration: none;");
out.println("border-radius: 5px;");
out.println("}");
out.println(".button:hover {");
out.println(" background-color: #4cae4c;");
out.println("text-decoration : none");
out.println("}");
out.println(".center {");
out.println("    text-align: center;");
	out.println("    margin-top: 20px;");
	out.println("}");
out.println("</style>");
out.println("</head>");
out.println("<body>");

		
		 try{
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch(ClassNotFoundException e){
	           e.printStackTrace();

	        }
		 
		 try (Connection con = DriverManager.getConnection(url, username, password);
				 PreparedStatement ps = con.prepareStatement(query);){
             ResultSet rs = ps.executeQuery();
             out.println("<table>");
             out.println("<tr>");
             out.println("<th>Reservation ID</th>");
             out.println("<th>Customer Name</th>");
             out.println("<th>Customer Number</th>");
             out.println("<th>Room Number</th>");
             out.println("<th>Date&Time</th>");
             out.println("<th>Update</th>");
             out.println("<th>Delete</th>");
             out.println("</tr>");
             while (rs.next()) {
                 out.println("<tr>");
                 out.println("<td>" + rs.getInt("reservation_id") + "</td>");
                 out.println("<td>" + rs.getString("guest_name") + "</td>");
                 out.println("<td>" + rs.getString("contact_number") + "</td>");
                 out.println("<td>" + rs.getInt("room_number") + "</td>");
                 out.println("<td>" + rs.getTimestamp("reservation_date").toString() + "</td>");
                 out.println("<td><a href='editScreen?id=" + rs.getInt("reservation_id") + "'>Update record</a></td>");
                 out.println("<td><a href='deleteurl?id=" + rs.getInt("reservation_id") + "'>Delete record</a></td>");
                 out.println("</tr>");
		     }
             out.println("</table>");
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
