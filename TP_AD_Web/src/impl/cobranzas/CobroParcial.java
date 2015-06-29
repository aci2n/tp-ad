package impl.cobranzas;

import impl.PersistentObject;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import views.clientes.CobroParcialView;

@Entity
@Table(name = "CobrosParciales")
@AttributeOverride(name = "id", column = @Column(name = "id_cobro_parcial"))
public class CobroParcial extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1811141989154565682L;
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "monto")
	private Float monto;

	public CobroParcial(Date fecha, Float monto) {
		this.fecha = fecha;
		this.monto = monto;
	}

	public CobroParcial() {
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public CobroParcialView getView() {
		return new CobroParcialView(fecha, monto);
	}
}
