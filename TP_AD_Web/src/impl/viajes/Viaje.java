package impl.viajes;

import impl.PersistentObject;
import impl.cargas.Carga;
import impl.misc.Ubicacion;
import impl.productos.CondicionEspecial;
import impl.sucursales.DistanciaEntreSucursales;
import impl.sucursales.Sucursal;
import impl.vehiculos.Vehiculo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import persistence.SucursalDAO;
import persistence.ViajeDAO;
import util.Utilities;
import views.viajes.ParadaIntermediaView;
import views.viajes.ViajeView;

@Entity
@Table(name = "Viajes")
@AttributeOverride(name = "id", column = @Column(name = "id_viaje"))
public class Viaje extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5092108929260301459L;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_viaje")
	private List<ItemCarga> cargas;
	@ManyToOne
	@JoinColumn(name = "id_seguro")
	private Seguro seguro;
	@ManyToOne
	@JoinColumn(name = "id_vehiculo")
	private Vehiculo vehiculo;
	@ManyToOne
	@JoinColumn(name = "id_origen")
	private Ubicacion origen;
	@ManyToOne
	@JoinColumn(name = "id_destino")
	private Ubicacion destino;
	@Column(name = "fecha_salida")
	private Date fechaSalida;
	@Column(name = "fecha_llegada")
	private Date fechaLlegada;
	@ElementCollection(targetClass = CondicionEspecial.class)
	@CollectionTable(name = "Viajes_CondicionesEspeciales", joinColumns = @JoinColumn(name = "id_viaje"))
	@Column(name = "condicion_especial")
	@Enumerated(EnumType.STRING)
	private List<CondicionEspecial> condicionesEspeciales;
	@Column(name = "esta_atrasado")
	private boolean estaAtrasado;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_viaje")
	@OrderBy(value = "id asc")
	private Collection<ParadaIntermedia> paradasIntermedias;

	public Viaje() {
	}

	public Viaje(Vehiculo v, Seguro s, ViajeView vi) {
		vehiculo = v;
		seguro = s;
		origen = new Ubicacion(vi.getOrigen());
		destino = new Ubicacion(vi.getDestino());
		fechaLlegada = Utilities.parseDate(vi.getFechaLlegada());
		fechaSalida = Utilities.parseDate(vi.getFechaSalida());
		id = ViajeDAO.getInstance().insert(this);
	}

	public void agregarCarga(Carga carga) {
		if (cargas == null)
			cargas = new ArrayList<ItemCarga>();
		// if (puedeTransportar(carga))
		cargas.add(new ItemCarga(carga));
	}

	public void agregarCondicionEspecial(String condicion) throws Exception {
		if (condicionesEspeciales == null)
			condicionesEspeciales = new ArrayList<CondicionEspecial>();
		CondicionEspecial c = CondicionEspecial.valueOf(condicion);
		if (!tieneCondicionEspecial(c)) {
			condicionesEspeciales.add(c);
			ViajeDAO.getInstance().update(this);
		} else {
			throw new Exception("El viaje ya tiene la condicion ingresada.");
		}
	}

	private boolean tieneCondicionEspecial(CondicionEspecial c) {
		for (CondicionEspecial ce : condicionesEspeciales)
			if (c.equals(ce))
				return true;
		return false;
	}

	public Integer agregarParadaIntermedia(ParadaIntermediaView p) {
		if (paradasIntermedias == null)
			paradasIntermedias = new ArrayList<ParadaIntermedia>();
		ParadaIntermedia parada = new ParadaIntermedia(p);
		paradasIntermedias.add(parada);
		ViajeDAO.getInstance().update(this);
		return parada.getId();
	}

	public float calcularPesoDisponible() {
		if (vehiculo == null)
			return 0;
		float peso = 0;
		for (ItemCarga c : cargas)
			peso += c.getCarga().calcularPesoTotal();
		return vehiculo.getPeso() - peso;
	}

	public float calcularVolumenDisponible() {
		float volumen = 0;
		for (ItemCarga c : cargas)
			volumen += c.getCarga().calcularVolumenTotal();
		return vehiculo.getTamano().calcularVolumen() - volumen;
	}

	public int cantidadParadasIntemedias() {
		return paradasIntermedias.size();
	}

	public void generarRemito() {
	}

	public List<CondicionEspecial> getCondicionesEspeciales() {
		return condicionesEspeciales;
	}

	public Date getFechaLlegada() {
		return fechaLlegada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public Collection<ParadaIntermedia> getParadasIntermedias() {
		return paradasIntermedias;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public Vehiculo getVehiculo() {
		return vehiculo;
	}

	public boolean isEstaAtrasado() {
		return estaAtrasado;
	}

	public void setCondicionesEspeciales(List<CondicionEspecial> condicionesEspeciales) {
		this.condicionesEspeciales = condicionesEspeciales;
	}

	public void setEstaAtrasado(boolean estaAtrasado) {
		this.estaAtrasado = estaAtrasado;
	}

	public void setFechaLlegada(Date fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public void setParadasIntermedias(Collection<ParadaIntermedia> paradasIntermedias) {
		this.paradasIntermedias = paradasIntermedias;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	public void setVehiculo(Vehiculo vehiculo) {
		this.vehiculo = vehiculo;
	}

	public List<ItemCarga> getCargas() {
		return cargas;
	}

	public void setCargas(List<ItemCarga> cargas) {
		this.cargas = cargas;
	}

	public Ubicacion getOrigen() {
		return origen;
	}

	public void setOrigen(Ubicacion origen) {
		this.origen = origen;
	}

	public Ubicacion getDestino() {
		return destino;
	}

	public void setDestino(Ubicacion destino) {
		this.destino = destino;
	}

	public Date existeLLegadaUbicacion(Ubicacion ubicacion) {
		for (ParadaIntermedia p : paradasIntermedias)
			if (p.getUbicacion().equals(ubicacion))
				return p.getLlegada();
		return null;
	}

	public boolean pasaPorSucursal(Sucursal sucursal) {
		if (origen.equals(sucursal.getUbicacion()) || destino.equals(sucursal.getUbicacion()))
			return true;
		for (ParadaIntermedia parada : paradasIntermedias) {
			if (!parada.isChecked() && parada.getUbicacion().equals(sucursal.getUbicacion()))
				return true;
		}
		return false;
	}

	public boolean puedeTransportar(Carga carga) {
		return carga.calcularPesoTotal() <= calcularPesoDisponible() && carga.calcularVolumenTotal() <= calcularVolumenDisponible();
	}

	public Date obtenerLlegadaAParada(Sucursal sucursal) {
		if (pasaPorSucursal(sucursal)) {
			if (destino.equals(sucursal.getUbicacion())) {
				return fechaLlegada;
			}
			for (ParadaIntermedia parada : paradasIntermedias) {
				if (parada.getUbicacion().equals(sucursal.getUbicacion())) {
					return parada.getLlegada();
				}
			}
		}
		return null;
	}

	public ViajeView getView() {

		return new ViajeView(fechaSalida.toString(), fechaSalida.toString(), origen.getView(), destino.getView());
	}

	public boolean tieneUbicacion(Ubicacion u) {
		if (origen.tieneMismasCoordenadas(u) || destino.tieneMismasCoordenadas(u)) {
			return true;
		}
		for (ParadaIntermedia pi : paradasIntermedias) {
			if (pi.getUbicacion().tieneMismasCoordenadas(u)) {
				return true;
			}
		}
		return false;
	}

	public ViajeOptimo getViajeOptimo(Ubicacion o, Ubicacion d) {
		Float distancia = 0f;
		Float duracion = 0f;
		Float costo = 0f;

		Ubicacion[] ubicaciones = new Ubicacion[paradasIntermedias.size() + 2];
		ubicaciones[0] = origen;
		int aux = 1;
		for (ParadaIntermedia pi : paradasIntermedias) {
			ubicaciones[aux++] = pi.getUbicacion();
		}
		ubicaciones[ubicaciones.length - 1] = destino;

		Integer indiceComienzo = 0;

		for (int i = 0; i < ubicaciones.length; i++) {
			if (ubicaciones[i].tieneMismasCoordenadas(o)) {
				indiceComienzo = i;
				break;
			}
		}
		for (int i = indiceComienzo; i < ubicaciones.length - 1; i++) {
			Float[] parametros = calcularParametrosEntreUbicaciones(ubicaciones[i], ubicaciones[i + 1]);
			distancia += parametros[0];
			duracion += parametros[1];
			costo += parametros[2];
			if (ubicaciones[i + 1].tieneMismasCoordenadas(d)) {
				break;
			}
		}

		return new ViajeOptimo(this, distancia, duracion, costo);
	}

	private Float[] calcularParametrosEntreUbicaciones(Ubicacion a, Ubicacion b) {
		Float[] parametros = new Float[3];
		// probamos con sucursales, si no usamos coordenadas
		Sucursal sucA = SucursalDAO.getInstance().obtenerSucursalDesdeUbicacion(a.getCoordenadaDestino());
		Sucursal sucB = SucursalDAO.getInstance().obtenerSucursalDesdeUbicacion(b.getCoordenadaDestino());
		if (sucA != null && sucB != null) {
			DistanciaEntreSucursales des = SucursalDAO.getInstance().obtenerDistanciaEntreSucursales(sucA, sucB);
			if (des != null) {
				parametros[0] = des.getDistanciaEnKm();
				parametros[1] = des.getDuracionEnHoras();
				parametros[2] = des.getCosto();
				return parametros;
			}
		}
		parametros[0] = a.getCoordenadaDestino().calcularDistanciaEnKilometros(b.getCoordenadaDestino());
		parametros[1] = parametros[0] / 180f; // kilometros por hora promedio
		parametros[2] = parametros[0] * 7.35f; // costo por kilometro promedio

		return parametros;
	}
}
