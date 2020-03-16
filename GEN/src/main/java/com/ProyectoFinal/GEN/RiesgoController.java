package com.ProyectoFinal.GEN;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import datos.AccionDatos;
import datos.AccionRiesgoDatos;
import datos.ciudadDatos;
import datos.provinciaDatos;
import datos.riesgoDatos;
import datos.tipoRiesgoDatos;
import modelo.AccionRiesgo;
import modelo.Riesgo;
import modelo.TipoRiesgo;
import modelo.Usuario;


@Controller
public class RiesgoController {
	
	private static final Logger logger = LoggerFactory.getLogger(RiesgoController.class);	
	
	@RequestMapping(value = "/nuevoRiesgo", method = RequestMethod.GET)
	public String nuevoRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
	else {
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		Riesgo rie=new Riesgo();
		model.addAttribute("rie",rie);
		model.addAttribute("cius", ciudadDatos.mostrarTodos() );
		model.addAttribute("prvs", provinciaDatos.mostrarTodos() );
		model.addAttribute("trs",tipoRiesgoDatos.mostrarTodos());
		return "nuevoRiesgo";
	}
	}
	
	@RequestMapping(value = "/crearRiesgo", method = RequestMethod.POST)
	public String crearRiesgo(@ModelAttribute("rie") Riesgo rie,
            BindingResult result, HttpSession sesion, Model model, @RequestParam(required = false) String error) {
		String ir="crearRiesgo";
		if(sesion.getAttribute("usuario")==null) 
			{
				model.addAttribute("usu", new Usuario());	
				ir="login";
				return ir;
			}
		else {
			Usuario usuh=(Usuario)sesion.getAttribute("usuario");
			boolean tipousu=usuh.getHabilitado();
			if(tipousu) {
				error="Usuario Deshabilitado";
				model.addAttribute("usu", new Usuario());
				model.addAttribute("error", error);
				return "login";
			}
			Usuario usu=(Usuario)sesion.getAttribute("usuario");
			rie.setId_usuario(usu.getIdusuario());
			rie.setEstado("Iniciado");
			riesgoDatos.nuevoRiesgo(rie);
			model.addAttribute("ries",riesgoDatos.mostrarTodosRiesgos());
			model.addAttribute("accs",AccionRiesgoDatos.mostrarAccRie());
			ir="home";
		}
		return ir;
	}
	@RequestMapping(value = "/nuevoTipoRiesgo", method = RequestMethod.GET)
	public String nuevoRipoRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String error) {
		String ir="login";
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
	else {
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		TipoRiesgo tr=new TipoRiesgo();
		model.addAttribute("tr",tr);
		ir="nuevoTipoRiesgo";
		}
		return ir;
	}

	@RequestMapping(value = "/crearTipoRiesgo", method = RequestMethod.POST)
	public String crearTipoRiesgo(@ModelAttribute("tr") TipoRiesgo tr,
            BindingResult result, HttpSession sesion, Model model, @RequestParam(required = false) String error) {
		String ir="crearTipoRiesgo";
		if(sesion.getAttribute("usuario")==null) 
			{
				model.addAttribute("usu", new Usuario());	
				ir="login";
			}
		else {
			Usuario usuh=(Usuario)sesion.getAttribute("usuario");
			boolean tipousu=usuh.getHabilitado();
			if(tipousu) {
				error="Usuario Deshabilitado";
				model.addAttribute("usu", new Usuario());
				model.addAttribute("error", error);
				return "login";
			}
			Usuario usu=(Usuario)sesion.getAttribute("usuario");
			tr.setId_usu_atr(usu.getIdusuario());
			tipoRiesgoDatos.nuevoTR(tr);
			ir="mapaInteractivo";
		}
		return ir;
	}
	
	@RequestMapping(value = "/gestionarRiesgos", method = RequestMethod.GET)
	public String gestionarRiesgos(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		Usuario usu=(Usuario)sesion.getAttribute("usuario");
		int idusu=usu.getIdusuario();
		model.addAttribute("msj", msj);
		model.addAttribute("ries",riesgoDatos.mostrarRiesgos(idusu));
		return "gestionarRiesgos";
	}
	
	@RequestMapping(value = "/eliminarRiesgo", method = RequestMethod.GET)
	public String eliminarRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int id, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		msj="Riesgo Eliminado";
		model.addAttribute("msj", msj);
		model.addAttribute("ries",riesgoDatos.eliminarRiesgo(id));
		return "mapaInteractivo";
	}
	
	@RequestMapping(value = "/modificarRiesgo", method = RequestMethod.GET)
	public String modificarRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int id, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		Riesgo rie = new Riesgo();
		rie = riesgoDatos.buscarRie(id);
		model.addAttribute("rie",rie);
		model.addAttribute("cius", ciudadDatos.mostrarTodos() );
		model.addAttribute("prvs", provinciaDatos.mostrarTodos() );
		model.addAttribute("trs",tipoRiesgoDatos.mostrarTodos());
		return "modificarRiesgo";
	}
	
	@RequestMapping(value = "/modiRie", method = RequestMethod.POST)
	public String modiRie(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@ModelAttribute("rie") Riesgo rie, @RequestParam(required = false) String error) {;
	if(sesion.getAttribute("usuario")==null) 
	{
		model.addAttribute("usu", new Usuario());
		return "login";
	}	
	Usuario usuh=(Usuario)sesion.getAttribute("usuario");
	boolean tipousu=usuh.getHabilitado();
	if(tipousu) {
		error="Usuario Deshabilitado";
		model.addAttribute("usu", new Usuario());
		model.addAttribute("error", error);
		return "login";
	}
	riesgoDatos.modificarRie(rie);
	model.addAttribute("ries",riesgoDatos.mostrarTodosRiesgos());
	model.addAttribute("accs",AccionRiesgoDatos.mostrarAccRie());
	return "home";	}
	
	@RequestMapping(value = "/verRiesgo", method = RequestMethod.GET)
	public String verRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int id, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		model.addAttribute("rie",riesgoDatos.buscarRie(id));
		return "visorRiesgo";
	}
	
	@RequestMapping(value = "/gestionarTipoRiesgos", method = RequestMethod.GET)
	public String gestionarTipoRiesgos(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		if(usuh.getTipoUsuario()!=0)
		 {
			error="Usted no es usuario administrador";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		model.addAttribute("msj", msj);
		model.addAttribute("trs",tipoRiesgoDatos.mostrarTipoRiesgos());
		return "gestionarTipoRiesgos";
	}
	
	@RequestMapping(value = "/eliminarTipoRiesgo", method = RequestMethod.GET)
	public String eliminarTipoRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int id, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		if(usuh.getTipoUsuario()!=0)
		 {
			error="Usted no es usuario administrador";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		msj="Tipo Riesgo Eliminado";
		model.addAttribute("msj", msj);
		model.addAttribute("ries",tipoRiesgoDatos.eliminartipoRiesgo(id));
		return "gestionarTipoRiesgos";
	}
	
	@RequestMapping(value = "/modificarTipoRiesgo", method = RequestMethod.GET)
	public String modificarTipoRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int id, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		if(usuh.getTipoUsuario()!=0)
		 {
			error="Usted no es usuario administrador";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		TipoRiesgo tr = new TipoRiesgo();
		tr = tipoRiesgoDatos.buscartipoRie(id);
		model.addAttribute("tr",tr);
		return "modificarTipoRiesgo";
	}
	
	@RequestMapping(value = "/modiTRie", method = RequestMethod.POST)
	public String modiTRie(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@ModelAttribute("tr") TipoRiesgo tr, @RequestParam(required = false) String error) {;
	if(sesion.getAttribute("usuario")==null) 
	{
		model.addAttribute("usu", new Usuario());
		return "login";
	}	
	Usuario usuh=(Usuario)sesion.getAttribute("usuario");
	boolean tipousu=usuh.getHabilitado();
	if(tipousu) {
		error="Usuario Deshabilitado";
		model.addAttribute("usu", new Usuario());
		model.addAttribute("error", error);
		return "login";
	}
	tipoRiesgoDatos.modificarTipoRie(tr);
		return "mapaInteractivo";
	}
	
	@RequestMapping(value = "/riesgoAcciones", method = RequestMethod.GET)
	public String riesgoAcciones(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int idRie, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		AccionRiesgo accrie = new AccionRiesgo();
		Riesgo riesgo = new Riesgo();
		riesgo = riesgoDatos.buscarRie(idRie);
		riesgo.setIdriesgo(idRie);
		accrie.setIdriesgo(idRie);
		model.addAttribute("rie",riesgo);
		model.addAttribute("accrie",accrie);
		model.addAttribute("accs", AccionDatos.mostrarTodAcciones());
		return "riesgoAcciones";
	}
	
	@RequestMapping(value = "/rieAcc", method = RequestMethod.POST)
	public String rieAcc(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj, @RequestParam(required = false) String error,@ModelAttribute("accrie") AccionRiesgo accrie) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		accrie.setId_usualta(usuh.getIdusuario());
		AccionRiesgoDatos.nuevoAR(accrie);
		model.addAttribute("ries",riesgoDatos.mostrarTodosRiesgos());
		model.addAttribute("accs",AccionRiesgoDatos.mostrarAccRie());
		return "home";
	}

	@RequestMapping(value = "/eliminaraccrie", method = RequestMethod.GET)
	public String eliminaraccrie(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String err,@RequestParam int id,@RequestParam int idusu, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Usuario Deshabilitado";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		model.addAttribute("ries",AccionRiesgoDatos.eliminarAccRie(id));
		model.addAttribute("ries",riesgoDatos.mostrarTodosRiesgos());
		model.addAttribute("accs",AccionRiesgoDatos.mostrarAccRie());
		return "home";	}
}
