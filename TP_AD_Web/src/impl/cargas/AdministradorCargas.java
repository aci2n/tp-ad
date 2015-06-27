package impl.cargas;

import impl.productos.ItemProducto;
import impl.sucursales.AdministradorSucursales;
import impl.sucursales.Sucursal;
import impl.vehiculos.AdministradorVehiculos;
import impl.vehiculos.Vehiculo;
import impl.viajes.AdministradorViajes;
import impl.viajes.ItemCarga;
import impl.viajes.ParadaIntermedia;
import impl.viajes.Viaje;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import persistence.CargaDAO;
import util.Utilities;
import views.cargas.CargaView;

public class AdministradorCargas {
	private static AdministradorCargas instance;
	private List<String> materialesProhibidos;

	public static AdministradorCargas getInstance() {
		if (instance == null)
			instance = new AdministradorCargas();
		return instance;
	}

	private AdministradorCargas() {
		this.materialesProhibidos = new ArrayList<String>();
	}

	public void altaCarga(Carga carga, Sucursal sucursal) {
		AdministradorSucursales admSuc = AdministradorSucursales.getInstance();
		AdministradorViajes admVi = AdministradorViajes.getInstance();
		AdministradorVehiculos admVeh = AdministradorVehiculos.getInstance();

		Date fechaEstimadaLlegada = admSuc.estimarLlegada(sucursal, admSuc.obtenerSucursalCercana(carga.getDestino()));
		Viaje mejorViaje = admVi.obtenerMejorViaje(sucursal, carga);

		if (mejorViaje != null) {
			for (ParadaIntermedia pi : mejorViaje.getParadasIntermedias()) {
				if (pi.getUbicacion().equals(sucursal.getUbicacion())) {
					if (pi.getLlegada().before(carga.getFechaMaximaEntrega())) {
						mejorViaje.agregarCarga(carga);
						return;
					}
				}
			}
		}

		Vehiculo vehiculo = null;
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR, 6);
		Date salida = cal.getTime();
		for (Vehiculo v : sucursal.getVehiculos()) {
			if (admVeh.estaDisponibleVehiculo(v, salida, fechaEstimadaLlegada)) {
				vehiculo = v;
			}
		}

		// mira como te lo comento admVi.altaViaje(null, vehiculo, salida, null,
		// null);
	}

	public Date fechaMaximaDeSalida(Viaje viaje) {
		Date salidaMaxima = null;

		for (ItemCarga carga : viaje.getCargas()) {
			Date salidaCarga = fechaMaximaDeSalida(carga.getCarga());
			if (salidaMaxima == null || salidaMaxima.after(salidaCarga)) {
				salidaMaxima = salidaCarga;
			}
		}

		return salidaMaxima;
	}

	public Date fechaMaximaDeSalida(Carga carga) {
		Calendar cal = Calendar.getInstance();

		AdministradorSucursales admSuc = AdministradorSucursales.getInstance();
		Sucursal origen = admSuc.obtenerSucursalCercana(carga.getOrigen());
		Sucursal destino = admSuc.obtenerSucursalCercana(carga.getDestino());

		float distancia = admSuc.calcularHorasEntreSucursales(origen, destino);

		cal.add(Calendar.HOUR, -((int) distancia));
		cal.add(Calendar.MINUTE, -((int) ((distancia - (int) distancia) * 60)));

		return cal.getTime();
	}

	public boolean tieneMaterialesProhibidos(Carga carga) {
		for (ItemProducto ip : carga.getProductos()) {
			if (esMaterialProhibido(ip.getProducto().getMaterial())) {
				return true;
			}
		}
		return false;
	}

	private boolean esMaterialProhibido(String material) {
		for (String s : materialesProhibidos) {
			if (s.equals(material)) {
				return true;
			}
		}
		return false;
	}

	public List<String> getMaterialesProhibidos() {
		return materialesProhibidos;
	}

	public void setMaterialesProhibidos(List<String> materialesProhibidos) {
		this.materialesProhibidos = materialesProhibidos;
	}

	public List<CargaView> obtenerCargasView() {

		List<CargaView> cargas = new ArrayList<CargaView>();

		for (Carga c : CargaDAO.getInstance().getAll())
			cargas.add(new CargaView(c.getTipo().toString(), Utilities.invParseDate(c.getFechaMaximaEntrega()), Utilities.invParseDate(c
					.getFechaProbableEntrega()), c.getManifiesto(), c.getOrigen().getView(), c.getDestino().getView(), c.getEstadoCarga().toString(),
					c.getId()));

		return cargas;
	}
}
