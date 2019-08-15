package playground3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends Canvas implements MouseListener{
	
	static int cWidth = 400;
	static int cHeight = 400;
	static int w = 20;
	static int row = cHeight/w;
	static int col = cWidth/w;
	
	Grid grid;

	Main() {
		addMouseListener(this);
		grid = new Grid(row, col, w);
		
	}
	
	public static void main(String[] args) {
		JFrame f = new JFrame("Minesweeper by LeonJM");
		Canvas canvas = new Main();
		f.setPreferredSize(new Dimension(cWidth*5/4, cHeight*5/4));
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(canvas);
		f.pack();
		f.setVisible(true);
		
	}
	
	@Override
	public void paint(Graphics g) {
		grid.show(g);
	}


	@Override
	public void mousePressed(MouseEvent e) {
		int mouseX = e.getX()/20;
		int mouseY = e.getY()/20;
		if (SwingUtilities.isRightMouseButton(e)) {
			grid.mark(mouseX,mouseY);
		} else {
			grid.click(mouseX, mouseY);
		}
		//repaint();
		grid.show(getGraphics());		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
