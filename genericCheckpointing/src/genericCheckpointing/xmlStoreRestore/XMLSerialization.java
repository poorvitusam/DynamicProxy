package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.SerializableObject;

public class XMLSerialization implements SerStrategy {
	private FileProcessor fp;
	
	public XMLSerialization(FileProcessor fileprocessor) {
		fp = fileprocessor; 
	}
	
	public void processInput(SerializableObject sObject) {
    	 StringBuilder string = new StringBuilder();
    	 
    	 string.append("<DPSerialization>\n\t<complexType xsi:type=\"genericCheckpointing.util.");
    	 Class<?> cls = sObject.getClass();
    	 
    	 string.append(cls.getSimpleName() + "\">\n");
    	 
    	 Field[] fieldList =  cls.getDeclaredFields();
    	 for (int j=0; j<fieldList.length; j++) {
    		 
             Class fieldClass = fieldList[j].getType();
             String fieldName = fieldList[j].getName();
             
             
             
    		 try {
    			//get field name
    			 String methodName = "get" + fieldName;
        		 Method getterMethod = cls.getDeclaredMethod(methodName);
        		 Object invokeRet = getterMethod.invoke(sObject);
        		 if(fieldClass == int.class && (int)invokeRet > 10) {
        			 string.append("\t\t<" + fieldName + " xsi:type=\"xsd:" + fieldClass.getSimpleName() + "\">");
            		 string.append(invokeRet);
            		 string.append("</" + fieldName + ">\n");
        		 } else if(fieldClass == double.class && (double)invokeRet > 10) {
        			 string.append("\t\t<" + fieldName + " xsi:type=\"xsd:" + fieldClass.getSimpleName() + "\">");
            		 string.append(invokeRet);
            		 string.append("</" + fieldName + ">\n");
        		 } else if(fieldClass == long.class && (long)invokeRet > 10) {
        			 string.append("\t\t<" + fieldName + " xsi:type=\"xsd:" + fieldClass.getSimpleName() + "\">");
            		 string.append(invokeRet);
            		 string.append("</" + fieldName + ">\n");
        		 } else if(fieldClass == String.class) {
        			 string.append("\t\t<" + fieldName + " xsi:type=\"xsd:string\">");
            		 string.append(invokeRet);
            		 string.append("</" + fieldName + ">\n");
        		 } else if(!(fieldClass == int.class) && !(fieldClass == double.class) && !(fieldClass == long.class)) {
        			 string.append("\t\t<" + fieldName + " xsi:type=\"xsd:" + fieldClass.getSimpleName() + "\">");
            		 string.append(invokeRet);
            		 string.append("</" + fieldName + ">\n");
        		 }
        		 
    		 } catch(Exception e) {
    			 e.printStackTrace();
    		 }
             
    	 }
    	 string.append("\t</complexType>\n</DPSerialization>\n");
    	 fp.writeLine(string.toString());
    	 //System.out.println(string);
     }     
}
