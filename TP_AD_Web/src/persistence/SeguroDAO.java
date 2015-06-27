package persistence;

import impl.cargas.TipoCarga;
import impl.viajes.Seguro;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class SeguroDAO extends AbstractGenericDAO<Seguro> {
	private static SeguroDAO instance;

	public static SeguroDAO getInstance() {
		if (instance == null)
			instance = new SeguroDAO();
		return instance;
	}

	@Override
	public Seguro get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Seguro Seguro = (Seguro) session.get(Seguro.class, id);
		session.close();
		return Seguro;
	}

	public Seguro obtenerSeguroPorTipo(TipoCarga tipo) {
		Seguro seguro = null;
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("from Seguro s where tipoCarga = :tipoCarga");
		q.setParameter("tipoCarga", tipo);
		List<Seguro> seguros = (List<Seguro>) q.list();
		s.close();
		if (seguros.size() > 0) {
			seguro = seguros.get(0); // elijo el primero arbitrariamente
			// no se puede usar uniqueResult porque a veces devuelve mas de 1
			// sucursal
		}
		return seguro;
	}
}
