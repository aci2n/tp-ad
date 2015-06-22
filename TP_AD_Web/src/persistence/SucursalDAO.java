package persistence;

import impl.sucursales.Sucursal;

import org.hibernate.Session;

public class SucursalDAO extends AbstractGenericDAO<Sucursal> {
	private static SucursalDAO instance;
	
	public static SucursalDAO getInstance() {
		if (instance == null)
			instance = new SucursalDAO();
		return instance;
	}

	@Override
	public Sucursal get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Sucursal Sucursal = (Sucursal) session.get(Sucursal.class, id);
		session.close();
		return Sucursal;
	}
}
