package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import apc.command.AppCommand;
import apc.command.DockerCommand;
import enums.JobType;
import service.ServiceDescription;

public class APCCommandTranslator {
	

	/**
	 * 
	 * @param sd
	 * @param type app/capsule/docker/service
	 * @return
	 */
	public static String translateCreationCommand(ServiceDescription sd, JobType type){
		String apcCommand = "";
		JobType.values();
		switch(type){
			case APP:
				AppCommand appCmd = new AppCommand(sd.getId(), "create");
				if(sd.getInstances()>1) appCmd.addInstancesParam(sd.getInstances());
				apcCommand = appCmd.toString();
				break;
			case DOCKER:
				DockerCommand dockCmd = new DockerCommand(sd.getId(), "run");
				dockCmd.addImagesParam(sd.getContainer().getImage());
				dockCmd.addStartCmdParam(sd.getCmd());
				dockCmd.enableAutoRestart();
				apcCommand = dockCmd.toString();
				System.out.println(sd.getId());
				break;
			default:
		}
		
		return apcCommand;
	}
	
	public static String translateDeletionAndRestartCommand(String jobAction){
		return "apc app " + jobAction;
	}
	


	
}
