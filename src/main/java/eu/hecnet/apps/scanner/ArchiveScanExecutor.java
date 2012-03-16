package eu.hecnet.apps.scanner;

import java.util.concurrent.Executor;

public class ArchiveScanExecutor implements Executor {

	public void execute(Runnable scanner) {
		new Thread(scanner).start();
	}

}