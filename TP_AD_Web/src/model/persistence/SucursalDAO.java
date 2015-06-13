package model.persistence;

import model.impl.sucursales.Sucursal;
import model.impl.vehiculos.Vehiculo;

import org.hibernate.Session;

public class SucursalDAO extends AbstractGenericDAO<Sucursal> {

	@Override
	public AbstractGenericDAO<Vehiculo> getInstance() {
		if (instance == null)
			instance = new SucursalDAO();
		return instance;
	}

	@Override
	public Sucursal get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Sucursal sucursal = (Sucursal) session.get(Sucursal.class, id);
		session.close();
		return sucursal;
	}

}
