package model.persistence;

import model.impl.personal.Empleado;
import model.impl.viajes.Viaje;

import org.hibernate.Session;

public class EmpleadoDAO extends AbstractGenericDAO<Empleado>{
	
	@Override
	public AbstractGenericDAO<Viaje> getInstance() {
		if (instance == null)
			instance = new ViajeDAO();
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
