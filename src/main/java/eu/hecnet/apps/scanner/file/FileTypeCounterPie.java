package eu.hecnet.apps.scanner.file;


/**
 * Java version of the data required for the file type pie chart
 * 
 * @author msw
 * 
 */
public class FileTypeCounterPie {
	public String label;
	public String color[];
	public String value;

	public FileTypeCounterPie(String label, Color color, String value) {
		this.label = label;
		this.color = new String[3];
		this.color[0] = "" + color.getR();
		this.color[1] = "" + color.getG();
		this.color[2] = "" + color.getB();

		this.value = value;
	}
}
