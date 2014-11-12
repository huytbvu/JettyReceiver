package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import service.ServiceDescription;
import enums.JobType;

public class CommandExecutor {
	
	public static void executeOperation(String action, BufferedReader br) throws IOException{
		String desc = JSONUtils.retrieveJSONString(br);
		String apcCmd = "";
		switch(action){
			case "start":
				ServiceDescription sd = JSONUtils.fromJSON(desc);
				apcCmd = APCCommandTranslator.translateCreationCommand(sd, JobType.DOCKER);
				break;
			case "stop":
			case "restart":
				apcCmd = APCCommandTranslator.translateDeletionAndRestartCommand(desc);
				break;
			case "list":
				apcCmd = APCCommandTranslator.translateListCommand();
			default:
				break;
		}
		Process p = Runtime.getRuntime().exec(apcCmd);
		
		// print out something to test
		String s = null;
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));
 
        // read the output from the command
        System.out.println("OUTPUT");
        while ((s = stdInput.readLine()) != null)
            System.out.println(s);
        
             
        System.out.println();
        // read any errors from the attempted command
        System.out.println("Standard ERROR");
        while ((s = stdError.readLine()) != null) 
            System.out.println(s);
        
	}

}