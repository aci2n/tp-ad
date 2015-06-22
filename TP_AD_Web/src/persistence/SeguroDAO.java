package persistence;

import impl.viajes.Seguro;

import org.hibernate.Session;

public class SeguroDAO extends AbstractGenericDAO<Seguro> {
	private static SeguroDAO instance;

	public static SeguroDAO getInstance() {
		if (instance == null)
			instance = new SeguroDAO();
		return instance;
	}

	@Override
	public Seguro get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Seguro Seguro = (Seguro) session.get(Seguro.class, id);
		session.close();
		return Seguro;
	}
}
