package eu.hecnet.apps.scanner.file;

public class PieChartColors {

	private static int MAX_COLORS = 50;
	private static int HALF_BRIGHTNESS = (255 + 255 + 255) / 2;

	private static Color[] colors;

	static {
		colors = new Color[MAX_COLORS];
		for (int i = 0; i < MAX_COLORS; i++) {
			Color c = new Color(getRandomColor(), getRandomColor(), getRandomColor());
			while (c.getBrightness() > HALF_BRIGHTNESS) {
				c = new Color(getRandomColor(), getRandomColor(), getRandomColor());
			}
			colors[i] = c;
		}
	}

	private static int getRandomColor() {
		double rnd = Math.random() * 255;
		int iRnd = (int) rnd;
		return iRnd;
	}

	public static Color getColorArray(int i) {
		int val = i % MAX_COLORS;
		return colors[val];
	}

}
