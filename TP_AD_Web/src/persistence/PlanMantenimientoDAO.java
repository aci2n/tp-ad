package persistence;

import impl.vehiculos.PlanMantenimiento;

import org.hibernate.Session;

public class PlanMantenimientoDAO extends AbstractGenericDAO<PlanMantenimiento> {
	private static PlanMantenimientoDAO instance;
	
	public static PlanMantenimientoDAO getInstance() {
		if (instance == null)
			instance = new PlanMantenimientoDAO();
		return instance;
	}

	@Override
	public PlanMantenimiento get(Integer id) {
		Session session = sf.openSession();
		session.beginTransaction();
		PlanMantenimiento PlanMantenimiento = (PlanMantenimiento) session.get(PlanMantenimiento.class, id);
		session.close();
		return PlanMantenimiento;
	}
}
