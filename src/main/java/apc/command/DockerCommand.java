package apc.command;

/**
 * APC docker command
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
	
	public void addStartCmdParam(String paramStartCmd){
		cmdBuild.add("-s");
		cmdBuild.add("'"+paramStartCmd+"'");
	}
	
	public void enableAutoRestart(){
		cmdBuild.add("-a");
	}

}
