package impl.viajes;

import impl.PersistentObject;
import impl.cargas.Carga;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "ItemsCarga")
@AttributeOverride(name = "id", column = @Column(name = "id_item_carga"))
public class ItemCarga extends PersistentObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2495648627363561220L;
	@ManyToOne
	@JoinColumn(name = "id_carga")
	Carga carga;
	@Column(name = "fecha")
	Date fecha;

	public ItemCarga(Carga carga) {
		this.carga = carga;
		this.fecha = new Date();
	}

	public ItemCarga() {

	}

	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
