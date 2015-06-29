package persistence;

import impl.cobranzas.Factura;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class FacturaDAO extends AbstractGenericDAO<Factura> {
	private static FacturaDAO instance;

	public static FacturaDAO getInstance() {
		if (instance == null)
			instance = new FacturaDAO();
		return instance;
	}

	@Override
	public Factura get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Factura Factura = (Factura) session.get(Factura.class, id);
		session.close();
		return Factura;
	}

	public List<Factura> getAll() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Factura");
		List<Factura> facturas = (List<Factura>) q.list();
		session.close();
		return facturas;
	}
}
