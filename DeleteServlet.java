package hotel.com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;


@WebServlet("/deleteurl")

public class DeleteServlet extends HttpServlet {


	  private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
	   private static final String username = "root";
	   private static final String password = "root@123";
	   
	   private static final String query = "DELETE FROM reservations WHERE reservation_id = ?";
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		
		
			PrintWriter out = resp.getWriter();
			resp.setContentType("text/html");
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<style>");
			out.println("body { background-color: lightblue;}");
			out.println("h2 { color: black; text-align: center; font-weight: bold; font-size: 1.5em;}");
	   	    out.println(".button {");
	    	out.println("display: inline-block;");
	   	   out.println("padding: 10px 20px;");
	     	out.println("margin: 10px;");
	   	   out.println("margin-top: 20px;");
	    	out.println("background-color: #5cb85c;");
	   	  out.println("color: white;");
	   	  out.println("text-align: center;");
	   	  out.println("text-decoration: none;");
	   	  out.println("border-radius: 5px;");
	   	  out.println("}");
	   	  out.println(".button:hover {");
	   	   out.println("background-color: #4cae4c;");
	   	  out.println("}");
	   	out.println(".center-container {");
	   	out.println("display: flex;");
	   	out.println("flex-direction: column;"); 
	   	out.println("align-items: center;");
	   	out.println("margin-top: 20px;");
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
	              
	              int count = ps.executeUpdate();
		            
		            if (count == 1) {
		            	out.println("<div class='center-container'>");
		                out.println("<h2>Record Is Deleted Sucessfully!</h2>");
		            } else {
		            	out.println("<div class='center-container'>");
		                out.println("<h2>Record not Deleted!");
		            }          
	            
			 }
			 catch(SQLException e) {
				 e.printStackTrace();
				 
			 }
			
			 out.println("<a href='index.html' class='button'><< Home Page</a>");
			 out.println("<a href='ReservationList' class = 'button'><< Reservation List</a>");
			 out.println("</body>");
			 out.println("</html>");
			
		}
		 @Override
		    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		        doGet(req, res);
		    }

}
