package rmi.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.tda.Parametros;
import rmi.tda.TDAControladorPrincipal;
import controllers.ControladorPrincipal;

public class RMIServer {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(Parametros.getPort());
			TDAControladorPrincipal instancia = ControladorPrincipal.getInstance();
			registry.bind(Parametros.getServerName(), instancia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
