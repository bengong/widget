package core.widget;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 * Loads an image from a file/resource Hopefully it works regardless of the packaging
 */
public class Images {

	private static Map<String, ImageIcon> imagesMap = new HashMap<String, ImageIcon>();

	private static String workspace = System.getProperty("workspace", new File("").getAbsolutePath().replaceAll("\\\\", "/"));
	
	public static ImageIcon imageIcon(String name) {
		name = name.replaceAll("\\\\", "/");
		if (imagesMap.containsKey(name)) {
			return imagesMap.get(name);
		}

		ImageIcon image = null;

		try {
			image = new ImageIcon(ImageIO.read(new File(workspace + name)));
		} catch (Exception e) {
			try {
				image = new ImageIcon(ImageIO.read(new File(name)));
			} catch (Exception e1) {
				// 忽略
				// if(!name.startsWith("/")) name = "/"+name;
				URL imageURL = Thread.currentThread().getContextClassLoader().getResource(name);
				if (imageURL != null) {
					try {
						image = new ImageIcon(ImageIO.read(imageURL));
					} catch (Exception e2) {
						
					}
				}
			}
		}

		if (image != null) {
			imagesMap.put(name, image);
			return image;
		}

		// 忽略
		throw new RuntimeException("在如下路径均未找到图片: ["+(workspace+name)+";"+name+"]");
	}

	public static Image image(String name) {
		Image image = null;
		try {
			image = imageIcon(name).getImage();
		} catch (Exception e) {
			System.out.println("图标 " + name + "[缺]...");
			return null;
		}
		return image;
	}

	public static void clear() {
		imagesMap.clear();
	}
	
	public static void main(String[] args) {
		ImageIcon image = Images.imageIcon("images/error.gif");
		System.out.print(image != null);
	}
}