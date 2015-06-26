package persistence;

import java.util.ArrayList;
import java.util.List;

import impl.clientes.Particular;
import impl.productos.Producto;

import org.hibernate.Query;
import org.hibernate.Session;

public class ProductoDAO extends AbstractGenericDAO<Producto> {
	private static ProductoDAO instance;
	
	public static ProductoDAO getInstance() {
		if (instance == null)
			instance = new ProductoDAO();
		return instance;
	}

	@Override
	public Producto get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Producto Producto = (Producto) session.get(Producto.class, id);
		session.close();
		return Producto;
	}
	
	public List<Producto> getAll(){
	
		List<Producto> productos = new ArrayList<Producto>();
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Particular");
		productos = (List<Producto>) q.list();
		session.close();
		return productos;
	}
}
