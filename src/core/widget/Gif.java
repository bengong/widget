package core.widget;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JPanel;

import com.sun.imageio.plugins.gif.GIFImageMetadata;

public class Gif extends JPanel {

	private Meta[] metas;
	private Map<Integer, Integer[]> gifBeanMap = new HashMap<Integer, Integer[]>();
	private int index = 0;
	private int delayFactor;
	private Timer timer;

	/**
	 * 
	 * @param gifFile
	 * @param delayFactor 显示gif每帧图片的时间因子
	 */
	public Gif(File gifFile, int delayFactor) {
		setDelayFactor(delayFactor);
		setGifFile(gifFile);
	}

	/**
	 * 设置Gif文件
	 * 
	 * @param file
	 */
	public void setGifFile(File file) {
		ImageReader reader = null;
		try {
			ImageInputStream input = ImageIO.createImageInputStream(file);
			Iterator<ImageReader> iter = ImageIO.getImageReadersByFormatName("gif");
			if (iter.hasNext()) {
				reader = iter.next();
			}
			reader.setInput(input, false);
			gifBeanMap.clear();
			metas = new Meta[reader.getNumImages(true)];
			GIFImageMetadata meta = null;
			for (int i = 0; i < metas.length; i++) {
				meta = (GIFImageMetadata) reader.getImageMetadata(i);
				metas[i] = new Meta();
				metas[i].image = reader.read(i);
				metas[i].x = meta.imageLeftPosition;
				metas[i].y = meta.imageTopPosition;
				metas[i].width = meta.imageWidth;
				metas[i].height = meta.imageHeight;
				metas[i].disposalMethod = meta.disposalMethod;
				metas[i].delayTime = meta.delayTime == 0 ? 1 : meta.delayTime;
			}
			for (int i = 1; i < metas.length; i++) {
				if (metas[i].disposalMethod == 2) {
					gifBeanMap.put(new Integer(i), new Integer[] { i });
					continue;
				}
				int firstIndex = getFirstIndex(i);
				List<Integer> list = new ArrayList<Integer>();
				for (int j = firstIndex; j <= i; j++) {
					list.add(j);
				}
				gifBeanMap.put(new Integer(i), list.toArray(new Integer[] {}));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		setTimer();
	}

	private synchronized void setTimer() {
		if (timer != null) {
			timer.cancel();
		}
		timer = new Timer("Loading Timer");
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				repaint();
				try {
					Thread.sleep(metas[index].delayTime * delayFactor);
				} catch (InterruptedException e) {
				}
				index++;
				if (index >= metas.length) {
					index = 0;
				}
			}

		}, 0, 1);

	}

	/**
	 * 设置时间因子
	 * 
	 * @param delayFactor
	 */
	public void setDelayFactor(int delayFactor) {
		this.delayFactor = delayFactor;
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.drawImage(metas[0].image, metas[0].x, metas[0].y, this);
		if (index > 0) {
			Integer[] array = gifBeanMap.get(index);
			for (Integer i : array) {
				g.drawImage(metas[i].image, metas[i].x, metas[i].y, this);
			}
		}
	}

	private int getFirstIndex(int index) {
		int tempIndex = index;
		while (tempIndex > 1) {
			if (tempIndex - 1 > 0 && metas[tempIndex - 1].disposalMethod == 2) {
				return index;
			}
			tempIndex--;
		}
		return tempIndex;
	}

	/**
	 * 用于保持gif每帧图片的信息
	 */
	public class Meta {
		public BufferedImage image;
		public int x;
		public int y;
		public int width;
		public int height;
		public int disposalMethod;
		public int delayTime;
	}
}