package uk.m0nom.apps.scanner.file;

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
