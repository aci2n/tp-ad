package model.persistence;

import model.impl.clientes.Cliente;
import model.impl.clientes.Particular;

import org.hibernate.Query;
import org.hibernate.Session;

public class ClienteDAO extends AbstractGenericDAO<Cliente> {
	private static ClienteDAO instance;
	
	public static ClienteDAO getInstance() {
		if (instance == null)
			instance = new ClienteDAO();
		return instance;
	}

	@Override
	public Cliente get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Cliente cliente = (Cliente) session.get(Cliente.class, id);
		session.close();
		return cliente;
	}

	public Particular obtenerClienteParticular(Integer id) {
		Particular particular = null;
		Session session = sf.openSession();
		String hql = "from Particular where id = :id";
		Query query = session.createQuery(hql).setParameter("id", id);
		particular = (Particular)query.uniqueResult();
		session.close();
		return particular;
	}
}
