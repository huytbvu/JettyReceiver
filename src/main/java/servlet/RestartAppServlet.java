package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CommandExecutor;

public class RestartAppServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("just receive a RESTART");
		BufferedReader br = req.getReader();
		CommandExecutor.executeOperations("restart", br, req.getPathInfo().substring(1));
	}

}
