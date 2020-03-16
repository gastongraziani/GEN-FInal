<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="includes/head.jsp" %>
<%@ include file="includes/navbar.jsp" %>
<body>

<div class="container margentop">    
	<h4>Alta de nuevo riesgo</h4>
	<br>
	<form:form class="border border-light p-5" role="form" id="modiRie" action="modiRie" method="post" modelAttribute="rie">
	<!--First row-->
	    <div class="row">
	        <!--First column-->
	        <div class="col-md-6">
	            <div class="md-form">
	            	<i class="fa fa-fire prefix"></i>
	            	<form:input path="idriesgo" type="hidden" class="form-control" />
	                <form:input type="text" path="nombre" class="form-control" required="true"/>
	    			<label for="nombre" class="textcontrol">Nombre del riesgo</label>
	            </div>
	        </div>
	      </div>
	      
	      <div class="row">
	        <!--Second column-->
	        <div class="col-md-6">
	               <div class="md-form">
                      <div class="form-group">
					    <form:select id="cmtr" path="tipo_riesgo" class="form-control" required="true">
					  	 	 <form:option value="1" label="Seleccione el tipo de riesgo" />
					  	  	 <form:options items="${trs}" itemValue="id_tipor" itemLabel="nombre" />
						</form:select>
					  </div>
	            </div>
	        </div>
	        <div class="col-md-6">
	               <div class="md-form">
                      <div class="form-group">
					    <form:select id="cmtr" path="estado" class="form-control" required="true">
					  	 	 <form:option value="Iniciado" label="Iniciado" />
					  	 	 <form:option value="Finalizado" label="Finalizado" />
					  	 	 <form:option value="Cancelado" label="Cancelado" />
						</form:select>
					  </div>
	            </div>
	        </div>
	    </div>
	     
	    <!--/.First row-->
	    <!--second row-->
	    <div class="row">       
	     <div class="col-md-6">
	               <div class="md-form">
                      <div class="form-group">
					    <form:select id="cmprov" path="prv" class="form-control" required="true">
    					  <form:option value="0" label="Selecciona Provincia" />
						  <form:options items="${prvs}" itemValue="id_provincia" itemLabel="nombre" />
						</form:select>
					  </div>
	            </div>
	        </div>
	
	      <div class="col-md-6">
	               <div class="md-form">
                      <div class="form-group">
					    <form:select id="cmciu" path="ciu" class="form-control">
    					  <form:option value="1111" label="Selecciona Ciudad" />
					 <!--/ <form:options items="${cius}" itemValue="id_ciudad" itemLabel="nombre" />-->
						</form:select>
					  </div>
	            </div>
	        </div> 
	    </div>
	    <!--Second row-->
	    <div class="row">
	        <!--First column-->
	        <div class="col-md-12">
	            <div class="md-form">
	            	<i class="fa fa-font prefix"></i>
	                <form:textarea type="text" id="desc" path="descripcion" class="md-textarea " required="true"/>
	                <label for="desc" class="textcontrol">Descripción del riesgo</label>
	            </div>
	
	        </div>
	    </div>
	    
	   <div class="row">
	 	 <div class="col-md-12">
	            <div class="md-form">
	            	<i class="fas fa-camera-retro prefix"></i>
	                <form:input type="text" path="imagen" class="form-control" required="true"/>
	    			<label for="nombre" class="textcontrol">Imagen del riesgo</label>
	            </div>
	        </div>
	   </div>
	    <br>
	   	<div class="row">
	   	<i class="fas fa-map-marked-alt"></i> <h3 class="text-centro">	Seleccione la ubicacion del riesgo</h3>
	   		<div class="col-md-12">
			   	<div id="mapubic">
				    <script src="https://unpkg.com/leaflet@1.2.0/dist/leaflet.js"></script>   
	      		</div>
		   	</div>
	    </div>
	  
	  	    <div class="row">
	    	<div class="col-md-4">
	    		<form:input type="text" path="latitud" id="latitud" name="latitud" class="form-control" />
	    	</div>
	    	<div class="col-md-4">
	    		<form:input type="text" path="longitud" id="longitud" name="longitud" class="form-control" />
	    	</div>
	   </div> 	
	   <div class="row">
	   <div class="col-md-4">
	   		Color Marcador:
	    	 <form:select id="color" path="color" class="form-control" required="true">
    					 <form:option value="red" label="Rojo" />
						  <form:option value="blue" label="Azul" />
						  <form:option value="black" label="Negro" />
						  <form:option value="white" label="Blanco" />
						  <form:option value="yellow" label="Amarillo" />
						  <form:option value="orange" label="Naranja" />
						  <form:option value="grey" label="gris" />
						  <form:option value="green" label="verde" />
			 </form:select>
	    	</div>
	    	<div class="col-md-4">
	    	Tamaño Marcador:
	    		 <form:select id="tamaño" path="tamaño" class="form-control" required="true">
    					 
    					  <form:option value="40" label="Chico" />
						  <form:option value="75" label="Mediano" />
						  <form:option value="150" label="Grande" />
						  <form:option value="450" label="Muy Grande" />
			 </form:select>
	    	</div>
	    </div>
	   	
	    <div class="row">
	    	<div class="col-md-7">
	    	</div>
	     	<div class="col-md-2">
	            <div class="md-form">
	            <button type="button" class="btn btn-blue-grey">Cancelar</button>
	    	 </div>
	        </div>
	        <div class="col-md-2">
	            <div class="md-form">
	            <button type="submit" class="btn btn-success">Guardar</button>
	    	 </div>
	        </div>
	    </div>
	    <!--/.Fourth row-->
	</form:form>
</div>

<script>

var mymap = L.map('mapubic').setView([-32.955096650, -60.655925274], 14);
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
	maxZoom: 16,
	id: 'mapbox.streets'
}).addTo(mymap);

var popup = L.popup();

function onMapClick(e) {
	longitud.value=e.latlng.lng
	latitud.value=e.latlng.lat
    popup
        .setLatLng(e.latlng)
        .setContent("Latitud: " + e.latlng.lat+" Longitud: " + e.latlng.lng)
        .openOn(mymap);
    
}


mymap.on('click', onMapClick);

</script>
 <%@ include file="includes/footer.jsp" %>      

</body>

</html>
