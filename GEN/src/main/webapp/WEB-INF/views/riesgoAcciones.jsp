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
	<h4>Asignar acciones a riesgos:</h4>
	<form:form class="border border-light p-5" role="form" id="crearRiesgo" action="rieAcc" method="post" modelAttribute="accrie">
	  	<div class="row">
	           <div class="col-md-6">
	               <div class="md-form">
                      <div class="form-group">Accion:
					    <form:select id="cmacc" path="id_accion" class="form-control" required="true">
					  	  	 <form:options items="${accs}" itemValue="id_accion" itemLabel="nombre" />
						</form:select>
					  </div>
	            </div>
	        </div>
	     </div>
	 <form:input path="idriesgo" type="hidden" class="form-control" />
	 
	    <div class="col-md-12">
	            <div class="md-form">
	            	<i class="fa fa-font prefix"></i>
	                <form:textarea type="text" id="desc" path="descripcion" class="md-textarea " required="true"/>
	                <label for="desc" class="textcontrol">Comentario</label>
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
	   <div class="col-md-5">
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
	</form:form>
	
</div>
<script>

var mymap = L.map('mapubic').setView([-32.955096650, -60.655925274], 14);
L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
	maxZoom: 16,
	id: 'mapbox.streets'
}).addTo(mymap);

var lon = ${rie.getLongitud()}
var lat = ${rie.getLatitud()}
var tamaño = ${rie.getTamaño()}
var color = '${rie.getColor()}'

var circle = L.circle([lat,lon], {
    color:color,
    fillColor: color,
    fillOpacity: 0.3,
    radius: tamaño
}).addTo(mymap);
 var customOptions =
 {
 'maxWidth': '500',
 'className' : 'custom'
 }
 


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
