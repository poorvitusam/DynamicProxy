
package genericCheckpointing.driver;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import genericCheckpointing.server.RestoreI;
import genericCheckpointing.server.StoreI;
import genericCheckpointing.server.StoreRestoreI;
import genericCheckpointing.util.MyAllTypesFirst;
import genericCheckpointing.util.MyAllTypesSecond;
import genericCheckpointing.util.ProxyCreator;
import genericCheckpointing.util.SerializableObject;
import genericCheckpointing.xmlStoreRestore.StoreRestoreHandler;


public class Driver {
    
    public static void main(String[] args) {
	
    	String SERDESER = "serdeser";
    	String DESER = "deser";
    	
		// FIXME: read the value of checkpointFile from the command line
	    String mode = args[0];
	    int N = Integer.parseInt(args[1]);
	    String filename = args[2];
	    
	    int NUM_OF_OBJECTS = N;
//	    if (mode.equals("serdeser")) {
//	    	NUM_OF_OBJECTS = N;
//	    } else {
//	    	
//	    }
		
		ProxyCreator pc = new ProxyCreator();
		
		// create an instance of StoreRestoreHandler (which implements
		// the InvocationHandler
		
		StoreRestoreHandler storeRestoreHandler = new StoreRestoreHandler();
		
		// create a proxy
		StoreRestoreI cpointRef = (StoreRestoreI) pc.createProxy(
									 new Class[] {
									     StoreI.class, RestoreI.class
									 }, 
									 storeRestoreHandler
									 );
			
		// FIXME: invoke a method on the handler instance to set the file name for checkpointFile and open the file
	
		MyAllTypesFirst myFirst;
		MyAllTypesSecond  mySecond;
		
		
		
		// Use an if/switch to proceed according to the command line argument
		// For deser, just deserliaze the input file into the data structure and then print the objects
		// The code below is for "serdeser" mode
		// For "serdeser" mode, both the serialize and deserialize functionality should be called.
		// create a data structure to store the objects being serialized
		ArrayList<SerializableObject> serializedRecord = new ArrayList<SerializableObject>();
		
		if(!mode.equals(DESER)) {
			storeRestoreHandler.openFile(filename, "write", "output.txt");
	        // NUM_OF_OBJECTS refers to the count for each of MyAllTypesFirst and MyAllTypesSecond
			for (int i=0; i<NUM_OF_OBJECTS; i++) {
				
				Random rand = new Random();
				int paramInt = getRandomInt(rand);
				double paramDouble = getRandomDouble(rand);
				float paramFloat = getRandomFloat(rand);
				boolean paramBool = false;
				long paramLong = getRandomLong(rand);
				
			    // FIXME: create these object instances correctly using an explicit value constructor
			    // use the index variable of this loop to change the values of the arguments to these constructors
			    myFirst = new MyAllTypesFirst(paramInt, paramLong, String.valueOf(i), paramBool, paramInt, paramLong);
			    mySecond = new MyAllTypesSecond(paramFloat, (short)paramInt, paramDouble, (char)paramInt, (short)paramInt, paramDouble);
		
			    // FIXME: store myFirst and mySecond in the data structure
			    serializedRecord.add(myFirst);
			    serializedRecord.add(mySecond);
			    
			    ((StoreI) cpointRef).writeObj(myFirst, 0,"XML");
			    ((StoreI) cpointRef).writeObj(mySecond, 0,"XML");
			}
		} else {
			storeRestoreHandler.openFile(filename, "read", "output.txt");
		}
		
		SerializableObject myRecordRet;
	
		// create a data structure to store the returned ojects
		ArrayList<SerializableObject> deserializedRecord = new ArrayList<SerializableObject>();
		
		for (int j=0; j<2*NUM_OF_OBJECTS; j++) {
	
		    myRecordRet = ((RestoreI) cpointRef).readObj(filename, "XML");
		    // FIXME: store myRecordRet in the vector
		    deserializedRecord.add(myRecordRet);
		    
		}
	
		if(mode.equals(SERDESER)) {
			storeRestoreHandler.closeFile("write");
		} else {
			storeRestoreHandler.closeFile("read");
		}
		
		// FIXME: invoke a method on the handler to close the file (if it hasn't already been closed)
	
		if(mode.equals(SERDESER)) {
			// FIXME: compare and confirm that the serialized and deserialzed objects are equal. 
			// The comparison should use the equals and hashCode methods. Note that hashCode 
			// is used for key-value based data structures
			int mismatches = 0;
			for(int i=0; i<2*NUM_OF_OBJECTS; i++) {
				SerializableObject der = deserializedRecord.get(i);
				SerializableObject ser = serializedRecord.get(i);
				if(!der.equals(ser)) {
					mismatches++;
				}
			}
			System.out.println(mismatches + " mismatches found");    
		} else {
			for(SerializableObject obj:deserializedRecord) {
				Class<?> cls = obj.getClass();
				System.out.println(cls.getSimpleName() + "\n");
		    	 
		    	 Field[] fieldList =  cls.getDeclaredFields();
		    	 for (int j=0; j<fieldList.length; j++) {
		    		 
		             Class fieldClass = fieldList[j].getType();
		             String fieldName = fieldList[j].getName();
		             
		    		 try {
		    			//get field name
		    			 String methodName = "get" + fieldName;
		        		 Method getterMethod = cls.getDeclaredMethod(methodName);
		        		 Object invokeRet = getterMethod.invoke(obj);
		        		 System.out.println("\t" + fieldName + " " + invokeRet + "\n");
		    		 } catch(Exception e) {
		    			 e.printStackTrace();
		    		 }
		    	 }
		    	 System.out.println("\n");
			}
		}
    }
    
    public static int getRandomInt(Random rand) {
    	return rand.nextInt(50);
    }
    
    public static float getRandomFloat(Random rand) {
    	return rand.nextFloat();
    }
    
    public static double getRandomDouble(Random rand) {
    	//(rand.nextDouble() * (max-min)) + min;
    	return (rand.nextDouble() * 50);
    }
    
    public static long getRandomLong(Random rand) {
    	return rand.nextLong();
    }
}