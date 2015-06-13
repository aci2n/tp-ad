package model.impl.viajes;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import model.impl.cargas.Carga;
import model.impl.productos.CondicionEspecial;
import model.impl.sucursales.AdministradorSucursales;
import model.impl.sucursales.Sucursal;
import model.impl.vehiculos.EstrategiaMantenimiento;
import model.impl.vehiculos.Vehiculo;

public class AdministradorViajes {
	private static AdministradorViajes instance;
	private List<Viaje> viajes;
	private List<Viaje> viajesExternos;
	private List<EstrategiaMantenimiento> mantenimientos;
	private List<CompaniaSeguro> companiasSeguros;
	
	public static AdministradorViajes getInstance() {
		if (instance == null)
			instance = new AdministradorViajes();
		return instance;
	}
	
	private AdministradorViajes() {
		this.viajes = new ArrayList<Viaje>();
		this.viajesExternos = new ArrayList<Viaje>();
		this.mantenimientos = new ArrayList<EstrategiaMantenimiento>();
		this.companiasSeguros = new ArrayList<CompaniaSeguro>();
	}

	
	public Viaje obtenerViaje(Integer codigoViaje) {
		for (Viaje viaje : viajes) {
			if (viaje.getId().equals(codigoViaje)) {
				return viaje;
			}
		}
		return null;
	}
	
	
	//REVISAR ESTE METODO QUE NO SE PUEDEN USAR VECTORES EN HIBERNATE
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
			float horasDistancia = admSuc.calcularHorasEntreSucursales(admSuc.obtenerSucursalCercana(v.getOrigen()), admSuc.obtenerSucursalCercana(paradasIntermedias.get(0).getUbicacion()));
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
	
	public void altaViaje(List<ItemCarga> list, Seguro seguro, Vehiculo vehiculo, Date fechaSalida, List<CondicionEspecial> condicionesEspeciales, ArrayList<ParadaIntermedia> paradasIntermedias) {
		viajes.add(new Viaje(list, seguro, vehiculo, fechaSalida, condicionesEspeciales, paradasIntermedias));
	}
	
//	public void altaViajeExterno(List<Carga> cargas, Seguro seguro, Date fechaSalida, Date fechaLLegada, 
//		Proveedor proveedor, TipoVehiculo tipoVehiculo, List<CondicionEspecial> condicionesEspeciales){
//
//		Vehiculo vehiculo = null;
//		for(Vehiculo v : proveedor.getVehiculos())
//			if(v.getTipo().equals(tipoVehiculo))
//				vehiculo = v;
//
//		viajesExternos.add(new Viaje(cargas, seguro, vehiculo, fechaSalida, condicionesEspeciales, null));
//	}

//	public void actualizarViaje(Viaje viaje, Sucursal sucursal) {
//		for (Iterator<Carga> iterator = viaje.getCargas().iterator(); iterator.hasNext();) {
//			Carga carga = iterator.next();
//			if (obtenerMejorViaje(sucursal, carga) != null) {
//				sucursal.getDeposito().almacenarCarga(carga);
//				iterator.remove();
//			}
//		}
//		for (ParadaIntermedia parada : viaje.getParadasIntermedias()) {
//			if (parada.getUbicacion().equals(sucursal.getUbicacion())) {
//				parada.setChecked(true);
//				break;
//			}
//		}
//		for (Iterator<Carga> iterator = sucursal.getDeposito().getCargas().iterator(); iterator.hasNext();) {
//			Carga carga = iterator.next();
//			Viaje mejorViaje = obtenerMejorViaje(sucursal, carga);
//			if (mejorViaje != null && mejorViaje.equals(viaje) && viaje.puedeTransportar(carga)) {
//				viaje.agregarCarga(carga);
//				iterator.remove();
//			}
//		}
//	}
	
	public Viaje obtenerMejorViaje(Sucursal sucursal, Carga carga) {
		Viaje mejorViaje = null;

		for (Viaje viaje : viajes) {
			if (viaje.pasaPorSucursal(sucursal) && viaje.puedeTransportar(carga)) {
				if (mejorViaje == null
						|| (
								viaje.puedeTransportar(carga)
								&& viaje.obtenerLlegadaAParada(sucursal).before(mejorViaje.obtenerLlegadaAParada(sucursal))
								)) {
					mejorViaje = viaje;
				}
			}
		}
		return mejorViaje;
	}
	
	public List<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}

	public List<Viaje> getViajesExternos() {
		return viajesExternos;
	}

	public void setViajesExternos(List<Viaje> viajesExternos) {
		this.viajesExternos = viajesExternos;
	}
	
	
}
