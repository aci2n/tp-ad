package model.impl.vehiculos;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import model.impl.misc.Tamano;

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
	
	public VehiculoLocal(){
		
	}
	
	public VehiculoLocal(String patente, Tamano tamano, Float peso, Float tara,
			Float tarifa, TipoVehiculo tipo,
			PlanMantenimiento planMantenimiento, Date vencimientoGarantia) {
		this.patente = patente;
		this.tamano = tamano;
		this.peso = peso;
		this.tara = tara;
		this.tarifa = tarifa;
		this.tipo = tipo;
		this.planMantenimiento = planMantenimiento;
		this.vencimientoGarantia = vencimientoGarantia;
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
			if (tarea.getFechaDevolucion().after(ahora) && tarea.getFechaEntrega().before(ahora)) {
				return false;
			}
		}
		
		return true;
	}
	
	public void realizarMantenimiento(boolean esEspecifico) {
		planMantenimiento.realizarMantenimiento(establecerEstrategiaMantenimiento(esEspecifico));
	}

	private EstrategiaMantenimiento establecerEstrategiaMantenimiento(boolean esEspecifico) {
		if (vencimientoGarantia.after(new Date())){
			return new MantenimientoGarantia();
		}
		else if (esEspecifico){
			return new MantenimientoEspecifico();
		}
		else{
			return new MantenimientoGeneral();
		}
	}
	
}
