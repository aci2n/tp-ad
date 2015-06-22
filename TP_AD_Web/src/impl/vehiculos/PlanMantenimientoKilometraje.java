package impl.vehiculos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import persistence.PlanMantenimientoDAO;

@Entity
@DiscriminatorValue(value = "Kilometraje")
public class PlanMantenimientoKilometraje extends PlanMantenimiento {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4030564496922700825L;
	@Column(name = "punto_control")
	private float puntoControl;

	public PlanMantenimientoKilometraje(float puntoControl) {
		this.puntoControl = puntoControl;
		PlanMantenimientoDAO.getInstance().insert(this);
	}

	public PlanMantenimientoKilometraje() {

	}

	@Override
	public float calcularRetraso() {
		return kilometraje / puntoControl;
	}

	public float getPuntoControl() {
		return puntoControl;
	}

	public void setPuntoControl(float puntoControl) {
		this.puntoControl = puntoControl;
	}

}
