package impl.vehiculos;

import impl.sucursales.AdministradorSucursales;
import impl.sucursales.Sucursal;
import impl.viajes.AdministradorViajes;
import impl.viajes.Viaje;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import persistence.ProveedorDAO;
import persistence.SucursalDAO;
import views.vehiculos.VehiculoExternoView;
import views.vehiculos.VehiculoLocalView;

public class AdministradorVehiculos {
	private static AdministradorVehiculos instance;
	private List<Proveedor> proveedores;

	private AdministradorVehiculos() {
		proveedores = new ArrayList<Proveedor>();
	}

	public static AdministradorVehiculos getInstance() {
		if (instance == null)
			instance = new AdministradorVehiculos();
		return instance;
	}

	public Integer altaProveedor(String cuit, String nombre) {
		Proveedor p = new Proveedor(cuit, nombre);
		return p.getId();
	}

	public Integer altaVehiculoLocal(Integer idSucursal, VehiculoLocalView v) throws Exception {
		Sucursal s = SucursalDAO.getInstance().get(idSucursal);
		if (s != null) {
			return s.agregarVehiculo(v);
		} else {
			throw new Exception("No existe sucursal con el id ingresado.");
		}
	}
	
	public Integer altaVehiculoExterno(Integer idProveedor, VehiculoExternoView v) throws Exception {
		Proveedor p = ProveedorDAO.getInstance().get(idProveedor);
		if (p != null) {
			VehiculoExterno vehiculo = new VehiculoExterno(p, v);
			return vehiculo.getId();
		} else {
			throw new Exception("No existe sucursal con el id ingresado.");
		}
	}

	// public void altaVehiculoExterno(String cuitProveedor, String patente,
	// Tamano tamano, Float peso, Float tara, Float tarifa, TipoVehiculo tipo){
	// Proveedor p = obtenerProveedor(cuitProveedor);
	// if (p != null){
	// if (p.existeVehiculo(patente)){
	// p.agregarVehiculo(new VehiculoExterno(patente, tamano, peso, tara,
	// tarifa, tipo));
	// }
	// }
	// }
	public void realizarMantenimientoVehiculo(Integer idSucursal, String patente, boolean esEspecifico) {
		Sucursal s = AdministradorSucursales.getInstance().obtenerSucursal(idSucursal);
		if (s != null) {
			VehiculoLocal v = s.obtenerVehiculo(patente);
			if (v != null) {
				v.realizarMantenimiento(esEspecifico);
			}
		}
	}

	public boolean estaDisponibleVehiculo(Vehiculo vehiculo, Date fechaDesde, Date fechaHasta) {
		for (Viaje viaje : AdministradorViajes.getInstance().getViajes()) {
			if (viaje.getVehiculo().equals(vehiculo)) {
				if ((viaje.getFechaSalida().after(fechaDesde) && viaje.getFechaLlegada().before(fechaDesde))
						|| (viaje.getFechaSalida().before(fechaDesde) && viaje.getFechaLlegada().after(fechaHasta))
						|| (viaje.getFechaSalida().before(fechaDesde) && viaje.getFechaLlegada().before(fechaHasta)) || (!vehiculo.estaDisponible())) {
					return false;
				}
			}
		}
		return true;
	}

	public Proveedor obtenerProveedor(String cuit) {
		for (Proveedor p : proveedores) {
			if (p.getCuit().equals(cuit)) {
				return p;
			}
		}
		return null;
	}
	// public List<Proveedor> obtenerViajesDeProveedores(Date fechaSalida, Date
	// fechaLLegada, TipoVehiculo tipoVehiculo){
	//
	// List<Proveedor> proveedores = new ArrayList<Proveedor>();
	//
	// for(Proveedor p : proveedores){
	// for(VehiculoExterno ve : p.getVehiculos())
	// if(ve.getTipo().equals(tipoVehiculo)){
	// proveedores.add(p);
	// break;
	// }
	// }
	//
	// return proveedores;
	// }




}