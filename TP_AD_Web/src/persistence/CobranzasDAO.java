package persistence;

import impl.clientes.Cliente;
import impl.clientes.CuentaCorriente;
import impl.clientes.Pago;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class CobranzasDAO extends AbstractGenericDAO<Cliente> {
	private static CobranzasDAO instance;
	
	private CobranzasDAO() {
		
	}
	
	public static CobranzasDAO getInstance() {
		if (instance == null)
			instance = new CobranzasDAO();
		return instance;
	}

	@Override
	protected Cliente get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Pago> obtenerPagos() {
		Session session = sf.openSession();
		session.beginTransaction();
		String hql = "from Pago";
		Query query = session.createQuery(hql);
		List<Pago> pagos = query.list();
		session.close();
		return pagos;
	}
	
	public List<CuentaCorriente> obtenerCuentasCorrientes() {
		Session session = sf.openSession();
		session.beginTransaction();
		String hql = "select cuentaCorriente from Empresa";
		Query query = session.createQuery(hql);
		List<CuentaCorriente> cuentas = query.list();
		session.close();
		return cuentas;
	}

}
