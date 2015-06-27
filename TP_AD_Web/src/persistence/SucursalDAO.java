package persistence;

import impl.misc.Coordenada;
import impl.sucursales.DistanciaEntreSucursales;
import impl.sucursales.Sucursal;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class SucursalDAO extends AbstractGenericDAO<Sucursal> {
	private static SucursalDAO instance;

	public static SucursalDAO getInstance() {
		if (instance == null)
			instance = new SucursalDAO();
		return instance;
	}

	@Override
	public Sucursal get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Sucursal sucursal = (Sucursal) session.get(Sucursal.class, id);
		session.close();
		return sucursal;
	}

	public List<Sucursal> getAll() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Sucursal");
		List<Sucursal> sucursales = (List<Sucursal>) q.list();
		session.close();
		return sucursales;
	}

	public DistanciaEntreSucursales obtenerDistanciaEntreSucursales(Sucursal sucA, Sucursal sucB) {
		StringBuilder queryBuilder = new StringBuilder("select d from DistanciaEntreSucursales d where");
		queryBuilder.append(" (d.sucursalA.id = :sucA and d.sucursalB.id = :sucB)");
		queryBuilder.append(" or (d.sucursalA.id = :sucB and d.sucursalB.id = :sucA)");
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery(queryBuilder.toString());
		q.setInteger("sucA", sucA.getId());
		q.setInteger("sucB", sucB.getId());
		DistanciaEntreSucursales dist = (DistanciaEntreSucursales) q.uniqueResult();
		session.close();
		return dist;
	}

	public Sucursal obtenerSucursalAPartirDeCarga(Integer idCarga) {
		Sucursal sucursal;
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("select s from Sucursal s inner join s.cargas c where c.id = :idCarga");
		q.setParameter("idCarga", idCarga);
		sucursal = (Sucursal) q.uniqueResult();
		s.close();
		return sucursal;
	}

	public Sucursal obtenerSucursalDesdeUbicacion(Coordenada c) {
		Sucursal sucursal;
		Session s = sf.openSession();
		s.beginTransaction();
		Query q = s.createQuery("select s from Sucursal s inner join s.ubicacion u where u.coordenadaDestino.latitud = :lat and u.coordenadaDestino.longitud = :lon");
		q.setParameter("lat", c.getLongitud());
		q.setParameter("lon", c.getLongitud());
		sucursal = (Sucursal) q.uniqueResult();
		s.close();
		return sucursal;
	}
}
