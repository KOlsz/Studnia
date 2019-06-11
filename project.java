package Projekt;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.event.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.border.TitledBorder;

import java.util.ArrayList;
import java.awt.Graphics.*;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Polygon;
import java.awt.Graphics2D;



public class project extends JFrame
{

	static final int SLIDER_MIN_M = 1;
	static final int SLIDER_MAX_M = 5;
	static final int SLIDER_INIT_M = 1;
	static final int SLIDER_MIN_A = 20;
	static final int SLIDER_MAX_A = 40;
	static final int SLIDER_INIT_A = 20;
	static final int n = 5;
	JSlider mass,boxWidth;
	JLabel massLabel, boxWidthLabel,fourierLabel;
	JPanel GUI,center,down;
	WavePanel waveGraph;
	JMenuBar menuBar;
	JMenu menu,languages,fizyka;
	JMenuItem saveGraph,end,polski,english,energy,waveFunction, stationaryState, exit;
	JButton onoff, next, prev;
	JCheckBox[] fourierElements = new JCheckBox[n];
	int wellWidth;
	ArrayList<Double> energyLevels;
	final double Pi = Math.PI;
	final double hBar = 1.054571800;
	int particleMass;
	EnergyPanel energyGraph;
	Boolean[] chosen;
	
	
	JFrame theory;
	ImageCanvas canvas;
	JMenuBar menuBarTheory;
	JMenu fizykaTheory;
	JMenuItem energyTheory,waveFunctionTheory, stationaryStateTheory, exitTheory;
	
	public project() throws HeadlessException
	{
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setSize(640,480);
		
		
		wellWidth = 20;
		particleMass = 1;
		chosen = new Boolean[n];
		for(int i = 0 ; i < n ; i++)
		{
			chosen[i] = true; 
		}
		energyLevels = new ArrayList<Double>();
		for(int i = 0 ; i < 6 ; i++)
		{
			energyLevels.add(i*i*Pi*Pi*hBar*hBar/(2*particleMass*wellWidth*wellWidth));
			System.out.println(energyLevels.get(i));
		}
		energyGraph = new EnergyPanel(wellWidth, energyLevels);
		
		menuBar = new JMenuBar();
		this.setJMenuBar(menuBar);
		menu = new JMenu("Menu");
		menu.setMnemonic(KeyEvent.VK_M);
		menuBar.add(menu);
		saveGraph = new JMenuItem("Zapisz wykres",KeyEvent.VK_S);
		menu.add(saveGraph);
		languages = new JMenu("Jêzyki");
		languages.setMnemonic(KeyEvent.VK_L);
		menu.add(languages);
		polski = new JMenuItem("Polski",KeyEvent.VK_P);
		languages.add(polski);
		english = new JMenuItem("Angielski",KeyEvent.VK_E);
		languages.add(english);
		menu.addSeparator();
		end = new JMenuItem("Zakoñcz",KeyEvent.VK_E);
		menu. add(end);
		fizyka = new JMenu("Fizyka");
		fizyka.setMnemonic(KeyEvent.VK_F);
		menuBar.add(fizyka);
		exit = new JMenuItem("Wyjœcie",KeyEvent.VK_C);
		energy = new JMenuItem("Energia", KeyEvent.VK_E);
		fizyka.add(energy);
		addEnergy();
		waveFunction = new JMenuItem("Funkcja falowa", KeyEvent.VK_W);
		fizyka.add(waveFunction);
		addWaveFunction();
		stationaryState = new JMenuItem("Stany stacjonarne", KeyEvent.VK_W);
		fizyka.add(stationaryState);
		addStationaryState();
		next = new JButton("Nastepny");
		prev = new JButton("Poprzedni");
		fizyka.addSeparator();
		fizyka.add(exit);
		
		
		
		GUI = new JPanel();
		center = new JPanel();
		down = new JPanel();
		canvas = new ImageCanvas();
		waveGraph = new WavePanel(wellWidth);
		this.setLayout(new BorderLayout());
		this.add(GUI,BorderLayout.LINE_END);
		this.add(center,BorderLayout.CENTER);                                                 
		
		
		center.setLayout(new GridLayout(2,1));
		center.add(energyGraph);
		center.add(waveGraph);
		canvas.setBackground(Color.white);
		down.add(prev);
		addPrev();
		down.add(next);
		addNext();
		down.setVisible(false);
		addExit();
		
		
		mass = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN_M, SLIDER_MAX_M, SLIDER_INIT_M);
		boxWidth = new JSlider(JSlider.HORIZONTAL, SLIDER_MIN_A, SLIDER_MAX_A, SLIDER_INIT_A);
		
		onoff = new JButton("ON/OFF");
		fourierLabel = new JLabel("Wybór n :");
		massLabel = new JLabel("Masa");
		boxWidthLabel = new JLabel("Szerokoœæ");
		
		GUI.add(Box.createRigidArea(new Dimension(0, 30)));
		GUI.setLayout(new BoxLayout(GUI, BoxLayout.PAGE_AXIS));
		GUI.setPreferredSize(new Dimension(150, 480));
		GUI.add(mass);	
		GUI.add(massLabel);
		GUI.add(boxWidth);
		GUI.add(boxWidthLabel);
		GUI.add(Box.createRigidArea(new Dimension(0, 20)));
		GUI.add(onoff);
		GUI.add(Box.createRigidArea(new Dimension(0, 20)));
		GUI.add(fourierLabel);
		for(int i = 0 ; i < n ; i ++)
		{
			fourierElements[i] = new JCheckBox(Integer.toString(i+1));
			GUI.add(fourierElements[i]);
		}
		
		
		theory = new JFrame();
		theory.setSize(1280, 850);
		theory.setLayout(new BorderLayout());
		theory.add(canvas, BorderLayout.CENTER); 
		theory.add(down, BorderLayout.SOUTH); 
		menuBarTheory = new JMenuBar();
		theory.setJMenuBar(menuBarTheory);
		fizykaTheory = new JMenu("Fizyka");
		menuBarTheory.add(fizykaTheory);
		energyTheory = new JMenuItem("Energia");
		waveFunctionTheory = new JMenuItem("Funkcja falowa"); 
		stationaryStateTheory = new JMenuItem("Stany stacjonarne"); 
		exitTheory = new JMenuItem("Wyjœcie");
		fizykaTheory.add(energyTheory);
		fizykaTheory.add(waveFunctionTheory);
		fizykaTheory.add(stationaryStateTheory);
		fizykaTheory.addSeparator();
		fizykaTheory.add(exitTheory);
		addEnergyTheory();
		addWaveFunctionTheory();
		addStationaryStateTheory();
		addExitTheory();
		
		
		ChangeListener widthSliderListener = new ChangeListener()
	   	{
			public void stateChanged(ChangeEvent arg0)
			{
				wellWidth = boxWidth.getValue();
				energyGraph.setWellWidth(wellWidth);
				calculateEnergyLevels();
				energyGraph.setEnergyLevels(energyLevels);
	    		energyGraph.repaint();
	    		waveGraph.setWellWidth(wellWidth);
	    		waveGraph.repaint();
	    		
				
			}
	 	};
	 	ChangeListener massSliderListener= new ChangeListener()
	   	{
			public void stateChanged(ChangeEvent arg0)
			{
				particleMass = mass.getValue();
				calculateEnergyLevels();
				energyGraph.setEnergyLevels(energyLevels);
	    		energyGraph.repaint();
				
			}
	 	};
	 	ItemListener FourierElementsListener = new ItemListener()
	 		{
				public void itemStateChanged(ItemEvent arg0) 
				{
					for(int i = 0 ; i < n ;i ++)
					{
						if(fourierElements[i].isSelected())
						{
							chosen[i] = false;
						}
						else chosen[i] = true;
					}
					energyGraph.setChosen(chosen);
					energyGraph.repaint();
					waveGraph.setChosen(chosen);
					waveGraph.repaint();
				}
	 		
	 		};
	 	boxWidth.addChangeListener(widthSliderListener);
	 	mass.addChangeListener(massSliderListener);
	 	for(int i = 0 ; i < 5 ; i++)
	 	{
	 		fourierElements[i].addItemListener(FourierElementsListener);
	 	}
	}
	public void calculateEnergyLevels()
	{
		energyLevels.clear();
		for(int i = 0 ; i < 6 ; i++)
		{
			energyLevels.add(i*i*Pi*Pi*hBar*hBar/(2*particleMass*wellWidth*wellWidth));
		}
	}
	public JFreeChart waveFunctionGraph(int n)
	{
		XYSeries DataSeries = new XYSeries("");
		for (double i=0; i <wellWidth; i+=0.05) DataSeries.add(i-wellWidth/2,Math.sqrt(wellWidth)*Math.sin(n*Pi*i/wellWidth));
		XYSeriesCollection xySeriesCollection = new XYSeriesCollection(DataSeries); 
	    XYDataset xyDataset = xySeriesCollection;      
	    JFreeChart lineGraph = ChartFactory.createXYLineChart 
	             ("",  // Title 
	  	               "",           // X-Axis label 
		               "",           // Y-Axis label 
	               xyDataset,          // Dataset 
	               PlotOrientation.VERTICAL,        //Plot orientation 
	               false,                //show legend 
	               false,                // Show tooltips 
	               false               //url show 
	              ); 
	    return lineGraph;
	}
	private void addEnergy() {
		energy.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				theory.setVisible(true);
				canvas.setImagePath("screens/1.png");
				down.setVisible(false);
			}
		});
	}
	private void addWaveFunction() {
		waveFunction.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				theory.setVisible(true);
				canvas.setImagePath("screens/2.png");
				down.setVisible(false);

			}
		});
	}
	private void addStationaryState() {
		stationaryState.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				theory.setVisible(true);
				canvas.setImagePath("screens/3a.png");
				down.setVisible(true);
			}
		});
	}

	
	private void addNext() {
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				theory.setVisible(true);
				canvas.setImagePath("screens/3b.png");

			}
		});
	}
	private void addPrev() {
		prev.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				theory.setVisible(true);
				canvas.setImagePath("screens/3a.png");

			}
		});
	}
	private void addExit()
	{
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				theory.setVisible(false);
				
			}
		});
	}
	
	private void addEnergyTheory() {
		energyTheory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				theory.setVisible(true);
				canvas.setImagePath("screens/1.png");
				down.setVisible(false);
			}
		});
	}
	private void addWaveFunctionTheory() {
		waveFunctionTheory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				theory.setVisible(true);
				canvas.setImagePath("screens/2.png");
				down.setVisible(false);

			}
		});
	}
	private void addStationaryStateTheory() {
		stationaryStateTheory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				theory.setVisible(true);
				canvas.setImagePath("screens/3a.png");
				down.setVisible(true);
			}
		});
	}

	private void addExitTheory()
	{
		exitTheory.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				theory.setVisible(false);
				
			}
		});
	}
	
	public static void main(String[] args) 
	{
		project frame = new project();
		frame.setVisible(true);
	}

}
