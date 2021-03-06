import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

import servlet.*;

/**
 * Jetty Server on Continuum Flock-Worker
 * handle request from Flock master
 * to create, start, stop, restart, delete application
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */

public class ContinuumFlockWorkerServer extends AbstractHandler{

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        baseRequest.setHandled(true);
        response.getWriter().println("<h1>Hello World</h1>");
	}
	
	private static void usage(){
		System.out.println("please provide arguments: <hostname> <port>");
	}

	public static void main(String[] args) throws Exception
	{
		if(args.length<2)
			usage();
		else {
			Server server = new Server(Integer.parseInt(args[1]));
			ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
	        context.setVirtualHosts(new String[]{args[0]});
			ServletHandler sh = new ServletHandler();
			sh.addServletWithMapping(DeleteAppServlet.class, "/delete/*");
			sh.addServletWithMapping(CreateAppServlet.class, "/create/");
			sh.addServletWithMapping(RestartAppServlet.class, "/restart/");
			sh.addServletWithMapping(StopAppServlet.class, "/stop/");
			sh.addServletWithMapping(ListAppServlet.class, "/list/");
			context.setHandler(sh);
			server.setHandler(context);
			server.start();
			server.join();
		}
		
	}

	
}

