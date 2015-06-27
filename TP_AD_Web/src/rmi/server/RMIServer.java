package rmi.server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.controller.ControllerAdministrador;
import rmi.tda.Parametros;
import rmi.tda.TDAControllerAdministrador;

public class RMIServer {
	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.createRegistry(Parametros.getPort());
			TDAControllerAdministrador instancia = ControllerAdministrador.getInstance();
			registry.bind(Parametros.getServerName(), instancia);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
