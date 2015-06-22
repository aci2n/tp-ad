package model.persistence;

import model.impl.vehiculos.Proveedor;

import org.hibernate.Session;

public class ProveedorDAO extends AbstractGenericDAO<Proveedor> {
	private static ProveedorDAO instance;
	
	public static ProveedorDAO getInstance() {
		if (instance == null)
			instance = new ProveedorDAO();
		return instance;
	}

	@Override
	public Proveedor get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Proveedor Proveedor = (Proveedor) session.get(Proveedor.class, id);
		session.close();
		return Proveedor;
	}
}
