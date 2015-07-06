<%@page import="impl.productos.CondicionEspecial"%>
<%@page import="impl.productos.TipoFragilidad"%>
<%@page import="impl.productos.TipoTratamiento"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Alta Producto</title>
</head>
<body>
	<form id="altaProducto">
		<table>
			<tr>
				<td>Cantidad:</td>
				<td><input type="number" name="cantidad"></td>
			</tr>
			<tr>
				<td>Productos de empresa:</td>
				<td><select id="productoEmpresa" disabled="disabled"></select></td>
			</tr>
			<tr>
				<td>Nombre:</td>
				<td><input type="text" name="nombre"></td>
			</tr>
			<tr>
				<td>Tipo fragilidad:</td>
				<td><select name="fragilidad">
						<%
							for (TipoFragilidad t : TipoFragilidad.values()) {
								out.print("<option value=\"" + t.toString() + "\">" + t.toString() + "</option>");
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Tipo tratamiento:</td>
				<td><select name="tratamiento">
						<%
							for (TipoTratamiento t : TipoTratamiento.values()) {
								out.print("<option value=\"" + t.toString() + "\">" + t.toString() + "</option>");
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Condición especial:</td>
				<td><select name="condicion">
						<%
							for (CondicionEspecial t : CondicionEspecial.values()) {
								out.print("<option value=\"" + t.toString() + "\">" + t.toString() + "</option>");
							}
						%>
				</select></td>
			</tr>
			<tr>
				<td>Profundidad:</td>
				<td><input type="text" name="profundidad"></td>
			</tr>
			<tr>
				<td>Alto:</td>
				<td><input type="text" name="alto"></td>
			</tr>
			<tr>
				<td>Ancho:</td>
				<td><input type="text" name="ancho"></td>
			</tr>
			<tr>
				<td>Peso:</td>
				<td><input type="text" name="peso"></td>
			</tr>
			<tr>
				<td>Apilable:</td>
				<td><input type="text" name="apilable"></td>
			</tr>
			<tr>
				<td>Manipulacion:</td>
				<td><input type="text" name="manipulacion"></td>
			</tr>
			<tr>
				<td>Material:</td>
				<td><input type="text" name="material"></td>
			</tr>
			<tr>
				<td>Consideraciones:</td>
				<td><input type="text" name="consideraciones"></td>
			</tr>
			<tr>
				<td>Refrigerado:</td>
				<td>
					<input type="checkbox" id="check" name="refrigerado">
					<label for="check"></label>
				</td>
			</tr>
		</table>
		<input class="btn btn-primary" type="submit" value="Alta">
	</form>
	
	<table id="productos" class="responsive-table hoverable" style="display: none">
		<thead>
			<th data-field="cantidad">Cantidad</th>
			<th data-field="nombre">Nombre</th>
			<th data-field="fragilidad">Fragilidad</th>
            <th data-field="tratamiento">Tratamiento</th>
            <th data-field="profundidad">Profundidad</th>
            <th data-field="alto">Alto</th>
            <th data-field="ancho">Ancho</th>
            <th data-field="peso">Peso</th>
            <th data-field="apilable">Apilable</th>
            <th data-field="manipulacion">Manipulación</th>
            <th data-field="material">Material</th>
            <th data-field="consideraciones">Consideraciones</th>
            <th data-field="refrigerado">Refrigerado</th>
		</thead>
		<tbody>
			
		</tbody>
	</table>
	
	<script>
		var _productos = [];
		
		$(document).ready(function() {
			$('select').material_select();
			
			$('#altaProducto').submit(function(event) {
				event.preventDefault();
				if (controlarCampos()) {
					if ($('#productoEmpresa').val() !== undefined && $('#productoEmpresa').val().trim() !== '') {
						agregarProducto();
						var data = $(this).serializeArray();
						$('#productos').show();
						$('#productos tbody').append(renderRow(data));
						$(this).find('table input').val('');	
					} else {
						var prod = {
							cantidad: form.find('input[name="cantidad"]').val(),
							id: $('#productoEmpresa').val()
						};
						_productos.push(prod);

						$('#productos tbody').append(
							$('<tr>').append(
								$('<td>').text(prod.cantidad)
							).append(
								$('<td>').text($('#productos option[value="' + prod.id + '"]').text());
							)
						);
					}
					
				} else {
					alert('Hay campos vacíos.');
				}
			});
			
		});
		
		function controlarCampos() {
			var form = $('#altaProducto');
				if (form.find('input[name="cantidad"]').val() !== ''
				&& form.find('input[name="nombre"]').val() !== ''
				&& form.find('select[name="fragilidad"]').val() !== ''
				&& form.find('select[name="tratamiento"]').val() !== ''
				&& form.find('input[name="profundidad"]').val() !== ''
				&& form.find('input[name="alto"]').val() !== ''
				&& form.find('input[name="ancho"]').val() !== ''
				&& form.find('input[name="peso"]').val() !== ''
				&& form.find('input[name="apilable"]').val() !== ''
				&& form.find('input[name="manipulacion"]').val() !== ''
				&& form.find('input[name="material"]').val() !== ''
				&& form.find('input[name="consideraciones"]').val() !== '') {
					// yee
					return true;
				}
				if (form.find('#productoEmpresa').val() !== undefined && form.find('#productoEmpresa').val() !== ''
				&& form.find('input[name="cantidad"]').val() !== '') {
					return true;
				}
				return false;
		}
		
		function agregarProducto() {
			var form = $('#altaProducto');
			var producto = {
				cantidad: form.find('input[name="cantidad"]').val(),
				nombre: form.find('input[name="nombre"]').val(),
				fragilidad: form.find('select[name="fragilidad"]').val(),
				tratamiento: form.find('select[name="tratamiento"]').val(),
				profundidad: form.find('input[name="profundidad"]').val(),
				alto: form.find('input[name="alto"]').val(),
				ancho: form.find('input[name="ancho"]').val(),
				peso: form.find('input[name="peso"]').val(),
				apilable: form.find('input[name="apilable"]').val(),
				manipulacion: form.find('input[name="manipulacion"]').val(),
				material: form.find('input[name="material"]').val(),
				consideraciones: form.find('input[name="consideraciones"]').val(),
				refrigerado: form.find('input[name="refrigerado"]').is(':checked')
			};
			_productos.push(producto);
		}
		
		function renderRow(data) {
			var row = $('<tr>');
			
			for (var i = 0; i < data.length; i++) {
				row.append(renderProd(data[i]));
			}
			
			return row;
		}
		
		function renderProd(prod) {
			var td = $('<td>');
			td.append(prod.value);
			
			var hidden = $('<input>');
			hidden.attr('type', 'hidden');
			hidden.attr('name', prod.name);
			hidden.val(prod.value);

			td.append(hidden);
			
			return td;
		}

		$('#productoEmpresa').change(function() {
			if ($(this).val() !== undefined && $(this).val().trim() !== '') {
				$(this).closest('tr').nextAll('tr').each(function() {
					$(this).find('input').attr('disabled', 'disabled');
				});
			} else {
				$(this).closest('tr').nextAll('tr').each(function() {
					$(this).find('input').removeAttr('disabled', 'disabled');
				});
			}
		});
	</script>
</body>
</html>