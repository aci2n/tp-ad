package impl.productos;

import persistence.ProductoDAO;
import views.productos.ProductoView;

public class AdministradorProductos {
	private static AdministradorProductos instance;

	private AdministradorProductos() {

	}

	public static AdministradorProductos getInstance() {
		if (instance == null)
			instance = new AdministradorProductos();
		return instance;
	}

	public Integer altaProducto(ProductoView p) {
		Producto producto = new Producto(p);
		return producto.getId();
	}

	public void agregarCondicionEspecialAProducto(Integer id, String condicionEspecial) throws Exception {
		Producto p = ProductoDAO.getInstance().get(id);
		if (p != null) {
			p.agregarCondicionEspecial(condicionEspecial);
		} else {
			throw new Exception("No existe producto con el id ingresado.");
		}
		
	}

}
