package impl.vehiculos;

import impl.PersistentObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PlanesMantenimiento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@AttributeOverride(name = "id", column = @Column(name = "id_plan_mantenimiento"))
@DiscriminatorColumn(name = "discriminador", discriminatorType = DiscriminatorType.STRING)
public abstract class PlanMantenimiento extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8624311545758941518L;

	@Column(name = "kilometraje")
	protected Float kilometraje;
	@Column(name = "fecha_fabricacion")
	protected Date fechaFabricacion;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_plan_mantenimiento")
	protected List<Tarea> tareas;

	public PlanMantenimiento() {
		this.tareas = new ArrayList<Tarea>();
		this.fechaFabricacion = new Date();
	}

	public List<Tarea> getTareas() {
		return tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Float getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Float kilometraje) {
		this.kilometraje = kilometraje;
	}

	public Date getFechaFabricacion() {
		return fechaFabricacion;
	}

	public void setFechaFabricacion(Date fechaFabricacion) {
		this.fechaFabricacion = fechaFabricacion;
	}

	public void realizarMantenimiento(
			EstrategiaMantenimiento estrategiaMantenimiento) {
		tareas.add(estrategiaMantenimiento.realizarMantenimiento(this));
	}
	
	public void agregarTarea(Tarea tarea) {
		if (tareas == null) {
			tareas = new ArrayList<Tarea>();
		}
		tareas.add(tarea);
	}

	public abstract float calcularRetraso();

}
