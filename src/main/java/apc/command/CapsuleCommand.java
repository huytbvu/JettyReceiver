package apc.command;

/**
 * structure of Continuum Capsule commands
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */
public class CapsuleCommand extends AbstractCommand{
	
	public CapsuleCommand(String name,String action){
		super();
		cmdBuild.add("apc");
		cmdBuild.add("capsule");
		cmdBuild.add(action);
		cmdBuild.add(name);
	}
	
	public void addImagesParam(String dockerImg){
		cmdBuild.add("--image");
		cmdBuild.add(dockerImg);
	}
	
	public void addSnapshotParam(String snapshot){
		cmdBuild.add("--snapshot");
		cmdBuild.add(snapshot);
	}

}
