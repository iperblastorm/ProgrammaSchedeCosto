package resourceData;


import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class Resource {	
	
	public static Image immaginePlus() {
		try (InputStream is = Resource.class.getResourceAsStream("/resourceData/plusIcon.png");){
			BufferedImage bi = ImageIO.read(is);
			BufferedImage result = new BufferedImage(19, 19, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = result.createGraphics();
			g.drawImage(bi, 0, 0, 19, 19, null);
			g.dispose();
			return result;
			} catch (FileNotFoundException e) {
				System.err.println("File non trovato");
				return null;
			} catch (IOException e) {
				System.err.println("Problema file generico" + e);
				return null;
			}
	}
	
	public static Image immagineMinus() {
		try (InputStream is = Resource.class.getResourceAsStream("/resourceData/minusIcon.png");){
			BufferedImage bi = ImageIO.read(is);
			BufferedImage result = new BufferedImage(19, 19, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = result.createGraphics();
			g.drawImage(bi, 0, 0, 19, 19, null);
			g.dispose();
			return result;
			} catch (FileNotFoundException e) {
				System.err.println("File non trovato");
				return null;
			} catch (IOException e) {
				System.err.println("Problema file generico" + e);
				return null;
		}
	}


}
