package servlet;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CommandExecutor;


@SuppressWarnings("serial")
public class DeleteAppServlet extends HttpServlet{
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		CommandExecutor.executeDeleteOperation(req.getPathInfo().substring(1));
	}
}
