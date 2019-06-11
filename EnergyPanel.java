package Projekt;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


import java.util.Vector;
import java.util.ArrayList;
import java.awt.Graphics.*;

	public class EnergyPanel extends JPanel
	{
		int wellWidth;
		int n = 5;
		Boolean[] chosen;
		final double Pi = Math.PI;
		ArrayList<Integer> energyLevels;
		public EnergyPanel(int a, ArrayList<Double> l)
		{
			this.setSize(200, 440);
			chosen = new Boolean[n];
			for(int i = 0 ; i < n ; i++)
			{
				chosen[i] = true; 
			}
			energyLevels = new ArrayList<Integer>();
			wellWidth = a;
			for(int i = 0 ; i < 6; i ++)
			{
				int k = (int)Math.round(4*100*l.get(i));
				energyLevels.add(k);
				System.out.println(energyLevels.get(i));
			}
		}
		public void setWellWidth(int k)
		{
			wellWidth = k;
		}
		public void setEnergyLevels(ArrayList<Double> l)
		{
			energyLevels.clear();
			for(int i = 0 ; i < 6; i ++)
			{
				int k = (int)Math.round(4*100*l.get(i));
				energyLevels.add(k);
				System.out.println(energyLevels.get(i));
			}
		}
		public void setChosen(Boolean[] f)
		{
			for(int i = 0 ; i < 5 ;i ++)
			{
				chosen[i] = f[i];
			}
		}
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			Graphics2D gg2d = (Graphics2D) g;
			BasicStroke bs1 = new BasicStroke(2);
			gg2d.setStroke(bs1);
			gg2d.drawLine(20, 180, 420, 180);
			gg2d.drawLine(220-7*wellWidth/2,180,220-7*wellWidth/2,20);
			gg2d.drawLine(220+7*wellWidth/2,180,220+7*wellWidth/2,20);
			for(int i = 0 ; i < 5 ; i++)
			{
				if(!chosen[i]) 
				{
					gg2d.setColor(Color.RED);
					for(int j = 0 ; j < 7*wellWidth ; j++)
					{
					gg2d.fillOval(220+j-7*wellWidth/2,180-energyLevels.get(i+1)-(int)(5*Math.sqrt(wellWidth)*Math.sin(((i+1)*Pi*j)/(7*wellWidth)))-4,3,3);
					}
				}
				else gg2d.setColor(Color.BLACK);
				gg2d.drawLine(220-7*wellWidth/2,180-energyLevels.get(i+1),220+7*wellWidth/2,180-energyLevels.get(i+1));
				

			}


		}
		
}
