package persistence;

import impl.cargas.SeguimientoCarga;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class SeguimientoCargasDAO extends AbstractGenericDAO<SeguimientoCarga>{

	private static SeguimientoCargasDAO instance;
	
	public static SeguimientoCargasDAO getInstance(){
		if(instance == null)
			instance = new SeguimientoCargasDAO();
		return instance;
	}
	
	public List<SeguimientoCarga> getByCarga(Integer idCarga) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from SeguimientoCarga where id_carga = ?").setInteger(0, idCarga);
		List<SeguimientoCarga> cargas = (List<SeguimientoCarga>) q.list();
		session.close();
		return cargas;
	}
	
	@Override
	protected SeguimientoCarga get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		SeguimientoCarga Carga = (SeguimientoCarga) session.get(SeguimientoCarga.class, id);
		session.close();
		return Carga;
	}

}
