package uk.m0nom.apps.scanner.file;

import java.util.HashMap;
import java.util.Map;

public class FileTypesMap {
	private final Map<String, FileTypeCounter> fileTypes;

	public FileTypesMap() {
		fileTypes = new HashMap<String, FileTypeCounter>();
		new PieChartColors();
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
		StringBuilder sb = new StringBuilder();
		sb.append("[\n");
		for (FileTypeCounter counter : fileTypes.values()) {
			sb.append(" [ fileType = ").append(counter.getFileType()).append(", count = ").append(counter.getCount()).append(" ] \n");
		}
		return sb.toString();
	}

	public FileTypeCounterPie[] asPieArray() {
		FileTypeCounterPie[] pieData = new FileTypeCounterPie[fileTypes.size()];
		int i = 0;
		for (FileTypeCounter counter : fileTypes.values()) {
			FileTypeCounterPie pieDatum = new FileTypeCounterPie(counter.getFileType(), PieChartColors.getColorArray(i), ""
					+ counter.getCount());
			pieData[i++] = pieDatum;
		}
		return pieData;
	}
}
