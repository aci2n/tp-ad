package views.clientes;

import java.util.List;

import views.GenericView;
import views.productos.ProductoView;

public class EmpresaView extends GenericView{

	private String nombre;
	private String esRegular;
	private List<ProductoView> productos;
	private CuentaCorrienteView cuentaCorriente;

	public EmpresaView() {

	}

	public EmpresaView(String nombre, String esRegular) {
		this.nombre = nombre;
		this.esRegular = esRegular;
	}


	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String isEsRegular() {
		return esRegular;
	}

	public void setEsRegular(String esRegular) {
		this.esRegular = esRegular;
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
