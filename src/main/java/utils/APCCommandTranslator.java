package utils;

import apc.command.*;
import enums.JobType;
import service.ServiceDescription;


/**
 * Translate from Flock-master service description to APC commands
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */
public class APCCommandTranslator {
	

	/**
	 * 
	 * @param service description
	 * @param type app/capsule/docker/service
	 * @return APC create commands
	 */
	public static String[] getCreateCommand(ServiceDescription sd, JobType type, int[] ports){
		String[] apcCommand = null;
		switch(type){
			case APP:
				AppCommand appCmd = new AppCommand(sd.getId(), "create");
				apcCommand = appCmd.toCmdString();
				break;
			case CAPSULE:
				CapsuleCommand capCmd = new CapsuleCommand(sd.getId(), "create");
				apcCommand = capCmd.toCmdString();
				break;
			case DOCKER:
				DockerCommand dockCmd = new DockerCommand(sd.getId(), "run");
				dockCmd.addImagesParam(sd.getContainer().getImage());
				if(sd.getCmd()!=null)
					dockCmd.addStartCmdParam(sd.getEnvSet(),sd.getCmd(), ports);
				if(sd.getDisk()>0)
					dockCmd.addDiskSpace(sd.getDisk());
				if(sd.getCpus()>0)
					dockCmd.addDiskSpace(sd.getCpus());
				if(sd.getMem()>0)
					dockCmd.addDiskSpace(sd.getMem());
				//dockCmd.enableAutoRestart();
				apcCommand = dockCmd.toCmdString();
				System.out.println(sd.getId());
				break;
			default:
		}
		
		return apcCommand;
	}
	
	/**
	 * 
	 * @param appName
	 * @return APC start command
	 */
	public static String[] getStartComand(String appName){
		return generateSimpleCommand(appName, "start");
	}
	
	/**
	 * 
	 * @param appName
	 * @return APC stop command
	 */
	public static String[] getStopComand(String appName){
		return generateSimpleCommand(appName, "stop");
	}
	
	/**
	 * 
	 * @param appName
	 * @return APC delete command
	 */
	public static String[] getDeleteComand(String appName){
		return generateSimpleCommand(appName, "delete");
	}
	
	/**
	 * 
	 * @param appName
	 * @return APC restart command
	 */
	public static String[] getRestartComand(String appName){
		return generateSimpleCommand(appName, "restart");
	}
	
	/**
	 * APC command that only involve app name and the operation, NO OTHER PARAMS
	 * @param appName
	 * @param jobAction
	 * @return
	 */
	private static String[] generateSimpleCommand(String appName, String jobAction){
		String[] cmds =  {"apc","app",jobAction,appName,"-q"};
		return cmds;
	}
	
	
	/**
	 * 
	 * @return APC commands to list all apps
	 */
	public static String[] getListCommand(){
		String[] cmds =  {"apc","app","list"};
		return cmds;
	}
	


	
}
