package Presentacio;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.BoxLayout;
import javax.swing.JToolBar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JSlider;
import java.awt.Font;
import java.awt.Cursor;

public class GenerarLlistesPanel extends JPanel {

	public GenerarLlistesPanel()
	{
		super();
		setPreferredSize(new Dimension(1000, 400));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelLeft = new JPanel();
		panelLeft.setBorder(new EmptyBorder(30, 30, 30, 30));
		add(panelLeft, BorderLayout.WEST);
		
		JPanel panelCriteris = new JPanel();
		panelCriteris.setPreferredSize(new Dimension(400, 470));
		panelCriteris.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelLeft.add(panelCriteris);
		panelCriteris.setLayout(new BoxLayout(panelCriteris, BoxLayout.Y_AXIS));
		
		JPanel panelLabelCriteris = new JPanel();
		panelLabelCriteris.setMaximumSize(new Dimension(32767, 30));
		panelCriteris.add(panelLabelCriteris);
		panelLabelCriteris.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblCriteris = new JLabel("Criteris");
		panelLabelCriteris.add(lblCriteris);
		
		JPanel criterisSliderContainer = new JPanel();
		panelCriteris.add(criterisSliderContainer);
		criterisSliderContainer.setLayout(null);
		
		JPanel panelDuracio = new JPanel();
		panelDuracio.setBounds(0, 0, 398, 65);
		criterisSliderContainer.add(panelDuracio);
		panelDuracio.setLayout(null);
		
		JLabel lblDuracio = new JLabel("Duracio");
		lblDuracio.setBounds(5, 5, 70, 15);
		panelDuracio.add(lblDuracio);
		
		JLabel lblCanonsAmbRelacio = new JLabel("Cançons amb duracio semblant");
		lblCanonsAmbRelacio.setBounds(5, 22, 374, 15);
		panelDuracio.add(lblCanonsAmbRelacio);
		lblCanonsAmbRelacio.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JSlider sliderDuracio = new JSlider();
		sliderDuracio.setBounds(0, 40, 332, 16);
		panelDuracio.add(sliderDuracio);
		sliderDuracio.setMajorTickSpacing(1);
		sliderDuracio.setMinorTickSpacing(1);
		sliderDuracio.setMaximum(10);
		sliderDuracio.setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
		
		JLabel labelSliderDuracio = new JLabel("00");
		labelSliderDuracio.setBounds(350, 40, 31, 15);
		panelDuracio.add(labelSliderDuracio);
		labelSliderDuracio.setFont(new Font("Dialog", Font.PLAIN, 12));
		
		JPanel panelAny = new JPanel();
		panelAny.setLayout(null);
		panelAny.setBounds(0, 70, 398, 65);
		criterisSliderContainer.add(panelAny);
		
		JLabel lblAny = new JLabel("Any");
		lblAny.setBounds(5, 5, 70, 15);
		panelAny.add(lblAny);
		
		JLabel lblCanonsAmbAnys = new JLabel("Cançons amb anys de creacio semblant");
		lblCanonsAmbAnys.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbAnys.setBounds(5, 22, 374, 15);
		panelAny.add(lblCanonsAmbAnys);
		
		JSlider sliderAny = new JSlider();
		sliderAny.setMinorTickSpacing(1);
		sliderAny.setMaximum(10);
		sliderAny.setMajorTickSpacing(1);
		sliderAny.setBounds(0, 40, 332, 16);
		panelAny.add(sliderAny);
		
		JLabel labelSliderAny = new JLabel("00");
		labelSliderAny.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderAny.setBounds(350, 40, 31, 15);
		panelAny.add(labelSliderAny);
		
		JPanel panelEstil = new JPanel();
		panelEstil.setLayout(null);
		panelEstil.setBounds(0, 140, 398, 65);
		criterisSliderContainer.add(panelEstil);
		
		JLabel lblEstil = new JLabel("Estil");
		lblEstil.setBounds(5, 5, 70, 15);
		panelEstil.add(lblEstil);
		
		JLabel lblCanonsAmbEstil = new JLabel("Cançons amb estil semblant");
		lblCanonsAmbEstil.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbEstil.setBounds(5, 22, 374, 15);
		panelEstil.add(lblCanonsAmbEstil);
		
		JSlider sliderEstil = new JSlider();
		sliderEstil.setMinorTickSpacing(1);
		sliderEstil.setMaximum(10);
		sliderEstil.setMajorTickSpacing(1);
		sliderEstil.setBounds(0, 40, 332, 16);
		panelEstil.add(sliderEstil);
		
		JLabel labelSliderEstil = new JLabel("00");
		labelSliderEstil.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderEstil.setBounds(350, 40, 31, 15);
		panelEstil.add(labelSliderEstil);
		
		JPanel panelPublic = new JPanel();
		panelPublic.setLayout(null);
		panelPublic.setBounds(0, 210, 398, 65);
		criterisSliderContainer.add(panelPublic);
		
		JLabel Public = new JLabel("Public");
		Public.setBounds(5, 5, 70, 15);
		panelPublic.add(Public);
		
		JLabel lblCanonsAmbPublic = new JLabel("Cançons amb public d'edats semblants");
		lblCanonsAmbPublic.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbPublic.setBounds(5, 22, 374, 15);
		panelPublic.add(lblCanonsAmbPublic);
		
		JSlider sliderPublic = new JSlider();
		sliderPublic.setMinorTickSpacing(1);
		sliderPublic.setMaximum(10);
		sliderPublic.setMajorTickSpacing(1);
		sliderPublic.setBounds(0, 40, 332, 16);
		panelPublic.add(sliderPublic);
		
		JLabel labelSliderPublic = new JLabel("00");
		labelSliderPublic.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderPublic.setBounds(350, 40, 31, 15);
		panelPublic.add(labelSliderPublic);
		
		JPanel panelProximitat = new JPanel();
		panelProximitat.setLayout(null);
		panelProximitat.setBounds(0, 280, 398, 65);
		criterisSliderContainer.add(panelProximitat);
		
		JLabel lblProximitat = new JLabel("Proximitat");
		lblProximitat.setBounds(5, 5, 113, 15);
		panelProximitat.add(lblProximitat);
		
		JLabel lblCanonsEscoltadesNormalment = new JLabel("Cançons escoltades juntes en periodes curts de temps");
		lblCanonsEscoltadesNormalment.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsEscoltadesNormalment.setBounds(5, 22, 374, 15);
		panelProximitat.add(lblCanonsEscoltadesNormalment);
		
		JSlider sliderProximitat = new JSlider();
		sliderProximitat.setMinorTickSpacing(1);
		sliderProximitat.setMaximum(10);
		sliderProximitat.setMajorTickSpacing(1);
		sliderProximitat.setBounds(0, 40, 332, 16);
		panelProximitat.add(sliderProximitat);
		
		JLabel labelSliderProximitat = new JLabel("00");
		labelSliderProximitat.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderProximitat.setBounds(350, 40, 31, 15);
		panelProximitat.add(labelSliderProximitat);
		
		JPanel panelAutor = new JPanel();
		panelAutor.setLayout(null);
		panelAutor.setBounds(0, 350, 398, 65);
		criterisSliderContainer.add(panelAutor);
		
		JLabel lblAutor = new JLabel("Autor");
		lblAutor.setBounds(5, 5, 70, 15);
		panelAutor.add(lblAutor);
		
		JLabel lblCanonsAmbMateix = new JLabel("Cançons amb mateix autor");
		lblCanonsAmbMateix.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblCanonsAmbMateix.setBounds(5, 22, 374, 15);
		panelAutor.add(lblCanonsAmbMateix);
		
		JSlider sliderAutor = new JSlider();
		sliderAutor.setMinorTickSpacing(1);
		sliderAutor.setMaximum(10);
		sliderAutor.setMajorTickSpacing(1);
		sliderAutor.setBounds(0, 40, 332, 16);
		panelAutor.add(sliderAutor);
		
		JLabel labelSliderAutor = new JLabel("00");
		labelSliderAutor.setFont(new Font("Dialog", Font.PLAIN, 12));
		labelSliderAutor.setBounds(350, 40, 31, 15);
		panelAutor.add(labelSliderAutor);
		
		JPanel panelRight = new JPanel();
		add(panelRight, BorderLayout.EAST);
		
		JButton btnNewButton = new JButton("Generar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panelRight.add(btnNewButton);
	}
}
