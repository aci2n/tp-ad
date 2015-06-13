package model.persistence;

import model.impl.personal.Empleado;

import org.hibernate.Session;

public class EmpleadoDAO extends AbstractGenericDAO<Empleado>{
	
	@Override
	public AbstractGenericDAO<Empleado> getInstance() {
		if (instance == null)
			instance = new EmpleadoDAO();
		return instance;
	}

	@Override
	public Empleado get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		Empleado empleado = (Empleado) session.get(Empleado.class, id);
		session.close();
		return empleado;
	}

}
