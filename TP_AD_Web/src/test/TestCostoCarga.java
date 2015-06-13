//package test;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
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
//
//public class TestCostoCarga {
//
//	public static void main(String[] args) {
//		//defino productos
//		List<CondicionEspecial> l1 = new ArrayList<CondicionEspecial>();
//		l1.add(CondicionEspecial.SEGURIDAD);
//		Producto p1 = new Producto(1, "p1", 100f, new Tamano(3f,8f,6f), TipoFragilidad.EXTREMADAMENTE_FRAGIL, 5,
//				"", "", TipoTratamiento.PELIGROSO, "", l1);
//		
//		List<CondicionEspecial> l2 = new ArrayList<CondicionEspecial>();
//		l2.add(CondicionEspecial.MONITOREO_SATELITAL);
//		l2.add(CondicionEspecial.TEMPERATURA);
//		Producto p2 = new Producto(2, "p2", 50f, new Tamano(1f,2.5f,0.5f), TipoFragilidad.FRAGIL, 2,
//				"", "", TipoTratamiento.EXTREMADAMENTE_PELIGROSO, "", l2);
//		
//		Ubicacion o1 = new Ubicacion(1, "", "", "", "", "", "", "", new Coordenada(10, 10));
//		Ubicacion d1 = new Ubicacion(2, "", "", "", "", "", "", "", new Coordenada(17.5f, 898));
//		Carga c1 = new Carga(1, TipoCarga.BARRIL, null, new Date(), null, "", o1, d1, EstadoCarga.EN_VIAJE);
//		
//		c1.agregarItemProducto(p1, 1);
//		c1.agregarItemProducto(p2, 3);
//		
//		System.out.println("factor p1: " + p1.calcularFactorProducto());
//		System.out.println("factor p2: " +p2.calcularFactorProducto());
//		System.out.println("factor p total: " +c1.calcularFactorProductos());
//		System.out.println("factor distancia: " +c1.calcularFactorDistancia());
//		System.out.println("costo: $" + c1.calcularCosto());
//	}
//}
