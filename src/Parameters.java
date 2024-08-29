//import Interactive;
package com.shogunautomation.opcuacli;

import com.shogunautomation.opcuacli.Config;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.regex.MatchResult;

public class Parameters {
	public static Config getConfig (String[] args) {
		Config config = new Config();
		Pattern equalPattern = Pattern.compile("--(?<key>\\w+)=\"?(?<value>.*)\"?");
		Pattern simplePattern = Pattern.compile("--(?<key>\\w+)");
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			String key;
			String value;
			try {
				Matcher equalMatcher = equalPattern.matcher(arg);
				Matcher simpleMatcher = simplePattern.matcher(arg);
				if (equalMatcher.matches()) {
					MatchResult result = equalMatcher.toMatchResult();
					key = result.group("key");
					value = result.group("value");
				} else if (simpleMatcher.matches()) {
					MatchResult result = simpleMatcher.toMatchResult();
					key = result.group("key");
					i++;
					value = args[i];
				} else {
					String message = String.format("Unknown argument => %s \n", arg);
					throw new Exception(message);
				}
				register(key, value);
				config.setParameter(key, value);
			} catch (Exception error) {
				System.out.println(error.getMessage());
			}
		}
		return config;
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
