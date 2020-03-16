              
<!--Navbar-->
<nav class="navbar sticky-top navbar-expand-lg navbar-dark indigo">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <a class="navbar-brand" href="/GEN">
        <img src="resources/img/GEN/huracanes-tifones-019.png" height="30" class="d-inline-block align-top" alt="">
        GEN
    </a>

    <!-- Collapse button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
        aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>

    <!-- Collapsible content -->
    <div class="collapse navbar-collapse" id="navbarSupportedContent">

        <!-- Links -->
        <ul class="navbar-nav mr-auto">
              <li class="nav-item dropdown">
			                    <a  class="nav-link" href="/GEN">Mapa Riesgos</a>
			            </li>              
             <!-- Dropdown -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Riesgos</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="nuevoRiesgo">Nuevo Riesgo</a>
                    <a class="dropdown-item" href="gestionarRiesgos">Gestionar Riesgo</a>
                    <a class="dropdown-item" href="nuevoTipoRiesgo">Nuevo Tipo de Riesgo</a>
                    <c:if test="${usuario.getTipoUsuario() == 0}">
                       <a class="dropdown-item" href="gestionarTipoRiesgos">Gestionar Tipo de Riesgos</a>
                    </c:if>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Acciones</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="nuevaAccion">Nueva Accion</a>
                    <a class="dropdown-item" href="gestionarAcciones">Gestionar Acciones</a>
                    <a class="dropdown-item" href="nuevoTipoAccion">Nuevo Tipo de Accion</a>
                     <c:if test="${usuario.getTipoUsuario() == 0}">
                       <a class="dropdown-item" href="gestionarTipoAccion">Gestionar Tipo de Acciones</a>
                    </c:if>
                </div>
            </li>
            <!-- Dropdown -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Mapas</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="mapaInteractivo">Mapas</a>
                    <a class="dropdown-item" href="aviacion">Mapas para aviacion</a>
                </div>
            </li>
           
           <c:if test="${usuario.getTipoUsuario() == 0}">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="habilitarUsuarios">Habilitar Usuarios</a>
                </div>
            </li>
            </c:if>
        
          </ul>
        <!-- Links -->

        <ul class="navbar-nav ml-auto">
                         <li class="nav-item dropdown">
			                    <a  class="nav-link" href="ayuda">Ayuda</a>
			            </li>        
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user"></i>
                             ${usuario.getUsuario()}</a>
                            <div class="dropdown-menu dropdown-menu-right dropdown-cyan" aria-labelledby="navbarDropdownMenuLink-4">
                             	<% if (session.getAttribute("usuario") == null){%>
                             	<a class="dropdown-item" href="login">Login</a>
                             	<%}%>
                             	<% if (session.getAttribute("usuario") == null){%>
                             	<a class="dropdown-item" href="nuevoUsuario">Crear cuenta</a>
                             	<%}%>
                             	<% if (session.getAttribute("usuario") != null){%>
                                <a class="dropdown-item" href="modusu">Mi cuenta</a>
                                <%}%>
                                <% if (session.getAttribute("usuario") != null){%>
                                <a class="dropdown-item" href="finsesion">cerrar sesion</a>
                                <%}%>
                            </div>
                        </li>
        </ul>
    </div>
    <!-- Collapsible content -->

<!-- Button trigger modal -->

</nav>
<!--/.Navbar-->
                