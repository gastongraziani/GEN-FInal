
<%@ include file="includes/head.jsp" %>
<body>
<%@ include file="includes/navbar.jsp" %>
        
                     
<div class="container-fluid" style="height:100%;">
  <div class="row">
	<div id="mapid"></div>
	 <!--llamada Leaflet-->
	    <script src="https://unpkg.com/leaflet@1.2.0/dist/leaflet.js"></script>   
  </div>
</div> 
    



<script>
	var mymap = L.map('mapid').setView([-32.955096650, -60.655925274], 14);
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
		maxZoom: 18,
		id: 'mapbox.streets'
	}).addTo(mymap);

	<c:forEach items="${ries}" var="rie">
	
	var lon = ${rie.getLongitud()}
	var lat = ${rie.getLatitud()}
	var tamaño = ${rie.getTamaño()}
	var color = '${rie.getColor()}'
	//var circle = L.circle([-32.955096650, -60.625925274], {
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
	 
	 var customPopup ="<div class='container'><center><p><h2>${rie.getNombre()} </h2><hr></p><p><img src='${rie.getImagen()}' alt='maptime logo gif' width='350px'/></p><hr><p><b><u>Descripcion:</u> </b>${rie.getDescripcion()}</p><hr><p><b><u>Estado:</u> </b>${rie.getEstado()}</p></center></div>" ;
	circle.bindPopup(customPopup,customOptions);
	
	var popup = L.popup();

	</c:forEach>
	
	<c:forEach items="${accs}" var="acc">
	
	var lat = ${acc.getLatitud()}
	var lon = ${acc.getLongitud()}
	var tamaño = ${acc.getTam()}
	var color = '${acc.getColor()}'
	//var circle = L.circle([-32.955096650, -60.625925274], {
	

	var indicador = new L.Icon({
	 	 iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-'+color+'.png',
	 	 shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
	 	 iconSize: [25, 41],
	  	 iconAnchor: [12, 41],
		 popupAnchor: [1, -34],
	 	 shadowSize: [41, 41]
		});
		

	var circle = L.marker([lat,lon], {
	    icon:indicador
	}).addTo(mymap);
	
	
	
	 var customOptions =
     {
     'maxWidth': '500',
     'className' : 'custom'
     }
	 
	 var customPopup ="<div class='container'><center> <p><h2>${acc.getNomacc()} </h2></p><hr><p><b><u>Descripcion:</u> </b>${acc.getDescripcion()}</p><hr><p><u><b>Riesgo:</u> </b>${acc.getNomrie()}</p><hr><p> <a href='eliminaraccrie?id=${acc.getIdaccrie()}&idusu=${acc.getId_usualta()}''><button type='button' class='btn btn-danger'>Eliminar <i class='far fa-trash-alt'></i></button></a>  </p></center></div>" ;
	circle.bindPopup(customPopup,customOptions);
	
	var popup = L.popup();
	</c:forEach>
	
</script>

 <%@ include file="includes/footer.jsp" %>      

</body>

</html>
