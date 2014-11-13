import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHandler;

public class ContinuumFlockWorkerServer extends AbstractHandler{

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
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
	        context.setVirtualHosts(new String[]{"localhost",args[0]});
			ServletHandler sh = new ServletHandler();
			sh.addServletWithMapping("ContinuumServlet", "/");
			context.setHandler(sh);
			server.setHandler(context);
			//server.setHandler(new HelloTest());
			server.start();
			server.join();
		}
		
	}

	
}

