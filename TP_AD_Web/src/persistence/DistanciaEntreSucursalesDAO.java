package persistence;

import impl.sucursales.DistanciaEntreSucursales;

import org.hibernate.Session;

public class DistanciaEntreSucursalesDAO extends AbstractGenericDAO<DistanciaEntreSucursales> {
	private static DistanciaEntreSucursalesDAO instance;
	
	public static DistanciaEntreSucursalesDAO getInstance() {
		if (instance == null)
			instance = new DistanciaEntreSucursalesDAO();
		return instance;
	}

	@Override
	public DistanciaEntreSucursales get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		DistanciaEntreSucursales DistanciaEntreSucursales = (DistanciaEntreSucursales) session.get(DistanciaEntreSucursales.class, id);
		session.close();
		return DistanciaEntreSucursales;
	}
}
