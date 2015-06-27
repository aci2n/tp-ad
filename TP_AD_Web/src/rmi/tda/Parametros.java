package rmi.tda;

public class Parametros {
	private static final int PORT = 10101;
	private static final String serverName = "RMIServer";
	private static final String ip = "localhost";

	public static int getPort() {
		return PORT;
	}

	public static String getServername() {
		return serverName;
	}

	public static String getIp() {
		return ip;
	}

}
