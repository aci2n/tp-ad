package impl.vehiculos;

import java.util.Calendar;
import java.util.Date;

public abstract class EstrategiaMantenimiento {
	public Tarea realizarMantenimiento(PlanMantenimiento plan) {
		mantener();
		return generarTarea(plan);
	}

	protected Tarea generarTarea(PlanMantenimiento plan){
		Calendar cal = Calendar.getInstance();
		Date entrega = cal.getTime();

		cal.add(Calendar.DATE, 1);
		Date devolucion = cal.getTime();

		return new Tarea(plan.kilometraje, entrega, devolucion);
	}

	protected abstract void mantener();
}
