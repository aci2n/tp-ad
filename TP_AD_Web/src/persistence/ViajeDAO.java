package persistence;

import impl.viajes.Viaje;

import org.hibernate.Session;

public class ViajeDAO extends AbstractGenericDAO<Viaje> {
	private static ViajeDAO instance;

	public static ViajeDAO getInstance() {
		if (instance == null)
			instance = new ViajeDAO();
		return instance;
	}

	@Override
	public Viaje get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Viaje Viaje = (Viaje) session.get(Viaje.class, id);
		session.close();
		return Viaje;
	}
}
