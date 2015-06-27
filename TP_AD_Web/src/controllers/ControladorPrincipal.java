package controllers;

import impl.cargas.AdministradorCargas;
import impl.clientes.AdministradorClientes;
import impl.productos.AdministradorProductos;
import impl.sucursales.AdministradorSucursales;
import impl.vehiculos.AdministradorVehiculos;
import impl.viajes.AdministradorViajes;
import views.clientes.EmpresaView;
import views.clientes.ParticularView;
import views.clientes.ReceptorView;

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
	private AdministradorProductos administradorProductos;

	private ControladorPrincipal() {
		administradorViajes = AdministradorViajes.getInstance();
		administradorSucursales = AdministradorSucursales.getInstance();
		administradorCargas = AdministradorCargas.getInstance();
		administradorVehiculos = AdministradorVehiculos.getInstance();
		administradorClientes = AdministradorClientes.getInstance();
		administradorProductos = AdministradorProductos.getInstance();
	}

	public Integer altaEmpresa(EmpresaView empresa) {
		return administradorClientes.altaClienteEmpresa(empresa);
	}
	
	public Integer altaParticular(ParticularView particular) {
		return administradorClientes.altaClienteParticular(particular);
	}
	
	public Integer agregarReceptor(Integer clienteId, ReceptorView receptor) {
		try {
			return administradorClientes.agregarReceptor(clienteId, receptor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
