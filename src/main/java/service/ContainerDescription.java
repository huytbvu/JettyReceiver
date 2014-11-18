package service;

import java.io.Serializable;
import java.util.List;

public class ContainerDescription implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String image;
	private String env;
	private List<String> options;
	
	public ContainerDescription(){}
	
	public String getImage() {
		return image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public List<String> getOptions() {
		return options;
	}
	
	public void setOptions(List<String> options) {
		this.options = options;
		
	}
	
	public String getENV(){
		for(String s:options){
			if(s.startsWith("--env"))
				env = s.substring(6);
		}
		return env;
	}
	
	
}
