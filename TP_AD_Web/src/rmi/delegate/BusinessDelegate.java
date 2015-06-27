package rmi.delegate;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import rmi.tda.Parametros;
import rmi.tda.TDAControladorPrincipal;

public class BusinessDelegate {
	private static BusinessDelegate instance;
	private TDAControladorPrincipal interfaz;

	private BusinessDelegate() {
		try {
			Registry registry = LocateRegistry.getRegistry(Parametros.getIp(), Parametros.getPort());
			interfaz = (TDAControladorPrincipal) registry.lookup(Parametros.getServerName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static BusinessDelegate getInstance() {
		if (instance == null)
			instance = new BusinessDelegate();
		return instance;
	}

	public TDAControladorPrincipal getInterfaz() {
		return interfaz;
	}
}
