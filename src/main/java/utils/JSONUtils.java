package utils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import service.ServiceDescription;

import com.google.gson.*;

/**
 * JSON Utils
 * 
 * @author Huy Vu <huy.vu@ericsson.com>
 *
 */

public class JSONUtils {
	
	public static String toJSON(Object o){
		Gson gson = new GsonBuilder().create();
		return gson.toJson(o);
	}
	
	public static ServiceDescription fromJSON(String json){
		Gson gson = new GsonBuilder().create();
		return gson.fromJson(json, ServiceDescription.class);
	}

	public static String readFileToString(String filename) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filename));
		return retrieveJSONString(br);
	}
	
	public static String retrieveJSONString(BufferedReader br) throws IOException{
		StringBuilder sb = new StringBuilder();
		while(br.ready()){
			sb.append(br.readLine());
		}
		br.close();
		return sb.toString();
	}
	
}
