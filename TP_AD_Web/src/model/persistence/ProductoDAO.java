package model.persistence;

import model.impl.productos.Producto;

import org.hibernate.Session;

public class ProductoDAO extends AbstractGenericDAO<Producto>{
	
	@Override
	public AbstractGenericDAO<Producto> getInstance() {
		if (instance == null)
			instance = new ViajeDAO();
		return instance;
	}

	@Override
	public Producto get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Producto producto = (Producto) session.get(Producto.class, id);
		session.close();
		return producto;
	}

}
