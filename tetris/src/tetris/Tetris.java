package tetris;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Tetris extends JFrame {
	public static final int M = 20;
	public static final int N = 10;
	private ImageIcon mainBackGroundImage = new ImageIcon("img/background.png");
	private BufferedImage fieldImage;
	private Image imageMainBackGround = mainBackGroundImage.getImage();
	private BufferedImage combinedImage;
	private int[][] field = new int[M][N];
	private int[][] tiles = new int[][] {
			// [7][4]
			{ 1, 3, 5, 7 }, // I
			{ 2, 4, 5, 7 }, // Z
			{ 3, 5, 4, 6 }, // S
			{ 3, 5, 4, 7 }, // T
			{ 2, 3, 5, 7 }, // L
			{ 3, 5, 7, 6 }, // J
			{ 2, 3, 4, 5 } // O
	};

	public Tetris() {
		super("Tetris Game!");
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		try {
			fieldImage = ImageIO.read(new File("img/tiles.png"));

			List<BufferedImage> croppedImages = new ArrayList<>();

			croppedImages.add(TilesImage.cropImage(fieldImage, 0, 0, 17, 17));

			combinedImage = TilesImage.combineImages(croppedImages, 2, 2, 100, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MyPanel mainPanel = new MyPanel();
		setContentPane(mainPanel);

		setVisible(true);
	}

	class MyPanel extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			int panelWidth = getWidth();
			int panelHeight = getHeight();
			g.drawImage(imageMainBackGround, 0, 0, panelWidth, panelHeight, this);
			g.drawImage(combinedImage, 17, 17, null);
		}
	}
}
