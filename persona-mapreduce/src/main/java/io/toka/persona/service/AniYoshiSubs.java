package io.toka.persona.service;

public class AniYoshiSubs {
	
	public static String getName(String line) {
		int nameStartIndex = line.indexOf("Main,") + 5;
		
		if (nameStartIndex <= 4)
			return "";
		
		String crop = line.substring(nameStartIndex, line.length());
		int nameEndIndex = crop.indexOf(",");
		return crop.substring(0, nameEndIndex);
	}
	
	public static String getDialogue(String line) {
		int dialogueStartIndex = line.indexOf("be1}") + 4;
		return line.substring(dialogueStartIndex);
	}
}
