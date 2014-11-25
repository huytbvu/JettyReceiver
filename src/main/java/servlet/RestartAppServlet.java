package servlet;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CommandExecutor;

@SuppressWarnings("serial")
public class RestartAppServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		CommandExecutor.executeRestartOperation(req.getPathInfo().substring(1));
	}

}
