package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import genericCheckpointing.util.SerializableObject;

public class XMLSerialization implements SerStrategy {
    
     public void processInput(SerializableObject sObject) {
    	 StringBuilder string = new StringBuilder();
    	 
    	 string.append("<DPSerialization><complexType xsi:type='genericCheckpointing.util.");
    	 Class<?> cls = sObject.getClass();
    	 
    	 string.append(cls.getName() + "'>\n");
    	 
    	 System.out.println("process");
    	 Field[] fieldList =  cls.getDeclaredFields();
    	 for (int j=0; j<fieldList.length; j++) {
             Class fieldClass = fieldList[j].getType();
             String fieldName = fieldList[j].getName();
             System.out.println(fieldName);
             
             if (fieldClass == int.class) {
        		 //get field name
        		 try {
        			 String methodName = "get" + fieldName;
	        		 Method getterMethod = cls.getMethod(methodName);
	        		 Object invokeRet = getterMethod.invoke(sObject);
	        		 System.out.println("invokeRet");
        		 } catch(Exception e) {
        			 e.printStackTrace();
        		 }
             }
    	 }
   }
}
