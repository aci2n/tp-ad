package model.impl.vehiculos;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@DiscriminatorValue(value = "Temporal")
public class PlanMantenimientoTemporal extends PlanMantenimiento {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8994537799759906446L;
	@Column(name = "intervalo_mantenimiento")
	private int intervaloMantenimiento;
	
	public PlanMantenimientoTemporal(int intervaloMantenimiento) {
		this.intervaloMantenimiento = intervaloMantenimiento;
	}
	
	@Override
	public float calcularRetraso() {
		Date hoy = new Date();
		Date ultimoMantenimiento = fechaFabricacion;
		
		if (!tareas.isEmpty()) {
			ultimoMantenimiento = tareas.get(tareas.size() - 1).getFechaDevolucion();
		}

		long diferenciaMilisegundos = hoy.getTime() - ultimoMantenimiento.getTime();

		float diferenciaDias = (float) diferenciaMilisegundos / (24 * 60 * 60 * 1000);
		
		return diferenciaDias / intervaloMantenimiento;
	}

	public int getIntervaloMantenimiento() {
		return intervaloMantenimiento;
	}

	public void setIntervaloMantenimiento(int intervaloMantenimiento) {
		this.intervaloMantenimiento = intervaloMantenimiento;
	}

}
