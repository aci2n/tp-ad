package rmi.tda;

public class Parametros {
	private static final int PORT = 10101;
	private static final String SERVER_NAME = "RMIServer";
	private static final String IP = "localhost";

	public static int getPort() {
		return PORT;
	}

	public static String getServerName() {
		return SERVER_NAME;
	}

	public static String getIp() {
		return IP;
	}

}
