package views.clientes;

import views.GenericView;
import views.vehiculos.ProveedorView;

public class PagoView extends GenericView {
	private static final long serialVersionUID = 1L;
	private ProveedorView proveedor;
	private Float monto;
	private String fecha;
	private boolean pagado;

	public PagoView() {
	}

	public PagoView(ProveedorView proveedor, Float monto, String fecha, boolean pagado) {
		this.proveedor = proveedor;
		this.monto = monto;
		this.fecha = fecha;
		this.pagado = pagado;
	}


	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
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
