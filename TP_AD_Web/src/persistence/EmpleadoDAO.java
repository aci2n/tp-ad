package persistence;

import java.util.List;

import impl.personal.Empleado;

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
	public List<Empleado> getAll(){
		
		Session session = sf.openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Empleado");
		List<Empleado> empleados = (List<Empleado>) q.list();
		session.close();
		return empleados;
	}
}
