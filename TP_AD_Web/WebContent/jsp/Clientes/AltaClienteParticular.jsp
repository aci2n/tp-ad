<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form id="alta-cliente" action="AltaClienteParticular"
		class="box-padding" class="form-control">

		<div class="input-field col s12">
			<input type="number" class="validate" name="dni"> <label
				for="email">DNI</label>
		</div>
		<div class="input-field col s12">
			<input type="text" class="validate" name="nombre"> <label
				for="email">Nombre</label>
		</div>
		<div class="input-field col s12">
			<input type="text" class="validate" name="apellido"> <label
				for="email">Apellido</label>
		</div>
		<input class="btn btn-primary" type="submit" value="Alta">
	</form>

</body>
</html>