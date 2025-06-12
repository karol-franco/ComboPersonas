package principal;

import ventanas.VentanaCombo;

public class Relaciones {

	public void iniciar() {
		// Crear las instancias
		VentanaCombo miVentanaCombo = new VentanaCombo();
		Coordinador miCoordinador = new Coordinador();

		miVentanaCombo.setCoordinador(miCoordinador);
		miCoordinador.setVentanaCombo(miVentanaCombo);
		miVentanaCombo.cargarRegistros();
		miVentanaCombo.setVisible(true);
	}
}
