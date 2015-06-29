package impl.clientes;

import impl.cobranzas.Factura;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import persistence.ClienteDAO;
import views.clientes.ParticularView;
import views.clientes.ReceptorView;

@Entity
@Table(name = "Clientes_Particulares")
@AttributeOverride(name = "id", column = @Column(name = "id_cliente"))
public class Particular extends Cliente {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3516360528659218982L;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cliente")
	private List<Receptor> receptores;
	@Column(name = "dni")
	private String dni;
	@Column(name = "apellido")
	private String apellido;

	public Particular(String dni, String nombre,
			String apellido) {
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.id = ClienteDAO.getInstance().insert(this);
	}

	public Particular() {
		
	}

	public List<Receptor> getReceptores() {
		return receptores;
	}

	public void setReceptores(List<Receptor> receptores) {
		this.receptores = receptores;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@Override
	public void cobrarEnvio(Factura factura) {
		// TODO Auto-generated method stub
		
	}
	
	public Integer agregarReceptor(ReceptorView r) {
		if (receptores == null)
			receptores = new ArrayList<Receptor>();
		Receptor receptor = new Receptor(r.getDni(), r.getNombre(), r.getApellido(), r.getUbicacion());
		receptores.add(receptor);
		ClienteDAO.getInstance().update(this);
		return receptor.getId();
	}
	
	public ParticularView getView(){
		
		ParticularView view = new ParticularView(this.id, this.dni, this.nombre, this.apellido);
		List<ReceptorView> receptores = null;
		try{
			receptores = new ArrayList<ReceptorView>();
			for(Receptor r : getReceptores())
				receptores.add(r.getView());
			
			view.setReceptores(receptores);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return view;
	}

}
