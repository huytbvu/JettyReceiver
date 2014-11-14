package apc.command;

public class DockerCommand extends AbstractCommand{

	public DockerCommand(String name, String action) {
		super();
		sb.append("apc docker "+action+" "+name);
	}
	
	public void addImagesParam(String dockerImg){
		sb.append(" -i "+dockerImg);
		cmdBuild.add("-i");
		cmdBuild.add(dockerImg);
	}
	
	public void addStartCmdParam(String paramStartCmd){
		sb.append(" -s '"+paramStartCmd+"'");
		cmdBuild.add("-s");
		cmdBuild.add("'"+paramStartCmd+"'");
	}
	
	public void enableAutoRestart(){
		sb.append(" -a");
		cmdBuild.add("-a");
	}

}
