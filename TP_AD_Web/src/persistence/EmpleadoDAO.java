package persistence;

import impl.personal.Empleado;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

public class EmpleadoDAO extends AbstractGenericDAO<Empleado> {
	private static EmpleadoDAO instance;

	public static EmpleadoDAO getInstance() {
		if (instance == null)
			instance = new EmpleadoDAO();
		return instance;
	}

	@Override
	public Empleado get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Empleado Empleado = (Empleado) session.get(Empleado.class, id);
		session.close();
		return Empleado;
	}
	
	public Empleado obtenerPorCuit(String cuit) {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Empleado where cuit = :cuit");
		q.setString("cuit", cuit);
		Empleado empleado = (Empleado) q.uniqueResult();
		session.close();
		return empleado;
	}

	public List<Empleado> getAll() {
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Empleado");
		List<Empleado> empleados = (List<Empleado>) q.list();
		session.close();
		return empleados;
	}

}
