package tetris;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import java.util.List;

public class TilesImage {
	
	/**
     * 이미지를 자릅니다.
     * @param src 원본 이미지
     * @param x 자르기 시작 X 좌표
     * @param y 자르기 시작 Y 좌표
     * @param width 자를 너비
     * @param height 자를 높이
     * @return 자른 이미지
     */
	
	public static BufferedImage cropImage(BufferedImage src, int x, int y, int width, int height) {
		BufferedImage dest = src.getSubimage(x, y, width, height);
		return dest;
	}
	
	/**
     * 여러 이미지를 합칩니다.
     * @param images 합칠 이미지 목록
     * @param rows 합칠 이미지의 행 수
     * @param cols 합칠 이미지의 열 수
     * @param width 각 이미지의 너비
     * @param height 각 이미지의 높이
     * @return 합쳐진 이미지
     */
	
	public static BufferedImage combineImages(List<BufferedImage> images, int rows, int cols, int width, int height) {
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