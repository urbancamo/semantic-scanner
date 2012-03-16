package eu.hecnet.apps.scanner.file;

import java.util.HashMap;
import java.util.Map;

public class FileTypesMap {
	private Map<String, FileTypeCounter> fileTypes;
	private PieChartColors colors;

	public FileTypesMap() {
		fileTypes = new HashMap<String, FileTypeCounter>();
		colors = new PieChartColors();
	}

	public void bumpOrCreateFileType(String fileType) {
		if (fileTypes.containsKey(fileType)) {
			FileTypeCounter counter = fileTypes.get(fileType);
			counter.bumpCount();
		} else {
			FileTypeCounter counter = new FileTypeCounter(fileType);
			counter.setCount(1L);
			fileTypes.put(fileType, counter);
		}
	}

	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[\n");
		for (FileTypeCounter counter : fileTypes.values()) {
			sb.append(" [ fileType = " + counter.getFileType() + ", count = "
					+ counter.getCount() + " ] \n");
		}
		return sb.toString();
	}

	public FileTypeCounterPie[] asPieArray() {
		FileTypeCounterPie pieData[] = new FileTypeCounterPie[fileTypes.size()];
		int i = 0;
		for (FileTypeCounter counter : fileTypes.values()) {
			FileTypeCounterPie pieDatum = new FileTypeCounterPie(
					counter.getFileType(), colors.getColorArray(i), ""
							+ counter.getCount());
			pieData[i++] = pieDatum;
		}
		return pieData;
	}
}
