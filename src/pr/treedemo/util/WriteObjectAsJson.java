package pr.treedemo.util;

import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WriteObjectAsJson {
	
	private String outputFolder = null;
	
	public WriteObjectAsJson(String outputFolder) {
		super();
		this.outputFolder = outputFolder;
	}

	public <K, T> void writeListOfMapValues(Map<K, List<T>> contentMap) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		Long currentTimeMillis = System.currentTimeMillis();
		
		String fileName = this.outputFolder+"writeListOfMapValues_" + String.valueOf(currentTimeMillis)+".json";
		
		try {
//			System.out.println(objectMapper.writeValueAsString(contentMap));
			objectMapper.writeValue(new FileOutputStream(fileName), contentMap);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}	
	}
	
	public <T> void writeObjOfMapValues(Map<String, T> objectMap) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		Long currentTimeMillis = System.currentTimeMillis();
		String fileName = this.outputFolder+"writeObjOfMapValues" + String.valueOf(currentTimeMillis)+".json";
		
		try {
//			System.out.println(objectMapper.writeValueAsString(objectMap));
			objectMapper.writeValue(new FileOutputStream(fileName), objectMap);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}	
	}	
	
	public <T> void writeObjectValues(T object) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		Long currentTimeMillis = System.currentTimeMillis();
		String fileName = this.outputFolder+"writeValueAsString" + String.valueOf(currentTimeMillis)+".json";
		
		try {
//			System.out.println(objectMapper.writeValueAsString(object));
			objectMapper.writeValue(new FileOutputStream(fileName), object);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		
	}


	public <K1, K2, V2> void writeMapOfMapValues(Map<K1, Map<K2, V2>> contentMap) {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		Long currentTimeMillis = System.currentTimeMillis();
		String fileName = this.outputFolder+"writeValueAsString" + String.valueOf(currentTimeMillis)+".json";
		
		try {
//			System.out.println(objectMapper.writeValueAsString(contentMap));
			objectMapper.writeValue(new FileOutputStream(fileName), contentMap);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public String getOutputFolder() {
		return outputFolder;
	}

	public void setOutputFolder(String outputFolder) {
		this.outputFolder = outputFolder;
	}
}
