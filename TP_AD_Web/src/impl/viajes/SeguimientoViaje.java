package impl.viajes;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import views.viajes.SeguimientoViajeView;

@Entity
@Table(name = "SeguimientoViajes")
@AttributeOverride(name = "id", column = @Column(name = "id"))
public class SeguimientoViaje {

	@ManyToOne
	@JoinColumn(name = "id_viaje")
	private Viaje viaje;
	@Column(name = "id_parada_intermedia")
	private Integer idParadaIntemedia;
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "checked")
	private boolean checked;

	public SeguimientoViaje(Viaje viaje, Integer paradaIntermedia, Date fecha,
			boolean checked) {

		this.viaje = viaje;
		this.fecha = fecha;
		this.idParadaIntemedia = paradaIntermedia;
		this.checked = checked;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	public Integer getParadaIntemedia() {
		return idParadaIntemedia;
	}

	public void setParadaIntemedia(Integer paradaIntemedia) {
		this.idParadaIntemedia = paradaIntemedia;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		
		if(checked == true){
			
			for(ParadaIntermedia p : viaje.getParadasIntermedias())
				if(p.getId() == this.idParadaIntemedia){
					p.setChecked(true);
					this.checked = checked;
				}
		}
	}
	
	public SeguimientoViajeView getView(){
		
		return new SeguimientoViajeView(viaje.getView(), idParadaIntemedia, fecha, checked);
	}
}
