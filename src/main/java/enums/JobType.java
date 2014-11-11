package enums;

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
