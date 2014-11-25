package servlet;
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CommandExecutor;


public class DeleteAppServlet extends HttpServlet{
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		BufferedReader br = req.getReader();
		System.out.println(req.getPathInfo().substring(1));
		CommandExecutor.executeOtherOperations("delete", req.getPathInfo().substring(1));
	}
}
