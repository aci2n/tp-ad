package persistence;

import impl.vehiculos.Proveedor;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
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
	
	public List<Proveedor> getAll(){
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Proveedor");
		List<Proveedor> proveedores = new ArrayList<Proveedor>();
		proveedores = (List<Proveedor>) q.list();
		session.close();
		return proveedores;
	}
}
