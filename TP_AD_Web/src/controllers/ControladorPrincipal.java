package controllers;

import impl.cargas.AdministradorCargas;
import impl.cargas.Carga;
import impl.clientes.AdministradorClientes;
import impl.cobranzas.AdministradorCobranzas;
import impl.cobranzas.Factura;
import impl.personal.AdministradorPersonal;
import impl.productos.AdministradorProductos;
import impl.productos.Producto;
import impl.sucursales.AdministradorSucursales;
import impl.sucursales.Sucursal;
import impl.vehiculos.AdministradorVehiculos;
import impl.viajes.AdministradorViajes;
import impl.viajes.Viaje;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import persistence.CargaDAO;
import persistence.ProductoDAO;
import rmi.tda.TDAControladorPrincipal;
import views.cargas.CargaView;
import views.clientes.CuentaCorrienteView;
import views.clientes.EmpresaView;
import views.clientes.PagoView;
import views.clientes.ParticularView;
import views.clientes.ReceptorView;
import views.misc.SucursalCargaView;
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

public class ControladorPrincipal extends UnicastRemoteObject implements TDAControladorPrincipal {
	private static final long serialVersionUID = 1L;

	public static ControladorPrincipal getInstance() throws Exception {
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
	private AdministradorPersonal administradorPersonal;

	private ControladorPrincipal() throws Exception {
		administradorViajes = AdministradorViajes.getInstance();
		administradorSucursales = AdministradorSucursales.getInstance();
		administradorCargas = AdministradorCargas.getInstance();
		administradorVehiculos = AdministradorVehiculos.getInstance();
		administradorClientes = AdministradorClientes.getInstance();
		administradorProductos = AdministradorProductos.getInstance();
		administradorCobranzas = AdministradorCobranzas.getInstance();
		administradorPersonal = AdministradorPersonal.getInstance();
	}

	// CLIENTES
	public Integer altaEmpresa(EmpresaView empresa) {
		return administradorClientes.altaClienteEmpresa(empresa);
	}

	public Integer altaParticular(ParticularView particular) {
		return administradorClientes.altaClienteParticular(particular);
	}

	public void agregarReceptor(Integer clienteId, ReceptorView receptor) throws Exception {
		administradorClientes.agregarReceptor(clienteId, receptor);
	}

	public List<EmpresaView> obtenerClientesEmpresas() {
		return administradorClientes.obtenerClientesEmpresas();
	}

	public List<ParticularView> obtenerClientesParticulares() {
		return administradorClientes.obtenerClientesParticulares();
	}
	
	public List<Factura> obtenerFacturasDelCliente(Integer id) throws Exception {
		return administradorClientes.obtenerFacturasDelCliente(id);
	}

	// SUCURSALES
	public Integer altaSucursal(SucursalView sucursal) {
		return administradorSucursales.altaSucursal(sucursal);
	}

	public Integer agregarEmpleadoASucursal(Integer id, EmpleadoView empleado) throws Exception {
		return administradorSucursales.agregarEmpleadoASucursal(id, empleado);
	}

	public void agregarDistanciaEntreSucursales(DistanciaEntreSucursalesView dist) throws Exception {
		administradorSucursales.agregarDistanciaEntreSucursales(dist);
	}

	public List<SucursalView> obtenerSucursales() {
		return administradorSucursales.obtenerSucursalesView();
	}

	public SucursalView obtenerSucursal(Integer id) {
		return administradorSucursales.obtenerSucursalView(id);
	}

	// PRODUCTOS
	public void agregarCondicionEspecialAProducto(Integer id, String condicionEspecial) throws Exception {
		administradorProductos.agregarCondicionEspecialAProducto(id, condicionEspecial);
	}

	public Integer altaProducto(ProductoView producto) {
		return administradorProductos.altaProducto(producto);
	}

	public List<ProductoView> obtenerProductosView() {

		List<ProductoView> productos = new ArrayList<ProductoView>();
		for (Producto p : ProductoDAO.getInstance().getAll()) {
			ProductoView view = p.getView();
			view.setId(p.getId());
			productos.add(view);
		}
		return productos;

	}

	// VEHICULOS
	public Integer altaProveedor(ProveedorView proveedor) {
		return administradorVehiculos.altaProveedor(proveedor);
	}

	public Integer altaVehiculoExterno(Integer idProveedor, VehiculoExternoView vehiculoExterno) throws Exception {
		return administradorVehiculos.altaVehiculoExterno(idProveedor, vehiculoExterno);
	}

	public Integer altaVehiculoLocal(Integer idSucursal, VehiculoLocalView v, PlanMantenimientoView p, Integer idEmpleado) throws Exception {
		return administradorVehiculos.altaVehiculoLocal(idSucursal, v, p, idEmpleado);
	}

	// VIAJES
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
		// creo que esto no se va a usar
		return null;
	}

	public ViajeView obtenerViajeActivo(Integer idChofer) {
		return administradorViajes.obtenerViajeActivo(idChofer);
	}

	public Date fechaProbableLlegada(Integer idCarga) {
		Carga carga = administradorCargas.obtenerCarga(idCarga);
		Viaje viaje = administradorViajes.obtenerViajePorCarga(idCarga);
		return viaje.obtenerLlegadaCarga(carga);
	}

	public List<ViajeView> obtenerViajesView() {
		return administradorViajes.obtenerViajesView();
	}

	public void registrarParada(Integer idParada) throws Exception {
		administradorViajes.reportarSeguimiento(idParada);
	}

	public void registrarLlegada(Integer idViaje) {
		administradorViajes.registrarLlegada(idViaje);
	}

	// VEHICULOS

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
	public List<CargaView> obtenerCargasView() {
		return administradorCargas.obtenerCargasView();
	}

	public Integer altaCarga(Integer idSucursal, Integer idCliente, CargaView carga, boolean esInternacional, boolean esExclusiva) throws Exception {
		return administradorCargas.altaCarga(idSucursal, idCliente, carga, esInternacional, esExclusiva);
	}

	public SucursalCargaView obtenerSucursalCargaView(Integer idCarga) throws Exception {
		Sucursal s = administradorSucursales.obtenerSucursalDesdeIdCarga(idCarga);
		Carga c = administradorCargas.obtenerCarga(idCarga);
		if (s != null && c != null) {
			return new SucursalCargaView(s.getView(), c.getView());
		} else {
			throw new Exception("No existe la carga o no se encontro la sucursal asociada a la carga.");
		}
	}

	public void cancelarCarga(Integer idCarga) {
		Carga carga = administradorCargas.obtenerCarga(idCarga);
		administradorViajes.removerCarga(carga);
		CargaDAO.getInstance().delete(carga);
	}

	// COBRANZAS Y PAGOS
	public List<PagoView> obtenerPagos() {
		return administradorCobranzas.obtenerPagos();
	}

	public List<CuentaCorrienteView> obtenerCuentasCorrientes() {
		return administradorCobranzas.obtenerCuentasCorrientes();
	}

	public void realizarPago(Integer idPago) throws Exception {
		administradorCobranzas.realizarPago(idPago);
	}

	public void realizarCobroParcial(Integer idFactura, Float monto) throws Exception {
		administradorCobranzas.realizarCobroParcial(idFactura, monto);
	}

	// PERSONAL
	public List<EmpleadoView> obtenerChoferes() {
		return administradorPersonal.obtenerChoferes();
	}

}
