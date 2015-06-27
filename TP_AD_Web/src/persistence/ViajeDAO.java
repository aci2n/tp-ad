package persistence;

import java.util.ArrayList;
import java.util.List;

import impl.clientes.Cliente;
import impl.viajes.Viaje;

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
}
