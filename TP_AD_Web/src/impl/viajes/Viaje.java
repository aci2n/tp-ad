package impl.viajes;

import impl.PersistentObject;
import impl.cargas.Carga;
import impl.misc.Ubicacion;
import impl.productos.CondicionEspecial;
import impl.productos.ItemProducto;
import impl.sucursales.DistanciaEntreSucursales;
import impl.sucursales.Sucursal;
import impl.vehiculos.Vehiculo;
import impl.vehiculos.VehiculoExterno;

import java.util.ArrayList;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	private List<ParadaIntermedia> paradasIntermedias;

	public Viaje() {
	}

	public Viaje(Vehiculo v, Seguro s, ViajeView vi) {
		vehiculo = v;
		seguro = s;
		origen = new Ubicacion(vi.getOrigen());
		destino = new Ubicacion(vi.getDestino());
		fechaLlegada = Utilities.parseDate(vi.getFechaLlegada());
		fechaSalida = Utilities.parseDate(vi.getFechaSalida());
		paradasIntermedias = new ArrayList<ParadaIntermedia>();
		condicionesEspeciales = new ArrayList<CondicionEspecial>();
		cargas = new ArrayList<ItemCarga>();
		id = ViajeDAO.getInstance().insert(this);
	}

	public void agregarCarga(Carga carga) {
		if (cargas == null)
			cargas = new ArrayList<ItemCarga>();
		if (puedeTransportar(carga))
			cargas.add(new ItemCarga(carga));
		ViajeDAO.getInstance().update(this);
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

	public void agregarParadaIntermedia(ParadaIntermediaView p) {
		ParadaIntermedia parada = new ParadaIntermedia(p);
		agregarParadaIntermedia(parada);
	}

	public void agregarParadaIntermedia(ParadaIntermedia parada) {
		if (paradasIntermedias == null)
			paradasIntermedias = new ArrayList<ParadaIntermedia>();
		paradasIntermedias.add(parada);
		if (paradasIntermedias.size() > 1) {
			paradasIntermedias = Utilities.ordenarParadasIntermedias(origen, paradasIntermedias);
		}

		for (int i = 0; i < paradasIntermedias.size(); i++) {
			ParadaIntermedia p = paradasIntermedias.get(i);
			p.setOrden(i);
			ViajeDAO.getInstance().update(p);
		}
		ViajeDAO.getInstance().update(this);
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

	public List<ParadaIntermedia> getParadasIntermedias() {
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

	public void setParadasIntermedias(List<ParadaIntermedia> paradasIntermedias) {
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
		return carga.calcularPesoTotal() <= calcularPesoDisponible() && carga.calcularVolumenTotal() <= calcularVolumenDisponible()
				&& vehiculo.esAptoParaCarga(carga);
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
		List<ParadaIntermediaView> paradasView = new ArrayList<ParadaIntermediaView>();
		for (ParadaIntermedia parada : paradasIntermedias) {
			paradasView.add(parada.getView());
		}
		return new ViajeView(fechaSalida.toString(), fechaSalida.toString(), origen.getView(), destino.getView(), paradasView);
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

	public boolean tieneTrayecto(Ubicacion a, Ubicacion b) {
		int pasaPorA = Integer.MIN_VALUE;
		int pasaPorB = Integer.MIN_VALUE;
		if (this.origen.tieneMismasCoordenadas(a)) {
			pasaPorA = -1;
		}
		if (this.destino.tieneMismasCoordenadas(b)) {
			pasaPorB = this.cantidadParadasIntemedias();
		}
		if (pasaPorA == Integer.MIN_VALUE || pasaPorB == Integer.MIN_VALUE) {
			for (int i = 0; i < this.cantidadParadasIntemedias(); i++) {
				if (this.paradasIntermedias.get(i).getUbicacion().tieneMismasCoordenadas(a)) {
					pasaPorA = i;
				} else if (this.paradasIntermedias.get(i).getUbicacion().tieneMismasCoordenadas(b)) {
					pasaPorB = i;
				}
			}
		}
		return (pasaPorA != Integer.MIN_VALUE && pasaPorB != Integer.MIN_VALUE) && pasaPorA < pasaPorB;
	}

	public ViajeOptimo getViajeOptimo(Ubicacion o, Ubicacion d) {
		Float distancia = 0f;
		Float duracion = 0f;
		Float costo = 0f;
		Ubicacion[] ubicaciones = new Ubicacion[paradasIntermedias.size() + 2];
		ubicaciones[0] = origen;
		int aux = 1;
		for (ParadaIntermedia pi : paradasIntermedias) {
			ubicaciones[aux] = pi.getUbicacion();
			aux++;
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

	public Date obtenerLlegadaCarga(Carga carga) {
		if (cargas.contains(carga)) {
		}
		return null;
	}

	public Float getCosto() {
		// devuelve 0 si no tiene vehiculo externo
		Float costo = 0f;
		if (vehiculo != null && vehiculo instanceof VehiculoExterno) {
			// x pesos por kilometro
			costo += origen.getCoordenadaDestino().calcularDistanciaEnKilometros(destino.getCoordenadaDestino()) * 10;
			// x pesos por parada intermedia
			costo += paradasIntermedias.size() * 500;
			// x pesos por condicion especial
			costo += condicionesEspeciales.size() * 1000;
		}
		return costo;
	}

	public Document generarXml() throws Exception {
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element eViaje = doc.createElement("viaje");
			doc.appendChild(eViaje);
			Element eId = doc.createElement("id");
			eId.appendChild(doc.createTextNode(id.toString()));
			eViaje.appendChild(eId);
			eViaje.appendChild(generarElementoUbicacion(doc, origen, "origen"));
			eViaje.appendChild(generarElementoUbicacion(doc, destino, "destino"));
			Element eFechaLlegada = doc.createElement("fechaLlegada");
			eFechaLlegada.appendChild(doc.createTextNode(Utilities.invParseDate(fechaLlegada)));
			eViaje.appendChild(eFechaLlegada);
			Element eCondiciones = doc.createElement("condiciones");
			eViaje.appendChild(eCondiciones);
			for (CondicionEspecial ce : condicionesEspeciales) {
				Element eCondicion = doc.createElement("condicion");
				eCondicion.appendChild(doc.createTextNode(ce.toString()));
				eCondiciones.appendChild(eCondicion);
			}
			Element eItemsCarga = doc.createElement("itemsCarga");
			eViaje.appendChild(eItemsCarga);
			for (ItemCarga ic : cargas) {
				Element eItemCarga = doc.createElement("itemCarga");
				eItemsCarga.appendChild(eItemCarga);
				Element eCarga = doc.createElement("carga");
				eItemCarga.appendChild(eCarga);
				Element eCliente = doc.createElement("cliente");
				eCliente.appendChild(doc.createTextNode(ic.getCarga().getCliente().getId().toString()));
				eCarga.appendChild(eCliente);
				Element eManifiesto = doc.createElement("manifiesto");
				eManifiesto.appendChild(doc.createTextNode(ic.getCarga().getManifiesto()));
				eCarga.appendChild(eManifiesto);
				for (ItemProducto ip : ic.getCarga().getProductos()) {
					Element eItemProducto = doc.createElement("itemProducto");
					eCarga.appendChild(eItemProducto);
					Element eProducto = doc.createElement("producto");
					eItemProducto.appendChild(eProducto);
					Element eNombre = doc.createElement("nombre");
					eNombre.appendChild(doc.createTextNode(ip.getProducto().getNombre()));
					eProducto.appendChild(eNombre);
					Element eConsideraciones = doc.createElement("consideraciones");
					eConsideraciones.appendChild(doc.createTextNode(ip.getProducto().getConsideraciones()));
					eProducto.appendChild(eConsideraciones);
					Element eCantidad = doc.createElement("cantidad");
					eCantidad.appendChild(doc.createTextNode(Float.toString(ip.getCantidad())));
					eItemProducto.appendChild(eCantidad);
				}
				Element eFecha = doc.createElement("fecha");
				eFecha.appendChild(doc.createTextNode(Utilities.invParseDate(ic.getFecha())));
				eItemCarga.appendChild(eFecha);
			}
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al generar XML.");
		}
	}

	private Element generarElementoUbicacion(Document doc, Ubicacion u, String tag) {
		Element e = doc.createElement(tag);
		Element pais = doc.createElement("pais");
		pais.appendChild(doc.createTextNode(u.getPais()));
		e.appendChild(pais);
		Element provincia = doc.createElement("provincia");
		provincia.appendChild(doc.createTextNode(u.getCiudad()));
		e.appendChild(provincia);
		Element ciudad = doc.createElement("ciudad");
		ciudad.appendChild(doc.createTextNode(u.getCiudad()));
		e.appendChild(ciudad);
		Element calle = doc.createElement("calle");
		calle.appendChild(doc.createTextNode(u.getCalle()));
		e.appendChild(calle);
		Element altura = doc.createElement("altura");
		altura.appendChild(doc.createTextNode(u.getAltura()));
		e.appendChild(altura);
		Element piso = doc.createElement("piso");
		piso.appendChild(doc.createTextNode(u.getPiso()));
		e.appendChild(piso);
		Element departamento = doc.createElement("departamento");
		departamento.appendChild(doc.createTextNode(u.getDepartamento()));
		e.appendChild(departamento);
		Element c = doc.createElement("coordenadas");
		e.appendChild(c);
		Element latitud = doc.createElement("latitud");
		latitud.appendChild(doc.createTextNode(u.getCoordenadaDestino().getLatitud().toString()));
		c.appendChild(latitud);
		Element longitud = doc.createElement("longitud");
		longitud.appendChild(doc.createTextNode(u.getCoordenadaDestino().getLongitud().toString()));
		c.appendChild(longitud);
		return e;
	}
}
