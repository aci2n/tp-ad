package persistence;

import impl.vehiculos.Vehiculo;
import impl.vehiculos.VehiculoLocal;

import java.util.List;

import org.hibernate.Query;
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

	public VehiculoLocal getVehiculoLocal(int id) {
		VehiculoLocal vehiculoLocal = null;
		Session session = sf.openSession();
		session.beginTransaction();
		String hql = "from VehiculoLocal where id = :id";
		Query query = session.createQuery(hql).setParameter("id", id);
		vehiculoLocal = (VehiculoLocal) query.uniqueResult();
		session.close();
		return vehiculoLocal;
	}

	public List<Vehiculo> getAllLocales() {
		Session session = sf.openSession();
		session.beginTransaction();
		String hql = "from VehiculoLocal";
		Query query = session.createQuery(hql);
		List<Vehiculo> vehiculos = (List<Vehiculo>) query.list();
		session.close();
		return vehiculos;
	}

	public List<Vehiculo> getAllExternos() {
		Session session = sf.openSession();
		session.beginTransaction();
		String hql = "from VehiculoExterno";
		Query query = session.createQuery(hql);
		List<Vehiculo> vehiculos = (List<Vehiculo>) query.list();
		session.close();
		return vehiculos;
	}
}
