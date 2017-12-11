package genericCheckpointing.util;

public class MyAllTypesFirst extends SerializableObject{
	private int myInt;
	private long myLong;
	private String myString;
	private boolean myBool;
	private int myOtherInt;
	private long myOtherLong;

	public MyAllTypesFirst() {}
	
	public MyAllTypesFirst(int param1, long param2, String param3, 
			boolean param4, int param5, long param6) {
		myInt = param1;
		myLong = param2;
		myString = param3;
		myBool = param4;
		myOtherInt = param5;
		myOtherLong = param6;
	}
	
	@Override
	public boolean equals(Object o) {
		MyAllTypesFirst obj = (MyAllTypesFirst) o;
		// TODO Auto-generated method stub
		if(myInt == obj.myInt && myLong == obj.myLong && myString.equals(obj.myString) && myBool == obj.myBool && myOtherInt == obj.myOtherInt) {
			return true;
		} 
		return false;
	}
	
	public int getmyInt() {
		return myInt;
	}

	public void setmyInt(int myInt) {
		this.myInt = myInt;
	}

	public long getmyLong() {
		return myLong;
	}

	public void setmyLong(long myLong) {
		this.myLong = myLong;
	}

	public String getmyString() {
		return myString;
	}

	public void setmyString(String myString) {
		this.myString = myString;
	}

	public Boolean getmyBool() {
		return myBool;
	}

	public void setmyBool(boolean myBool) {
		this.myBool = myBool;
	}

	public int getmyOtherInt() {
		return myOtherInt;
	}

	public void setmyOtherInt(int myOtherInt) {
		this.myOtherInt = myOtherInt;
	}

	public long getmyOtherLong() {
		return myOtherLong;
	}

	public void setmyOtherLong(long myOtherLong) {
		this.myOtherLong = myOtherLong;
	}
	
	
}
