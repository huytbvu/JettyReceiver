package apc.command;

import java.util.ArrayList;

import utils.DefaultConfig;

/**
 * abstract class for all APC commands
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */

public class AbstractCommand {

	protected ArrayList<String> cmdBuild;

	
	protected AbstractCommand(){
		cmdBuild = new ArrayList<String>();
	}
	
	/**
	 * 
	 * @return String[] of APC command and is parameters
	 */
	public String[] toCmdString(){
		String[] cmds = new String[cmdBuild.size()];
		for(int i=0; i<cmdBuild.size(); i++){
			cmds[i] = cmdBuild.get(i);
		//	System.out.println(cmds[i]);
		}return cmds;
	}
	
	/**
	 * add cpu parameter
	 * 
	 * @param cpus
	 */
	public void addCpusParam(double cpus){
		cmdBuild.add("--cpus");
		cmdBuild.add(Double.toString(cpus));
	}
	
	/**
	 * add memory parameter
	 * 
	 * @param memory
	 */
	public void addMemoryParam(int memory){
		cmdBuild.add("--memory");
		cmdBuild.add(Integer.toString(memory));
	}
	
	/**
	 * 
	 * @param name of App
	 * @param number of instances to make
	 * @return APC command to generate instances
	 */
	public static String[] generateUpdateInstanceCommand(String name, int instance){
		String[] cmds = {"apc","app","update",name,"--instances",Integer.toString(instance)};
		return cmds;
	}
	
	/**
	 * generate APC command to add route for docker containers
	 * 
	 * @param route
	 * @param appName
	 * @param type
	 * @param weight
	 * @param port
	 * @return
	 */
	public static String[] generateRouteCommand(int routePort, String appName,String type, int weight, int exposePort){
		if(type.equals("http")){
			String r = appName+"."+DefaultConfig.DEFAULT_HTTP_BASE_ROUTE;
			String[] cmds = {"apc","route","add",r,"--app",appName,
					"--type","http","--weight",Integer.toString(weight),
					"--port",Integer.toString(exposePort),
					"-q"};
			return cmds;
		}
		else{
			String r = DefaultConfig.DEFAULT_TCP_IP + ":" + routePort;
			String[] cmds = {"apc","route","add",r,"--app",appName,
					"--type","tcp","--weight",Integer.toString(weight),
					"--port",Integer.toString(exposePort),"--tcp",
					"-q"};
			return cmds;
		}
		
		
	}
	
	/**
	 * add disk space 
	 * @param diskSpace
	 */
	public void addDiskSpace(double diskSpace){
		cmdBuild.add("--disk");
		cmdBuild.add(Double.toString(diskSpace));
	}
	
	/**
	 * update app to allow-egress
	 * this feature is currently not functioning properly for -ae tag
	 * therefore need to add another dummy tag
	 * @param name
	 * @return
	 */
	public static String[] generateUpdateEgressCommand(String name){
		//String[] cmds = {"echo","'\\n\\n'","|","apc","app","update",name,"-ae","-e","DUMMY=dummy"};
		String cmd = "echo '\\n\\n' | apc app update " + name + " -ae -e DUMMY=dummy";
		String[] cmds = {"/bin/sh","-c",cmd};
		return cmds;
	}
}
