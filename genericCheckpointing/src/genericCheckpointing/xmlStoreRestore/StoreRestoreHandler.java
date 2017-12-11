package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.*;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler {
	private FileProcessor fp;
	
	public void openFile(String filename, String mode, String name) {
		fp = new FileProcessor(filename, mode, name);
	}
	
	public void closeFile(String mode) {
		fp.closeFile(mode);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args) {
		
		try {
			if(method.getName().equals("writeObj")) {
				if(args[2].equals("XML")) {
					SerStrategy serStrategy = new XMLSerialization(fp);
					serializeData((SerializableObject)args[0], serStrategy);
				}
			} else if (method.getName().equals("readObj")) {
				if(args[1].equals("XML")) {
					DeserStrategy deserStrategy = new XMLDeserialization(fp);
					return deserializeData(deserStrategy);
				}
			}
		} catch (Exception e) {
			System.out.println("Exception caught");
			e.printStackTrace();
		}
		return null;
	}
	
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
        sStrategy.processInput(sObject);
	}
	
	public SerializableObject deserializeData(DeserStrategy sStrategy) {
        return sStrategy.processInput();
	}
	
    
}
