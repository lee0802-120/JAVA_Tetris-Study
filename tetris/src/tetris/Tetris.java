package tetris;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

class Tiles {
	
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

public class Tetris extends JFrame {
	public static final int M = 20;
	public static final int N = 10;
	private ImageIcon mainBackGroundImage = new ImageIcon("img/background.png");
	private ImageIcon fieldImage = new ImageIcon("img/tiles.png");
	private Image imageMainBackGround = mainBackGroundImage.getImage();
	private BufferedImage combinedImage;
	private int[][] field = new int[M][N];
	private int[][] tiles = new int[][] {
		// [7][4]
			{1,3,5,7}, // I
			{2,4,5,7}, // Z
			{3,5,4,6}, // S
			{3,5,4,7}, // T
			{2,3,5,7}, // L
			{3,5,7,6}, // J
			{2,3,4,5}  // O
	};
	
	public Tetris() {
		super("Tetris Game!");
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		MyPanel mainPanel = new MyPanel();
		setContentPane(mainPanel);
		
		try {
			BufferedImage fieldImage = ImageIO.read(new File("img/tiles.png"));
			
			List<BufferedImage> croppedImages = new ArrayList<>();
			croppedImages.add(Tiles.cropImage(fieldImage, 17, 0, 17, 17));
			croppedImages.add(Tiles.cropImage(fieldImage, 17, 0, 17, 17));
			croppedImages.add(Tiles.cropImage(fieldImage, 35, 0, 17, 17));
//			croppedImages.add(Tiles.cropImage(fieldImage, 53, 0, 71, 17));
//			croppedImages.add(Tiles.cropImage(fieldImage, 71, 0, 89, 17));
//			croppedImages.add(Tiles.cropImage(fieldImage, 89, 0, 107, 17));
//			croppedImages.add(Tiles.cropImage(fieldImage, 107, 0, 125, 17));
//			croppedImages.add(Tiles.cropImage(fieldImage, 125, 0, 143, 17));
			
			combinedImage = Tiles.combineImage(croppedImages, 2, 2, 100, 100);
			
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setVisible(true);
	}

	public static void main(String[] args) {
		new Tetris();
	}
	
	class MyPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int panelWidth = getWidth();
			int panelHeight = getHeight();
			g.drawImage(imageMainBackGround, 0, 0, panelWidth, panelHeight, this);
		}
	}
}
