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
	@Column(name = "checked")
	private boolean checked;

	public ParadaIntermedia(ParadaIntermediaView p) {
		if (p.getUbicacion() != null) {
			this.ubicacion = new Ubicacion(p.getUbicacion());
		}
		this.llegada = Utilities.parseDate(p.getLlegada());
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
		return new ParadaIntermediaView(llegada.toString(), ubicacion.getView());
	}
	
	public boolean equals(ParadaIntermedia parada) {
		return this.ubicacion.tieneMismasCoordenadas(parada.ubicacion);
	}
}
