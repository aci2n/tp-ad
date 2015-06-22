package impl.sucursales;

import impl.cargas.AdministradorCargas;
import impl.cargas.Carga;
import impl.clientes.Cliente;
import impl.misc.Ubicacion;
import impl.personal.Empleado;
import impl.viajes.AdministradorViajes;
import impl.viajes.Viaje;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import persistence.SucursalDAO;
import views.personal.EmpleadoView;
import views.sucursales.DistanciaEntreSucursalesView;
import views.sucursales.SucursalView;

public class AdministradorSucursales {
	private static AdministradorSucursales instance;
	private List<Sucursal> sucursales;
	private List<DistanciaEntreSucursales> distancias;

	public static AdministradorSucursales getInstance() {
		if (instance == null)
			instance = new AdministradorSucursales();
		return instance;
	}

	private AdministradorSucursales() {
		this.sucursales = new ArrayList<Sucursal>();
		this.distancias = new ArrayList<DistanciaEntreSucursales>();
	}

	public Integer altaSucursal(SucursalView s) {
		Sucursal sucursal = new Sucursal(s);
		return sucursal.getId();
	}

	public Integer agregarEmpleadoASucursal(Integer id, EmpleadoView e) throws Exception {
		Sucursal s = SucursalDAO.getInstance().get(id);
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
		for (DistanciaEntreSucursales d : distancias)
			if (d.getSucursalA().getId() == sucursalA.getId() || d.getSucursalB().getId() == sucursalB.getId())
				if (d.getSucursalB().getId() == sucursalB.getId() || d.getSucursalB().getId() == sucursalA.getId())
					return d.getDuracionEnHoras();
		return 0;
	}

	public Sucursal obtenerSucursalCercana(Ubicacion ubicacion) {
		Sucursal cercana = null;
		for (Sucursal sucursal : sucursales) {
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
					if (!AdministradorCargas.getInstance().tieneMaterialesProhibidos(carga)) {
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
		for (Viaje v : AdministradorViajes.getInstance().getViajes()) {
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
		for (Sucursal s : sucursales)
			if (s.getId() == numero)
				return s;
		return null;
	}

	public Ubicacion obtenerUbicacion(int codigoUbicacion) {
		for (Sucursal sucursal : sucursales) {
			if (sucursal.getUbicacion().getId() == codigoUbicacion) {
				return sucursal.getUbicacion();
			}
		}
		return null;
	}

	public Empleado obtenerEmpleado(String cuit) {
		for (Sucursal s : sucursales)
			for (Empleado e : s.getEmpleados())
				if (e.getCuit().equals(cuit))
					return e;
		return null;
	}

	public List<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(List<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public List<DistanciaEntreSucursales> getDistancias() {
		return distancias;
	}

	public void setDistancias(List<DistanciaEntreSucursales> distancias) {
		this.distancias = distancias;
	}
}
