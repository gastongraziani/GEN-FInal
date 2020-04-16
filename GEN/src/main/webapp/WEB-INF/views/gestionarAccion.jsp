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
	<h4>Gestione delle Azioni</h4>
	<br>
	
<table id="dtBasicExample" class="table table-striped table-bordered">
  <thead>
    <tr>
      <th class="th-sm text-centro">Nome 
      </th>
      <th class="th-sm text-centro">Descrizione
      </th>
      <th class="th-sm text-centro">Stato
      </th>
      <th class="th-sm text-centro">Data di creazione
      </th>
      <th class="th-sm text-centro">Cambiamento
      </th>
      <th class="th-sm text-centro">Remove
      </th>
    </tr>
  </thead>
  <tbody>
    <c:forEach items="${accs}" var="accs">
		<tr>
		 <td><c:out value="${accs.nombre}" /></td>
		 <td><c:out value="${accs.descripcion}" /></td>
		 <td><c:out value="${accs.estado}" /></td>
		 <td><c:out value="${accs.fecha_alta}" /></td>
		 <td><a href="modificarAccion?id=${accs.id_accion}"><button type="button" class="btn btn-warning"><i class="fas fa-edit"></i></button></a></td>
		 <td><a href="eliminarAccion?id=${accs.id_accion}"><button type="button" class="btn btn-danger"><i class="far fa-trash-alt"></i></button></a></td>			
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
