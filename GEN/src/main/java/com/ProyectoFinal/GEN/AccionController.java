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
import datos.ciudadDatos;
import datos.provinciaDatos;
import datos.riesgoDatos;
import datos.tipoAccionDatos;
import datos.tipoRiesgoDatos;
import modelo.Accion;
import modelo.Riesgo;
import modelo.TipoAccion;
import modelo.TipoRiesgo;
import modelo.Usuario;



@Controller
public class AccionController {
	
	private static final Logger logger = LoggerFactory.getLogger(AccionController.class);	
	
	@RequestMapping(value ="/nuevaAccion")
	public String nuevaAccion(Model model, HttpSession sesion, @RequestParam(required = false) String error) {
	if(sesion.getAttribute("usuario")==null) 
		{
			error="Per creare un'azione è necessario aver effettuato il login";
			model.addAttribute("error", error);
			model.addAttribute("usu", new Usuario());
			return "login";
		}
	else {
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Utente Disabile";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		Accion acc = new Accion();
		model.addAttribute("accion", acc);
		model.addAttribute("accs", tipoAccionDatos.mostrarTodos());
		return "nuevaAccion";
		}
	}

	
	@RequestMapping(value="/guardarAccion")
	public String formularioPersona(@ModelAttribute("acc") Accion accion,
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
				error="Utente Disabile";
				model.addAttribute("usu", new Usuario());
				model.addAttribute("error", error);
				return "login";
			}
			Usuario usu=(Usuario)sesion.getAttribute("usuario");
			accion.setId_usualta_acc(usu.getIdusuario());
			AccionDatos.nuevoA(accion);
			ir="mapaInteractivo";
		}
		return ir;
	}
	
	@RequestMapping(value = "/nuevoTipoAccion", method = RequestMethod.GET)
	public String nuevoTipoAccion(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String error) {
		String ir="login";
		if(sesion.getAttribute("usuario")==null) 
		{
			error="È necessario effettuare il login per creare una categoria di azione";
			model.addAttribute("error", error);
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		else {
			Usuario usuh=(Usuario)sesion.getAttribute("usuario");
			boolean tipousu=usuh.getHabilitado();
			if(tipousu) {
				error="Utente Disabile";
				model.addAttribute("usu", new Usuario());
				model.addAttribute("error", error);
				return "login";
			}
		model.addAttribute("error", error);
		TipoAccion ta=new TipoAccion();
		model.addAttribute("ta",ta);
		ir="nuevoTipoAccion";
		}
		return ir;
	}

	@RequestMapping(value = "/crearTipoAccion", method = RequestMethod.POST)
	public String crearTipoAccion(@ModelAttribute("ta") TipoAccion ta,
            BindingResult result, HttpSession sesion, Model model, @RequestParam(required = false) String error) {
		String ir="crearTipoAccion";
		if(sesion.getAttribute("usuario")==null) 
			{
				ir="login";
			}
		else {
			Usuario usuh=(Usuario)sesion.getAttribute("usuario");
			boolean tipousu=usuh.getHabilitado();
			if(tipousu) {
				error="Utente Disabile";
				model.addAttribute("usu", new Usuario());
				model.addAttribute("error", error);
				return "login";
			}
			Usuario usu=(Usuario)sesion.getAttribute("usuario");
			ta.setUsu_alta_ta(usu.getIdusuario());
			tipoAccionDatos.nuevoTA(ta);
			ir="mapaInteractivo";
		}
		return ir;
	}
	
	@RequestMapping(value = "/gestionarAcciones", method = RequestMethod.GET)
	public String gestionarRiesgos(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Utente Disabile";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		Usuario usu=(Usuario)sesion.getAttribute("usuario");
		int idusu=usu.getIdusuario();
		model.addAttribute("msj", msj);
		model.addAttribute("accs",AccionDatos.mostrarAcciones(idusu));
		return "gestionarAccion";
	}
	
	@RequestMapping(value = "/eliminarAccion", method = RequestMethod.GET)
	public String eliminarRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int id, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Utente Disabile";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		msj="Azione Eliminata";
		model.addAttribute("msj", msj);
		model.addAttribute("accs",AccionDatos.eliminarAccion(id));
		return "mapaInteractivo";
	}
	
	@RequestMapping(value = "/modificarAccion", method = RequestMethod.GET)
	public String modificarRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int id, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Utente Disabile";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		Accion acc = new Accion();
		acc = AccionDatos.buscarAcc(id);
		model.addAttribute("acc",acc);
		model.addAttribute("ta",tipoAccionDatos.mostrarTodos());
		return "modificarAccion";
	}
	
	@RequestMapping(value = "/modiAcc", method = RequestMethod.POST)
	public String modiAcc(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@ModelAttribute("acc") Accion acc, @RequestParam(required = false) String error) {;
	if(sesion.getAttribute("usuario")==null) 
	{
		model.addAttribute("usu", new Usuario());
		return "login";
	}	
	Usuario usuh=(Usuario)sesion.getAttribute("usuario");
	boolean tipousu=usuh.getHabilitado();
	if(tipousu) {
		error="Utente Disabile";
		model.addAttribute("usu", new Usuario());
		model.addAttribute("error", error);
		return "login";
	}
	AccionDatos.modificarAcc(acc);
		return "mapaInteractivo";
	}
	
	@RequestMapping(value = "/gestionarTipoAccion", method = RequestMethod.GET)
	public String gestionarTipoRiesgos(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Utente Disabile";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		if(usuh.getTipoUsuario()!=0)
		 {
			error="Non sei un utente amministratore";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		model.addAttribute("msj", msj);
		model.addAttribute("tas",tipoAccionDatos.mostrarTipoAccion());
		return "gestionarTipoAccion";
	}
	
	@RequestMapping(value = "/eliminarTipoAccion", method = RequestMethod.GET)
	public String eliminarTipoRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int id, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Utente Disabile";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		if(usuh.getTipoUsuario()!=0)
		 {
			error="Non sei un utente amministratore";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		msj="Categoria di azione eliminata";
		model.addAttribute("msj", msj);
		model.addAttribute("ries",tipoAccionDatos.eliminartipoAccion(id));
		return "gestionarTipoAccion";
	}
	
	@RequestMapping(value = "/modificarTipoAccion", method = RequestMethod.GET)
	public String modificarTipoRiesgo(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@RequestParam int id, @RequestParam(required = false) String error) {
		if(sesion.getAttribute("usuario")==null) 
		{
			model.addAttribute("usu", new Usuario());
			return "login";
		}
		Usuario usuh=(Usuario)sesion.getAttribute("usuario");
		boolean tipousu=usuh.getHabilitado();
		if(tipousu) {
			error="Utente Disabile";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		if(usuh.getTipoUsuario()!=0)
		 {
			error="Non sei un utente amministratore";
			model.addAttribute("usu", new Usuario());
			model.addAttribute("error", error);
			return "login";
		}
		TipoAccion ta = new TipoAccion();
		ta = tipoAccionDatos.buscartipoAcc(id);
		model.addAttribute("ta",ta);
		return "modificarTipoAccion";
	}
	
	@RequestMapping(value = "/modiTAcc", method = RequestMethod.POST)
	public String modiTRie(Locale locale, Model model, HttpSession sesion, @RequestParam(required = false) String msj,@ModelAttribute("ta") TipoAccion ta, @RequestParam(required = false) String error) {;
	if(sesion.getAttribute("usuario")==null) 
	{
		model.addAttribute("usu", new Usuario());
		return "login";
	}	
	Usuario usuh=(Usuario)sesion.getAttribute("usuario");
	boolean tipousu=usuh.getHabilitado();
	if(tipousu) {
		error="Utente Disabile";
		model.addAttribute("usu", new Usuario());
		model.addAttribute("error", error);
		return "login";
	}
	tipoAccionDatos.modificarTipoAcc(ta);
		return "mapaInteractivo";
	}
	
}
