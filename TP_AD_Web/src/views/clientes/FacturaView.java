package views.clientes;

import java.util.Date;
import java.util.List;

import views.cargas.CargaView;

public class FacturaView {

	private int id;
	private String tipoFactura;
	private Date fechaCreacion;
	private Float montoTotal;
	private List<CobroParcialView> cobrosParciales;
	private CargaView carga;

	public FacturaView() {

	}

	public FacturaView(String tipoFactura, Date fechaCreacion, Float montoTotal) {

		this.tipoFactura = tipoFactura;
		this.fechaCreacion = fechaCreacion;
		this.montoTotal = montoTotal;
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

	public CargaView getCarga() {
		return carga;
	}

	public void setCarga(CargaView carga) {
		this.carga = carga;
	}

}
