<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Listado de Clientes</title>
</head>
<body>

	<div class="row">
		<div class="container">
			<div class="card blue-grey darken-1">
				<div class="card-content white-text">
					<span class="card-title">Listado de Clientes</span>
				</div>
			</div>
		</div>
	</div>
	<div class="row container z-depth-1">
		<div class="input-field col s6">
          <input id="filtrado" type="text" class="validate">
          <label for="first_name">Filtrado de Tabla</label>
        </div>

		<table>
			<thead>
				<tr>
					<th class="">ID</th>
					<th data-field="name">Name</th>
					<th data-field="item-name">Item Name</th>
					<th data-field="price">Item Price</th>
				</tr>
			</thead>

			<tbody id="tbody">
				<tr>
					<td>Alvin</td>
					<td>Eclair</td>
					<td>$0.87</td>
				</tr>
				<tr>
					<td>Alan</td>
					<td>Jellybean</td>
					<td>$3.76</td>
				</tr>
				<tr>
					<td>Jonathan</td>
					<td>Lollipop</td>
					<td>$7.00</td>
				</tr>
			</tbody>
		</table>
	</div>

	<script>
		$("#filtrado").keyup(function() {
			//split the current value of searchInput
			var data = this.value.split(" ");
			//create a jquery object of the rows
			var jo = $("tbody").find("tr");
			if (this.value == "") {
				jo.show();
				return;
			}
			//hide all the rows
			jo.hide();

			//Recusively filter the jquery object to get results.
			jo.filter(function(i, v) {
				var $t = $(this);
				for (var d = 0; d < data.length; ++d) {
					if ($t.is(":contains('" + data[d] + "')")) {
						return true;
					}
				}
				return false;
			})
			//show the rows that match.
			.show();
		}).focus(function() {
			this.value = "";
			$(this).css({
				"color" : "black"
			});
			$(this).unbind('focus');
		}).css({
			"color" : "#C0C0C0"
		});
	</script>
</body>