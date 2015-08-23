package com.gesoft.MainSearch;

public class staticNumint {
	static int intnum=0;
	
	
	public static int intNum(int intnumint){
		intnum=intnumint;
		return intnum;
		
	}
	public static int readintNum(){
		return intnum;
		
	}
	
	public static int updateintNum(){
		intnum=0;
		return intnum;
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) {
			System.out.println(intNum(i));
			System.out.println(readintNum());
			if (i==10) {
				updateintNum();
			}
		}

	}

}
