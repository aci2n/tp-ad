package impl.productos;

import persistence.ProductoDAO;
import views.productos.ProductoView;

public class AdministradorProductos {
	private static AdministradorProductos instance;
	private ProductoDAO productoDao;

	private AdministradorProductos() {
		productoDao = ProductoDAO.getInstance();
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
		Producto p = productoDao.get(id);
		if (p != null) {
			p.agregarCondicionEspecial(condicionEspecial);
		} else {
			throw new Exception("No existe producto con el id ingresado.");
		}
		
	}

}
