package controllers;

import impl.cargas.AdministradorCargas;
import impl.clientes.AdministradorClientes;
import impl.sucursales.AdministradorSucursales;
import impl.vehiculos.AdministradorVehiculos;
import impl.viajes.AdministradorViajes;

public class ControladorPrincipal {
	public static ControladorPrincipal getInstance() {
		if (instance == null)
			instance = new ControladorPrincipal();
		return instance;
	}

	private static ControladorPrincipal instance;
	private AdministradorViajes administradorViajes;
	private AdministradorSucursales administradorSucursales;
	private AdministradorCargas administradorCargas;
	private AdministradorVehiculos administradorVehiculos;
	private AdministradorClientes administradorClientes;

	private ControladorPrincipal() {
		administradorViajes = AdministradorViajes.getInstance();
		administradorSucursales = AdministradorSucursales.getInstance();
		administradorCargas = AdministradorCargas.getInstance();
		administradorVehiculos = AdministradorVehiculos.getInstance();
		administradorClientes = AdministradorClientes.getInstance();
	}

	// GETTERS PARA TESTEAR
	public AdministradorViajes getAdministradorViajes() {
		return administradorViajes;
	}

	public AdministradorSucursales getAdministradorSucursales() {
		return administradorSucursales;
	}

	public AdministradorCargas getAdministradorCargas() {
		return administradorCargas;
	}

	public AdministradorVehiculos getAdministradorVehiculos() {
		return administradorVehiculos;
	}

	public AdministradorClientes getAdministradorClientes() {
		return administradorClientes;
	}
}
