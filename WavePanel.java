package Projekt;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.*;


import java.util.Vector;
import java.util.ArrayList;
import java.awt.Graphics.*;

	public class WavePanel extends JPanel
	{
		int wellWidth;
		int n = 5;
		final double Pi = Math.PI;
		Boolean[] chosen;
		public WavePanel(int a)
		{
			this.setSize(200, 440);
			chosen = new Boolean[n];
			for(int i = 0 ; i < n ; i++)
			{
				chosen[i] = true; 
			}
			wellWidth = a;
		}
		public void setWellWidth(int k)
		{
			wellWidth = k;
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
					gg2d.fillOval(220+j-7*wellWidth/2,100-(int)Math.abs(10*Math.sqrt(wellWidth)*Math.sin(((i+1)*Pi*j)/(7*wellWidth))),3,3);
					//gg2d.drawLine(220+j-7*wellWidth/2, 100-(int)Math.abs(10*Math.sqrt(wellWidth)*Math.sin(((i+1)*Pi*j)/(7*wellWidth))), 220+1+j-7*wellWidth/2,100-(int)Math.abs(10*Math.sqrt(wellWidth)*Math.sin(((i+1)*Pi*j+1)/(7*wellWidth))) );
					}
				}

			}


		}
		
}
