package apc.command;

public class ServiceCommand extends AbstractCommand {

	public ServiceCommand(String name,String action){
		super();
		sb.append("apc service "+action+" "+name);
	}
	
	public void addProvider(String provider){
		sb.append(" --provider "+provider);
	}
	
	public void addBinding(String binding){
		sb.append(" --binding "+binding);
	}
	
	public void addDescription(String description){
		sb.append(" --description "+description);
	}
	
	public void addJob(String job){
		sb.append(" --job "+job);
	}
	
	public void addServiceType(String type){
		sb.append(" --type "+type);
	}
}
