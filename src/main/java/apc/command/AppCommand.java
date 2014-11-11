package apc.command;

public class AppCommand extends AbstractCommand {

	
	public AppCommand(String name,String action){
		super();
		sb.append("apc app "+action+" "+name);
	}
	
	public void addStartCmdParam(String paramStartCmd){
		sb.append(" --start-cmd \""+paramStartCmd+"\"");
	}
	
	public void addInstancesParam(int instances){
		sb.append(" --instances "+instances);
	}
	
	public void addRouteParam(String route){
		sb.append(" --routes "+route);
	}
	
	public void enableStart(){
		sb.append(" --start");
	}
	
	public void enableAllowEgress(){
		sb.append(" --allow-egress");
	}
	
	
}
