package model.persistence;

import model.impl.clientes.Cliente;

import org.hibernate.Session;

public class ClienteDAO extends AbstractGenericDAO<Cliente>{
	
	@Override
	public AbstractGenericDAO<Cliente> getInstance() {
		if (instance == null)
			instance = new ViajeDAO();
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

}
