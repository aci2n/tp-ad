package test;


public class Test1 {

	public static void main(String[] args) {
//		try{
//			ControladorPrincipal con = ControladorPrincipal.getInstance();
//
//			//dar de alta clientes
//			for (int i = 0; i<5; i++)
//				con.altaClienteEmpresa("codigoempresa" + i, "nombreempresa" + i);
//			for (int i = 0; i<5; i++)
//				con.altaClienteParticular("codigoparticular" + i, "dniparticular" + i, "nombreparticular" + i, "apellidoparticular" + i);
//
//			con.bajaCliente("codigoempresa4");
//
//			for (Cliente c : con.getClientes()){
//				System.out.println(c.getCodigoUnico());
//				System.out.println(c.getNombre());
//			}
//
//			//dar de alta sucursales
//			for (int i = 0; i<5; i++)
//				con.altaSucursal(i, "sucursal" + i);		
//
//			for (Sucursal s : con.getSucursales()){
//				System.out.println(s.getNumero());
//				System.out.println(s.getNombre());
//			}
//
//			//materiales no transportables
//			List<String> materialesNoTransportables = new ArrayList<String>();
//			materialesNoTransportables.add("Bombas");
//			materialesNoTransportables.add("Droga");
//			con.setMaterialesProhibidos(materialesNoTransportables);
//
//			//agrego un par de productos
//			List<Producto> productosControlador = new ArrayList<Producto>();
//			/*Producto producto1 = new Producto();
//			producto1.setCodigoProducto(1);
//			productosControlador.add(producto1);
//			producto1.setMaterial("Ayy lmao");
//			producto1.setTamano(new Tamano(3f,3f,3f));
//			Producto producto2 = new Producto();
//			producto2.setCodigoProducto(2);
//			productosControlador.add(producto2);
//			producto2.setTamano(new Tamano(2f,2f,2f));
//			con.setProductos(productosControlador);*/
//
//			//intento agregar una carga
//			Ubicacion origen =  new Ubicacion (1,"a","a","a","a","a","a","a",new Coordenada(1,1));
//			Ubicacion destino = new Ubicacion (2,"a","a","a","a","a","a","a",new Coordenada(1,1));
//			//CargaView carga = new CargaView(1, TipoCarga.BARRIL, new Date(), new Date(), "codigoempresa1", "ayylmao", origen, destino, EstadoCarga.ENTREGADA);
//			List<ItemProducto> productos = new ArrayList<ItemProducto>();
//			/*productos.add(new ItemProducto(producto1, 10));
//			productos.add(new ItemProducto(producto2, 40));*/
//			
//
//			//con.asignarCargaASucursal(1, carga);
//			
//			Carga carga1 = new Carga(1, TipoCarga.BARRIL, new Date(),
//			new Date(), con.obtenerCliente("codigoempresa1"), "a",
//			origen, destino, EstadoCarga.EN_DEPOSITO);
//			carga1.setProductos(productos);
//			
//			con.asignarCargaASucursal(1, carga1);
//			
//			System.out.println();
//			System.out.println("/* CARGASSS */");
//			System.out.println();
//
//			for (Carga c : con.obtenerSucursal(1).getDeposito().getCargas()){
//				System.out.println(
//						"codigo:" + c.getCodigo() + "\n" +
//								"pais:" + c.getDestino().getPais() + "\n");
//				for (ItemProducto ip : c.getProductos()){
//					System.out.println(
//							"codigoProducto:" + ip.getProducto().getCodigoProducto() + "\n" +
//									"cantidad:" + ip.getCantidad() + "\n");
//				}
//			}
//			
//			System.out.println();
//			System.out.println("/* VIAJES */");
//			System.out.println();
//
//			//creo un viaje
//			List<CondicionEspecial> condicionesEspeciales = new ArrayList<CondicionEspecial>();
//			condicionesEspeciales.add(CondicionEspecial.SEGURIDAD);
//			condicionesEspeciales.add(CondicionEspecial.TEMPERATURA);
//			con.altaViaje(1, con.obtenerSucursal(1).getDeposito().getCargas(), null, null, 
//					new Date(), condicionesEspeciales, null);
//
//			for (Viaje v : con.getViajes()){
//				System.out.println(
//						"codigoViaje:" + v.getCodigo() + "\n" +
//						"condicion especial 0:" + v.getCondicionesEspeciales().get(0));
//				for (Carga c : v.getCargas()){
//					System.out.println(
//							"codigo:" + c.getCodigo() + "\n" +
//									"pais:" + c.getDestino().getPais() + "\n");
//				}
//			}
//			
//			System.out.println();
//			System.out.println("/* VEHICULOS */");
//			System.out.println();
//			
//			Calendar c = Calendar.getInstance();
//			c.setTime(new Date()); // Now use today date.
//			c.add(Calendar.DATE, -1); //arranca con garantia vencida
//			
//			con.altaVehiculoLocal(1, "vehiculo1", new Tamano(10f,10f,10f), 1f, 1f, 1f, TipoVehiculo.CAMION_CON_TANQUE, new PlanMantenimientoKilometraje(100), c.getTime());
//			
//			for (Vehiculo v : con.obtenerSucursal(1).getVehiculos()){
//				System.out.println(v.getPatente());
//			}
//			
//			con.realizarMantenimientoVehiculo(1, "vehiculo1", true); //mantenimiento especifico
//			con.realizarMantenimientoVehiculo(1, "vehiculo1", false); //mantenimiento general
//			c.add(Calendar.DATE, 5);((VehiculoLocal)con.obtenerSucursal(1).obtenerVehiculo("vehiculo1")).setVencimientoGarantia(c.getTime()); //hago que este en garantia
//			con.realizarMantenimientoVehiculo(1, "vehiculo1", true); //lo hace en garantia en ambos casos
//			con.realizarMantenimientoVehiculo(1, "vehiculo1", false);
//			
//			for (Tarea t : ((VehiculoLocal)con.obtenerSucursal(1).obtenerVehiculo("vehiculo1")).getPlanMantenimiento().getTareas()){
//				System.out.println("fecha entrega: " + t.getFechaEntrega());
//				System.out.println("fecha devolucion: " + t.getFechaDevolucion());
//			}
//			
//			/*
//			Carga cg = new Carga(carga.getCodigo(), carga.getTipo(), carga.getFechaMaximaEntrega(),
//			carga.getFechaProbableEntrega(), cliente, carga.getManifiesto(), origen,
//			destino, carga.getEstadoCarga());
//			 */
//			
//			/*producto1.setTratamiento(TipoTratamiento.EXTREMADAMENTE_PELIGROSO);
//			producto1.setFragilidad(TipoFragilidad.EXTREMADAMENTE_FRAGIL);
//			System.out.println(producto1.calcularFactorProducto());
//			System.out.println(producto2.calcularFactorProducto());*/
//			
//			System.out.println(carga1.calcularCosto());
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
	}
}
