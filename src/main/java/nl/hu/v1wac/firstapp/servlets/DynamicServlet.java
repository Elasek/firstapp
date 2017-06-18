package nl.hu.v1wac.firstapp.servlets;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet(urlPatterns = "/DynamicServlet.do")
public class DynamicServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("username");
		String submit = req.getParameter("submit");
		int cijfer1 = Integer.parseInt(req.getParameter("cijfer1"));
		int cijfer2 = Integer.parseInt(req.getParameter("cijfer2"));
		int result = 0;
		
		if(submit.equals("Optellen")){
			result = cijfer1+cijfer2;
		} else if(submit.equals("Aftellen")){
			result = cijfer1-cijfer2;
		} else if(submit.equals("Delen")){
			result = cijfer1/cijfer2;
		} else if(submit.equals("Vermenigvuldigen")){
			result = cijfer1*cijfer2;
		}

		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println(" <title>Dynamic Example</title>");
		out.println(" <body>");
		out.println(" <h2>Dynamic webapplication example</h2>");
		out.println(" <h2>cijfer 1: " + cijfer1 + "</h2>");
		out.println(" <h2>cijfer 2: " + cijfer2 + "</h2>");
		out.println(" <h2>U heeft gekozen voor: " + submit + "</h2>");
		out.println(" <h2>De uitkomst is: " + result + "</h2>");
		out.println(" </body>");
		out.println("</html>");
	}
}