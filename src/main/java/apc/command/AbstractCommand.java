package apc.command;

import java.util.ArrayList;

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
	
	public void addCpusParam(double cpus){
		cmdBuild.add("--cpus");
		cmdBuild.add(Double.toString(cpus));
	}
	
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
	
	public static String[] generateRouteCommand(String route, String appName,String type, int weight, int port){
		String[] cmds = {"apc","route","add",route,"--app",appName,
				"--type",type,"--weight",Integer.toString(weight),
				"--port",Integer.toString(port),
				"--batch || true"};
		return cmds;
	}
	
	public void addEnvSetParam(String paramEnvSet){
		cmdBuild.add("--env-set");
		cmdBuild.add(paramEnvSet);
	}
}
