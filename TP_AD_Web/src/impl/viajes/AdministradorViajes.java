package impl.viajes;

import impl.cargas.Carga;
import impl.misc.Ubicacion;
import impl.sucursales.AdministradorSucursales;
import impl.sucursales.Sucursal;
import impl.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import persistence.CargaDAO;
import persistence.CompaniaSeguroDAO;
import persistence.SeguroDAO;
import persistence.SucursalDAO;
import persistence.VehiculoDAO;
import persistence.ViajeDAO;
import views.viajes.CompaniaSeguroView;
import views.viajes.ParadaIntermediaView;
import views.viajes.SeguroView;
import views.viajes.ViajeView;

public class AdministradorViajes {
	private static AdministradorViajes instance;
	private ViajeDAO viajeDao;
	private CompaniaSeguroDAO companiaSeguroDao;
	private VehiculoDAO vehiculoDao;
	private SeguroDAO seguroDao;

	public static AdministradorViajes getInstance() {
		if (instance == null)
			instance = new AdministradorViajes();
		return instance;
	}

	private AdministradorViajes() {
		viajeDao = ViajeDAO.getInstance();
		companiaSeguroDao = CompaniaSeguroDAO.getInstance();
		vehiculoDao = VehiculoDAO.getInstance();
		seguroDao = SeguroDAO.getInstance();
	}

	public Viaje obtenerViaje(Integer codigoViaje) {
		return viajeDao.get(codigoViaje);
	}

	public Integer altaViaje(int idVehiculo, int idSeguro, ViajeView viaje) throws Exception {
		Vehiculo v = vehiculoDao.get(idVehiculo);
		Seguro s = seguroDao.get(idSeguro);
		if (v != null) {
			// el seguro puede ser null
			Viaje vi = new Viaje(v, s, viaje);
			return vi.getId();
		} else {
			throw new Exception("No existe vehiculo con el ID ingresado.");
		}
	}

	// REVISAR ESTE METODO QUE NO SE PUEDEN USAR VECTORES EN HIBERNATE
	public void determinarCostoViaje(Viaje v, List<Sucursal> sucursales) {
		if (v == null)
			return;
		AdministradorSucursales admSuc = AdministradorSucursales.getInstance();
		Calendar cal;
		ArrayList<ParadaIntermedia> paradasIntermedias = (ArrayList<ParadaIntermedia>) v.getParadasIntermedias();
		if (v.getParadasIntermedias().size() == 0) {
			cal = Calendar.getInstance();
			Sucursal sucursalA = null, sucursalB = null;
			for (Sucursal s : sucursales) {
				if (v.getOrigen().equals(s.getUbicacion()))
					sucursalA = s;
				else if (v.getDestino().equals(s.getUbicacion()))
					sucursalB = s;
			}
			if (sucursalA == null || sucursalB == null)
				return;
			float costo = admSuc.calcularHorasEntreSucursales(sucursalA, sucursalB);
			int horas = (int) costo;
			int minutos = (int) (60 * (costo - horas));
			cal.add(Calendar.HOUR, horas);
			cal.add(Calendar.MINUTE, minutos);
			v.setFechaLlegada(cal.getTime());
		} else if (v.getParadasIntermedias().size() > 0) {
			float horasDistancia = admSuc.calcularHorasEntreSucursales(admSuc.obtenerSucursalCercana(v.getOrigen()),
					admSuc.obtenerSucursalCercana(paradasIntermedias.get(0).getUbicacion()));
			int horas = (int) horasDistancia;
			int minutos = (int) (60 * (horasDistancia - horas));
			cal = Calendar.getInstance();
			cal.add(Calendar.HOUR, horas);
			cal.add(Calendar.MINUTE, minutos);
			v.getParadasIntermedias().iterator().next().setLlegada(cal.getTime());
			if (v.getParadasIntermedias().size() > 1) {
				for (int i = 1; i < v.getParadasIntermedias().size() - 1; i++) {
					Sucursal sucA = admSuc.obtenerSucursalCercana(paradasIntermedias.get(i - 1).getUbicacion());
					Sucursal sucB = admSuc.obtenerSucursalCercana(paradasIntermedias.get(i).getUbicacion());
					horasDistancia = admSuc.calcularHorasEntreSucursales(sucA, sucB);
					horas = (int) horasDistancia;
					minutos = (int) (60 * (horasDistancia - horas));
					cal.add(Calendar.HOUR, horas);
					cal.add(Calendar.MINUTE, minutos);
					paradasIntermedias.get(i).setLlegada(cal.getTime());
				}
			}
			Sucursal sucA = admSuc.obtenerSucursalCercana(paradasIntermedias.get(v.getParadasIntermedias().size() - 1).getUbicacion());
			Sucursal sucB = admSuc.obtenerSucursalCercana(v.getDestino());
			horasDistancia = admSuc.calcularHorasEntreSucursales(sucA, sucB);
			horas = (int) horasDistancia;
			minutos = (int) (60 * (horasDistancia - horas));
			cal.add(Calendar.HOUR, horas);
			cal.add(Calendar.MINUTE, minutos);
			v.setFechaLlegada(cal.getTime());
		}
	}

	// public void altaViajeExterno(List<Carga> cargas, Seguro seguro, Date
	// fechaSalida, Date fechaLLegada,
	// Proveedor proveedor, TipoVehiculo tipoVehiculo, List<CondicionEspecial>
	// condicionesEspeciales){
	//
	// Vehiculo vehiculo = null;
	// for(Vehiculo v : proveedor.getVehiculos())
	// if(v.getTipo().equals(tipoVehiculo))
	// vehiculo = v;
	//
	// viajesExternos.add(new Viaje(cargas, seguro, vehiculo, fechaSalida,
	// condicionesEspeciales, null));
	// }
	// public void actualizarViaje(Viaje viaje, Sucursal sucursal) {
	// for (Iterator<Carga> iterator = viaje.getCargas().iterator();
	// iterator.hasNext();) {
	// Carga carga = iterator.next();
	// if (obtenerMejorViaje(sucursal, carga) != null) {
	// sucursal.getDeposito().almacenarCarga(carga);
	// iterator.remove();
	// }
	// }
	// for (ParadaIntermedia parada : viaje.getParadasIntermedias()) {
	// if (parada.getUbicacion().equals(sucursal.getUbicacion())) {
	// parada.setChecked(true);
	// break;
	// }
	// }
	// for (Iterator<Carga> iterator =
	// sucursal.getDeposito().getCargas().iterator(); iterator.hasNext();) {
	// Carga carga = iterator.next();
	// Viaje mejorViaje = obtenerMejorViaje(sucursal, carga);
	// if (mejorViaje != null && mejorViaje.equals(viaje) &&
	// viaje.puedeTransportar(carga)) {
	// viaje.agregarCarga(carga);
	// iterator.remove();
	// }
	// }
	// }
	public Viaje obtenerMejorViaje(Sucursal sucursal, Carga carga) {
		Viaje mejorViaje = null;
		for (Viaje viaje : obtenerViajes()) {
			if (viaje.pasaPorSucursal(sucursal) && viaje.puedeTransportar(carga)) {
				if (mejorViaje == null
						|| (viaje.puedeTransportar(carga) && viaje.obtenerLlegadaAParada(sucursal).before(mejorViaje.obtenerLlegadaAParada(sucursal)))) {
					mejorViaje = viaje;
				}
			}
		}
		return mejorViaje;
	}

	public Integer altaCompaniaSeguro(CompaniaSeguroView c) {
		CompaniaSeguro compania = new CompaniaSeguro(c);
		return compania.getId();
	}

	public Integer agregarSeguro(Integer id, SeguroView s) throws Exception {
		CompaniaSeguro c = CompaniaSeguroDAO.getInstance().get(id);
		if (c != null) {
			return c.agregarSeguro(s);
		} else {
			throw new Exception("No existe compania de seguros con el id ingresado.");
		}
	}

	public void agregarCondicionEspecialAViaje(Integer id, String condicionEspecial) throws Exception {
		Viaje v = viajeDao.get(id);
		if (v != null) {
			v.agregarCondicionEspecial(condicionEspecial);
		} else {
			throw new Exception("No existe viaje con el id ingresado.");
		}
	}

	public Integer agregarParadaIntermediaAViaje(int id, ParadaIntermediaView p) throws Exception {
		Viaje v = viajeDao.get(id);
		if (v != null) {
			return v.agregarParadaIntermedia(p);
		} else {
			throw new Exception("No existe viaje con el id ingresado.");
		}
	}

	public List<Viaje> obtenerViajes() {
		return viajeDao.getAll();
	}

	public List<CompaniaSeguroView> obtenerCompaniasSeguroView() {
		List<CompaniaSeguroView> companias = new ArrayList<CompaniaSeguroView>();
		for (CompaniaSeguro cs : companiaSeguroDao.getAll()) {
			companias.add(cs.getView());
		}
		return companias;
	}

	public Integer obtenerIdViajeOptimo(Integer idCarga) throws Exception {
		Carga carga = CargaDAO.getInstance().get(idCarga);
		Sucursal sucursal = SucursalDAO.getInstance().obtenerSucursalAPartirDeCarga(idCarga);
		if (sucursal != null && carga != null) {
			Viaje viajeOptimo = obtenerViajeOptimo(sucursal.getUbicacion(), carga.getDestino());
			return viajeOptimo.getId();
		} else {
			throw new Exception("Hubo un error al intentar obtener el viaje optimo.");
		}
	}

	private Viaje obtenerViajeOptimo(Ubicacion origen, Ubicacion destino) {
		List<Viaje> viajesPosibles = obtenerViajesPosibles(origen, destino);
		Viaje viajeOptimo = null;
		Float distanciaOptima = Float.MAX_VALUE;
		for (Viaje v : viajesPosibles) {
			Float distanciaTemp = getDistancia(v, origen, destino);
			if (distanciaOptima > distanciaTemp) {
				viajeOptimo = v;
				distanciaOptima = distanciaTemp;
			}
		}
		return viajeOptimo;
	}

	private List<Viaje> obtenerViajesPosibles(Ubicacion origen, Ubicacion destino) {
		return ViajeDAO.getInstance().getViajesPosibles(origen.getId(), destino.getId());
	}

	private Float getDistancia(Viaje v, Ubicacion origen, Ubicacion destino) {
		Float distancia = 0f;
		ArrayList<ParadaIntermedia> paradas = (ArrayList<ParadaIntermedia>) v.getParadasIntermedias();
		Integer indiceComienzo = 0;

		for (int i = 0; i < paradas.size(); i++) {
			if (paradas.get(i).getUbicacion().equals(origen)) {
				indiceComienzo = i;
				break;
			}
		}

		for (int i = indiceComienzo; i < paradas.size(); i++) {
			distancia += calcularDistanciaEntreUbicaciones(paradas.get(i).getUbicacion(), paradas.get(i + 1).getUbicacion());
			if (paradas.get(i + 1).getUbicacion().equals(destino)) {
				break;
			}
		}

		return distancia;
	}
	
	private Float calcularDistanciaEntreUbicaciones(Ubicacion a, Ubicacion b) {
		//probamos con sucursales, si no usamos coordenadas
		Sucursal sucA = SucursalDAO.getInstance().obtenerSucursalDesdeUbicacion(a.getId());
		Sucursal sucB = SucursalDAO.getInstance().obtenerSucursalDesdeUbicacion(b.getId());
		if (sucA != null && sucB != null) {
			return SucursalDAO.getInstance().obtenerDistanciaEntreSucursales(sucA, sucB).getDistanciaEnKm();
		} else {
			return calcularDistanciaEntreUbicaciones(a, b);
		}
	}
}
