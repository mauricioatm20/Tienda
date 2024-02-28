<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="nombre" content="contenido">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Productos</title>
<link rel="stylesheet" href="css/listados.css">
<link rel="stylesheet" href="css/formularios.css">
<link rel="stylesheet" href="css/productos.css">
</head>
<body>

	<div class="cabecera">
		<h2>Busqueda de Productos</h2>
	</div>
	<div id="contPrincipal">
		<form action="listado_productos" method="post">
		<input name="descripcion">
		<button type="submit">Buscar</button>
		</form>
		<c:if test="${not empty prods}"> 
			<table id = "tabla_datos" class="datos">
				<thead>
						<tr>
							<th> Descripcion</th>
							<th> Precio</th>
							<th> Fabricante</th>
							
						</tr>
				</thead>
			<tbody>
				<c:forEach var="prod" items="${prods}">
					<tr>
						<td>${prod.producto}</td>
						<td>${prod.precio}</td>
						<td>${prod.fabricante.fabricante}</td>
					</tr>
				</c:forEach>
			</tbody>
			</table>
				
		 </c:if>
		 
		 <a href="home"><button>volver</button></a>
	</div>

</body>
</html>