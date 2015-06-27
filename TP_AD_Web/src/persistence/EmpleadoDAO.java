package persistence;

import impl.personal.Empleado;

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

	/*
	 * public List<Empleado> getEmpleados() { List<Empleado> empleados = new
	 * ArrayList<Empleado>(); Session s = sf.openSession(); Query q =
	 * s.createQuery("from Empleado"); empleados = (List<Empleado>) q.list();
	 * s.close(); return empleados; }
	 */
}
