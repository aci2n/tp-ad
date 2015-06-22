package model.view.clientes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import model.impl.vehiculos.Proveedor;

public class PagoView {

	private ProveedorView proveedor;
	private int id;
	private Float monto;
	private Date fecha;
	private boolean pagado;
	
	public PagoView(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public ProveedorView getProveedor() {
		return proveedor;
	}

	public void setProveedor(ProveedorView proveedor) {
		this.proveedor = proveedor;
	}
	
}
