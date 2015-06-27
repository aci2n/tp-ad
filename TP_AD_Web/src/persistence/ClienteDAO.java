package persistence;

import impl.clientes.Cliente;
import impl.clientes.Empresa;
import impl.clientes.Particular;

import java.util.ArrayList;
import java.util.List;

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
		session.beginTransaction();
		String hql = "from Particular where id = :id";
		Query query = session.createQuery(hql).setParameter("id", id);
		particular = (Particular) query.uniqueResult();
		session.close();
		return particular;
	}

	@SuppressWarnings("unchecked")
	public List<Particular> getAllClientesParticulares() {

		List<Particular> particulares = new ArrayList<Particular>();
		Session s = sf.openSession();
		Query q = s.createQuery("from Particular");
		particulares = (List<Particular>) q.list();
		s.close();
		return particulares;
	}

	@SuppressWarnings("unchecked")
	public List<Empresa> getAllClientesEmpresas() {

		List<Empresa> empresas = new ArrayList<Empresa>();
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("from Empresa");
		empresas = (List<Empresa>) q.list();
		s.close();
		return empresas;
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> getAllClientes() {

		List<Cliente> clientes = new ArrayList<Cliente>();
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("from Cliente");
		clientes = (List<Cliente>) q.list();
		s.close();
		return clientes;
	}
}
