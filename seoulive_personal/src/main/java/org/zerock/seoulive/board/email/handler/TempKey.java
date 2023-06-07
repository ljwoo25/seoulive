package org.zerock.seoulive.board.email.handler;

import java.util.Random;

public class TempKey {
	private boolean lowerCheck;
	private int size;
	
	public String getKey(int size, boolean lowerCheck) {
		this.size = size;
		this.lowerCheck = lowerCheck;
		return init();
	}	// end getKey
	
	private String init() {
		Random ran = new Random();
		StringBuffer sb = new StringBuffer();
		int num = 0;
		do {
			num = ran.nextInt(75)+48;
			if((num>=48 && num<=57)||(num<=65 && num <=90) ||(num>=97 && num<=122)) {
				sb.append((char) num);
			}else {
				continue;
			}	// end if-else
			
		}while(sb.length()<size);
		// end do-while
		if(lowerCheck) {
			return sb.toString().toLowerCase();
		}	// if
		return sb.toString();
	}	// end init
}	// end class
