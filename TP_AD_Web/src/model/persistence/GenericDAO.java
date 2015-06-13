package model.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class GenericDAO {
	private static GenericDAO instance;
	private SessionFactory sf;
	
	private GenericDAO() {
		sf = HibernateUtil.getSessionFactory();
	}
	
	public GenericDAO getInstance() {
		if (instance == null)
			instance = new GenericDAO();
		return instance;
	}

	public Object get(Class clazz, Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Object obj = session.get(clazz, id);
		session.close();
		return obj;
	}

}
