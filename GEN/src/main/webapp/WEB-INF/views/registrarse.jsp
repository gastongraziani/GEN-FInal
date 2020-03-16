<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="includes/head.jsp" %>
<%@ include file="includes/navbar.jsp" %>

<body>
	<form:form class="border border-light p-5" role="form" id="registrarse" action="registrarse" method="post" modelAttribute="nu">
	<!--First row-->
	    <div class="row">
	        <!--First column-->
	        <div class="col-md-6">
	            <div class="md-form">
				<i class="fas fa-signature prefix"></i>
	                <form:input type="text" path="nombre" class="form-control" required="true"/>
	    			<label for="nombre" class="textcontrol">Nombre</label>
	            </div>
	        </div>
	           <div class="col-md-6">
	            <div class="md-form">
				<i class="fas fa-signature prefix"></i>	   
                <form:input type="text" path="apellido" class="form-control" required="true"/>
	    		<label for="apellido" class="textcontrol">Apellido</label>
	            </div>
	        </div>
	    </div>
	    <!--/.First row-->
	    
	    <div class="row">
	    <div class="col-md-6">
	            <div class="md-form">
				<i class="fas fa-user prefix"></i>	   
	               <form:input type="text" path="usuario" class="form-control" maxlength="18" pattern="[a-zA-Z0-9]((\.|_|-)?[a-zA-Z0-9]+){6}" title="El nombre de cuenta debe tener al menos 6 caracteres, pueden ser letras,numeros y ._- pero no al comienzo" required="true"/>
	    			<label for="usuario" class="textcontrol">Usuario</label>
	            </div>
	        </div>
	          <div class="col-md-6">
	            <div class="md-form">
				<i class="fas fa-envelope-square prefix"></i>
	                <form:input type="email" path="email" class="md-textarea form-control"  aria-describedby="emailHelp" maxlength="18" pattern="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$" title="Ingrese un mail valido" required="true"/>
	    			<label for="email" class="textcontrol">Email</label>
	            </div>
	      </div>
	    </div>
	     
     
	    <div class="row">
	      <div class="col-md-6">
	            <div class="md-form">
				<i class="fas fa-key prefix"></i>
               	 <form:input type="password" id="clave" path="clave" class="form-control" onkeyup='check();' pattern="(?=.*\d)(?=.*[a-z]).{8,}" title="La contraseña debe tener al menos 8 caracteres, letras y numeros" required="true"/>
	    			<label for="clave" class="textcontrol">Password</label>
	            </div>
	        </div>
	         <div class="col-md-6">
	            <div class="md-form">
				<i class="fas fa-key prefix"></i>
               	 <input type="password" id="clave2"  class="form-control" onkeyup='check();' pattern="(?=.*\d)(?=.*[a-z]).{8,}" title="La contraseña debe tener al menos 8 caracteres, letras y numeros" required/>
	    			<label for="clave2" class="textcontrol">Repetir Password</label>
	    			 <small id="defaultRegisterFormPasswordHelpBlock" class="form-text text-muted mb-4">
   						   	<span id='message'></span>
   					 </small>
	            </div>
	        </div>
	   </div>
	   
	    <div class="row">
	    	<div class="col-md-12">
	            <div class="md-form">
	            	<i class="fas fa-camera-retro prefix"></i>
	                <form:input type="text" path="imagen" class="form-control" required="true"/>
	    			<label for="nombre" class="textcontrol">Imagen de perfil</label>
	            </div>
	        </div>
	    </div>
	   
		<!--Fourth row-->
	    <div class="row">
	    <div class="col-md-4">
	    </div>
	     	<div class="col-md-2">
	            <div class="md-form">
	            <button type="button" class="btn btn-blue-grey">Cancelar</button>
	    	 </div>
	        </div>
	        <div class="col-md-2">
	            <div class="md-form">
	            <button type="submit" id="btnReg" class="btn btn-success">Registrarse</button>
	    	 </div>
	        </div>
	    </div>
	    <!--/.Fourth row-->
	</form:form>


 <%@ include file="includes/footer.jsp" %>      

</body>


<script>
var check = function() {
	  var botonEnviar = document.getElementById('btnReg');
	  botonEnviar.disabled=true;
	  if (document.getElementById('clave').value ==
	    document.getElementById('clave2').value) {
	    document.getElementById('message').style.color = 'green';
	    document.getElementById('message').innerHTML = 'Las contraseñas coinciden <i class="far fa-laugh-beam"></i>';
	    botonEnviar.disabled=false;
	    return true;
	  } else {
	    document.getElementById('message').style.color = 'red';
	    document.getElementById('message').innerHTML = 'No coinciden las contraseñas <i class="far fa-tired"></i>';
	    botonEnviar.disabled=true;
	    return false;
	  }
	}
</script>

</html>
