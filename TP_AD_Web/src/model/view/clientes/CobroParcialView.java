package model.view.clientes;

import java.util.Date;

import javax.persistence.Column;

public class CobroParcialView {

	private int id;
	private Date fecha;
	private Float monto;

	public CobroParcialView() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

}
