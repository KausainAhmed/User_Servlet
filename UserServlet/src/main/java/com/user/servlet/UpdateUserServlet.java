package com.user.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection con;

	public void init() {
		try {
			System.out.println("init()");
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/servlet", "root", "open6162");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost()");
		String id = req.getParameter("id");
		String password = req.getParameter("password");
		try {

			Statement st = con.createStatement();
			int result = st.executeUpdate("update record set password='" + password + "' where id='" + id + "'");
			PrintWriter out = res.getWriter();
			if (result > 0) {
				out.println("update successfully");
			} else {
				out.println("not updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
