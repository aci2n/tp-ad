package persistence;

import impl.vehiculos.VehiculoLocal;
import impl.viajes.Viaje;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class ViajeDAO extends AbstractGenericDAO<Viaje> {
	private static ViajeDAO instance;

	public static ViajeDAO getInstance() {
		if (instance == null)
			instance = new ViajeDAO();
		return instance;
	}

	private ViajeDAO() {
	}

	@Override
	public Viaje get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Viaje Viaje = (Viaje) session.get(Viaje.class, id);
		session.close();
		return Viaje;
	}

	public List<Viaje> getAll() {
		List<Viaje> viajes = new ArrayList<Viaje>();
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("from Viaje");
		viajes = (List<Viaje>) q.list();
		s.close();
		return viajes;
	}

	public List<Viaje> getViajesEmpleado(int idChofer) {
		List<Viaje> viajes = new ArrayList<Viaje>();
		Session s = sf.openSession();
		VehiculoLocal v = (VehiculoLocal) s.createQuery("from VehiculoLocal where id_empleado = ?").setParameter(0, idChofer).uniqueResult();
		s.beginTransaction();
		Query q = s.createQuery("from Viaje where id_vehiculo = ?").setParameter(0, v.getId());
		viajes = (List<Viaje>) q.list();
		s.close();
		return viajes;
	}

	public Viaje getUltimoViaje() {
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("from Viaje order by id desc").setMaxResults(1);
		Viaje viaje = (Viaje) q.uniqueResult();
		s.close();
		return viaje;
	}
	
	public Viaje getViajePorParada(int idParada) {
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("from Viaje v inner join v.paradasIntermedias as p where p.id = ?");
		q.setParameter(0, idParada);
		Viaje viaje = (Viaje) q.uniqueResult();
		s.close();
		return viaje;
	}
}
