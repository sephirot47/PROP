package Presentacio;

import javax.swing.JPanel;
import java.awt.Dimension;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class GestioUsuarisPanel extends JPanel
{
	private JTextField txtBuscar;
	public GestioUsuarisPanel()
	{
		super();
		setPreferredSize(new Dimension(800, 560));
		setLayout(null);
		
		JPanel panelUsers = new JPanel();
		panelUsers.setBounds(30, 29, 311, 497);
		add(panelUsers);
		panelUsers.setLayout(null);
		
		txtBuscar = new JTextField();
		txtBuscar.setBounds(12, 36, 216, 19);
		panelUsers.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JList list = new JList();
		list.setBorder(new LineBorder(new Color(0, 0, 0)));
		list.setBounds(12, 67, 278, 369);
		panelUsers.add(list);
		
		JButton btnNouUsuari = new JButton("Nou usuari");
		btnNouUsuari.setBounds(12, 448, 111, 25);
		panelUsers.add(btnNouUsuari);
		
		JButton btnImportarFitxer = new JButton("Importar fitxer");
		btnImportarFitxer.setBounds(135, 448, 161, 25);
		panelUsers.add(btnImportarFitxer);
		
		JLabel lblBuscar = new JLabel("Buscar usuari:");
		lblBuscar.setBounds(12, 12, 145, 20);
		panelUsers.add(lblBuscar);
		
		JPanel panelUserDetail = new JPanel();
		panelUserDetail.setBounds(408, 29, 364, 497);
		add(panelUserDetail);
		panelUserDetail.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom:");
		lblNom.setBounds(12, 33, 36, 20);
		panelUserDetail.add(lblNom);
		
		JLabel lblEdat = new JLabel("Edat:");
		lblEdat.setBounds(12, 61, 48, 20);
		panelUserDetail.add(lblEdat);
		
		JLabel lblReproduccions = new JLabel("Reproduccions:");
		lblReproduccions.setBounds(12, 93, 119, 20);
		panelUserDetail.add(lblReproduccions);
		
		JList list_1 = new JList();
		list_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		list_1.setBounds(12, 127, 340, 304);
		panelUserDetail.add(list_1);
		
		JLabel labelEdatValue = new JLabel("20");
		labelEdatValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelEdatValue.setBounds(72, 61, 48, 20);
		panelUserDetail.add(labelEdatValue);
		
		JLabel labelNomValue = new JLabel("Victor");
		labelNomValue.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelNomValue.setBounds(72, 33, 48, 20);
		panelUserDetail.add(labelNomValue);
		
		JButton buttonEditarUsuari = new JButton("Editar usuari");
		buttonEditarUsuari.setBounds(12, 443, 161, 25);
		panelUserDetail.add(buttonEditarUsuari);
		
		JButton btnEliminarUsuari = new JButton("Eliminar usuari");
		btnEliminarUsuari.setBounds(191, 443, 161, 25);
		panelUserDetail.add(btnEliminarUsuari);
		
		JLabel lblDetallsUsuari = new JLabel("Detalls usuari:");
		lblDetallsUsuari.setBounds(122, 0, 114, 20);
		panelUserDetail.add(lblDetallsUsuari);
	}
}
