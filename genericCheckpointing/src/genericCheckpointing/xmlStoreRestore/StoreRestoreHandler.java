package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.*;

import genericCheckpointing.util.SerializableObject;

public class StoreRestoreHandler implements InvocationHandler {

	public Object invoke(Object proxy, Method method, Object[] args) {
		System.out.println("here");
		
		try {
			if(method.getName().equals("writeObj")) {
				if(args[2].equals("XML")) {
					SerStrategy serStrategy = new XMLSerialization();
					serializeData((SerializableObject)args[0], serStrategy);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void serializeData(SerializableObject sObject, SerStrategy sStrategy) {
        sStrategy.processInput(sObject);
	}
}
