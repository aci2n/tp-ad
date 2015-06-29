package impl.vehiculos;

import impl.misc.Tamano;
import impl.personal.Empleado;
import impl.personal.TipoPuesto;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import persistence.EmpleadoDAO;
import persistence.VehiculoDAO;
import util.Utilities;
import views.vehiculos.PlanMantenimientoView;
import views.vehiculos.VehiculoLocalView;

@Entity
@Table(name = "VehiculosLocales")
public class VehiculoLocal extends Vehiculo {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8225795586920003275L;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_plan_mantenimiento")
	private PlanMantenimiento planMantenimiento;
	@Column(name = "vencimiento_garantia")
	private Date vencimientoGarantia;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_empleado")
	private Empleado empleado;

	public VehiculoLocal() {
	}

	public VehiculoLocal(VehiculoLocalView v, PlanMantenimientoView p, Integer idEmpleado) {
		patente = v.getPatente();
		tamano = new Tamano(v.getTamano());
		peso = v.getPeso();
		tara = v.getTara();
		tarifa = v.getTarifa();
		tipo = TipoVehiculo.obtenerPorTipo(v.getTipo());
		vencimientoGarantia = Utilities.parseDate(v.getVencimientoGarantia());
		planMantenimiento = procesarPlanMantenimiento(p);
		empleado = EmpleadoDAO.getInstance().get(idEmpleado);
		id = VehiculoDAO.getInstance().insert(this);
	}

	private PlanMantenimiento procesarPlanMantenimiento(PlanMantenimientoView p) {
		PlanMantenimiento plan = null;
		switch (p.getTipoPlan()) {
		case "kilometraje":
			plan = new PlanMantenimientoKilometraje(p.getPuntoControl());
			break;
		case "kilometrajeRelativo":
			plan = new PlanMantenimientoKilometrajeRelativo(p.getPuntoControl());
			break;
		case "temporal":
			plan = new PlanMantenimientoTemporal(p.getIntervaloMantenimiento());
			break;
		}
		return plan;
	}

	public PlanMantenimiento getPlanMantenimiento() {
		return planMantenimiento;
	}

	public void setPlanMantenimiento(PlanMantenimiento planMantenimiento) {
		this.planMantenimiento = planMantenimiento;
	}

	public Date getVencimientoGarantia() {
		return vencimientoGarantia;
	}

	public void setVencimientoGarantia(Date vencimientoGarantia) {
		this.vencimientoGarantia = vencimientoGarantia;
	}

	public boolean estaDisponible() {
		Date ahora = new Date();
		for (Tarea tarea : planMantenimiento.getTareas()) {
			if (tarea.getFechaDevolucion().after(ahora)
					&& tarea.getFechaEntrega().before(ahora)) {
				return false;
			}
		}
		return true;
	}

	public void realizarMantenimiento(boolean esEspecifico) {
		planMantenimiento
				.realizarMantenimiento(establecerEstrategiaMantenimiento(esEspecifico));
	}

	private EstrategiaMantenimiento establecerEstrategiaMantenimiento(
			boolean esEspecifico) {
		if (vencimientoGarantia.after(new Date())) {
			return new MantenimientoGarantia();
		} else if (esEspecifico) {
			return new MantenimientoEspecifico();
		} else {
			return new MantenimientoGeneral();
		}
	}

	public void setEmpleado(Empleado e) {

		if (e.getPuesto().equals(TipoPuesto.CHOFER)) {
			this.empleado = e;
		}
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public VehiculoLocalView getView() {

		return new VehiculoLocalView(patente, tamano.getView(), peso, tara,
				tarifa, tipo.toString(), vencimientoGarantia.toString(), empleado.getView());
	}
}
