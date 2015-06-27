package controllers;

import impl.cargas.AdministradorCargas;
import impl.clientes.AdministradorClientes;
import impl.cobranzas.AdministradorCobranzas;
import impl.productos.AdministradorProductos;
import impl.sucursales.AdministradorSucursales;
import impl.vehiculos.AdministradorVehiculos;
import impl.viajes.AdministradorViajes;

import java.util.List;

import views.cargas.CargaView;
import views.clientes.CuentaCorrienteView;
import views.clientes.EmpresaView;
import views.clientes.PagoView;
import views.clientes.ParticularView;
import views.clientes.ReceptorView;
import views.personal.EmpleadoView;
import views.productos.ProductoView;
import views.sucursales.DistanciaEntreSucursalesView;
import views.sucursales.SucursalView;
import views.vehiculos.PlanMantenimientoView;
import views.vehiculos.ProveedorView;
import views.vehiculos.TareaView;
import views.vehiculos.VehiculoExternoView;
import views.vehiculos.VehiculoLocalView;
import views.viajes.CompaniaSeguroView;
import views.viajes.ParadaIntermediaView;
import views.viajes.SeguroView;
import views.viajes.ViajeView;

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
	private AdministradorCobranzas administradorCobranzas;


	private ControladorPrincipal() {
		administradorViajes = AdministradorViajes.getInstance();
		administradorSucursales = AdministradorSucursales.getInstance();
		administradorCargas = AdministradorCargas.getInstance();
		administradorVehiculos = AdministradorVehiculos.getInstance();
		administradorClientes = AdministradorClientes.getInstance();
		administradorProductos = AdministradorProductos.getInstance();
		administradorCobranzas = AdministradorCobranzas.getInstance();
	}

	
	//	CLIENTES
	
	public Integer altaEmpresa(EmpresaView empresa) {
		return administradorClientes.altaClienteEmpresa(empresa);
	}
	
	public Integer altaParticular(ParticularView particular) {
		return administradorClientes.altaClienteParticular(particular);
	}
	
	public Integer agregarReceptor(Integer clienteId, ReceptorView receptor) throws Exception {
		return administradorClientes.agregarReceptor(clienteId, receptor);
	}
	
	public List<EmpresaView> obtenerClientesEmpresas() {
		return administradorClientes.obtenerClientesEmpresas();
	}
	
	public List<ParticularView> obtenerClientesParticulares() {
		return administradorClientes.obtenerClientesParticulares();
	}
	
	
	//	SUCURSALES
	
	public Integer altaSucursal(SucursalView sucursal) {
		return administradorSucursales.altaSucursal(sucursal);
	}
	
	public Integer agregarEmpleadoASucursal(Integer id, EmpleadoView empleado) throws Exception {
		return administradorSucursales.agregarEmpleadoASucursal(id, empleado);
	}
	
	public void agregarDistanciaEntreSucursales(DistanciaEntreSucursalesView dist) throws Exception {
		administradorSucursales.agregarDistanciaEntreSucursales(dist);
	}
	
	
	//	PRODUCTOS
	
	public void agregarCondicionEspecialAProducto(Integer id, String condicionEspecial) throws Exception {
		administradorProductos.agregarCondicionEspecialAProducto(id, condicionEspecial);
	}
	
	public Integer altaProducto(ProductoView producto) {
		return administradorProductos.altaProducto(producto);
	}
	
	
	//	VEHICULOS
	
	public Integer altaProveedor(ProveedorView proveedor) {
		return administradorVehiculos.altaProveedor(proveedor);
	}
	
	public Integer altaVehiculoExterno(Integer idProveedor, VehiculoExternoView vehiculoExterno) throws Exception {
		return administradorVehiculos.altaVehiculoExterno(idProveedor, vehiculoExterno);
	}
	
	public Integer altaVehiculoLocal(Integer idSucursal, VehiculoLocalView v, PlanMantenimientoView p) throws Exception {
		return administradorVehiculos.altaVehiculoLocal(idSucursal, v, p);
	}
	
	public void agregarCondicionEspecialAViaje(Integer id, String condicionEspecial) throws Exception {
		administradorViajes.agregarCondicionEspecialAViaje(id, condicionEspecial);
	}


	public void agregarParadaIntermediaAViaje(Integer viajeId, ParadaIntermediaView p) throws Exception {
		administradorViajes.agregarParadaIntermediaAViaje(viajeId, p);
	}


	public Integer agregarSeguro(Integer companiaId, SeguroView s) throws Exception {
		return administradorViajes.agregarSeguro(companiaId, s);
	}
	
	public Integer altaCompaniaSeguro(CompaniaSeguroView c) {
		return administradorViajes.altaCompaniaSeguro(c);
	}


	public Integer altaViaje(Integer idVehiculo, Integer idSeguro, ViajeView viaje) throws Exception {
		return administradorViajes.altaViaje(idVehiculo, idSeguro, viaje);
	}


	public void actualizarPrecioVehiculo(Integer id, Float precio) throws Exception {
		administradorVehiculos.actualizarPrecioVehiculo(id, precio);
	}


	public void agregarTarea(Integer id, TareaView tarea) throws Exception {
		administradorVehiculos.agregarTarea(id, tarea);
	}


	public void bajaEmpleado(Integer id) throws Exception {
		administradorSucursales.bajaEmpleado(id);
	}


	public List<CompaniaSeguroView> getCompaniasSeguroView() {
		return administradorViajes.obtenerCompaniasSeguroView();
	}
	
	// CARGAS
	
	public List<CargaView> obtenerCargasView(){
		return administradorCargas.obtenerCargasView();
	}
	
	public Integer altaCarga(Integer idSucursal, Integer idCliente, CargaView carga) throws Exception {
		return administradorCargas.altaCarga(idSucursal, idCliente, carga);
	}
	
	
	//	COBRANZAS Y PAGOS
	
	public List<PagoView> obtenerPagos() {
		return administradorCobranzas.obtenerPagos();
	}
	
	public List<CuentaCorrienteView> obtenerCuentasCorrientes() {
		return administradorCobranzas.obtenerCuentasCorrientes();
	}
	
}
