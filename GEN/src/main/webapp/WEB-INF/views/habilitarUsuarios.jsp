<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="includes/head.jsp" %>
<%@ include file="includes/navbar.jsp" %>
<body>

<c:if test="${not empty msj}">
	<div class="alert alert-success alert-dismissible text-centro" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Cerrar" ><span aria-hidden="true">&times;</span>	</button>
						<strong><c:out value="${msj}"></c:out></strong>
	</div>  
</c:if>


<div class="container margentop">    
	<h4>Abilitare utenti:</h4>
	<br>
	
<table id="dtBasicExample" class="table table-striped table-bordered">
  <thead>
    <tr>
      <th class="th-sm">Nome 
      </th>
      <th class="th-sm">Cognome
      </th>
      <th class="th-sm">E-mail
      </th>
      <th class="th-sm">Utente
      </th>
      <th class="th-sm">Stato
      </th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${usus}" var="usus">
		<tr>
		 <td><c:out value="${usus.nombre}" /></td>
		 <td><c:out value="${usus.apellido}" /></td>
		 <td><c:out value="${usus.email}" /></td>
		 <td><c:out value="${usus.usuario}" /></td>
		 <c:if test="${(usus.habilitado)}">
		 <td>
			<a href="habilitarUsu?id=${usus.idusuario}"><button type="button" class="btn btn-success">abilitare <i class="far fa-check-circle"></i></button></a>
		</td>
		</c:if>
		<c:if test="${!(usus.habilitado)}">
		<td>
			<a href="deshabilitarUsu?id=${usus.idusuario}"><button type="button" class="btn btn-danger">disabilitare <i class="fas fa-times"></i></button></a>
		</td>
		</c:if>
											
		</tr>
	</c:forEach>
  </tbody>
</table>
	
	
</div>

<script>
$(document).ready(function () {
	$('#dtBasicExample').DataTable();
	$('.dataTables_length').addClass('bs-select');
	});
</script>

 <%@ include file="includes/footer.jsp" %>      

</body>

</html>
