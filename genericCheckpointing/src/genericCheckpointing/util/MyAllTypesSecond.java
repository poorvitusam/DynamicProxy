package genericCheckpointing.util;

public class MyAllTypesSecond extends SerializableObject{
	private float myFloatT;
	private short myShortT;
	private double myDoubleT;
	private char myCharT;
	private short myOtherShortT;
	private double myOtherDoubleT;
	
	public MyAllTypesSecond() {}
	
	public MyAllTypesSecond(float param1, short param2, double param3, char param4, short param5, double param6) {
		myFloatT = param1;
		myShortT = param2;
		myDoubleT = param3;
		myCharT = param4;
		myOtherShortT = param5;
		myOtherDoubleT = param6;
	}

	@Override
	public boolean equals(Object o) {
		MyAllTypesSecond obj = (MyAllTypesSecond) o;
		// TODO Auto-generated method stub
		if(myFloatT == obj.myFloatT && myShortT == obj.myShortT 
				&& myDoubleT==obj.myDoubleT && myCharT == obj.myCharT 
				&& myOtherShortT == obj.myOtherShortT && myOtherShortT == obj.myOtherShortT
				&& myOtherDoubleT == obj.myOtherDoubleT) {
			return true;
		} 
		return false;
	}
	public float getmyFloatT() {
		return myFloatT;
	}

	public void setmyFloatT(float myFloatT) {
		this.myFloatT = myFloatT;
	}

	public short getmyShortT() {
		return myShortT;
	}

	public void setmyShortT(short myShortT) {
		this.myShortT = myShortT;
	}

	public double getmyDoubleT() {
		return myDoubleT;
	}

	public void setmyDoubleT(double myDoubleT) {
		this.myDoubleT = myDoubleT;
	}

	public char getmyCharT() {
		return myCharT;
	}

	public void setmyCharT(char myCharT) {
		this.myCharT = myCharT;
	}

	public short getmyOtherShortT() {
		return myOtherShortT;
	}

	public void setmyOtherShortT(short myOtherShortT) {
		this.myOtherShortT = myOtherShortT;
	}

	public double getmyOtherDoubleT() {
		return myOtherDoubleT;
	}

	public void setmyOtherDoubleT(double myOtherDoubleT) {
		this.myOtherDoubleT = myOtherDoubleT;
	}
}
