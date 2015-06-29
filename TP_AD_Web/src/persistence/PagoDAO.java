package persistence;

import impl.cobranzas.Pago;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class PagoDAO extends AbstractGenericDAO<Pago> {
	private static PagoDAO instance;

	public static PagoDAO getInstance() {
		if (instance == null)
			instance = new PagoDAO();
		return instance;
	}

	@Override
	public Pago get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Pago Pago = (Pago) session.get(Pago.class, id);
		session.close();
		return Pago;
	}

	public List<Pago> getAll() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Pago");
		List<Pago> pagos = (List<Pago>) q.list();
		session.close();
		return pagos;
	}
}
