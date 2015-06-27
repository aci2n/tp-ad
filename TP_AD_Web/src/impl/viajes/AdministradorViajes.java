package impl.viajes;

import impl.cargas.Carga;
import impl.misc.Ubicacion;
import impl.sucursales.AdministradorSucursales;
import impl.sucursales.DistanciaEntreSucursales;
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
import views.viajes.ViajeOptimoView;
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

	public ViajeOptimoView obtenerViajeOptimo(Integer idCarga) throws Exception {
		Carga carga = CargaDAO.getInstance().get(idCarga);
		if (carga != null) {
			List<Viaje> viajesPosibles = obtenerViajesPosibles(carga);
			ViajeOptimo viajeOptimo = null;
			for (Viaje v : viajesPosibles) {
				ViajeOptimo aux = v.getViajeOptimo(carga.getOrigen(), carga.getDestino());
				if (viajeOptimo == null || aux.getDistanciaOptima() < viajeOptimo.getDistanciaOptima()) {
					viajeOptimo = aux;
				}
			}
			return viajeOptimo.getView();
		} else {
			throw new Exception("No existe carga con el ID ingresado.");
		}
	}

	private List<Viaje> obtenerViajesPosibles(Carga carga) {
		List<Viaje> viajes = ViajeDAO.getInstance().getAll();
		List<Viaje> viajesPosibles = new ArrayList<Viaje>();
		for (Viaje v : viajes) {
			if (v.puedeTransportar(carga) && v.tieneUbicacion(carga.getOrigen()) && v.tieneUbicacion(carga.getDestino())) {
				viajesPosibles.add(v);
			}
		}
		return viajesPosibles;
	}
	
	private Float duracionViajeCarga(Viaje viaje, Carga carga) throws Exception {
		if (viaje.tieneUbicacion(carga.getOrigen()) && viaje.tieneUbicacion(carga.getDestino())) {
			float duracion = 0;
			int indiceOrigen = Integer.MIN_VALUE;
			int indiceDestino = Integer.MIN_VALUE;
			
			//	Busco indice de origen y destino de la carga entre origen, destino y paradas intermedias del viaje
			if (carga.getOrigen().tieneMismasCoordenadas(viaje.getOrigen())) {
				indiceOrigen = -1;
			}
			if (carga.getDestino().tieneMismasCoordenadas(viaje.getDestino())) {
				indiceDestino = viaje.cantidadParadasIntemedias();
			}
			
			for (int i = 0; i < viaje.getParadasIntermedias().size(); i++) {
				Ubicacion ub = viaje.getParadasIntermedias().get(i).getUbicacion();
				if (ub.tieneMismasCoordenadas(carga.getOrigen())) {
					indiceOrigen = i;
				} else if (ub.tieneMismasCoordenadas(carga.getDestino())) {
					indiceDestino = i;
				}
				if (indiceOrigen != Integer.MIN_VALUE && indiceDestino != Integer.MIN_VALUE) {
					break;
				}
			}
			
			if (indiceOrigen != Integer.MIN_VALUE && indiceDestino != Integer.MIN_VALUE) {
				
				//	Si paradasIntermedias = 0, duracion = origen a destino
				if (viaje.cantidadParadasIntemedias() == 0) {
					duracion = viaje.getOrigen().calcularDistanciaEnKilometros(viaje.getDestino()) / VELOCIDAD_PROMEDIO;
				} else {
					//	Si el origen de la carga es el mismo que el del viaje
					if (indiceOrigen == -1) {
						Sucursal sucOrigen = sucursalDao.obtenerSucursalDesdeUbicacion(viaje.getOrigen().getCoordenadaDestino());;
						Sucursal sucDestino = sucursalDao.obtenerSucursalDesdeUbicacion(viaje.getParadasIntermedias().get(0).getUbicacion().getCoordenadaDestino());
						
						//	Si el origen del viaje y la primera parada del viaje son sucursales, obtengo la duracion predefinida del trayecto
						if (sucOrigen != null && sucDestino != null) {
							DistanciaEntreSucursales dis = sucursalDao.obtenerDistanciaEntreSucursales(sucOrigen, sucDestino);
							duracion += dis.getDuracionEnHoras();
						} else {
						//	Si el origen y/o la primera parada o el destino final del viaje no son sucursales, calculo duracion
							Ubicacion ubicacionDestino = viaje.cantidadParadasIntemedias() > 0 ? viaje.getParadasIntermedias().get(0).getUbicacion() : viaje.getDestino();
							duracion += viaje.getOrigen().calcularDistanciaEnKilometros(ubicacionDestino) / VELOCIDAD_PROMEDIO;
						}
					}
					
					//	Si indiceOrigen == -1 (origen carga = origen viaje), itero desde la primera parada intermedia
					//	Aplico misma lógica (invertida) para el tope de la iteración con el destino de la carga
					for (int i = Math.max(indiceOrigen, 0); i < Math.min(indiceDestino, viaje.cantidadParadasIntemedias()); i++) {
						Ubicacion ubA = viaje.getParadasIntermedias().get(i).getUbicacion();
						Ubicacion ubB = viaje.getParadasIntermedias().get(i + 1).getUbicacion();
						
						Sucursal sucA = sucursalDao.obtenerSucursalDesdeUbicacion(ubA.getCoordenadaDestino());
						Sucursal sucB = sucursalDao.obtenerSucursalDesdeUbicacion(ubB.getCoordenadaDestino());
						
						if (sucA != null && sucB != null) {
							DistanciaEntreSucursales dis = sucursalDao.obtenerDistanciaEntreSucursales(sucA, sucB);
							duracion += dis.getDuracionEnHoras();
						} else {
							duracion += ubA.calcularDistanciaEnKilometros(ubB) / VELOCIDAD_PROMEDIO;
						}
					}
					
					if (indiceDestino == viaje.cantidadParadasIntemedias()) {
						duracion += viaje
								.getParadasIntermedias().get(viaje.cantidadParadasIntemedias() - 1)
								.getUbicacion().calcularDistanciaEnKilometros(viaje.getDestino()) / VELOCIDAD_PROMEDIO;
					}
				}
				
			}
			return duracion;
		}
		throw new Exception("El viaje no pasa por destino y/o origen de la carga");
	}
}
