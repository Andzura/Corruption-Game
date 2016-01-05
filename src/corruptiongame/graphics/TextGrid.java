package corruptiongame.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.JPanel;

import me.grea.antoine.utils.Log;

public class TextGrid extends JPanel {
		private char[] displayedChar;
		private int[] backgroundChar;
		private int[] foregroundChar;
		private int widthTile;
		private int heightTile;
		private int nbCharW;
		private int nbCharH;
		private Font font;
		
		public TextGrid(int width, int height, int widthTile, int heightTile){
			this.nbCharH = height;
			this.nbCharW = width;
			this.widthTile = widthTile;
			this.heightTile =heightTile;
			this.displayedChar = new char[nbCharH*nbCharW];
			this.backgroundChar = new int[nbCharH*nbCharW];
			this.foregroundChar = new int[nbCharH*nbCharW];
			font = null;
			InputStream is = TextGrid.class.getResourceAsStream("/font/DOSVGA437.ttf");
			if(is != null){
				try {
					font = Font.createFont(Font.TRUETYPE_FONT, is);
				} catch (FontFormatException | IOException e) {
					Log.f(e.getMessage());
				}
			}else
				Log.f("Font not found");
			try {
				is.close();
			} catch (IOException e) {
				Log.f(e.getMessage());
			}
			font = font.deriveFont(Font.BOLD, 19);
		}

		public char getDisplayedChar(int index){
			if(index < nbCharW*nbCharH)
				return displayedChar[index];
			else
				return ' ';
		}
		
		
		public void setDisplayedChar(char[] displayedChar){
			if(displayedChar.length == nbCharW*nbCharH){
				for(int i = 0; i < nbCharH*nbCharW; i++){
					this.displayedChar[i] = displayedChar[i];
				}
			}
		}
		
		public int getBackgroundChar(int index){
			if(index < nbCharW*nbCharH)
				return backgroundChar[index];
			else
				return -1;
		}

		public void setBackgroundChar(int[] backgroundChar) {
			if(backgroundChar.length == nbCharW*nbCharH){
				for(int i = 0; i < nbCharH*nbCharW; i++){
					this.backgroundChar[i] = backgroundChar[i];
				}
			}
		}

		public int getForegroundChar(int index) {
			if(index < nbCharW*nbCharH)
				return foregroundChar[index];
			else
				return -1;
		}

		public void setForegroundChar(int[] foregroundChar) {
			if(foregroundChar.length == nbCharW*nbCharH){
				for(int i = 0; i < nbCharH*nbCharW; i++){
					this.foregroundChar[i] = foregroundChar[i];
				}
			}
		}
		
		@Override
		public void paint(Graphics g){
			Graphics2D g2;
			if (g instanceof Graphics2D) {
					g2 = (Graphics2D) g;
			}
			else {
					Log.e(" g isn't an instance of Graphics2D.");
					return;
			}
			g2.setFont(font);
			FontMetrics fm = g2.getFontMetrics();
			for(int i = 0; i < nbCharH; i++){
			  for(int j = 0; j <nbCharW ; j++){
				  g2.setColor(new Color(getBackgroundChar(i*nbCharW + j)));
				  g2.fillRect(j*widthTile, i*heightTile, widthTile , heightTile);
				  g2.setColor(new Color(getForegroundChar(i*nbCharW + j)));
			      g2.drawString(String.valueOf(displayedChar[i*nbCharW + j]), j*widthTile, (i+1)*heightTile - (fm.getDescent())/2);
			  }
			}
			 
		}

}
