package ventanas;

import javax.swing.*;
import principal.Coordinador;
import vo.PersonaVo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

public class VentanaCombo extends JFrame implements ActionListener {

	private JTextField txtNombre;
	private JTextField txtDireccion;
	private JTextField txtDoc;
	private JTextField txtTelefono;
	private JComboBox<PersonaVo> comboPersonas;
	private JButton btnRegistrar;
	private Coordinador miCoordinador;
	JButton btnEliminar;
	private JLabel lblSeleccion;
	private JTable tablaNombres;
	private JScrollPane miBarra1;
	private JButton btnActualizar;

	public VentanaCombo() {
		getContentPane().setLayout(null);
		setSize(544, 455);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JLabel lblTitulo = new JLabel("Gestionar personas ");
		lblTitulo.setFont(new Font("Engravers MT", Font.PLAIN, 11));
		lblTitulo.setBounds(145, 11, 199, 27);
		getContentPane().add(lblTitulo);

		iniciarComponentes();
	}

	private void iniciarComponentes() {
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 60, 60, 14);
		getContentPane().add(lblNombre);

		JLabel lblDireccion = new JLabel("Dirección:");
		lblDireccion.setBounds(10, 100, 60, 14);
		getContentPane().add(lblDireccion);

		txtNombre = new JTextField();
		txtNombre.setBounds(78, 57, 199, 20);
		getContentPane().add(txtNombre);

		txtDireccion = new JTextField();
		txtDireccion.setBounds(78, 97, 199, 20);
		getContentPane().add(txtDireccion);

		JLabel lblDocumento = new JLabel("Documento:");
		lblDocumento.setBounds(287, 60, 76, 14);
		getContentPane().add(lblDocumento);

		JLabel lblTelefono = new JLabel("Teléfono:");
		lblTelefono.setBounds(287, 100, 57, 14);
		getContentPane().add(lblTelefono);

		txtDoc = new JTextField();
		txtDoc.setBounds(356, 57, 123, 20);
		getContentPane().add(txtDoc);

		txtTelefono = new JTextField();
		txtTelefono.setBounds(354, 97, 123, 20);
		getContentPane().add(txtTelefono);

		btnRegistrar = new JButton("Registrar");
		btnRegistrar.setBounds(356, 139, 123, 35);
		btnRegistrar.addActionListener(this);
		getContentPane().add(btnRegistrar);

		lblSeleccion = new JLabel("");
		lblSeleccion.setBounds(370, 216, 148, 25);
		getContentPane().add(lblSeleccion);

		tablaNombres = new JTable();
		miBarra1 = new JScrollPane();
		miBarra1.setBounds(10, 285, 508, 108);
		getContentPane().add(miBarra1);

		comboPersonas = new JComboBox<>();
		comboPersonas.setModel(new DefaultComboBoxModel(new String[] { "Seleccione" }));
		comboPersonas.setSelectedIndex(0);
		comboPersonas.setBounds(139, 221, 224, 20);
		comboPersonas.addActionListener(this);
		getContentPane().add(comboPersonas);

		JLabel lblComboPersonas = new JLabel("Combo personas");
		lblComboPersonas.setBounds(22, 224, 107, 14);
		getContentPane().add(lblComboPersonas);

		JLabel lblListaPersonas = new JLabel("Lista personas");
		lblListaPersonas.setBounds(10, 260, 134, 14);
		getContentPane().add(lblListaPersonas);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(189, 139, 123, 35);
		getContentPane().add(btnEliminar);
		
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(21, 139, 123, 35);
		getContentPane().add(btnActualizar);
		btnActualizar.addActionListener(this);
		

	}

	public void setCoordinador(Coordinador miCoordinador) {
		this.miCoordinador = miCoordinador;
	}

	public void cargarRegistros(PersonaVo persona) {
		txtDoc.setText(persona.getDocumento());
		txtNombre.setText(persona.getNombre());
		txtDireccion.setText(persona.getDireccion());
		txtTelefono.setText(persona.getTelefono());	
	}

	private void llenarTabla(ArrayList<PersonaVo> listaPersonas) {
		String titulos[] = { "Documento", "Nombre", "Dirección", "Teléfono" };
		String informacion[][] = new String[listaPersonas.size()][4];

		for (int i = 0; i < listaPersonas.size(); i++) {
			informacion[i][0] = listaPersonas.get(i).getDocumento();
			informacion[i][1] = listaPersonas.get(i).getNombre();
			informacion[i][2] = listaPersonas.get(i).getDireccion();
			informacion[i][3] = listaPersonas.get(i).getTelefono();
		}

		tablaNombres = new JTable(informacion, titulos);
		int[] anchos = { 100, 150, 150, 100 };
		for (int i = 0; i < tablaNombres.getColumnCount(); i++) {
			tablaNombres.getColumnModel().getColumn(i).setPreferredWidth(anchos[i]);
		}
		miBarra1.setViewportView(tablaNombres);
	}

	private void llenarCombo(ArrayList<PersonaVo> listaPersonas) {
		comboPersonas.removeAllItems();
		comboPersonas.addItem(null);
		for (PersonaVo persona : listaPersonas) {
	        comboPersonas.addItem(persona);
	    }
	}

	private void registrarPersonas() {
		PersonaVo persona = new PersonaVo();
		persona.setNombre(txtNombre.getText());
		persona.setDocumento(txtDoc.getText());
		persona.setTelefono(txtTelefono.getText());
		persona.setDireccion(txtDireccion.getText());

		miCoordinador.registrarUsuario(persona);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			registrarPersonas();
			cargarRegistros();
		}
		if (e.getSource() == comboPersonas) {
		    PersonaVo seleccionada = (PersonaVo) comboPersonas.getSelectedItem();

		    if (seleccionada != null) {
		        txtDoc.setText(seleccionada.getDocumento());
		        txtNombre.setText(seleccionada.getNombre());
		        txtDireccion.setText(seleccionada.getDireccion());
		        txtTelefono.setText(seleccionada.getTelefono());
		        lblSeleccion.setText(seleccionada.toString());
		    } else {
		        txtDoc.setText("");
		        txtNombre.setText("");
		        txtDireccion.setText("");
		        txtTelefono.setText("");
		        lblSeleccion.setText("");
		    }
		}

		if(e.getSource()==btnEliminar) {
			//eliminarPersonas();
			cargarRegistros();
		}
		if (e.getSource()==btnActualizar) {
			ActualizarRegistro();
			cargarRegistros();
			
			
		}
	}

	private void ActualizarRegistro() {

	    if (comboPersonas.getSelectedIndex() <= 0 || comboPersonas.getSelectedItem() == null) {
	        JOptionPane.showMessageDialog(this, "Debes seleccionar una persona del combo para actualizar.");
	        return;
	    }

	    try {
	        PersonaVo personaSeleccionada = (PersonaVo) comboPersonas.getSelectedItem();

	        PersonaVo personaActualizada = new PersonaVo();
	        personaActualizada.setDocumento(personaSeleccionada.getDocumento()); 
	        personaActualizada.setNombre(txtNombre.getText());
	        personaActualizada.setDireccion(txtDireccion.getText());
	        personaActualizada.setTelefono(txtTelefono.getText());

	        boolean actualizado = miCoordinador.actualizarPersona(personaActualizada);

	        if (actualizado) {
	            JOptionPane.showMessageDialog(this, "Persona actualizada correctamente.\nVuelve a seleccionar otra si deseas.");
	            cargarRegistros(); 
	            comboPersonas.setSelectedIndex(0);
	            txtDoc.setText("");
	            txtNombre.setText("");
	            txtDireccion.setText("");
	            txtTelefono.setText("");
	        } else {
	            JOptionPane.showMessageDialog(this, "No se pudo actualizar la persona.");
	        }

	    } catch (Exception e) {
	        JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
	    }
	}



	


	
}
