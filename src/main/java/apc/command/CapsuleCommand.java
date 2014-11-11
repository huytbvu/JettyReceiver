package apc.command;

public class CapsuleCommand extends AbstractCommand{
	
	public CapsuleCommand(String name,String action){
		super();
		sb.append("apc capsule "+action+" "+name);
	}
	
	public void addImagesParam(String dockerImg){
		sb.append(" --image "+dockerImg);
	}
	
	public void addSnapshotParam(String snapshot){
		sb.append(" --snapshot "+snapshot);
	}

}
