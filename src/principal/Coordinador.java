package principal;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.PersonaDao;
import ventanas.VentanaCombo;
import vo.PersonaVo;

public class Coordinador {
	private PersonaDao miPersonaDao;
	private PersonaVo miPersonaVo;
	private VentanaCombo miVentanaCombo;
	
	public Coordinador() {
		miPersonaDao = new PersonaDao();
	}
	
	public void setVentanaCombo(VentanaCombo miVentanaCombo) {		
		this.miVentanaCombo = miVentanaCombo;
	}
	
	public void registrarUsuario(PersonaVo miPersonaVo)  {
		miPersonaDao.registrarUsuario(miPersonaVo);
	}
	
	public void cargarRegistros() {
		
	}

	public ArrayList<PersonaVo> consultarListaPersonas() {
	    try {
	        return miPersonaDao.consultarListaPersonas();
	    } catch (Exception e) {
	        System.err.println("Error consultando personas: " + e.getMessage());
	        return new ArrayList<>();
	    }
	}

	public boolean eliminarPersona(PersonaVo personaObtenida) {
		return miPersonaDao.eliminarUsuario(personaObtenida);
	}

}
