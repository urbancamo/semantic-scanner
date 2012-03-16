package eu.hecnet.apps.scanner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.Logger;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

import eu.hecnet.apps.model.Archive;
import eu.hecnet.apps.model.FileType;
import eu.hecnet.apps.model.ScanResults;
import eu.hecnet.apps.scanner.ScanFeedback.ScanStatus;
import eu.hecnet.apps.scanner.file.FileScanner;
import eu.hecnet.apps.websocket.FeedbackWebSocket;
import eu.hecnet.jena.vocabulary.FILE;

public class ArchiveScanner implements Runnable {

	public static String URI_PREFIX = "http://slave.hecnet.eu";
	private static boolean WRITE_RDF = true;

	private static Logger logger = Logger.getLogger(ArchiveScanner.class);
	private File archiveBaseFile;
	private ScanCommand command;

	private AtomicBoolean running;

	private boolean complete;
	private ScanResults results;

	private ScanContext context;

	// RDF stuff
	private Model model;

	private FileScanner fileScanner = new FileScanner();

	// private DirectoryScanner directoryScanner = new DirectoryScanner(model);

	public ArchiveScanner() {
		running = new AtomicBoolean(false);
	}

	public void initialise(FeedbackWebSocket socket) {
		Archive archive = new Archive(command.getArchiveName(),
				command.getArchiveBase(), FileType.REPOSITORY);

		results = new ScanResults(archive);
		results.setFilesRead(0L);
		results.setDirectoriesRead(0L);
		results.setSuccess(false);

		context = new ScanContext();
		context.setSocket(socket);

		model = ModelFactory.createDefaultModel();
		model.setNsPrefix("file", FILE.getURI());

		archiveBaseFile = new File(command.getArchiveBase());
		if (archiveBaseFile.exists() && archiveBaseFile.isDirectory()) {
			complete = false;
		}

		// RDF stuff
	}

	private void writeRdf() {
		if (WRITE_RDF) {
			BufferedOutputStream out = null;
			try {
				out = new BufferedOutputStream(
						new FileOutputStream("model.xml"));
				model.write(out, "RDF/XML-ABBREV");
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void run() {
		running.set(true);
		Resource archiveBaseFileResource = model.createResource(FILE.getURI());
		recursiveDescentFileScan(null, archiveBaseFileResource, archiveBaseFile);

		logger.info("Scan results:");
		logger.info(results);

		context.getSocket().sendMessage(
				new ScanFeedback(ScanStatus.PERSISTING, "", "", results
						.getDirectoriesRead(), results.getFilesRead()));

		writeRdf();

		// This is the scan complete or halted
		ScanFeedback feedback = new ScanFeedback(ScanStatus.COMPLETE, "", "",
				results.getDirectoriesRead(), results.getFilesRead());
		if (command.isUpdateIntervalOnCompletion()) {
			feedback.setPieData(results.getFileTypes());
		}
		context.getSocket().sendMessage(feedback);
		complete = true;
	}

	private void recursiveDescentFileScan(File parent, Resource parentResource,
			File current) {
		// External termination
		if (!running.get()) {
			return;
		} else if (current == null) {
			// Recursion termination condition
			return;
		} else if (current.isDirectory()) {
			// Add this file as a resource property to the parent resource
			Resource directoryResource = processFile(current);
			parentResource.addProperty(FILE.FILE, directoryResource);

			for (File file : current.listFiles()) {
				// Descend down into child files
				recursiveDescentFileScan(current, directoryResource, file);
			}
			results.bumpDirectoriesRead();
			String parentPath = (parent != null) ? parent.getAbsolutePath()
					: "";
			ScanFeedback feedback = new ScanFeedback(ScanStatus.DIRECTORY,
					parentPath, current.getAbsolutePath(),
					results.getDirectoriesRead(), results.getFilesRead());
			if (command.isUpdateIntervalOnDirectoryScan()) {
				feedback.setPieData(results.getFileTypes());
			}
			// Push update via websocket
			context.getSocket().sendMessage(feedback);
		} else if (current.isFile()) {
			// Add this file as a resource property to the parent resource
			parentResource.addProperty(FILE.FILE, processFile(current));
			ScanFeedback feedback = new ScanFeedback(ScanStatus.FILE,
					parent.getAbsolutePath(), current.getAbsolutePath(),
					results.getDirectoriesRead(), results.getFilesRead());
			if (command.isUpdateIntervalOnFileScan()) {
				feedback.setPieData(results.getFileTypes());
			}
			// Push update via websocket
			context.getSocket().sendMessage(feedback);
		}
	}

	private Resource processFile(File file) {
		Resource resource = model.createResource(URI_PREFIX
				+ file.getAbsolutePath());
		String fileType = fileScanner.scanFileForProperties(resource, file);
		results.getFileTypes().bumpOrCreateFileType(fileType);
		results.bumpFilesRead();
		return resource;
	}

	public ScanContext getContext() {
		return context;
	}

	public void setContext(ScanContext context) {
		this.context = context;
	}

	public boolean isScanComplete() {
		return complete;
	}

	public ScanResults getScanResults() {
		return results;
	}

	public void stop() {
		running.set(false);
	}

	public AtomicBoolean getRunning() {
		return running;
	}

	public void setRunning(AtomicBoolean running) {
		this.running = running;
	}

	public ScanCommand getCommand() {
		return command;
	}

	public void setCommand(ScanCommand command) {
		this.command = command;
	}

}
