              
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
			                    <a  class="nav-link" href="/GEN">Mappa dei Rischi</a>
			            </li>              
             <!-- Dropdown -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Rischi</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="nuevoRiesgo">Nuovo Rischio</a>
                    <a class="dropdown-item" href="gestionarRiesgos">Gestire i Rischi</a>
                    <a class="dropdown-item" href="nuevoTipoRiesgo">Nueva Categoria di Rischi</a>
                    <c:if test="${usuario.getTipoUsuario() == 0}">
                       <a class="dropdown-item" href="gestionarTipoRiesgos">Gestire i Categorie di Rischi</a>
                    </c:if>
                </div>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Azioni</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="nuevaAccion">Nuova Azione</a>
                    <a class="dropdown-item" href="gestionarAcciones">Gestire le Azioni</a>
                    <a class="dropdown-item" href="nuevoTipoAccion">Nuova Categoria di Azioni</a>
                     <c:if test="${usuario.getTipoUsuario() == 0}">
                       <a class="dropdown-item" href="gestionarTipoAccion">Gestire Categoria di Azioni</a>
                    </c:if>
                </div>
            </li>
            
            <!-- Dropdown -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Covid 19</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="covid19JN">Covid 19 Johns Hopkins</a>
                    <a class="dropdown-item" href="covid19TimeLine">Covid 19 TimeLine</a>
                    <a class="dropdown-item" href="covid19Mundo3D">Covid 19 Mondo 3D</a>
                </div>
            </li>
            
            <!-- Dropdown -->
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Mappe</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="mapaInteractivo">Mappe</a>
                    <a class="dropdown-item" href="aviacion">Mappe per l'aviazione</a>
                </div>
            </li>
           
           <c:if test="${usuario.getTipoUsuario() == 0}">
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin</a>
                <div class="dropdown-menu dropdown-primary" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="habilitarUsuarios">Abilita gli utenti</a>
                </div>
            </li>
            </c:if>
        
          </ul>
        <!-- Links -->

        <ul class="navbar-nav ml-auto">
                         <li class="nav-item dropdown">
			                    <a  class="nav-link" href="ayuda">Aiuto</a>
			            </li>        
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" id="navbarDropdownMenuLink-4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><i class="fa fa-user"></i>
                             ${usuario.getUsuario()}</a>
                            <div class="dropdown-menu dropdown-menu-right dropdown-cyan" aria-labelledby="navbarDropdownMenuLink-4">
                             	<% if (session.getAttribute("usuario") == null){%>
                             	<a class="dropdown-item" href="login">Login</a>
                             	<%}%>
                             	<% if (session.getAttribute("usuario") == null){%>
                             	<a class="dropdown-item" href="nuevoUsuario">Crea un nuovo account</a>
                             	<%}%>
                             	<% if (session.getAttribute("usuario") != null){%>
                                <a class="dropdown-item" href="modusu">Il mio Account</a>
                                <%}%>
                                <% if (session.getAttribute("usuario") != null){%>
                                <a class="dropdown-item" href="finsesion">Logout</a>
                                <%}%>
                            </div>
                        </li>
        </ul>
    </div>
    <!-- Collapsible content -->

<!-- Button trigger modal -->

</nav>
<!--/.Navbar-->
                