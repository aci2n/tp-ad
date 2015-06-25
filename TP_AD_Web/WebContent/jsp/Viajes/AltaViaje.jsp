<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Alta Viaje</title>
</head>
<body>
	<form action="AltaViaje">
		<table>
			<tr>
				<td>ID Vehiculo:</td>
				<td><input type="text" name="idVehiculo"></td>
			</tr>
			<tr>
				<td>ID Seguro:</td>
				<td><input type="text" name="idSeguro"></td>
			</tr>
			<tr>
				<td>Fecha Salida:</td>
				<td><input type="text" name="fechaSalida"></td>
			</tr>
			<tr>
				<td>Fecha Llegada:</td>
				<td><input type="text" name="fechaLlegada"></td>
			</tr>
			<tr>
				<td>- Ubicacion origen -</td>
			</tr>
			<tr>
				<td>Pais:</td>
				<td><input type="text" name="paisO"></td>
			</tr>
			<tr>
				<td>Provincia:</td>
				<td><input type="text" name="provinciaO"></td>
			</tr>
			<tr>
				<td>Ciudad:</td>
				<td><input type="text" name="ciudadO"></td>
			</tr>
			<tr>
				<td>Calle:</td>
				<td><input type="text" name="calleO"></td>
			</tr>
			<tr>
				<td>Altura:</td>
				<td><input type="text" name="alturaO"></td>
			</tr>
			<tr>
				<td>Piso:</td>
				<td><input type="text" name="pisoO"></td>
			</tr>
			<tr>
				<td>Departamento:</td>
				<td><input type="text" name="departamentoO"></td>
			</tr>
			<tr>
				<td>Latitud:</td>
				<td><input type="text" name="latitudO"></td>
			</tr>
			<tr>
				<td>Longitud:</td>
				<td><input type="text" name="longitudO"></td>
			</tr>
			<tr>
				<td>- Ubicacion destino -</td>
			</tr>
			<tr>
				<td>Pais:</td>
				<td><input type="text" name="paisD"></td>
			</tr>
			<tr>
				<td>Provincia:</td>
				<td><input type="text" name="provinciaD"></td>
			</tr>
			<tr>
				<td>Ciudad:</td>
				<td><input type="text" name="ciudadD"></td>
			</tr>
			<tr>
				<td>Calle:</td>
				<td><input type="text" name="calleD"></td>
			</tr>
			<tr>
				<td>Altura:</td>
				<td><input type="text" name="alturaD"></td>
			</tr>
			<tr>
				<td>Piso:</td>
				<td><input type="text" name="pisoD"></td>
			</tr>
			<tr>
				<td>Departamento:</td>
				<td><input type="text" name="departamentoD"></td>
			</tr>
			<tr>
				<td>Latitud:</td>
				<td><input type="text" name="latitudD"></td>
			</tr>
			<tr>
				<td>Longitud:</td>
				<td><input type="text" name="longitudD"></td>
			</tr>
		</table>
		<br /> <input type="submit" value="Alta">
	</form>
</body>
</html>