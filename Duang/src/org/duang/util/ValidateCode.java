package org.duang.util;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 验证码
 * ClassName: ValidateCode 
 * date: 2016年7月15日 下午3:11:19 
 * @author 白攀
 */
public class ValidateCode {
	public  String[] CHARS = {"0","1","2","3","4","5","6","7","8","9","A","B","c","d","e","f","g","h","j","k","L","m","n","p","q","r","S","T","U","V","W","X","Y","Z"}; 
	
	/*
	 * 随机4位数
	 * */
	public String createRandom(){
		String end = "";
		Random randoms = new Random(); 
		for(int i = 0; i<6; i++){
			end += CHARS[randoms.nextInt(CHARS.length)];
		}
		return end;
	}
	
	public BufferedImage creatImage(String img) {

		/* 在内存中创建图象 */
		int width = 270, height = 22;
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);

		/* 获取图形上下文 */
		Graphics g = image.getGraphics();

		/* 生成随机类 */
		Random random = new Random();

		/* 设定背景色 */
		g.setColor(Color.white);
		g.fillRect(0, 0, width, height);

		/* 设定字体 */
		g.setFont(new Font("Times New Roman", Font.BOLD, 25));

		/* 随机产生50条干扰线，使图象中的认证码不易被其它程序探测到 */
		g.setColor(Color.black);
		for (int i = 0; i < 50; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			// int xl = random.nextInt(1);
			// int yl = random.nextInt(1);

			g.drawLine(x, y, x, y);
		}

		/* 取随机产生的认证码(4位字母数字) */
		g.setColor(Color.blue);
		img = "  "+img.charAt(0) + "   " + img.charAt(1) + "   " + img.charAt(2) + "   " + img.charAt(3) +"   "+ img.charAt(4)+"   "+ img.charAt(5);
		g.drawString(img, 10, 17);
		/* 图象生效 */
		g.dispose();  
		return image;
	}
}
