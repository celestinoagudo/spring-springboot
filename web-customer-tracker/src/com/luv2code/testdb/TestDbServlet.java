package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException
	{
		//setup connection variables
		String username = "springstudent";
		String password = "springstudent";
		String jdbcURL =
			"jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&serverTimezone=UTC";
		String driver = "com.mysql.jdbc.Driver";

		//get the connection to the database
		try ( PrintWriter printWriter = response.getWriter())
		{
			Class.forName(driver);

			Connection connection = DriverManager.getConnection(jdbcURL, username, password);

			printWriter.println("Connection successful!");

			connection.close();

		}
		catch (Exception exception)
		{
			exception.printStackTrace();
			throw new ServletException(exception);
		}

	}

}
