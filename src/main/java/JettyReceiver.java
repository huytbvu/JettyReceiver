import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

import utils.CommandExecutor;


public class JettyReceiver {

	@SuppressWarnings({ "serial" })
	public class ContinuumServlet extends HttpServlet{
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			
			req.getReader();
			
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			resp.getWriter().println("<p>POST yolo</p>");
			
			BufferedReader reader = req.getReader();
			CommandExecutor.executeOperation("start", reader);
		}
		
		@Override
		protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			resp.getWriter().println("<p>Delete it</p>");
			
			
			BufferedReader reader = req.getReader();
			CommandExecutor.executeOperation("stop", reader);
		}
	}
	
	
	
	public static void main(String[] args) throws Exception{

        SelectChannelConnector connector1 = new SelectChannelConnector();
        connector1.setHost("127.0.0.1");
        connector1.setPort(8080);
        connector1.setName("admin");        
        
        final ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        context.setResourceBase("webapp");

		
		Server myserver = new Server(8080);
		myserver.setConnectors(new Connector[]{connector1});
		ServletHandler handler = new ServletHandler();
		handler.addServletWithMapping(ContinuumServlet.class, "/hello");
		myserver.setHandler(handler); 
		myserver.setHandler(context); 
		
		// start and join
		myserver.start();
		myserver.join();
	}
	
}
