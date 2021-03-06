package service;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */
public class ContainerDescription implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String image;
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

}
