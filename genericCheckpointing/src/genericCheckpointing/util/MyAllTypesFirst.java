package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
	private int myInt;

	public MyAllTypesFirst(int param1) {
		myInt = param1;
	}
	
	public int getmyInt() {
		return myInt;
	}

	public void setmyInt(int myInt) {
		this.myInt = myInt;
	}
	
	
}
