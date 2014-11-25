package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CommandExecutor;

@SuppressWarnings("serial")
public class ListAppServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		CommandExecutor.executeListOperation(req.getPathInfo().substring(1));
	}

}
