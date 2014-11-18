package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.eclipse.jetty.util.BufferUtil;

import apc.command.AbstractCommand;
import service.ServiceDescription;
import enums.JobType;

public class CommandExecutor {
	
	public static void executeCreateOperations(BufferedReader br) throws IOException{
		try {
			String desc = JSONUtils.retrieveJSONString(br);
			System.out.println("JSON String received from flock master is:");
			System.out.println(desc);
			System.out.println();
			ServiceDescription sd = JSONUtils.fromJSON(desc);
			String[] apcCmd = APCCommandTranslator.getCreateCommands(sd, JobType.DOCKER);
			
			System.out.println("Command to execute is following: ");
			for(String s:apcCmd)
				System.out.print(s+" ");
			Process p = Runtime.getRuntime().exec(apcCmd);
		
			printOutputAndError(p);
		
			p.waitFor();
			if(sd.getInstances()>1){
				String[] instanceCmd = AbstractCommand.generateUpdateInstanceCommand(sd.getId(), sd.getInstances());
				for(String s:instanceCmd)
					System.out.print(s+" ");
				p = Runtime.getRuntime().exec(instanceCmd);
				printOutputAndError(p);
			}
			
			int port = (sd.getPorts() != null && sd.getPorts().get(0) != null) ? sd.getPorts().get(0) : 27017;
			String[] routeCmd = AbstractCommand.generateRouteCommand(sd.getId()+".smntberday.continuum-demo.io", sd.getId(), "http", 0, port);
			for(String s:routeCmd)
				System.out.print(s+" ");
			p = Runtime.getRuntime().exec(routeCmd);
			printOutputAndError(p);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static void executeOtherOperations(String action, String appName) throws IOException{
		
		String[] apcCmd = {};
		System.out.println("Current operation is "+action);
		switch(action){
			case "start":
				apcCmd = APCCommandTranslator.getStartComands(appName);
				break;
			case "stop":
				apcCmd = APCCommandTranslator.getStopComands(appName);
				break;
			case "restart":
				apcCmd = APCCommandTranslator.getRestartComands(appName);
				break;
			case "delete":
				apcCmd = APCCommandTranslator.getDeleteComands(appName);
				break;
			case "list":
				apcCmd = APCCommandTranslator.getListCommand();
			default:
				break;
		}
		
		// apc, docker run, appname, -i, huytbvu/apctest ,-s, "ls", -a
		
		System.out.println("Command to execute is following: ");
		for(String s:apcCmd)
			System.out.println(s+" ");
		Process p = Runtime.getRuntime().exec(apcCmd);
		try {
			printOutputAndError(p);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
	}
	
	private static void printOutputAndError(Process p) throws IOException, InterruptedException{
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		
		String s = null;
		System.out.println("OUTPUT");
        while ((s = stdInput.readLine()) != null)
            System.out.println(s);
        
        System.out.println();
        // read any errors from the attempted command
        System.out.println("Standard ERROR");
        while ((s = stdError.readLine()) != null) 
            System.out.println(s);
        
        p.waitFor();
        stdInput.close();
        stdError.close();
	}

}