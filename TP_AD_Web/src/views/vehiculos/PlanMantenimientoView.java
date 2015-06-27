package views.vehiculos;

import views.GenericView;

public class PlanMantenimientoView extends GenericView{
	private String tipoPlan;
	private Float puntoControl;
	private Integer intervaloMantenimiento;
	
	public PlanMantenimientoView(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}

	public String getTipoPlan() {
		return tipoPlan;
	}

	public void setTipoPlan(String tipoPlan) {
		this.tipoPlan = tipoPlan;
	}

	public Float getPuntoControl() {
		return puntoControl;
	}

	public void setPuntoControl(Float puntoControl) {
		this.puntoControl = puntoControl;
	}

	public Integer getIntervaloMantenimiento() {
		return intervaloMantenimiento;
	}

	public void setIntervaloMantenimiento(Integer intervaloMantenimiento) {
		this.intervaloMantenimiento = intervaloMantenimiento;
	}

}
