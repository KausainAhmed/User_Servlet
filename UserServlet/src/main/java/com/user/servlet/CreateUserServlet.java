package com.user.servlet;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//creating html form and linking it with a present database
//getting values from front end form and store it in database


public class CreateUserServlet extends HttpServlet {
	private Connection connection;

	public void init() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/servlet", "root", "open6162");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) {
		String name = req.getParameter("name");
		String id = req.getParameter("id");
		String age = req.getParameter("age");
		String password = req.getParameter("password");

		try {
			Statement st = connection.createStatement();

			int result = st.executeUpdate(
					"insert into record values('" + name + "','" + id + "','" + age + "','" + password + "')");

			PrintWriter out = res.getWriter();
			if (result > 0) {
				out.println("<h1><marquee>User Created</marquee></h1>");
			} else {
				out.println("<h1>Error in User Creation</h1>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
