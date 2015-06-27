package impl.viajes;

import impl.PersistentObject;
import impl.cargas.TipoCarga;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import persistence.SeguroDAO;
import views.viajes.SeguroView;

@Entity
@Table(name = "Seguros")
@AttributeOverride(name = "id", column = @Column(name = "id_seguro"))
public class Seguro extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1508681287329096593L;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "tipo_carga")
	@Enumerated(value = EnumType.STRING)
	private TipoCarga tipoCarga;
	@Column(name = "tarifa")
	private Float tarifa;

	public Seguro() {

	}

	public Seguro(SeguroView s) {
		nombre = s.getNombre();
		tarifa = s.getTarifa();
		tipoCarga = TipoCarga.valueOf(s.getTipoCarga());
		id = SeguroDAO.getInstance().insert(this);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public TipoCarga getTipoCarga() {
		return tipoCarga;
	}

	public void setTipoCarga(TipoCarga tipoCarga) {
		this.tipoCarga = tipoCarga;
	}

	public Float getTarifa() {
		return tarifa;
	}

	public void setTarifa(Float tarifa) {
		this.tarifa = tarifa;
	}

	public SeguroView getView() {
		return new SeguroView(nombre, tipoCarga.getTipo(), tarifa);
	}

}
