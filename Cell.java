package playground3;

import java.awt.*;
import java.awt.event.*;

public class Cell {

	private int x, y, w;
	private int mR, mC;
	private boolean bomb = false;
	private int neighbours = 0;
	
	private boolean isMarked = false;
	private boolean revealed = false;
	
	Cell(int x, int y, int w, int mR, int mC) {		
		this.x = x;
		this.y = y;
		this.w = w;
		
		this.mR = mR;
		this.mC = mC;
		
		//sets probability of bomb spawning
		if (Math.random() < 0.13) {
			this.bomb = true;
		}	
	}
	
	public boolean isBomb() {
		return this.bomb;
	}
	 
	public void setNeighbours(int n) {
		this.neighbours = n;
	}
	
	public int getNeighbourCount() {
		return this.neighbours; 
	}
	
	public void setReveal() {
		this.revealed = true;
		
		//flood fill
//		if (this.getNeighbourCount() == 0 && !this.isBomb()) {
//			for (int i = -1; i < 2; i ++) {
//				for (int j = 0; j < 2; j ++) {
//					//make sure not out of bounds
//					if (this.x+i<0 || this.x+i>=this.mR || this.y+j<0 || this.y+j>=this.mC) {
//						continue;
//					}
//					Cell temp = cell[this.x+i][this.y+j];
//				}
//			}
//		}
	}
	
	public boolean isRevealed() {
		return this.revealed;
	}
	
	public void mark() {
		this.isMarked = true;
	}
	
	public void unmark() {
		this.isMarked = false;
	}
	
	public boolean getMark() {
		return this.isMarked;
	}
	
	public void show(Graphics g) {
		int X = this.x*this.w;
		int Y = this.y*this.w;
		
		if (this.isMarked) {
			g.setColor(Color.PINK);
			g.fillRect(X, Y, this.w, this.w);
		} else {
			g.setColor(Color.WHITE);
			g.fillRect(X, Y, this.w, this.w);
		}
		
		if (revealed) {
			if (this.bomb) {
				g.setColor(Color.RED);
				g.fillOval(X + this.w/5, Y + this.w/5, this.w*3/5, this.w*3/5);
			} else {
				if (this.neighbours == 0) {
					g.setColor(Color.GRAY);
					g.fillRect(X, Y, this.w, this.w);
				} else {
					g.setColor(Color.BLACK);
					g.drawString(Integer.toString(this.neighbours), X + this.w/2, Y + this.w*3/4);
				}
			}
		}
		//grid overlay
		g.setColor(Color.BLACK);
		g.drawRect(X, Y, this.w, this.w);
	}
}
