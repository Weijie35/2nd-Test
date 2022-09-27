package edu.qc.seclass;

public class MyCustomString implements MyCustomStringInterface{

	//the attribute to store the string
	public String value;
	
    /*construction method*/ 
	public MyCustomString(){
		this.value = null;
	}
	
	@Override
	public String getString() {
		if(this.value != null) {
			return this.value;
		}
		return null;
	}

	@Override
	public void setString(String string) {
		this.value= string;
	}

	@Override
	public int countNumbers() throws NullPointerException{
		if(this.value == null) {
			throw new NullPointerException();
		}
		int count=0;
		boolean flag = true;//for the contiguous digits
		for(int i=0; i<this.value.length(); i++) {
			if(Character.isDigit(this.value .charAt(i))&& flag) {
				count++;
				flag = false;
			}else if(!Character.isDigit(this.value .charAt(i))){//begin to find new number
				flag = true;
			}
		}
		return count;
	}
    /**
     * Returns a string that consists of all and only the characters in positions n, 2n, 3n, and
     * so on in the current string
     */
	@Override
	public String getEveryNthCharacterFromBeginningOrEnd(int n, boolean startFromEnd) {
		if(n <= 0) {
			throw new IllegalArgumentException();
		}
		else if(this.value == null && n > 0) {//when the string is null and n >0 , nullPointerException
			throw new NullPointerException();
		}
		else if(this.value == "" || this.value.length() < n) {
			return "";
		}
		
        //regular part
		String resultString = "";
		int stringLength = this.value.length();
		int i;
		if(startFromEnd) {//start counting from the end
			for(i = stringLength % n; i < stringLength; i+=n) {//get the first element by modulus
				resultString = resultString + this.value.charAt(i);
			}
		}else {//false mean to start counting from the beginning
			for(i = n-1; i < stringLength; i+=n) {
				resultString = resultString + this.value.charAt(i);
			}
		}
		return resultString;
	}
	
    /**
     * Replace the individual digits in the string, between startPosition and endPosition
     *(included), with the corresponding English names of those digits
     **/
	@Override
	public void convertDigitsToNamesInSubstring(int startPosition, int endPosition) {
		if(startPosition > endPosition) {
			throw new IllegalArgumentException("starPosition can not be greater than endPosition");
		}
		else if(startPosition < 1 || endPosition < 1) {
			throw new MyIndexOutOfBoundsException("starPosition or endPosition can not be less than 1");
		}
		else if(this.value == null) {
			throw new NullPointerException("the string is null");
		}
		else if(startPosition > this.value.length() || endPosition > this.value.length()) {
			throw new MyIndexOutOfBoundsException("starPosition or endPosition can not be greater than the length of the string");
		}
		
		//regular input
		StringBuilder temp = new StringBuilder();
		String[] Num = {"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine"};
		int i = 0;
		while(i < this.value.length()) {
			if(i >= startPosition-1 && i <= endPosition-1 && Character.isDigit(this.value.charAt(i))) {
				temp.append(Num[this.value.charAt(i)-'0']);//utilizes ASCII code
			    i++;
			}
			else {
				temp.append(this.value.charAt(i));
				i++;
			}
		}
		this.value = temp.toString();
	}
}
