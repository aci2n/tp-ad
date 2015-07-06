package impl.viajes;

import impl.PersistentObject;
import impl.misc.Ubicacion;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import persistence.ViajeDAO;
import util.Utilities;
import views.viajes.ParadaIntermediaView;

@Entity
@Table(name = "ParadasIntermedias")
@AttributeOverride(name = "id", column = @Column(name = "id_parada_intermedia"))
public class ParadaIntermedia extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	@JoinColumn(name = "id_ubicacion")
	private Ubicacion ubicacion;
	@Column(name = "llegada")
	private Date llegada;
	@Column(name = "llegada_esperada")
	private Date llegadaEsperada;
	@Column(name = "checked")
	private boolean checked;
	@Column(name = "orden")
	private Integer orden;

	

	public ParadaIntermedia(ParadaIntermediaView p) {
		this(p.getUbicacion() != null ? new Ubicacion(p.getUbicacion()) : null, Utilities.parseDate(p.getLlegada()));
	}
	
	public ParadaIntermedia(Ubicacion ubicacion, Date llegada) {
		this.ubicacion = ubicacion;
		this.llegadaEsperada = llegada;
		this.checked = false;
		this.id = ViajeDAO.getInstance().insert(this);
	}

	public ParadaIntermedia() {
		// TODO Auto-generated constructor stub
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Date getLlegada() {
		return llegada;
	}

	public void setLlegada(Date llegada) {
		this.llegada = llegada;
	}

	public ParadaIntermediaView getView() { 
		return new ParadaIntermediaView(
			llegadaEsperada != null ? Utilities.invParseDate(llegadaEsperada) : null,
			llegada != null ? Utilities.invParseDate(llegada) : null,
			ubicacion.getView(),
			orden
		);
	}
	
	public ParadaIntermediaView toView(){
		
		ParadaIntermediaView pw = new ParadaIntermediaView();
		pw.setId(this.id);
		pw.setLlegada(llegada != null ? Utilities.invParseDate(llegada) : null);
		pw.setLlegadaEsperada(llegadaEsperada != null ? Utilities.invParseDate(llegadaEsperada) : null);
		pw.setSucursal(ubicacion.getCiudad());
		return pw;		
	}
	
	public boolean equals(ParadaIntermedia parada) {
		return this.ubicacion.tieneMismasCoordenadas(parada.ubicacion);
	}
	
	public Date getLlegadaEsperada() {
		return llegadaEsperada;
	}

	public void setLlegadaEsperada(Date llegadaEsperada) {
		this.llegadaEsperada = llegadaEsperada;
	}
	
	public Integer getOrden() {
		return orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}
}
