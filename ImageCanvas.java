package Projekt;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImageCanvas extends JPanel {

	String imagePath;
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		loadImage(g);
		
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
		repaint();
	}
	
	
	public void loadImage(Graphics g) {
		
		if (imagePath != null) {
			try {
				File imageFile = Paths.get(imagePath).toFile();
				BufferedImage image = ImageIO.read(imageFile);
				g.drawImage(image, 0, 0, this);
			} catch (IOException ignored) {
				ignored.printStackTrace();
			}
		}
	}

}
