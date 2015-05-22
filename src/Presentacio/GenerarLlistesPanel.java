package Presentacio;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Cursor;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.plaf.SliderUI;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GenerarLlistesPanel extends JPanel {

	JLabel labelSliderDuracio = null, 
			labelSliderAny = null, 
			labelSliderEstil = null,
			labelSliderPublic = null,
			labelSliderProximitat = null,
			labelSliderAutor = null;

	private final ButtonGroup algorismeGroup = new ButtonGroup();
	private JRadioButton Girvan, louvain, clique;
	private JSlider sliderDuracio, sliderAny,sliderEstil, sliderPublic, sliderProximitat, sliderAutor;
	
	public GenerarLlistesPanel()
	{
		super();
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setPreferredSize(new Dimension(800, 600));
		setLayout(null);
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(0, 0, 480, 510);
		panelLeft.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panelLeft);
		panelLeft.setLayout(null);
		
		JLabel lblCriteris = new JLabel("Criteris:");
		lblCriteris.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCriteris.setBounds(35, 11, 410, 14);
		panelLeft.add(lblCriteris);
		
		JPanel panelCriteris = new JPanel();
		panelCriteris.setBounds(35, 35, 435, 434);
		panelCriteris.setPreferredSize(new Dimension(410, 470));
		panelCriteris.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelLeft.add(panelCriteris);
		panelCriteris.setLayout(null);
		
		JPanel criterisSliderContainer = new JPanel();
		criterisSliderContainer.setBounds(15, 10, 408, 420);
		panelCriteris.add(criterisSliderContainer);
		criterisSliderContainer.setLayout(null);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(0, 70, 400, 4);
		criterisSliderContainer.add(separator_2);
		
		JSeparator separator_7 = new JSeparator();
		separator_7.setBounds(0, 135, 400, 4);
		criterisSliderContainer.add(separator_7);
		
		JSeparator separator_6 = new JSeparator();
		separator_6.setBounds(0, 205, 400, 4);
		criterisSliderContainer.add(separator_6);
		
		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(0, 275, 400, 4);
		criterisSliderContainer.add(separator_5);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(0, 345, 400, 4);
		criterisSliderContainer.add(separator_3);
		
		JPanel panelDuracio = new JPanel();
		panelDuracio.setBounds(0, 0, 398, 65);
		criterisSliderContainer.add(panelDuracio);
		panelDuracio.setLayout(null);
		
		JLabel lblDuracio = new JLabel("Duracio:");
		lblDuracio.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDuracio.setBounds(5, 5, 70, 15);
		panelDuracio.add(lblDuracio);
		
		JLabel lblCanonsAmbRelacio = new JLabel("Cancons amb duracio semblant");
		lblCanonsAmbRelacio.setBounds(5, 22, 374, 15);
		panelDuracio.add(lblCanonsAmbRelacio);
		lblCanonsAmbRelacio.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		 sliderDuracio = new JSlider();
		sliderDuracio.setBounds(0, 40, 332, 16);
		panelDuracio.add(sliderDuracio);
		sliderDuracio.setMajorTickSpacing(1);
		sliderDuracio.setMinorTickSpacing(1);
		sliderDuracio.setMaximum(10);
		sliderDuracio.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		labelSliderDuracio = new JLabel("10");
		labelSliderDuracio.setBounds(350, 40, 31, 15);
		panelDuracio.add(labelSliderDuracio);
		labelSliderDuracio.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JPanel panelAny = new JPanel();
		panelAny.setBounds(0, 70, 398, 65);
		panelAny.setLayout(null);
		criterisSliderContainer.add(panelAny);
		
		JLabel lblAny = new JLabel("Any:");
		lblAny.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAny.setBounds(5, 5, 70, 15);
		panelAny.add(lblAny);
		
		JLabel lblCanonsAmbAnys = new JLabel("Cancons amb anys de creacio semblant");
		lblCanonsAmbAnys.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbAnys.setBounds(5, 22, 374, 15);
		panelAny.add(lblCanonsAmbAnys);
		
		 sliderAny = new JSlider();
		sliderAny.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sliderAny.setMinorTickSpacing(1);
		sliderAny.setMaximum(10);
		sliderAny.setMajorTickSpacing(1);
		sliderAny.setBounds(0, 40, 332, 16);
		panelAny.add(sliderAny);
		
		labelSliderAny = new JLabel("10");
		labelSliderAny.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderAny.setBounds(350, 40, 31, 15);
		panelAny.add(labelSliderAny);
		
		JPanel panelEstil = new JPanel();
		panelEstil.setBounds(0, 140, 398, 65);
		panelEstil.setLayout(null);
		criterisSliderContainer.add(panelEstil);
		
		JLabel lblEstil = new JLabel("Estil:");
		lblEstil.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEstil.setBounds(5, 5, 70, 15);
		panelEstil.add(lblEstil);
		
		JLabel lblCanonsAmbEstil = new JLabel("Cancons amb estil semblant");
		lblCanonsAmbEstil.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbEstil.setBounds(5, 22, 374, 15);
		panelEstil.add(lblCanonsAmbEstil);
		
		 sliderEstil = new JSlider();
		sliderEstil.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sliderEstil.setMinorTickSpacing(1);
		sliderEstil.setMaximum(10);
		sliderEstil.setMajorTickSpacing(1);
		sliderEstil.setBounds(0, 40, 332, 16);
		panelEstil.add(sliderEstil);
		
		labelSliderEstil = new JLabel("10");
		labelSliderEstil.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderEstil.setBounds(350, 40, 31, 15);
		panelEstil.add(labelSliderEstil);
		
		JPanel panelPublic = new JPanel();
		panelPublic.setBounds(0, 210, 398, 65);
		panelPublic.setLayout(null);
		criterisSliderContainer.add(panelPublic);
		
		JLabel Public = new JLabel("Public:");
		Public.setFont(new Font("Tahoma", Font.BOLD, 11));
		Public.setBounds(5, 5, 70, 15);
		panelPublic.add(Public);
		
		JLabel lblCanonsAmbPublic = new JLabel("Cancons amb public d'edats semblants");
		lblCanonsAmbPublic.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbPublic.setBounds(5, 22, 374, 15);
		panelPublic.add(lblCanonsAmbPublic);
		
		 sliderPublic = new JSlider();
		sliderPublic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sliderPublic.setMinorTickSpacing(1);
		sliderPublic.setMaximum(10);
		sliderPublic.setMajorTickSpacing(1);
		sliderPublic.setBounds(0, 40, 332, 16);
		panelPublic.add(sliderPublic);
		
		labelSliderPublic = new JLabel("10");
		labelSliderPublic.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderPublic.setBounds(350, 40, 31, 15);
		panelPublic.add(labelSliderPublic);
		
		JPanel panelProximitat = new JPanel();
		panelProximitat.setBounds(0, 280, 398, 65);
		panelProximitat.setLayout(null);
		criterisSliderContainer.add(panelProximitat);
		
		JLabel lblProximitat = new JLabel("Proximitat:");
		lblProximitat.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblProximitat.setBounds(5, 5, 113, 15);
		panelProximitat.add(lblProximitat);
		
		JLabel lblCanonsEscoltadesNormalment = new JLabel("Cancons escoltades juntes en periodes curts de temps");
		lblCanonsEscoltadesNormalment.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsEscoltadesNormalment.setBounds(5, 22, 374, 15);
		panelProximitat.add(lblCanonsEscoltadesNormalment);
		
		 sliderProximitat = new JSlider();
		sliderProximitat.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sliderProximitat.setMinorTickSpacing(1);
		sliderProximitat.setMaximum(10);
		sliderProximitat.setMajorTickSpacing(1);
		sliderProximitat.setBounds(0, 40, 332, 16);
		panelProximitat.add(sliderProximitat);
		
		labelSliderProximitat = new JLabel("10");
		labelSliderProximitat.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderProximitat.setBounds(350, 40, 31, 15);
		panelProximitat.add(labelSliderProximitat);
		
		JPanel panelAutor = new JPanel();
		panelAutor.setBounds(0, 350, 398, 65);
		panelAutor.setLayout(null);
		criterisSliderContainer.add(panelAutor);
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblAutor.setBounds(5, 5, 70, 15);
		panelAutor.add(lblAutor);
		
		JLabel lblCanonsAmbMateix = new JLabel("Cancons amb mateix autor");
		lblCanonsAmbMateix.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbMateix.setBounds(5, 22, 374, 15);
		panelAutor.add(lblCanonsAmbMateix);
		
		 sliderAutor = new JSlider();
		sliderAutor.setMinorTickSpacing(1);
		sliderAutor.setMaximum(10);
		sliderAutor.setMajorTickSpacing(1);
		sliderAutor.setBounds(0, 40, 332, 16);
		panelAutor.add(sliderAutor);
		
		labelSliderAutor = new JLabel("10");
		labelSliderAutor.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderAutor.setBounds(350, 40, 31, 15);
		panelAutor.add(labelSliderAutor);
		
		JButton btnGenerar = new JButton("Generar llista");
		btnGenerar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGenerar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				generateSolution();
			}
		});
		btnGenerar.setBounds(545, 443, 171, 25);
		add(btnGenerar);

		
		JPanel panelAlgorisme = new JPanel();
		panelAlgorisme.setBounds(500, 38, 250, 125);
		add(panelAlgorisme);
		panelAlgorisme.setPreferredSize(new Dimension(250, 410));
		panelAlgorisme.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panelAlgorisme.setLayout(null);
		
		JLabel lblAlgorisme = new JLabel("Algorisme:");
		lblAlgorisme.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAlgorisme.setBounds(499, 13, 251, 14);
		add(lblAlgorisme);
		

		Girvan = new JRadioButton("Girvan Newman");
		Girvan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		Girvan.setSize(234, 20);
		Girvan.setLocation(6, 7);
		algorismeGroup.add(Girvan);
		panelAlgorisme.add(Girvan);
		
		clique = new JRadioButton("Clique Percolation");
		clique.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		clique.setLocation(6, 48);
		clique.setSize(234, 20);
		algorismeGroup.add(clique);
		panelAlgorisme.add(clique);
		
		louvain = new JRadioButton("Louvain");
		louvain.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		louvain.setLocation(6, 90);
		louvain.setSize(234, 20);
		algorismeGroup.add(louvain);
		panelAlgorisme.add(louvain);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 37, 225, 2);
		panelAlgorisme.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 80, 225, 2);
		panelAlgorisme.add(separator_1);
		
		sliderDuracio.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderDuracio != null)
					labelSliderDuracio.setText( String.valueOf(sliderDuracio.getValue()) );
			}
		});

		sliderAny.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderAny != null)
					labelSliderAny.setText( String.valueOf(sliderAny.getValue()) );
			}
		});

		sliderEstil.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderEstil != null)
					labelSliderEstil.setText( String.valueOf(sliderEstil.getValue()) );
			}
		});

		sliderPublic.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderPublic != null)
					labelSliderPublic.setText( String.valueOf(sliderPublic.getValue()) );
			}
		});

		sliderProximitat.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderProximitat!= null)
					labelSliderProximitat.setText( String.valueOf(sliderProximitat.getValue()) );
			}
		});

		sliderAutor.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) 
			{
				if(labelSliderAutor!= null)
					labelSliderAutor.setText( String.valueOf(sliderAutor.getValue()) );
			}
		});
	}
	
	private void generateSolution()
	{
		char algorisme = '-';
		if(louvain.isSelected()) algorisme = 'L';
		else if(clique.isSelected()) algorisme = 'C';
		else if(Girvan.isSelected()) algorisme = 'G';
		else 
		{
			PresentationManager.errorWindow("Selecciona un algorisme primer!");
			return;
		}
		
		int durationP = sliderDuracio.getValue();
		int yearP = sliderAny.getValue();
		int styleP = sliderEstil.getValue();
		int publicP = sliderPublic.getValue();
		int proximityP = sliderProximitat.getValue();
		int authorP = sliderAutor.getValue();
		
		PresentationManager.generateSolution(algorisme, durationP, yearP, styleP, publicP, proximityP, authorP);
	}

	public void refresh() {
		// TODO Auto-generated method stub
		
	}
}
