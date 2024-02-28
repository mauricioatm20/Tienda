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
<title>Tienda</title>
<link rel="stylesheet" href="css/index.css">
</head>
<body>

	<div class="cabecera">
		<h2>Menu Principal</h2>
	</div>
	
	<div id="contPrincipal">
	
		<ul>
			<li><a href="listado_productos">Listado Productos </a></li>
			<li><a href="Registro_productos">Crear Productos </a></li>
			<li><a href="productos_fabricante">Productos por Fabricante</a></li>
		
		</ul>
	</div>
</body>
</html>