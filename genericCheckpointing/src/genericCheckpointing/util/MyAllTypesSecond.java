package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject{
	private int myInt;
	
	public MyAllTypesSecond(int param1) {
		myInt = param1;
	}
	
	public int getmyInt() {
		return myInt;
	}

	public void setmyInt(int myInt) {
		this.myInt = myInt;
	}
	

}
