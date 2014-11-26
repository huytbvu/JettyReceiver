package apc.command;

import utils.DefaultConfig;

/**
 * structure of Continuum Docker commands
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */
public class DockerCommand extends AbstractCommand{

	public DockerCommand(String name, String action) {
		super();
		cmdBuild.add("apc");
		cmdBuild.add("docker");
		cmdBuild.add(action);
		cmdBuild.add(name);
	}
	
	public void addImagesParam(String dockerImg){
		cmdBuild.add("-i");
		cmdBuild.add(dockerImg);
	}
	
	public void addStartCmdParam(String envVar, String paramStartCmd, int[] ports){
		String e = envVar == null ? "" : envVar;
		cmdBuild.add("-s");
		cmdBuild.add("sh -c 'export "+e+"; export SVC_ROUTE_IP="+DefaultConfig.DEFAULT_TCP_IP
				+ "; export SVC_ROUTE_PORT="+ports[0]+"; export SVC_ROUTE_HTTP_PORT="+ports[1]+"; "
				+ "while true; do wget google.com; if [ $? -eq 0 ]; then break; fi; sleep 1; done; "
				+ paramStartCmd +"' &");
	}
	
	public void enableAutoRestart(){
		cmdBuild.add("-a");
	}

}
