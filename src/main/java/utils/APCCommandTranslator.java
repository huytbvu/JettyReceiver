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
	public static String[] getCreateCommands(ServiceDescription sd, JobType type){
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
				dockCmd.addStartCmdParam(sd.getCmd());
				dockCmd.enableAutoRestart();
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
	public static String[] getStartComands(String appName){
		return generateSimpleCommand(appName, "start");
	}
	
	/**
	 * 
	 * @param appName
	 * @return APC stop command
	 */
	public static String[] getStopComands(String appName){
		return generateSimpleCommand(appName, "stop");
	}
	
	/**
	 * 
	 * @param appName
	 * @return APC delete command
	 */
	public static String[] getDeleteComands(String appName){
		return generateSimpleCommand(appName, "delete");
	}
	
	/**
	 * 
	 * @param appName
	 * @return APC restart command
	 */
	public static String[] getRestartComands(String appName){
		return generateSimpleCommand(appName, "restart");
	}
	
	/**
	 * APC command that only involve app name and the operation, NO OTHER PARAMS
	 * @param appName
	 * @param jobAction
	 * @return
	 */
	private static String[] generateSimpleCommand(String appName, String jobAction){
		String[] cmds =  {"apc","app",jobAction,appName,"--batch","||","true"};
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
