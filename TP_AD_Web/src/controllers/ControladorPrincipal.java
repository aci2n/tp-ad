package controllers;

import impl.cargas.AdministradorCargas;
import impl.clientes.AdministradorClientes;
import impl.cobranzas.AdministradorCobranzas;
import impl.productos.AdministradorProductos;
import impl.sucursales.AdministradorSucursales;
import impl.vehiculos.AdministradorVehiculos;
import impl.viajes.AdministradorViajes;

import java.util.List;

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
	
	public Integer agregarReceptor(Integer clienteId, ReceptorView receptor) {
		try {
			return administradorClientes.agregarReceptor(clienteId, receptor);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
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
	
	public Integer agregarEmpleadoASucursal(Integer id, EmpleadoView empleado) {
		try {
			return administradorSucursales.agregarEmpleadoASucursal(id, empleado);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void agregarDistanciaEntreSucursales(DistanciaEntreSucursalesView dist) {
		try {
			administradorSucursales.agregarDistanciaEntreSucursales(dist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	//	PRODUCTOS
	
	public void agregarCondicionEspecialAProducto(Integer id, String condicionEspecial) {
		try {
			administradorProductos.agregarCondicionEspecialAProducto(id, condicionEspecial);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Integer altaProducto(ProductoView producto) {
		return administradorProductos.altaProducto(producto);
	}
	
	
	//	VEHICULOS
	
	public Integer altaProveedor(ProveedorView proveedor) {
		return administradorVehiculos.altaProveedor(proveedor);
	}
	
	public Integer altaVehiculoExterno(Integer idProveedor, VehiculoExternoView vehiculoExterno) {
		try {
			return administradorVehiculos.altaVehiculoExterno(idProveedor, vehiculoExterno);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer altaVehiculoLocal(Integer idSucursal, VehiculoLocalView v, PlanMantenimientoView p) {
		try {
			return administradorVehiculos.altaVehiculoLocal(idSucursal, v, p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public void agregarCondicionEspecialAViaje(Integer id, String condicionEspecial) {
		try {
			administradorViajes.agregarCondicionEspecialAViaje(id, condicionEspecial);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void agregarParadaIntermediaAViaje(Integer viajeId, ParadaIntermediaView p) {
		try {
			administradorViajes.agregarParadaIntermediaAViaje(viajeId, p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Integer agregarSeguro(Integer companiaId, SeguroView s) {
		try {
			return administradorViajes.agregarSeguro(companiaId, s);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public Integer altaCompaniaSeguro(CompaniaSeguroView c) {
		return administradorViajes.altaCompaniaSeguro(c);
	}


	public Integer altaViaje(Integer idVehiculo, Integer idSeguro, ViajeView viaje) {
		try {
			return administradorViajes.altaViaje(idVehiculo, idSeguro, viaje);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	public void actualizarPrecioVehiculo(Integer id, Float precio) {
		try {
			administradorVehiculos.actualizarPrecioVehiculo(id, precio);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void agregarTarea(Integer id, TareaView tarea) {
		try {
			administradorVehiculos.agregarTarea(id, tarea);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void bajaEmpleado(Integer id) {
		try {
			administradorSucursales.bajaEmpleado(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public List<CompaniaSeguroView> getCompaniasSeguroView() {
		return administradorViajes.obtenerCompaniasSeguroView();
	}
	
	
	//	COBRANZAS Y PAGOS
	
	public List<PagoView> obtenerPagos() {
		return administradorCobranzas.obtenerPagos();
	}
	
	public List<CuentaCorrienteView> obtenerCuentasCorrientes() {
		return administradorCobranzas.obtenerCuentasCorrientes();
	}
	
}
