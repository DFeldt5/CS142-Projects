import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/*
 * Dustin Feldt
 * CS 142 Assignment 3
 * 11-17-2022
 * 
 * Draws a generalized, proportional American flag with the designated height, width,
 * number of stars, and number of stripes
 */
 
public class StarsAndStripes {

	//Draws the white base layer, red stripes, blue field, and white star grid
	public static void drawFlag(int stars, int stripes, java.awt.Graphics g, int x, int y, int width, int height) {
		
		//Draws a white rectangle base layer/background
		g.setColor(Color.white);
		g.fillRect(x, y, width, height);
		
		//Determines the number of red stripes and their thickness
		int redStripes = (int) Math.ceil(stripes / 2.0);
		int stripeHeight = height / stripes;
		
		//Draws red stripes, determines color of bottom stripe, and if red adjusts size to fill
		g.setColor(Color.red);
		int yStripe = y;
		if(stripes % 2 == 0) {
			for(int stripeCount = 0; stripeCount < redStripes; stripeCount++) {
				g.fillRect(x, yStripe, width, stripeHeight);
				yStripe = yStripe + stripeHeight * 2;
			}
		}	
		if(stripes % 2 != 0) {
			for(int stripeCount = 0; stripeCount < redStripes - 1; stripeCount++) {
				g.fillRect(x, yStripe, width, stripeHeight);
				yStripe = yStripe + stripeHeight * 2;
			}
			g.fillRect(x, yStripe, width, stripeHeight + height % stripes);
		}
		
		//Draws blue starfield in upper left corner, proportional to size of flag
		g.setColor(Color.blue);
		int starfieldHeight = stripeHeight * redStripes;
		int starfieldWidth = starfieldHeight * width / height;
		g.fillRect(x, y, starfieldWidth, starfieldHeight);
		
		//Determines how many rows and columns of stars to draw
		int rows = 1;
		int columns = 1;
		for(int divisor = stars / 2; divisor >= rows; divisor--) {
			rows = stars / divisor;
			columns = divisor;
			if(stars % columns == 0 && columns > rows && columns < 2 * rows) {
				break;
			}
		}
		
		//Determines size of stars based on rows and columns
		int size = starfieldHeight / rows;
		if(starfieldHeight / rows > starfieldWidth / columns) {
			size = starfieldWidth / columns;
		}
		
		//Draws and centers white star grid in starfield
		int stopXAt = 0;
		int stopYAt = 0;
		for(int yStars = y + (starfieldHeight - size * rows) / 2; yStars <= starfieldHeight + y - size; yStars = yStars + size) {
			for(int xStars = x + (starfieldWidth - size * columns) / 2; xStars <= starfieldWidth + x - size; xStars = xStars + size) {
				g.setColor(Color.white);
				drawStar(g, xStars, yStars, size);
				stopXAt++;
				if(stopXAt == columns) {
					break;
				}
			}
			stopXAt = 0;
			stopYAt++;
			if(stopYAt == rows) {
				break;
			}
			
		}
	}

	//Draws a single five-pointed star with the given coordinates and size
	public static void drawStar(java.awt.Graphics g, int x, int y, int size) {
		
		//Point A, top of star
		int aX = x + size / 2;
		int aY = y;
		
		//Point B, lower right arm
		int bX = x + size - size / 5;
		int bY = y + size;
		
		//Point C, upper left arm
		int cX = x;
		int cY = y + size / 5 * 2;
		
		//Point D, upper right arm
		int dX = x + size;
		int dY = y + size / 5 * 2;
		
		//Point E, lower left arm
		int eX = x + size / 5;
		int eY = y + size;
		
		//Draws lines in sequence
		g.drawLine(aX, aY, bX, bY);
		g.drawLine(bX, bY, cX, cY);
		g.drawLine(cX, cY, dX, dY);
		g.drawLine(dX, dY, eX, eY);
		g.drawLine(eX, eY, aX, aY);
	
	}

	// Only alter the "drawFlag" part of the paintComponent
	// code to call it in different ways. You can also test
	// drawing multiple flags at once!
	public static void main(String[] args) {
		JFrame window = new JFrame("Graphics window");
		window.setLocationByPlatform(true);
		final JLabel coords = new JLabel(" ");
		@SuppressWarnings("serial")
		final JPanel panel = new JPanel() {

			protected void paintComponent(Graphics gx) {
				coords.setText(" ");
				Graphics2D g = (Graphics2D) gx;
				int width = getWidth();
				int height = getHeight();
				g.setBackground(Color.CYAN); // To make sure you cover the base rectangle!
				g.clearRect(0, 0, width, height);
				g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g.setColor(Color.BLACK);

				// You could alter this code to try different flags!
				drawFlag(48, 16, g, width/2, height/2, width/2, height/2);
				drawFlag(24, 15, g, 0, height/2, width/2, height/2);
				drawFlag(20, 14, g, width/2, 0, width/2, height/2);
				drawFlag(15, 13, g, 0, 0, width/2, height/2);
				//drawFlag(50, 13, g, 0, 0, width, height);
			}
		};
		panel.addMouseMotionListener(new MouseMotionListener() {


			@Override
			public void mouseDragged(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseMoved(MouseEvent e) {
				coords.setText(e.getX()+", "+e.getY());
			}

		});
		window.setLayout(new BorderLayout());
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		window.setSize(d.width / 2, d.height / 2);

		JPanel coordPanel = new JPanel();
		coordPanel.setLayout(new BorderLayout());
		coordPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		window.add(coordPanel, BorderLayout.SOUTH);
		coordPanel.add(coords, BorderLayout.WEST);

		window.setBackground(Color.WHITE); // To make sure you cover the base rectangle!
		panel.setBackground(Color.BLACK);
		window.add(panel, BorderLayout.CENTER);
		//window.setContentPane(panel);
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
