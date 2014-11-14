package apc.command;

import java.util.ArrayList;

public class AbstractCommand {

	protected StringBuilder sb;
	protected ArrayList<String> cmdBuild;

	
	protected AbstractCommand(){
		sb = new StringBuilder();
		cmdBuild = new ArrayList<String>();
	}
	
	public String toString(){
		return sb.toString();
	}
	
	public String[] toCmdString(){
		String[] cmds = new String[cmdBuild.size()];
		for(int i=0; i<cmdBuild.size(); i++){
			cmds[i] = cmdBuild.get(i);
			System.out.println(cmds[i]);
		}return cmds;
	}
	
	public void addCpusParam(double cpus){
		sb.append(" --cpus "+cpus);
		cmdBuild.add("--cpus");
		cmdBuild.add(Double.toString(cpus));
	}
	
	public void addMemoryParam(int memory){
		sb.append(" --memory "+memory);
		cmdBuild.add("--memory");
		cmdBuild.add(Integer.toString(memory));
	}
	
	public static String generateUpdateInstanceCommand(String name, int instance){
		return "apc app update " + name + " --instances " + instance;
	}
	
	public void addEnvSetParam(String paramEnvSet){
		sb.append(" --env-set "+paramEnvSet);
		cmdBuild.add("--env-set");
		cmdBuild.add(paramEnvSet);
	}
}
