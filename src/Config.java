package com.shogunautomation.opcuacli;

public class Config {
	String host;
	String port;
	String user;
	String password;

	void setParameter (String key, String value) throws InvalidParameterException {
		switch (key) {
			case "host" -> this.host = value;
			case "port" -> this.port = value;
			default -> throw new InvalidParameterException(key);
		}
	}

	public class InvalidParameterException extends Exception {
		public InvalidParameterException () {
			super("Invalid Parameter Requested");
		}

		public InvalidParameterException (String parameter) {
			super(String.format("Invalid Parameter Requested {%s}", parameter));
		}
	}
}

