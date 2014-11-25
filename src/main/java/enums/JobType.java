package enums;

/**
 * Type of Continuum resources, currently support the followings:
 * App, Capsule, Docker, Provider, Service
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */
public enum JobType {
	APP("app"),
	CAPSULE("capsule"),
	DOCKER("docker"),
	PROVIDER("provider"),
	SERVICE("service");

	private String type;
	
	JobType(String jobType){
		type = jobType;
	}
	
	public String getType(){
		return type + " ";
	}
}
