package views.clientes;

import java.util.Date;

import views.vehiculos.ProveedorView;

public class PagoView {

	private int id;
	private ProveedorView proveedor;
	private Float monto;
	private Date fecha;
	private boolean pagado;
	
	public PagoView(){
		
	}
	
	public PagoView(ProveedorView proveedor, Float monto, Date fecha, boolean pagado){
		
		this.proveedor = proveedor;
		this.monto = monto;
		this.fecha = fecha;
		this.pagado = pagado;
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
