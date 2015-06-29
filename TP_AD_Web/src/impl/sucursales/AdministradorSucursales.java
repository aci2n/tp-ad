package impl.sucursales;

import impl.cargas.Carga;
import impl.clientes.Cliente;
import impl.misc.Coordenada;
import impl.misc.Ubicacion;
import impl.personal.Empleado;
import impl.productos.ItemProducto;
import impl.productos.Producto;
import impl.viajes.AdministradorViajes;
import impl.viajes.Viaje;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import persistence.EmpleadoDAO;
import persistence.SucursalDAO;
import views.personal.EmpleadoView;
import views.sucursales.DistanciaEntreSucursalesView;
import views.sucursales.SucursalView;

public class AdministradorSucursales {
	private static AdministradorSucursales instance;
	private SucursalDAO sucursalDao;
	private EmpleadoDAO empleadoDao;

	public static AdministradorSucursales getInstance() {
		if (instance == null)
			instance = new AdministradorSucursales();
		return instance;
	}

	private AdministradorSucursales() {
		sucursalDao = SucursalDAO.getInstance();
		empleadoDao = EmpleadoDAO.getInstance();
	}

	public Integer altaSucursal(SucursalView s) {
		Ubicacion ubicacion = null;
		if (s.getUbicacion() != null) {
			Coordenada coordenada = null;
			if (s.getUbicacion().getCoordenadaDestino() != null) {
				coordenada = new Coordenada(s.getUbicacion().getCoordenadaDestino().getLatitud(), s.getUbicacion().getCoordenadaDestino()
						.getLongitud());
			}
			ubicacion = new Ubicacion(s.getUbicacion().getPais(), s.getUbicacion().getCiudad(), s.getUbicacion().getProvincia(), s.getUbicacion()
					.getCalle(), s.getUbicacion().getAltura(), s.getUbicacion().getPiso(), s.getUbicacion().getDepartamento(), coordenada);
		}
		Sucursal sucursal = new Sucursal(s.getNombre(), ubicacion);
		return sucursal.getId();
	}

	public Integer agregarEmpleadoASucursal(Integer id, EmpleadoView e) throws Exception {
		Sucursal s = sucursalDao.get(id);
		if (s != null) {
			return s.agregarEmpleado(e);
		} else {
			throw new Exception("No existe sucursal con el id ingresado.");
		}
	}

	public void agregarDistanciaEntreSucursales(DistanciaEntreSucursalesView d) throws Exception {
		if (d.getSucursalA().equals(d.getSucursalB()))
			throw new Exception("Las sucursales A y B tienen el mismo ID.");
		Sucursal sucursalA = SucursalDAO.getInstance().get(d.getSucursalA());
		Sucursal sucursalB = SucursalDAO.getInstance().get(d.getSucursalB());
		if (sucursalA != null && sucursalB != null) {
			new DistanciaEntreSucursales(sucursalA, sucursalB, d.getDuracionEnHoras(), d.getDuracionEnKm(), d.getCosto());
		} else {
			throw new Exception("Al menos una de las sucursales ingresadas no existe.");
		}
	}

	public float calcularHorasEntreSucursales(Sucursal sucursalA, Sucursal sucursalB) {
		DistanciaEntreSucursales dist = sucursalDao.obtenerDistanciaEntreSucursales(sucursalA, sucursalB);
		return dist != null ? dist.getDuracionEnHoras() : 0;
	}

	public Sucursal obtenerSucursalCercana(Ubicacion ubicacion) {
		Sucursal cercana = null;
		for (Sucursal sucursal : sucursalDao.getAll()) {
			if (cercana == null
					|| cercana.getUbicacion().calcularDistanciaEnKilometros(ubicacion) > sucursal.getUbicacion().calcularDistanciaEnKilometros(
							ubicacion)) {
				cercana = sucursal;
			}
		}
		return cercana;
	}

	public void asignarCargaASucursal(int codigoSucursal, Carga carga) throws Exception {
		Sucursal sucursal = obtenerSucursal(codigoSucursal);
		if (sucursal != null) {
			Cliente cliente = carga.getCliente();
			if (cliente != null) {
				if (!sucursal.existeCarga(carga.getId())) {
					boolean tieneMaterialesProhibidos = false;
					for (ItemProducto item : carga.getProductos()) {
						if (!Producto.esMaterialPermitido(item.getProducto().getManipulacion())) {
							tieneMaterialesProhibidos = true;
							break;
						}
					}
					if (!tieneMaterialesProhibidos) {
						sucursal.getCargas().add(carga);
					} else {
						throw new Exception("La carga de codigo " + carga.getId() + " tiene materiales prohibidos.");
					}
				} else {
					throw new Exception("Esta sucursal ya tiene una carga de codigo: " + carga.getId() + ".");
				}
			} else {
				throw new Exception("Cliente de codigo " + carga.getCliente() + " inexistente.");
			}
		} else {
			throw new Exception("Sucursal de codigo " + codigoSucursal + " inexistente.");
		}
	}

	public Date estimarLlegada(Sucursal origen, Sucursal destino) {
		Date partida = new Date();
		Float distancia = null;
		for (Viaje v : AdministradorViajes.getInstance().obtenerViajes()) {
			if (v.getOrigen().equals(origen.getUbicacion()))
				if (v.getDestino().equals(destino.getUbicacion()))
					return v.getFechaLlegada();
				else {
					Date llegada = v.existeLLegadaUbicacion(destino.getUbicacion());
					if (llegada != null)
						return llegada;
				}
		}
		distancia = calcularHorasEntreSucursales(origen, destino);
		int minutos = (int) (distancia % 1) * 60;
		int horas = (int) (distancia - distancia % 1);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, horas);
		cal.add(Calendar.MINUTE, minutos);
		return cal.getTime();
	}

	public Sucursal obtenerSucursal(Integer numero) {
		return sucursalDao.get(numero);
	}

	// public Ubicacion obtenerUbicacion(int codigoUbicacion) {
	// for (Sucursal sucursal : sucursales) {
	// if (sucursal.getUbicacion().getId() == codigoUbicacion) {
	// return sucursal.getUbicacion();
	// }
	// }
	// return null;
	// }
	public Empleado obtenerEmpleado(String cuit) {
		return empleadoDao.obtenerPorCuit(cuit);
	}

	public void bajaEmpleado(int id) throws Exception {
		Empleado e = EmpleadoDAO.getInstance().get(id);
		if (e != null) {
			EmpleadoDAO.getInstance().delete(e);
		} else {
			throw new Exception("No existe empleado con el ID ingresado.");
		}
	}

	public List<EmpleadoView> obtenerEmpleadosView() {
		List<EmpleadoView> empleadosView = new ArrayList<EmpleadoView>();
		for (Empleado e : EmpleadoDAO.getInstance().getAll())
			empleadosView.add(e.getView());
		return empleadosView;
	}

	public Sucursal obtenerSucursalDesdeIdCarga(Integer idCarga) {
		return sucursalDao.obtenerSucursalDesdeIdCarga(idCarga);
	}
	
	public List<SucursalView> obtenerSucursalesView() {
		List<SucursalView> sucViews = new ArrayList<SucursalView>();
		for (Sucursal suc : sucursalDao.getAll()) {
			sucViews.add(suc.getView());
		}
		return sucViews;
	}
	
	public SucursalView obtenerSucursalView(Integer idSuc) {
		return sucursalDao.get(idSuc).getView();
	}
}
