package uk.m0nom.apps.scanner.file;

public class PieChartColors {

	private static final int MAX_COLORS = 50;

    private static final Color[] colors;

	static {
		colors = new Color[MAX_COLORS];
		for (int i = 0; i < MAX_COLORS; i++) {
			Color c = new Color(getRandomColor(), getRandomColor(), getRandomColor());
            int HALF_BRIGHTNESS = (255 + 255 + 255) / 2;
            while (c.getBrightness() > HALF_BRIGHTNESS) {
				c = new Color(getRandomColor(), getRandomColor(), getRandomColor());
			}
			colors[i] = c;
		}
	}

	private static int getRandomColor() {
		double rnd = Math.random() * 255;
        return (int) rnd;
	}

	public static Color getColorArray(int i) {
		int val = i % MAX_COLORS;
		return colors[val];
	}

}
