package views.clientes;

import java.util.List;

import views.GenericView;
import views.productos.ProductoView;

public class EmpresaView extends GenericView {
	private static final long serialVersionUID = 1L;
	private String nombre;
	private boolean regular;
	private List<ProductoView> productos;
	private CuentaCorrienteView cuentaCorriente;

	public EmpresaView() {
	}

	public EmpresaView(String nombre, boolean regular, CuentaCorrienteView cv) {
		this.nombre = nombre;
		this.regular = regular;
		this.cuentaCorriente = cv;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isRegular() {
		return regular;
	}

	public void setRegular(boolean esRegular) {
		this.regular = esRegular;
	}

	public List<ProductoView> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoView> productos) {
		this.productos = productos;
	}

	public CuentaCorrienteView getCuentaCorriente() {
		return cuentaCorriente;
	}

	public void setCuentaCorriente(CuentaCorrienteView cuentaCorriente) {
		this.cuentaCorriente = cuentaCorriente;
	}
}
