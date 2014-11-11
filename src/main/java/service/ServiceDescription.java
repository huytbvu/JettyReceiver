package service;

import java.io.Serializable;

/**
 * Service Description
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */

public class ServiceDescription implements Serializable {

	
	private static final long serialVersionUID = 1L;
	private String images;
	private String id;
	private int instances;
	private double cpus;
	private String cmd;
	private ContainerDescription container;
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getId() {
		return id.replace("/", ".");
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getInstances() {
		return instances;
	}
	public void setInstances(int instances) {
		this.instances = instances;
	}
	public double getCpus() {
		return cpus;
	}
	public void setCpus(double cpus) {
		this.cpus = cpus;
	}
	public String getCmd() {
		return cmd;
	}
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}
	public ContainerDescription getContainer() {
		return container;
	}
	public void setContainer(ContainerDescription container) {
		this.container = container;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getEnvSet(){
		for(String s: container.getOptions()){
			if(s.startsWith("--env"))
				return s.substring(6);
		}
		return null;
	}
	
}
