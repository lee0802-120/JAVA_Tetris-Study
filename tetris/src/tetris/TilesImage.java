package tetris;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class TilesImage {
	
	public static BufferedImage cropImage(BufferedImage src, int x, int y, int width, int height) {
		BufferedImage dest = src.getSubimage(x, y, width, height);
		return dest;
	}
	
	public static BufferedImage combineImage(List<BufferedImage> images, int rows, int cols, int width, int height) {
		BufferedImage combined = new BufferedImage(width * cols, height * rows, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = combined.createGraphics();
		
		for (int i = 0; i < images.size(); i++) {
			int row = i / cols;
			int col = i % cols;
			g2d.drawImage(images.get(i), col * width, row * height, null);
		}
		
		g2d.dispose();
		return combined;
	}

}
