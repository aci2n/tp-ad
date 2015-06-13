package model.impl.clientes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.impl.PersistentObject;
import model.impl.cargas.Carga;

@Entity
@Table(name = "Facturas")
@AttributeOverride(name = "id", column = @Column(name = "id_factura"))
public class Factura extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6357766629462615184L;

	@Column(name = "tipo_factura")
	private String tipoFactura;
	@Column(name = "fecha_creacion")
	private Date fechaCreacion;
	@Column(name = "monto_total")
	private Float montoTotal;
	@OneToOne
	@JoinColumn(name = "id_carga")
	private Carga carga;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_factura")
	private List<CobroParcial> cobrosParciales;

	public Factura() {

	}

	public Float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getTipoFactura() {
		return tipoFactura;
	}

	public void setTipoFactura(String tipoFactura) {
		this.tipoFactura = tipoFactura;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Carga getCarga() {
		return carga;
	}

	public void setCarga(Carga carga) {
		this.carga = carga;
	}

	public List<CobroParcial> getCobrosParciales() {
		return cobrosParciales;
	}

	public void setCobrosParciales(List<CobroParcial> cobrosParciales) {
		this.cobrosParciales = cobrosParciales;
	}

	public void realizarCobroParcial(Date fecha, Float monto) {
		if (cobrosParciales == null)
			cobrosParciales = new ArrayList<CobroParcial>();
		cobrosParciales.add(new CobroParcial(fecha, monto));
	}
}
