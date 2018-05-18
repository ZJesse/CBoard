package org.cboard.controller;

public class PasswordEncryptUtil {

	private static DESPlus DES = null;
	
	public static DESPlus getDESPlusInstance() throws Exception{
		if(DES == null){
			synchronized (PasswordEncryptUtil.class) {
				if(DES == null){
					DES = new DESPlus();
				}
			}
		}
		return DES;
	}
}
