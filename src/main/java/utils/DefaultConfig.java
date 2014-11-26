package utils;

public class DefaultConfig {

	public static final String DEFAULT_TCP_IP = "172.18.32.5";
	public static final String DEFAULT_HTTP_BASE_ROUTE = "smntberday.continuum-demo.io";
	
	private static int base_port = 64000;
	
	public static int getNextAvailablePort(){
		return (base_port++);
	}
}
