package persistence;

import impl.viajes.CompaniaSeguro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CompaniaSeguroDAO extends AbstractGenericDAO<CompaniaSeguro> {
	private static CompaniaSeguroDAO instance;

	public static CompaniaSeguroDAO getInstance() {
		if (instance == null)
			instance = new CompaniaSeguroDAO();
		return instance;
	}

	@Override
	public CompaniaSeguro get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		CompaniaSeguro CompaniaSeguro = (CompaniaSeguro) session.get(CompaniaSeguro.class, id);
		session.close();
		return CompaniaSeguro;
	}

	public List<CompaniaSeguro> getAll() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from CompaniaSeguro");
		List<CompaniaSeguro> companiaSeguros = (List<CompaniaSeguro>) q.list();
		session.close();
		return companiaSeguros;
	}
}
