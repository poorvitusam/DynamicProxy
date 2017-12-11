package genericCheckpointing.xmlStoreRestore;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import genericCheckpointing.util.FileProcessor;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.SerializableObject;

public class XMLDeserialization implements DeserStrategy {
	private FileProcessor fp;
	
	public XMLDeserialization(FileProcessor fileprocessor) {
		fp = fileprocessor; 
	}
	
	public SerializableObject processInput() {
		String MYALLTYPESFIRST = "MyAllTypesFirst";
		String MYALLTYPESSECOND = "MyAllTypesSecond";
		String INT = "int";
		String FLOAT = "float";
		String DOUBLE = "double";
		String SHORT = "short";
		String LONG = "long";
		String BOOLEAN = "boolean";
		String STRING = "string";
		String CHAR = "char";
		
		SerializableObject obj = null;
    	 
		String nextLine;
		while ((nextLine = fp.readLine()) != null) {
			if(nextLine.contains("</DPSerialization>")) {
				fp.readLine();
				break;
			}
			
			if(nextLine.contains("<complexType xsi:type=\"genericCheckpointing.util.")) {
				int i = nextLine.lastIndexOf("<complexType xsi:type=\"genericCheckpointing.util.");
				int j = nextLine.indexOf("\">");
				if (nextLine.contains(MYALLTYPESFIRST)) {
					obj = new MyAllTypesFirst();
				} else if (nextLine.contains(MYALLTYPESSECOND)) {
					obj = new MyAllTypesSecond();
				}
			}
			
			if(nextLine.contains("xsi:type=\"xsd:")) {
				int startIndex = nextLine.indexOf("<") + 1;
				int endIndex = nextLine.indexOf("xsi:type") - 1;
				int startIndex1 = nextLine.indexOf("xsd:") + "xsd:".length();
				int endIndex1 = nextLine.indexOf(">") - 1;
				int startIndex2 = nextLine.indexOf(">") + 1; 
				int endIndex2 = nextLine.indexOf("</");
				
				String methodName = "set" + nextLine.substring(startIndex, endIndex);
				String fieldName = nextLine.substring(startIndex1, endIndex1);
				
				try{
					Class<?> cls = obj.getClass();
					if(fieldName.equals(INT)) {
						int value = Integer.parseInt(nextLine.substring(startIndex2, endIndex2));
						Method setterMethod = cls.getDeclaredMethod(methodName, Integer.TYPE);
						setterMethod.invoke(obj, value);
					} else if(fieldName.equals(FLOAT)) {
						float value = Float.parseFloat(nextLine.substring(startIndex2, endIndex2));
						Method setterMethod = cls.getDeclaredMethod(methodName, Float.TYPE);
						setterMethod.invoke(obj, value);
					} else if(fieldName.equals(DOUBLE)) {
						double value = Double.parseDouble(nextLine.substring(startIndex2, endIndex2));
						Method setterMethod = cls.getDeclaredMethod(methodName, Double.TYPE);
						setterMethod.invoke(obj, value);
					} else if(fieldName.equals(SHORT)) {
						short value = Short.parseShort(nextLine.substring(startIndex2, endIndex2));
						Method setterMethod = cls.getDeclaredMethod(methodName, Short.TYPE);
						setterMethod.invoke(obj, value);
					} else if(fieldName.equals(LONG)) {
						long value = Long.parseLong(nextLine.substring(startIndex2, endIndex2));
						Method setterMethod = cls.getDeclaredMethod(methodName, Long.TYPE);
						setterMethod.invoke(obj, value);
					} else if(fieldName.equals(BOOLEAN)) {
						boolean value = Boolean.parseBoolean(nextLine.substring(startIndex2, endIndex2));
						Method setterMethod = cls.getDeclaredMethod("setmyBool", Boolean.TYPE);
						setterMethod.invoke(obj, value);
					} else if(fieldName.equals(STRING)) {
						String value = nextLine.substring(startIndex2, endIndex2);
						Method setterMethod = cls.getDeclaredMethod(methodName, String.class);
						setterMethod.invoke(obj, value);
					} else if(fieldName.equals(CHAR)) {
						if (endIndex2 >= 0 && ((endIndex2 - 1) != startIndex2)) {
							char value = nextLine.substring(startIndex2, endIndex2).charAt(0);
							Method setterMethod = cls.getDeclaredMethod(methodName, Character.TYPE);
							setterMethod.invoke(obj, value);
						}
						
					}
					
				} catch(Exception e) {
					System.out.println("Exception caught");
					e.printStackTrace();
				}
			}
		}
    	return obj;
     }
}
