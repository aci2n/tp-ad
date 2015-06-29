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

	/*
	 * public List<Viaje> getViajesPosibles(Integer idOrigen, Integer idDestino)
	 * { List<Viaje> viajes = new ArrayList<Viaje>(); Session s =
	 * sf.openSession(); s.beginTransaction(); String hql =
	 * "select v1 from Viaje v1 where " +
	 * "exists (select v2 from Viaje v2 inner join v2.paradasIntermedias pi where v2.id = v1.id and pi.ubicacion.id = :idOrigen) "
	 * +
	 * "and exists (select v2 from Viaje v2 inner join v2.paradasIntermedias pi where v2.id = v1.id and pi.ubicacion.id = :idDestino)"
	 * ; Query q = s.createQuery(hql); q.setParameter("idOrigen", idOrigen);
	 * q.setParameter("idDestino", idDestino); viajes = (List<Viaje>) q.list();
	 * s.close(); return viajes; }
	 */

	public List<Viaje> getViajesEmpleado(int idChofer) {

		List<Viaje> viajes = new ArrayList<Viaje>();
		Session s = sf.openSession();
		VehiculoLocal v = (VehiculoLocal) s
				.createQuery("from VehiculoLocal where id_empleado = ?")
				.setParameter(0, idChofer).uniqueResult();

		s.beginTransaction();
		Query q = s.createQuery("from Viaje where id_vehiculo = ?").setParameter(0, v.getId());
		viajes = (List<Viaje>) q.list();
		s.close();
		return viajes;

	}
}
