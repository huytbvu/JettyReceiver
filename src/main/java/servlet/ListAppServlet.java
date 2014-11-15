package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CommandExecutor;

public class ListAppServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("just receive a LIST");
		BufferedReader br = req.getReader();
		CommandExecutor.executeOtherOperations("list", req.getPathInfo().substring(1));
	}

}