package views.clientes;

import java.util.Date;

import views.GenericView;

public class CobroParcialView extends GenericView{

	private Date fecha;
	private Float monto;

	public CobroParcialView() {

	}
	
	public CobroParcialView(Date fecha, Float monto){
		
		this.fecha = fecha;
		this.monto = monto;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Float getMonto() {
		return monto;
	}

	public void setMonto(Float monto) {
		this.monto = monto;
	}

}
