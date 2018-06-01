/**
 * 
 */
package com.anirvan.reservation.ticket.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author anirvanroy
 *
 */
public class ConfirmationGenerator {
	
	public static Map<String, String> numGen = new HashMap<>();
	public static Map<Integer, Integer> tempNum = new HashMap<>();
	
	/**
	 * Generate an Alphanumeric confirmation code
	 * @return
	 */
	 public static String getConfirmationCode() {
		 String uuid = UUID.randomUUID().toString();
		 while(numGen.containsKey(uuid)) {
			 uuid = UUID.randomUUID().toString();
		 }
		 numGen.put(uuid, uuid);
		 return uuid;
	}
	 
	 /**
	  * Generate a Temporary confirmation code
	  * @return
	  */
	 public static int getTemporaryConfirmationCode() {
		 int random = ThreadLocalRandom.current().nextInt();
		 while(tempNum.containsKey(random)) {
			 random = ThreadLocalRandom.current().nextInt();
		 }
		tempNum.put(new Integer(random), new Integer(random));
		return random;
	}

}
