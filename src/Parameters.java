//import Interactive;
//import Config;
package com.shogunautomation.opcuacli;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.MatchResult;

public class Parameters {
	//public static Config getConfig (String[] args) {
	public static void getConfig (String[] args) {
		//Config config = new Config();
		Pattern equalPattern = Pattern.compile("--(?<key>\\w+)=\"?(?<value>.*)\"?");
		Pattern simplePattern = Pattern.compile("--(?<key>\\w+)");
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			Matcher equalMatcher = equalPattern.matcher(arg);
			Matcher simpleMatcher = simplePattern.matcher(arg);
			if (equalMatcher.matches()) {
				MatchResult result = equalMatcher.toMatchResult();
				String key = result.group("key");
				String value = result.group("value");
				register(key, value);
			} else if (simpleMatcher.matches()) {
				MatchResult result = simpleMatcher.toMatchResult();
				String key = result.group("key");
				i++;
				String value = args[i];
				register(key, value);
			} else {
				System.out.printf("Unknown parameter => %s \n", arg);
			}
		}
		//return config;
	}

	private static void register (String key, String value) {
		System.out.printf("Recording... key => %s // value => %s \n", key, value);
	}
}

/*
--host
--port
--username
--password
--node
*/
