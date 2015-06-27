package persistence;

import impl.cargas.Carga;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CargaDAO extends AbstractGenericDAO<Carga> {
	private static CargaDAO instance;
	
	public static CargaDAO getInstance() {
		if (instance == null)
			instance = new CargaDAO();
		return instance;
	}

	@Override
	public Carga get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Carga Carga = (Carga) session.get(Carga.class, id);
		session.close();
		return Carga;
	}
	
	public List<Carga> getAll(){
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Carga");
		List<Carga> cargas = (List<Carga>) q.list();
		session.close();
		return cargas;
	}

}
