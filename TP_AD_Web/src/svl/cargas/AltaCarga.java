package svl.cargas;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import svl.GenericHttpServlet;
import util.Utilities;
import views.cargas.CargaView;
import views.misc.CoordenadaView;
import views.misc.TamanoView;
import views.misc.UbicacionView;
import views.productos.ItemProductoView;
import views.productos.ProductoView;
import views.sucursales.SucursalView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import controllers.ControladorPrincipal;

@WebServlet("/altaCarga")
public class AltaCarga extends GenericHttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8589610416039812469L;
	
	private static class ItemProductoJson {
		private Float cantidad;
		private String nombre;
		private String fragilidad;
		private String tratamiento;
		private Float profundidad;
		private Float alto;
		private Float ancho;
		private Float peso;
		private Integer apilable;
		private String manipulacion;
		private String material;
		private String consideraciones;
		private Boolean refrigerado;
		
		public float getCantidad() {
			return cantidad;
		}
		public void setCantidad(float cantidad) {
			this.cantidad = cantidad;
		}
		public String getNombre() {
			return nombre;
		}
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
		public String getFragilidad() {
			return fragilidad;
		}
		public void setFragilidad(String fragilidad) {
			this.fragilidad = fragilidad;
		}
		public String getTratamiento() {
			return tratamiento;
		}
		public void setTratamiento(String tratamiento) {
			this.tratamiento = tratamiento;
		}
		public float getProfundidad() {
			return profundidad;
		}
		public void setProfundidad(float profundidad) {
			this.profundidad = profundidad;
		}
		public float getAlto() {
			return alto;
		}
		public void setAlto(float alto) {
			this.alto = alto;
		}
		public float getAncho() {
			return ancho;
		}
		public void setAncho(float ancho) {
			this.ancho = ancho;
		}
		public float getPeso() {
			return peso;
		}
		public void setPeso(float peso) {
			this.peso = peso;
		}
		public Integer getApilable() {
			return apilable;
		}
		public void setApilable(Integer apilable) {
			this.apilable = apilable;
		}
		public String getManipulacion() {
			return manipulacion;
		}
		public void setManipulacion(String manipulacion) {
			this.manipulacion = manipulacion;
		}
		public String getMaterial() {
			return material;
		}
		public void setMaterial(String material) {
			this.material = material;
		}
		public String getConsideraciones() {
			return consideraciones;
		}
		public void setConsideraciones(String consideraciones) {
			this.consideraciones = consideraciones;
		}
		public Boolean getRefrigerado() {
			return refrigerado;
		}
		public void setRefrigerado(Boolean refrigerado) {
			this.refrigerado = refrigerado;
		}
		
		public ItemProductoView toItemProducto() {
			ProductoView producto = new ProductoView(
				nombre,
				fragilidad,
				tratamiento,
				new TamanoView(alto, ancho, profundidad),
				peso,
				apilable,
				manipulacion,
				material,
				consideraciones,
				refrigerado
			);
			
			return new ItemProductoView(producto, cantidad);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			Boolean local = request.getParameter("local") != null ? Boolean.parseBoolean(request.getParameter("local")) : null;
			String tipoCarga = request.getParameter("tipoCarga");
			Integer idCliente = request.getParameter("cuit") != null ? Integer.parseInt(request.getParameter("cuit")) : null;
			Integer idSucOrigen = request.getParameter("idSucursalOrigen") != null ? Integer.parseInt(request.getParameter("idSucursalOrigen")) : null;
			Date fechaMax = request.getParameter("fechaMaxEntrega") != null ? Utilities.parseDate(request.getParameter("fechaMaxEntrega")) : null;
			String manifiesto = request.getParameter("manifiesto");
			Boolean retira = request.getParameter("retira") != null ? Boolean.parseBoolean(request.getParameter("retira")) : null;
			Integer idSucDestino = request.getParameter("idSucursalDestino") != null ? Integer.parseInt(request.getParameter("idSucursalDestino")) : null;
			String pais = request.getParameter("pais");
			String provincia = request.getParameter("provincia");
			String ciudad = request.getParameter("ciudad");
			String calle = request.getParameter("calle");
			String altura = request.getParameter("altura");
			String departamento = request.getParameter("departamento");
			String piso = request.getParameter("piso");
			Float longitud = request.getParameter("longitud") != null ? Float.parseFloat(request.getParameter("longitud")) : null;
			Float latitud = request.getParameter("latitud") != null ? Float.parseFloat(request.getParameter("latitud")) : null;
			
			List<ItemProductoJson> productosJson = null;
			if (request.getParameter("productos") != null) {
				Gson gson = new Gson();
				Type tipo = new TypeToken<List<ItemProductoJson>>(){}.getType();
				productosJson = gson.fromJson((String) request.getParameter("productos"), tipo);
			}
			
			UbicacionView destino = null;
			if (idSucDestino != null) {
				SucursalView sucDestino = ControladorPrincipal.getInstance().obtenerSucursal(idSucDestino);
				destino = sucDestino.getUbicacion();
			} else {
				destino = new UbicacionView(pais, provincia, ciudad, calle, altura, piso, departamento, new CoordenadaView(latitud, longitud));
			}
			
			
			
			if (productosJson == null || idCliente == null || idSucOrigen == null || fechaMax == null) {
				throw new Exception();
			}
			
			List<ItemProductoView> productos = new ArrayList<ItemProductoView>();
			
			for (ItemProductoJson prodJson : productosJson) {
				productos.add(prodJson.toItemProducto());
			} 

			
			CargaView carga = new CargaView(tipoCarga, Utilities.invParseDate(fechaMax), null, manifiesto, null, destino, null, productos, retira);
			
			ControladorPrincipal.getInstance().altaCarga(idSucOrigen, idCliente, carga, !local);
		} catch (Exception e) {
			e.printStackTrace();
			forwardError(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
