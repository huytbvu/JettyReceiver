package apc.command;

/**
 * structure of Continuum Application commands
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */
public class AppCommand extends AbstractCommand {

	
	public AppCommand(String name,String action){
		super();
		cmdBuild.add("apc");
		cmdBuild.add("app");
		cmdBuild.add(action);
		cmdBuild.add(name);
	}
	
	public void addStartCmdParam(String paramStartCmd){
		cmdBuild.add("--start-cmd");
		cmdBuild.add(paramStartCmd);
	}
	
	public void addRouteParam(String route){
		cmdBuild.add("--routes ");
		cmdBuild.add(route);
	}
	
	public void enableStart(){
		cmdBuild.add("--start");
	}
	
	public void enableAllowEgress(){
		cmdBuild.add(" --allow-egress");
	}
	
	
}
