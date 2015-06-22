package persistence;

import impl.vehiculos.Vehiculo;

import org.hibernate.Session;

public class VehiculoDAO extends AbstractGenericDAO<Vehiculo> {
	private static VehiculoDAO instance;
	
	public static VehiculoDAO getInstance() {
		if (instance == null)
			instance = new VehiculoDAO();
		return instance;
	}

	@Override
	public Vehiculo get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Vehiculo Vehiculo = (Vehiculo) session.get(Vehiculo.class, id);
		session.close();
		return Vehiculo;
	}
}
