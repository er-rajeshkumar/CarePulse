package com.carepulse.util;

public class NameUtil {

	 public static String getFullName(String firstName,String middleName,String lastName) {

			StringBuilder fullName = new StringBuilder();

			if(firstName != null) {
				fullName.append(firstName);
			}

			if(middleName != null) {
				fullName.append(" ").append(middleName);
			}

			if(lastName != null) {
				fullName.append(" ").append(lastName);
			}

			return fullName.toString().trim();
		}

	// Overloaded method without middle name
	    public static String getFullName(String firstName, String lastName) {
	        return getFullName(firstName, null, lastName);
	    }
}
