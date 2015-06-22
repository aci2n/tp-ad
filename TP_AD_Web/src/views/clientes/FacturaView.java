package views.clientes;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;

public class FacturaView {

	private int id;
	private String tipoFactura;
	private Date fechaCreacion;
	private Float montoTotal;
	private List<CobroParcialView> cobrosParciales;

	public FacturaView() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Float getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(Float montoTotal) {
		this.montoTotal = montoTotal;
	}

	public List<CobroParcialView> getCobrosParciales() {
		return cobrosParciales;
	}

	public void setCobrosParciales(List<CobroParcialView> cobrosParciales) {
		this.cobrosParciales = cobrosParciales;
	}

}
