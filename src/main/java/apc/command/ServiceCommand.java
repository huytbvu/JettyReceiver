package apc.command;

/**
 * structure of Continuum Service commands
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */
public class ServiceCommand extends AbstractCommand {

	public ServiceCommand(String name,String action){
		super();
		cmdBuild.add("apc");
		cmdBuild.add("service");
		cmdBuild.add(action);
		cmdBuild.add(name);
	}
	
	public void addProvider(String provider){
		cmdBuild.add("--provider");
		cmdBuild.add(provider);
	}
	
	public void addBinding(String binding){
		cmdBuild.add("--binding");
		cmdBuild.add(binding);
	}
	
	public void addDescription(String description){
		cmdBuild.add("--description");
		cmdBuild.add(description);
	}
	
	public void addJob(String job){
		cmdBuild.add("--job");
		cmdBuild.add(job);
	}
	
	public void addServiceType(String type){
		cmdBuild.add("--type");
		cmdBuild.add(type);
	}
}
