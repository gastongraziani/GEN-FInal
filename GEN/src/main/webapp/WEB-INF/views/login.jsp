<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="includes/head.jsp" %>
<body>
<%@ include file="includes/navbar.jsp" %>
  
  <c:if test="${not empty error}">
	<div class="alert alert-danger alert-dismissible text-centro" role="alert">
						<button type="button" class="close" data-dismiss="alert" aria-label="Cerrar" ><span aria-hidden="true">&times;</span>	</button>
						<strong><c:out value="${error}"></c:out></strong>
	</div>  
  </c:if>
<div class="container">
	<div class="col-md-6 login">
	 <!-- form login -->
	<form:form class="text-center border border-light p-5" role="form" id="valida" action="validar" method="POST" modelAttribute="usu">
	    <p class="h4 mb-4">Accedi a GEN</p>
	    <!-- Email -->
		<form:input path="usuario" class="form-control mb-4" type="text" placeholder="Account" required="true"/>	
	    <!-- Password -->
	    <form:input  path="clave"  class="form-control mb-4"  type="password" placeholder="Password" required="true"/>
	
	    <div class="d-flex justify-content-around">
	        <div>
	            <!-- Forgot password -->
	            <a href="">Non ricordi più come accedere all'account?</a>
	        </div>
	    </div>
	
	    <!-- Sign in button -->
	    <button class="btn btn-info btn-block my-4" type="submit">Accedi</button>
	
	    <!-- Register -->
	    <p>¿Non hai un account?
	        <a href="nuevoUsuario">Crea un nuovo account</a>
	    </p>

	
	</form:form>
	<!-- form login -->
		   
	</div>
</div>


 <%@ include file="includes/footer.jsp" %>      

</body>

</html>
