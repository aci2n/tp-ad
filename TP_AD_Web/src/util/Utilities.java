package util;

import impl.cargas.Carga;
import impl.misc.Ubicacion;
import impl.viajes.AdministradorViajes;
import impl.viajes.ItemCarga;
import impl.viajes.ParadaIntermedia;
import impl.viajes.Viaje;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

public class Utilities {
	public static Date parseDate(String fecha) {
		Date d = new Date();
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			d = formatter.parse(fecha);
		} catch (Exception e) {
			// mandar saludos
		}
		return d;
	}

	public static String invParseDate(Date date) {
		String s = "";
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
			s = formatter.format(date);
		} catch (Exception e) {
			// mandar saludos
		}
		return s;
	}

	public static Date fechaMaximaDeSalida(Viaje viaje) {
		Date salidaMaxima = null;
		for (ItemCarga carga : viaje.getCargas()) {
			Date salidaCarga = fechaMaximaDeSalida(carga.getCarga(), viaje);
			if (salidaMaxima == null || salidaMaxima.after(salidaCarga)) {
				salidaMaxima = salidaCarga;
			}
		}
		return salidaMaxima;
	}

	public static Date fechaMaximaDeSalida(Carga carga, Viaje viaje) {
		float distancia = 0;
		if (!viaje.getCargas().contains(carga)) {
			viaje = mockViajeConCarga(viaje, carga);
		}
		if (viaje.cantidadParadasIntemedias() == 0) {
			distancia = viaje.getOrigen().calcularDistanciaEnKilometros(viaje.getDestino());
		} else if (viaje.cantidadParadasIntemedias() == 1) {
			distancia = viaje.getOrigen().calcularDistanciaEnKilometros(viaje.getParadasIntermedias().get(0).getUbicacion());
			if (carga.getDestino().tieneMismasCoordenadas(viaje.getDestino())) {
				distancia += viaje.getParadasIntermedias().get(0).getUbicacion().calcularDistanciaEnKilometros(viaje.getDestino());
			}
		} else {
			distancia += viaje.getOrigen().calcularDistanciaEnKilometros(viaje.getParadasIntermedias().get(0).getUbicacion());
			if (!viaje.getParadasIntermedias().get(0).getUbicacion().tieneMismasCoordenadas(carga.getDestino())) {
				boolean llego = false;
				for (int i = 0; i < viaje.cantidadParadasIntemedias() - 1; i++) {
					ParadaIntermedia actual = viaje.getParadasIntermedias().get(i);
					ParadaIntermedia siguiente = viaje.getParadasIntermedias().get(i + 1);
					distancia += actual.getUbicacion().calcularDistanciaEnKilometros(siguiente.getUbicacion());
					if (siguiente.getUbicacion().tieneMismasCoordenadas(carga.getDestino())) {
						llego = true;
						break;
					}
				}
				if (!llego) {
					distancia += viaje.getParadasIntermedias().get(viaje.cantidadParadasIntemedias() - 1).getUbicacion()
							.calcularDistanciaEnKilometros(viaje.getDestino());
				}
			}
		}
		float horas = distancia / AdministradorViajes.VELOCIDAD_PROMEDIO;
		Calendar cal = Calendar.getInstance();
		cal.setTime(carga.getFechaMaximaEntrega());
		cal.add(Calendar.DATE, -1);
		cal.add(Calendar.HOUR, -((int) horas));
		cal.add(Calendar.MINUTE, -((int) ((horas - (int) horas) * 60)));
		return cal.getTime();
	}

	private static Viaje mockViajeConCarga(Viaje viaje, Carga carga) {
		Viaje v = new Viaje();
		v.setOrigen(viaje.getOrigen());
		v.setDestino(viaje.getDestino());
		List<ParadaIntermedia> paradas = new ArrayList<ParadaIntermedia>();
		paradas.addAll(viaje.getParadasIntermedias());
		ParadaIntermedia nuevaParada = new ParadaIntermedia();
		nuevaParada.setUbicacion(carga.getDestino());
		paradas.add(nuevaParada);
		v.setParadasIntermedias(ordenarParadasIntermedias(v.getOrigen(), paradas));
		return v;
	}

	public static List<ParadaIntermedia> ordenarParadasIntermedias(Ubicacion origen, List<ParadaIntermedia> paradas) {
		List<ParadaIntermedia> ordenadas = new ArrayList<ParadaIntermedia>();
		if (paradas.size() == 1) {
			ordenadas.add(paradas.get(0));
		} else {
			ParadaIntermedia masCercana = obtenerParadaMasCercana(origen, paradas);
			ordenadas.add(masCercana);
			paradas.remove(masCercana);
			ordenadas.addAll(ordenarParadasIntermedias(masCercana.getUbicacion(), paradas));
		}
		return ordenadas;
	}

	private static ParadaIntermedia obtenerParadaMasCercana(Ubicacion ubicacion, List<ParadaIntermedia> paradas) {
		ParadaIntermedia mejorParada = null;
		float distancia = Integer.MAX_VALUE;
		for (ParadaIntermedia parada : paradas) {
			float dist = ubicacion.calcularDistanciaEnKilometros(parada.getUbicacion());
			if (dist < distancia) {
				distancia = dist;
				mejorParada = parada;
			}
		}
		return mejorParada;
	}

	public static void saveXml(Document doc) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("./xml/" + generateFileName() + ".xml"));
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	public static void printXml(Document doc) {
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(System.out);
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

	private static String generateFileName() {
		try {
			Date date = new Date();
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			return format.format(date) + "_" + randomString();
		} catch (Exception e) {
			return "error_" + randomString();
		}
	}

	private static String randomString() {
		char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 10; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		return sb.toString();
	}
}
