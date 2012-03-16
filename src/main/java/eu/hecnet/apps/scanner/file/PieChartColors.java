package eu.hecnet.apps.scanner.file;

public class PieChartColors {

	private int MAX_COLORS = 50;
	private static int HALF_BRIGHTNESS = (255 + 255 + 255) / 2;

	public class Color {
		private int r, g, b;

		public Color(int r, int g, int b) {
			setR(r);
			setG(g);
			setB(b);
		}

		public int getR() {
			return r;
		}

		public void setR(int r) {
			this.r = r;
		}

		public int getG() {
			return g;
		}

		public void setG(int g) {
			this.g = g;
		}

		public int getB() {
			return b;
		}

		public void setB(int b) {
			this.b = b;
		}

		public int getBrightness() {
			return r + g + b;
		}
	}

	private Color[] colors;

	public PieChartColors() {
		colors = new Color[MAX_COLORS];
		for (int i = 0; i < MAX_COLORS; i++) {
			Color c = new Color(getRandomColor(), getRandomColor(),
					getRandomColor());
			while (c.getBrightness() > HALF_BRIGHTNESS) {
				c = new Color(getRandomColor(), getRandomColor(),
						getRandomColor());
			}
			colors[i] = c;
		}
	}

	private int getRandomColor() {
		double rnd = Math.random() * 255;
		int iRnd = (int) rnd;
		return iRnd;
	}

	public Color getColorArray(int i) {
		int val = i % MAX_COLORS;
		return colors[val];
	}

}
