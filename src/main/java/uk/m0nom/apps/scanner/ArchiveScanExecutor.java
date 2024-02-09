package uk.m0nom.apps.scanner;

import javax.validation.constraints.NotNull;
import java.util.concurrent.Executor;

public class ArchiveScanExecutor implements Executor {

	public void execute(@NotNull Runnable scanner) {
		new Thread(scanner).start();
	}

}