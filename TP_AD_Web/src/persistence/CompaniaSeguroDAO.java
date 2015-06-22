package persistence;

import impl.viajes.CompaniaSeguro;

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
}
