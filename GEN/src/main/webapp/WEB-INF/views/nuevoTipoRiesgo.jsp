<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="includes/head.jsp" %>
<%@ include file="includes/navbar.jsp" %>
<body>

<div class="container margentop">    
	<h4>Nuova categoria di rischio</h4>
	<br>
	<form:form class="border border-light p-5" role="form" id="crearTipoRiesgo" action="crearTipoRiesgo" method="post" modelAttribute="tr">
	<!--First row-->
	    <div class="row">
	        <!--First column-->
	        <div class="col-md-6">
	            <div class="md-form">
	            	<i class="fas fa-file-signature prefix"></i>
	                <form:input type="text" path="nombre" class="form-control"/>
	    			<label for="nombre" class="textcontrol">Nome</label>
	            </div>
	        </div>
	    </div>
	    <div class="row">
	      <div class="col-md-6">
	            <div class="md-form">
					<i class="fas fa-align-justify prefix"></i>
	                <form:textarea type="text" path="descripcion" class="md-textarea form-control"/>
	    			<label for="descripcion" class="textcontrol">Descrizione</label>
	            </div>
	      </div>
	   </div>
	    <!--/.First row-->
	    
		    <!--Fourth row-->
	    <div class="row">
	    <div class="col-md-2">
	    </div>
	     	<div class="col-md-2">
	            <div class="md-form">
	            <button type="button" class="btn btn-blue-grey">Annullare</button>
	    	 </div>
	        </div>
	        <div class="col-md-2">
	            <div class="md-form">
	            <button type="submit" class="btn btn-success">Salvare</button>
	    	 </div>
	        </div>
	    </div>
	    <!--/.Fourth row-->
	</form:form>
</div>

 <%@ include file="includes/footer.jsp" %>      

</body>

</html>
