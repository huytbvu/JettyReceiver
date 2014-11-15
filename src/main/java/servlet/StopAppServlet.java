package servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CommandExecutor;

public class StopAppServlet extends HttpServlet{

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		System.out.println("just receive a STOP");
		BufferedReader br = req.getReader();
		CommandExecutor.executeOtherOperations("stop", req.getPathInfo().substring(1));
	}
}
