package utils;

import java.io.BufferedReader;
import java.io.IOException;

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
			default:
				break;
		}
		Runtime.getRuntime().exec(apcCmd);
	}

}
