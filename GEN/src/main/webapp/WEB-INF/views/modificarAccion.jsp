<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="includes/head.jsp" %>
<%@ include file="includes/navbar.jsp" %>
<body>

<div class="container margentop">    
	<h4>Nuova azione</h4>
	<br>
	<form:form class="border border-light p-5" role="form" id="modiAcc" action="modiAcc" method="post" modelAttribute="acc">
	<!--First row-->
	    <div class="row">
	        <!--First column-->
	        <div class="col-md-6">
	            <div class="md-form">
	            	<i class="fa fa-fire prefix"></i>
	            	<form:input type="hidden" path="id_accion" class="form-control" />
	                <form:input type="text" path="nombre" class="form-control" required="true"/>
	    			<label for="nombre" class="textcontrol">Nome Azione</label>
	            </div>
	        </div>
	    </div>
	    
	    <div class="row">
	    <div class="col-md-6">
	               <div class="md-form">
                      <div class="form-group">
					    <form:select id="cmtr" path="estado" class="form-control" required="true">
					  	 	 <form:option value="Iniciado" label="Iniziato" />
					  	 	 <form:option value="Finalizado" label="Finalizzato" />
					  	 	 <form:option value="Cancelado" label="Annullato" />
						</form:select>
					  </div>
	            </div>
	        </div>
	    
	        <!--Second column-->
	        <div class="col-md-4">
	               <div class="md-form">
                      <div class="form-group">
					    <form:select id="cmta" path="id_tipo_Accion" class="browser-default custom-select form-control" required="true">
						   	 <form:option value="1" label="Seleziona il tipo di azione" />
					  	  	 <form:options items="${ta}" itemValue="idTipo_accion" itemLabel="descripcion" />
						</form:select>
					  </div>
	            </div>
	        </div>
	        </div>
	     
	    <!--/.First row-->
	    
	    <div class="row">
	        <!--First column-->
	        <div class="col-md-12">
	            <div class="md-form">
	            	<i class="fa fa-font prefix"></i>
	                <form:textarea type="text" id="descripcion" path="descripcion" class="md-textarea " required="true"/>
	                <label for="desc" class="textcontrol">Descrizione dell'azione</label>
	            </div>
	
	        </div>
	    </div>
	    <br>
	   	<div class="row">
	   	<i class="fas fa-map-marked-alt"></i> <h3 class="text-centro">	Seleziona la posizione dell'azione</h3>
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
	    	<div class="col-md-7">
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
<script>

var mymap = L.map('mapubic').setView([43.93667, 12.44639], 14);
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
        .setContent("Latitudine: " + e.latlng.lat+" Longitudine: " + e.latlng.lng)
        .openOn(mymap);
    
}


mymap.on('click', onMapClick);

</script>
 <%@ include file="includes/footer.jsp" %>      

</body>

</html>
