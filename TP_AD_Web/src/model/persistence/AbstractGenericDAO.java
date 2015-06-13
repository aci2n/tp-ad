package model.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractGenericDAO<T> {
	protected AbstractGenericDAO<T> instance;
	protected SessionFactory sf;
	
	public AbstractGenericDAO () {
		this.sf = HibernateUtil.getSessionFactory();
	}
	
	public abstract AbstractGenericDAO<T> getInstance();
	
	public abstract T get(Integer id);
	
	public void save(Object obj) {
		Session session = sf.openSession();
		session.beginTransaction();
		session.saveOrUpdate(obj);
		session.getTransaction().commit();
		session.close();
	};
	
}
