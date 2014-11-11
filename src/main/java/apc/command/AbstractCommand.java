package apc.command;

public class AbstractCommand {

	protected StringBuilder sb;

	
	protected AbstractCommand(){
		sb = new StringBuilder();
	}
	
	public String toString(){
		return sb.toString();
	}
	
	public void addCpusParam(double cpus){
		sb.append(" --cpus "+cpus);
	}
	
	public void addMemoryParam(int memory){
		sb.append(" --memory "+memory);
	}
	
	public static String generateUpdateInstanceCommand(String name, int instance){
		return "apc app update " + name + " --instances " + instance;
	}
	
	public void addEnvSetParam(String paramEnvSet){
		sb.append(" --env-set "+paramEnvSet);
	}
}
