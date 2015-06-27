package rmi.delegate;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.tda.Parametros;
import rmi.tda.TDAControllerAdministrador;

public class BusinessDelegate {
	private static BusinessDelegate instance;
	private TDAControllerAdministrador interfaz;

	private BusinessDelegate() {
		try {
			Registry registry = LocateRegistry.getRegistry(Parametros.getIp(), Parametros.getPort());
			interfaz = (TDAControllerAdministrador) registry.lookup(Parametros.getServerName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BusinessDelegate getInstance() {
		if (instance == null)
			instance = new BusinessDelegate();
		return instance;

	}

	public TDAControllerAdministrador getInterfaz() {
		return interfaz;
	}
}
