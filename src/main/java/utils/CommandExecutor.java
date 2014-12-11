package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import apc.command.AbstractCommand;
import service.ServiceDescription;
import enums.JobType;
/**
 * Command executor is the tool to execute APC commands in Continuum
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */

public class CommandExecutor {
	

	/**
	 * execute create operation
	 * @param appName
	 */
	public static void executeCreateOperation(BufferedReader br) throws IOException{
		printRequestHeader("CREATE");
		String desc = JSONUtils.retrieveJSONString(br);
		System.out.println();
		System.out.println("[STDOUT] Service Description received from flock master:");
		System.out.println(desc);
		System.out.println();
		ServiceDescription sd = JSONUtils.fromJSON(desc);
		int[] ports = new int[]{DefaultConfig.getNextAvailablePort(),
				DefaultConfig.getNextAvailablePort(),DefaultConfig.getNextAvailablePort()};
		String[] apcCmd = APCCommandTranslator.getCreateCommand(sd, JobType.DOCKER, ports);
		executeOneCommand(apcCmd);
		
		if(sd.getInstances()>1){
			String[] instanceCmd = AbstractCommand.generateUpdateInstanceCommand(sd.getId(), sd.getInstances());
			executeOneCommand(instanceCmd);
		}
		
		int i=0;
		for(int port : sd.getPorts()){
			String[] routeCmd = AbstractCommand.generateRouteCommand(ports[i], sd.getId(), "tcp", 0, port);
			i++;
			executeOneCommand(routeCmd);
		}
		
		String[] updateCmd = AbstractCommand.generateUpdateEgressCommand(sd.getId());
		executeOneCommand(updateCmd);	
		
		String[] restartCmd = APCCommandTranslator.getRestartComand(sd.getId());
		executeOneCommand(restartCmd);
	}

	/**
	 * execute start operation
	 * @param appName
	 */
	public static void executeStartOperation(String appName){
		printRequestHeader("START");
		executeOneCommand(APCCommandTranslator.getStartComand(appName));
	}

	/**
	 * execute stop operation
	 * @param appName
	 */
	public static void executeStopOperation(String appName){
		printRequestHeader("STOP");
		executeOneCommand(APCCommandTranslator.getStopComand(appName));
	}

	/**
	 * execute restart operation
	 * @param appName
	 */
	public static void executeRestartOperation(String appName){
		printRequestHeader("RESTART");
		executeOneCommand(APCCommandTranslator.getRestartComand(appName));
	}

	/**
	 * execute delete operation
	 * @param appName
	 */
	public static void executeDeleteOperation(String appName){
		printRequestHeader("DELETE");
		executeOneCommand(APCCommandTranslator.getDeleteComand(appName));
	}
	
	/**
	 * execute list operation
	 * @param appName
	 */
	public static void executeListOperation(String appName){
		printRequestHeader("LIST");
		executeOneCommand(APCCommandTranslator.getListCommand());
	}
	
	/**
	 * execute a single APC command
	 * @param cmds
	 */
	private static void executeOneCommand(String[] cmds){
		System.out.println("[STDOUT] Command to execute is following: ");
		for(String s:cmds)
			System.out.print(s+" ");
		
		try {
			Process p = Runtime.getRuntime().exec(cmds);
			printOutputAndError(p);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * print information about this service request
	 * @param action
	 */
	private static void printRequestHeader(String action){
		System.out.println("[STDOUT]");
		System.out.println("********************************************");
		System.out.println("NEW COMMAND FROM FLOCK MASTER");
		System.out.println("********************************************");
		System.out.println("[STDOUT] Current operation is " + action); 
	}
	
	/**
	 * this method print STDOUT and STDERR of commandline execution
	 * 
	 * @param p
	 * @throws IOException
	 * @throws InterruptedException
	 */
	private static void printOutputAndError(Process p) throws IOException, InterruptedException{
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		
		String s = null;
		System.out.println();
        while ((s = stdInput.readLine()) != null)
            System.out.println("[STDOUT] "+s);
        
        System.out.println();
        // read any errors from the attempted command
        while ((s = stdError.readLine()) != null) 
            System.out.println("[STDERR]" + s);
        
        p.waitFor();
        stdInput.close();
        stdError.close();
	}

}