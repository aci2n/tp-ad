package persistence;

import impl.misc.Ubicacion;

import org.hibernate.Session;

public class UbicacionDAO extends AbstractGenericDAO<Ubicacion> {
	private static UbicacionDAO instance;
	
	public static UbicacionDAO getInstance() {
		if (instance == null)
			instance = new UbicacionDAO();
		return instance;
	}

	@Override
	public Ubicacion get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Ubicacion Ubicacion = (Ubicacion) session.get(Ubicacion.class, id);
		session.close();
		return Ubicacion;
	}
}
