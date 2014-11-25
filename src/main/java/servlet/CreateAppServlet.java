package servlet;
import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CommandExecutor;


@SuppressWarnings("serial")
public class CreateAppServlet extends HttpServlet{

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
		resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
		BufferedReader reader = req.getReader();
		CommandExecutor.executeCreateOperation(reader);
		
	}
}
