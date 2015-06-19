package model.impl.vehiculos;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "KilometrajeRelativo")
public class PlanMantenimientoKilometrajeRelativo extends PlanMantenimiento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9027189985164803874L;
	@Column(name = "punto_control")
	private float puntoControl;
	
	public PlanMantenimientoKilometrajeRelativo(float puntoControl) {
		this.puntoControl = puntoControl;
	}
	
	@Override
	public float calcularRetraso() {
		if (tareas.isEmpty()) {
			return 0;
		}
		return tareas.get(tareas.size() - 1).getKilometraje() / puntoControl;
	}

	public float getPuntoControl() {
		return puntoControl;
	}
	public void setPuntoControl(float puntoControl) {
		this.puntoControl = puntoControl;
	}
	
}
