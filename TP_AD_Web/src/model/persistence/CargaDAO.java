package model.persistence;

import model.impl.cargas.Carga;

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
}
