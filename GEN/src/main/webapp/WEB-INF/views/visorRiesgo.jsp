<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="includes/head.jsp" %>
<%@ include file="includes/navbar.jsp" %>
<body>

<div class="container margentop">    
	<div class="text-centro">
		<h2>Rischio ${rie.nombre}</h2>
	</div>
	<br>
	<form:form class="border border-light" role="form" id="crearRiesgo" action="crearRiesgo" method="post" modelAttribute="rie">
	 <div class="card mb-3">  
		  <img src="${rie.imagen}" class="card-img-top" alt="Imagen del riesgo">
		  <div class="card-body">
		    <h5 class="card-title">Rischio:${rie.nombre}</h5>
		    <p class="card-text">${rie.descripcion}</p>
		    <p class="card-text">Stato: ${rie.estado}</p>
		    <p class="card-text"><small class="text-muted">Data di Inizio: ${rie.fecha_inicio}</small></p>
		  </div>
	</div>

	
	    <!--/.Fourth row-->
	</form:form>
</div>

 <%@ include file="includes/footer.jsp" %>      

</body>

</html>
