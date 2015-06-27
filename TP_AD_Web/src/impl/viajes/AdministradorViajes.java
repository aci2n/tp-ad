package impl.viajes;

import impl.cargas.Carga;
import impl.cargas.TipoCarga;
import impl.misc.Coordenada;
import impl.misc.Ubicacion;
//github.com/alvarocalace/tp_ad_web.git
import impl.sucursales.AdministradorSucursales;
import impl.sucursales.Sucursal;
import impl.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import persistence.CompaniaSeguroDAO;
import persistence.SeguroDAO;
import persistence.SucursalDAO;
import persistence.VehiculoDAO;
import persistence.ViajeDAO;
import util.Utilities;
import views.cargas.CargaView;
import views.viajes.CompaniaSeguroView;
import views.viajes.ParadaIntermediaView;
import views.viajes.SeguroView;
import views.viajes.ViajeView;

public class AdministradorViajes {
	private static final float VELOCIDAD_PROMEDIO = 180f;
	private static AdministradorViajes instance;
	private ViajeDAO viajeDao;
	private CompaniaSeguroDAO companiaSeguroDao;
	private VehiculoDAO vehiculoDao;
	private SeguroDAO seguroDao;
	private SucursalDAO sucursalDao;

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
		sucursalDao = SucursalDAO.getInstance();
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

	public ViajeOptimo obtenerViajeOptimo(Carga carga) throws Exception {
		List<Viaje> viajesPosibles = obtenerViajesPosibles(carga);
		ViajeOptimo viajeOptimo = null;
		for (Viaje v : viajesPosibles) {
			ViajeOptimo aux = v.getViajeOptimo(carga.getOrigen(), carga.getDestino());
			if (viajeOptimo == null || aux.getDistanciaOptima() < viajeOptimo.getDistanciaOptima()) {
				viajeOptimo = aux;
			}
		}
		return viajeOptimo;
	}

	private List<Viaje> obtenerViajesPosibles(Carga carga) {
		List<Viaje> viajes = ViajeDAO.getInstance().getAll();
		List<Viaje> viajesPosibles = new ArrayList<Viaje>();
		for (Viaje v : viajes) {
			if (v.puedeTransportar(carga) && v.tieneTrayecto(carga.getOrigen(), carga.getDestino())) {
				viajesPosibles.add(v);
			}
		}
		return viajesPosibles;
	}

	public void crearViajeEnBaseACarga(Carga c) throws Exception {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(c.getFechaMaximaEntrega());
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		// seteo fecha inicio viaje a 1 dia menos

		ViajeView vv = new ViajeView(Utilities.invParseDate(calendar.getTime()), Utilities.invParseDate(c.getFechaMaximaEntrega()), c.getOrigen()
				.getView(), c.getDestino().getView());

		Vehiculo vehiculoDisponible = obtenerVehiculoDisponible();
		Seguro seguro = obtenerSeguro(c.getTipo());
		if (vehiculoDisponible != null && seguro != null) {
			altaViaje(vehiculoDisponible.getId(), seguro.getId(), vv);
		} else {
			throw new Exception("No hay vehiculos o seguros disponibles.");
		}
	}

	public Vehiculo obtenerVehiculoDisponible() {
		for (Vehiculo v : VehiculoDAO.getInstance().getAll()) {
			if (v.estaDisponible()) {
				return v;
			}
		}
		return null;
	}

	public Seguro obtenerSeguro(TipoCarga tipo) {
		return SeguroDAO.getInstance().obtenerSeguroPorTipo(tipo);
	}

	public String fechaProbable(CargaView c) throws Exception {
		Carga carga = new Carga();
		Ubicacion origen = new Ubicacion();
		Ubicacion destino = new Ubicacion();
		origen.setCoordenadaDestino(new Coordenada(c.getOrigen().getCoordenadaDestino().getLatitud(), c.getOrigen().getCoordenadaDestino()
				.getLongitud()));
		destino.setCoordenadaDestino(new Coordenada(c.getDestino().getCoordenadaDestino().getLatitud(), c.getDestino().getCoordenadaDestino()
				.getLongitud()));
		carga.setOrigen(origen);
		carga.setDestino(destino);
		ViajeOptimo viaje = obtenerViajeOptimo(carga);
		if (viaje != null) {
			return calcularFechaProbable(carga, viaje);
		} else {
			throw new Exception("No existen viajes disponibles.");
		}
	}

	private String calcularFechaProbable(Carga carga, ViajeOptimo viaje) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(viaje.getViaje().getFechaSalida());
		cal.add(Calendar.HOUR, (int) (float) viaje.getDuracionOptima());
		return Utilities.invParseDate(cal.getTime());
	}
}
