package impl.clientes;

import impl.PersistentObject;
import impl.vehiculos.Proveedor;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import views.clientes.PagoView;

@Entity
@Table(name = "Pagos")
@AttributeOverride (name = "id", column = @Column(name ="id_pago"))
public class Pago extends PersistentObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6122982765032430365L;
	
	@ManyToOne
	@JoinColumn(name = "id_proveedor")
	private Proveedor proveedor;
	@Column(name = "monto")
	private Float monto;
	@Column(name = "fecha")
	private Date fecha;
	@Column(name = "pagado")
	private boolean pagado;
	
	public Pago(Proveedor proveedor, Float monto, Date fecha){
		
		this.proveedor = proveedor;
		this.monto = monto;
		this.fecha = fecha;
	}
	
	public Pago() {
		
	}

	public Proveedor getProveedor() {
		return proveedor;
	}
	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
	public Float getMonto() {
		return monto;
	}
	public void setMonto(Float monto) {
		this.monto = monto;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public boolean isEstado() {
		return pagado;
	}
	public void setEstado(boolean estado) {
		this.pagado = estado;
	}
	
	public PagoView getView(){
	
		return new PagoView(proveedor.getView(), monto, fecha, pagado);		
	}
}
