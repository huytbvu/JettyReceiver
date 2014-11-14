import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import utils.CommandExecutor;

@SuppressWarnings({ "serial" })
	public class ContinuumServlet extends HttpServlet{
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			System.out.println("just receive a GET");
			resp.setContentType("text/html;charset=utf-8");
	        resp.setStatus(HttpServletResponse.SC_OK);
			BufferedReader reader = req.getReader();
			CommandExecutor.executeOperation("list", reader);
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			System.out.println("just receive a POST");

			resp.setContentType("text/html;charset=utf-8");
	        resp.setStatus(HttpServletResponse.SC_OK);
			BufferedReader reader = req.getReader();
			CommandExecutor.executeOperations("start", reader);
		}
		
		@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			System.out.println("just receive a DELETE");

			resp.setContentType("text/html;charset=utf-8");
	        resp.setStatus(HttpServletResponse.SC_OK);
			BufferedReader reader = req.getReader();
			CommandExecutor.executeOperation("stop", reader);
		}
	}