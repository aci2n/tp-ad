//package test;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import model.controllers.ControladorPrincipal;
//import model.impl.cargas.Carga;
//import model.impl.cargas.EstadoCarga;
//import model.impl.cargas.TipoCarga;
//import model.impl.misc.Coordenada;
//import model.impl.misc.Tamano;
//import model.impl.misc.Ubicacion;
//import model.impl.productos.CondicionEspecial;
//import model.impl.productos.Producto;
//import model.impl.productos.TipoFragilidad;
//import model.impl.productos.TipoTratamiento;
//import model.impl.sucursales.DistanciaEntreSucursales;
//import model.impl.vehiculos.TipoVehiculo;
//import model.impl.vehiculos.VehiculoLocal;
//import model.impl.viajes.Viaje;
//
//public class TestViaje {
//
//	public static void main(String[] args){
//		List<CondicionEspecial> l1 = new ArrayList<CondicionEspecial>();
//		l1.add(CondicionEspecial.SEGURIDAD);
//		Producto p1 = new Producto(1, "p1", 100f, new Tamano(3f,3f,3f), TipoFragilidad.EXTREMADAMENTE_FRAGIL, 5,
//				"", "", TipoTratamiento.PELIGROSO, "", l1);
//
//		List<CondicionEspecial> l2 = new ArrayList<CondicionEspecial>();
//		l2.add(CondicionEspecial.MONITOREO_SATELITAL);
//		l2.add(CondicionEspecial.TEMPERATURA);
//		Producto p2 = new Producto(2, "p2", 50f, new Tamano(1f,2.5f,0.5f), TipoFragilidad.FRAGIL, 2,
//				"", "", TipoTratamiento.EXTREMADAMENTE_PELIGROSO, "", l2);
//
//		Ubicacion o1 = new Ubicacion(1, "", "", "", "", "", "", "", new Coordenada(10, 10));
//		Ubicacion d1 = new Ubicacion(2, "", "", "", "", "", "", "", new Coordenada(17.5f, 15));
//		Carga c1 = new Carga(1, TipoCarga.BARRIL, null, new Date(), null, "", o1, d1, EstadoCarga.EN_VIAJE);
//
//		c1.agregarItemProducto(p1, 1);
//		c1.agregarItemProducto(p2, 3);
//
//		ControladorPrincipal con = ControladorPrincipal.getInstance();
//				
//		con.getAdministradorSucursales().altaSucursal(1, "1", o1);
//		con.getAdministradorSucursales().altaSucursal(2, "2", d1);
//		
//
//		DistanciaEntreSucursales des1 = new DistanciaEntreSucursales(con.getAdministradorSucursales().obtenerSucursal(1), con.getAdministradorSucursales().obtenerSucursal(2), 455, 6, 500);
//		List<DistanciaEntreSucursales> ldes = new ArrayList<DistanciaEntreSucursales>();
//		ldes.add(des1);
//		
//		con.getAdministradorVehiculos().altaVehiculoLocal(1,"1", new Tamano(10f,10f,10f), 1000f, 60f, 12f, TipoVehiculo.CAMION_CON_CAJA, null, new Date());
//		
//		for (VehiculoLocal vl : con.getAdministradorSucursales().obtenerSucursal(1).getVehiculos()){
//			System.out.println(vl.getPatente());
//		}
//
//		con.getAdministradorCargas().altaCarga(c1, con.getAdministradorSucursales().obtenerSucursal(1));
//		for (Viaje v : con.getAdministradorViajes().getViajes()){
//			for (Carga c : v.getCargas()){
//				System.out.println(c.getCodigo());
//			}
//			System.out.println(v.calcularPesoDisponible());
//		}
//	}
//}
