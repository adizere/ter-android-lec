package fr.univ.orleans.ter.lec.utility;

import java.util.HashMap;
import java.util.Locale;

import android.util.Log;

public class LocaleDefs {
	
	private static final HashMap<String, Locale> defs = getLocaleMap();
	
	private static HashMap<String, Locale> getLocaleMap(){
		HashMap<String, Locale> retMap = new HashMap<String, Locale>();
		
		retMap.put("Français", Locale.FRANCE);
		retMap.put("English", Locale.ENGLISH);
		retMap.put("German", Locale.GERMAN);
		
		
		return retMap;
	}
	
	public static Locale getLocaleForLanguageName(String langName) {
		if (defs.containsKey(langName)){
			return defs.get(langName);
		} else {
			Log.e("","Requested Local for an unknown language name: " + langName);
			return null;
		}
	}
}
