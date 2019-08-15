package playground3;

import java.awt.*;
import java.awt.event.*;

public class Grid {
	int x, y, w;
	Cell[][] cell;
	
	Grid(int x, int y, int w) {
		cell = new Cell[x][y];
		this.x = x;
		this.y = y;
		this.w = w;
		for (int i = 0; i < x; i ++) {
			for (int j = 0; j < y; j ++) {
				cell[i][j] = new Cell(i,j,w,x,y);
			}
		}
	}
	
	public void checkNeighbours() {
		for (int i = 0; i < this.x; i ++) {
			for (int j = 0; j < this.y; j ++) {
				if(!cell[i][j].isBomb()) {
					int result = 0;
					//check for neighbour cell (math)
					for (int p = -1; p < 2; p ++) {
						for (int q = -1; q < 2; q ++) {
							if (i+p<0 || i+p>=this.x || j+q<0 || j+q>=this.y) {
								//skip, check next cell
								continue;
							}
							Cell temp = cell[i+p][j+q];
							if (temp.isBomb()) {
								result ++;
							}
						}
					}
					cell[i][j].setNeighbours(result);
				}
			}
		}
	}
	
	public void mark(int x, int y) {
		cell[x][y].mark();
	}
	
	private void unmark(int x, int y) {
		cell[x][y].unmark();
	}
	
	public void click(int x, int y) {
		int X = x;
		int Y = y;
		if (cell[X][Y].getMark()) {
			this.unmark(X, Y);
		} else {
			cell[X][Y].setReveal();
			//flood fill
			if (cell[X][Y].getNeighbourCount() == 0 && !cell[X][Y].isBomb()) {
				this.floodFill(X, Y);
			}
			if (cell[X][Y].isBomb()) {
				this.revealAll();
			}
		}
	}
	
	private void revealAll() {
		for (int i = 0; i < this.x; i ++) {
			for(int j = 0; j < this.y; j ++) {
				cell[i][j].setReveal();
			}
		}
	}
	
	private void floodFill(int X, int Y) {
		//check neighbour cells
		for (int i = -1; i < 2; i ++) {
			for (int j = -1; j < 2; j ++) {
				//make sure not out of bounds
				if (X+i<0 || X+i>=this.x || Y+j<0 || Y+j>=this.y) {
					continue;
				}
				Cell temp = cell[X+i][Y+j];
				if (!temp.isRevealed()) {
					this.click(X+i, Y+j);
				}
			}
		}
	}
	
	public void show(Graphics g) {
		this.checkNeighbours();
		for (int i = 0; i < x; i ++) {
			for (int j = 0; j < y; j ++) {
				cell[i][j].show(g);
			}
		}
	}

}
