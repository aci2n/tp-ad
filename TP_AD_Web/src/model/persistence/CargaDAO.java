package model.persistence;

import model.impl.cargas.Carga;

import org.hibernate.Session;

public class CargaDAO extends AbstractGenericDAO<Carga>{
	
	@Override
	public AbstractGenericDAO<Carga> getInstance() {
		if (instance == null)
			instance = new CargaDAO();
		return instance;
	}

	@Override
	public Carga get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Carga carga = (Carga) session.get(Carga.class, id);
		session.close();
		return carga;
	}

}
