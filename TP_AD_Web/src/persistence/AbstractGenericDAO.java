package persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractGenericDAO<T> {
	protected static SessionFactory sf = HibernateUtil.getSessionFactory();

	protected abstract T get(Integer id);

	public Integer insert(Object obj) {
		Integer id = null;
		Session session = sf.openSession();
		session.beginTransaction();
		id = (Integer) session.save(obj);
		session.getTransaction().commit();
		session.close();
		return id;
	}

	public void update(Object obj) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.update(obj);
		session.getTransaction().commit();
		session.close();
	};

	public void delete(Object obj) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.delete(obj);
		session.getTransaction().commit();
		session.close();
	}

}
