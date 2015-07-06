package rmi.tda;

import java.rmi.Remote;
import java.util.Date;
import java.util.List;

import views.cargas.CargaView;
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

public interface TDAControladorPrincipal extends Remote {
	public void actualizarPrecioVehiculo(Integer id, Float precio) throws Exception;

	public void agregarTarea(Integer id, TareaView tarea) throws Exception;

	public Integer agregarEmpleadoASucursal(Integer idSucursal, EmpleadoView empleado) throws Exception;

	public void bajaEmpleado(Integer id) throws Exception;

	public List<CompaniaSeguroView> getCompaniasSeguroView() throws Exception;

	public void agregarReceptor(Integer id, ReceptorView receptor) throws Exception;

	public Integer altaEmpresa(EmpresaView empresa) throws Exception;

	public Integer altaParticular(ParticularView particular) throws Exception;

	public Integer altaProducto(ProductoView p) throws Exception;

	public void agregarCondicionEspecialAProducto(Integer parseInt, String condicionEspecial) throws Exception;

	public Integer altaSucursal(SucursalView s) throws Exception;

	public void agregarDistanciaEntreSucursales(DistanciaEntreSucursalesView d) throws Exception;

	public Integer altaProveedor(ProveedorView proveedor) throws Exception;

	public Integer altaVehiculoExterno(Integer parseInt, VehiculoExternoView v) throws Exception;

	public Integer altaVehiculoLocal(Integer parseInt, VehiculoLocalView v, PlanMantenimientoView p, Integer idEmpleado) throws Exception;

	public void agregarCondicionEspecialAViaje(Integer parseInt, String condicionEspecial) throws Exception;

	public void agregarParadaIntermediaAViaje(Integer parseInt, ParadaIntermediaView p) throws Exception;

	public Integer agregarSeguro(Integer idCompania, SeguroView s) throws Exception;

	public Integer altaCompaniaSeguro(CompaniaSeguroView c) throws Exception;

	public Integer altaViaje(Integer idVehiculo, Integer idSeguro, ViajeView viaje) throws Exception;

	public SucursalCargaView obtenerSucursalCargaView(Integer idCarga) throws Exception;

	public Integer altaCarga(Integer idSucursal, Integer idCliente, CargaView carga, boolean esInternacional, boolean esExclusiva) throws Exception;

	public List<PagoView> obtenerPagos() throws Exception;

	public void realizarPago(Integer idPago) throws Exception;

	public void realizarCobroParcial(Integer idFactura, Float monto) throws Exception;

	public List<ParticularView> obtenerClientesParticulares() throws Exception;

	public List<EmpresaView> obtenerClientesEmpresas() throws Exception;
	
	public List<ProductoView> obtenerProductosView() throws Exception;
	
	public ViajeView obtenerViajeActivo(Integer idChofer) throws Exception;
	
	public List<SucursalView> obtenerSucursales() throws Exception;
	
	public List<CargaView> obtenerCargasView() throws Exception;

	public SucursalView obtenerSucursal(Integer idSucursal) throws Exception;

	public Date fechaProbableLlegada(Integer id) throws Exception;
	
	public void registrarLlegada(Integer idViaje) throws Exception;

	public void cancelarCarga(Integer idCarga) throws Exception;
}
