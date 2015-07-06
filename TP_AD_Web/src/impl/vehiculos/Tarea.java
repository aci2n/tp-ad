package impl.vehiculos;

import impl.PersistentObject;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import persistence.VehiculoDAO;
import util.Utilities;
import views.vehiculos.TareaView;

@Entity
@Table(name = "Tareas")
@AttributeOverride(name = "id", column = @Column(name = "id_tarea"))
public class Tarea extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1988465934755698719L;

	@Column(name = "kilometraje")
	private Float kilometraje;
	@Column(name = "fecha_entrega")
	private Date fechaEntrega;
	@Column(name = "fecha_devolucion")
	private Date fechaDevolucion;

	public Tarea(TareaView tarea) {
		kilometraje = tarea.getKilometraje();
		fechaEntrega = Utilities.parseDate(tarea.getFechaEntrega());
		fechaDevolucion = Utilities.parseDate(tarea.getFechaDevolucion());
		VehiculoDAO.getInstance().insert(this);
	}

	public Tarea() {

	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Float getKilometraje() {
		return kilometraje;
	}

	public void setKilometraje(Float kilometraje) {
		this.kilometraje = kilometraje;
	}
	
	public TareaView getView() {
		return new TareaView(kilometraje, Utilities.invParseDate(fechaEntrega), Utilities.invParseDate(fechaDevolucion));		
	}

}
