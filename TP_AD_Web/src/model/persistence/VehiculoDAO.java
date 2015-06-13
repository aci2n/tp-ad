package model.persistence;

import model.impl.vehiculos.Vehiculo;

import org.hibernate.Session;

public class VehiculoDAO extends AbstractGenericDAO<Vehiculo> {

	@Override
	public AbstractGenericDAO<Vehiculo> getInstance() {
		if (instance == null)
			instance = new VehiculoDAO();
		return instance;
	}

	@Override
	public Vehiculo get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Vehiculo vehiculo = (Vehiculo) session.get(Vehiculo.class, id);
		session.close();
		return vehiculo;
	}

}
