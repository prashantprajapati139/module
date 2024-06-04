
  // Servlet

  package com.assignment;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/reg")
public class regi extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		
		String Name=req.getParameter("name");
		String email_address=req.getParameter("email");
		String Phone_number=req.getParameter("contact");
		String birth_date=req.getParameter("dob");
		String Gender=req.getParameter("gender");
		String address=req.getParameter("address");
		String country=req.getParameter("country");
		String city=req.getParameter("city");
       
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");		
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud","root","123456");
			
				PreparedStatement pt=con.prepareStatement("insert into PPP values(?,?,?,?,?,?,?,?)");
				pt.setString(1, Name);
				pt.setString(2, email_address);
				pt.setString(3,Phone_number);
				pt.setString(4, birth_date);
				pt.setString(5, Gender);
				pt.setString(6, address);
				pt.setString(7, country);
				pt.setString(8,city);

				int count=pt.executeUpdate();
				System.out.println(count);
				
				if(count>0) {
					resp.setContentType("text/html");
					out.print("<h2> Registration Succssfuly <h2/>");
					
					RequestDispatcher rd=req.getRequestDispatcher("/Registration.jsp");
					rd.include(req, resp);
				}else {
					resp.setContentType("text/html");
					out.print("<h2> Registration not Successfully <h2/>");
					
					RequestDispatcher rd=req.getRequestDispatcher("/Registration.jsp");
					rd.include(req, resp);
				}
					
			} catch ( Exception e) {
				resp.setContentType("text/html");
				out.print("<h2> Exception occured:"+e.getMessage() +"<h2/>");
				
				RequestDispatcher rd=req.getRequestDispatcher("/Registration.html");
				rd.include(req, resp);		
				
			}

		
		
	}

}