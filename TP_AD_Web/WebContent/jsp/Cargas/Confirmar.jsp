<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<input type="hidden" id="idCarga" value="<%= request.getAttribute("idCarga")%>">
	<div class="modal-content">
		<h4>Confirmación de la Carga</h4>
		<p>La fecha de llegada será <%= request.getAttribute("fechaProbable")%></p>
		<p>¿Desea finalizar?</p>
	</div>
	<div class="modal-footer">
		<a href="#!"
			class="modal-action modal-close waves-effect waves-green btn-flat">Cancelar</a>
		<input class="btn btn-primary" type="submit" id="submitForReal" value="Alta">
	</div>