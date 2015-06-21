package model.persistence;

import model.impl.clientes.Receptor;

import org.hibernate.Session;

public class ReceptorDAO extends AbstractGenericDAO<Receptor> {
	private static ReceptorDAO instance;
	
	public static ReceptorDAO getInstance() {
		if (instance == null)
			instance = new ReceptorDAO();
		return instance;
	}

	@Override
	public Receptor get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Receptor Receptor = (Receptor) session.get(Receptor.class, id);
		session.close();
		return Receptor;
	}
}
