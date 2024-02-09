package uk.m0nom.apps.scanner;

import java.io.BufferedOutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.m0nom.apps.model.Archive;
import uk.m0nom.apps.model.FileType;
import uk.m0nom.apps.model.ScanResults;
import uk.m0nom.apps.scanner.file.DirectoryScanner;
import uk.m0nom.apps.scanner.file.FileScanner;
import uk.m0nom.apps.websocket.ScanWebSocket;
import uk.m0nom.jena.vms.LexicalRdf;
import uk.m0nom.jena.vocabulary.ARCHIVE;
import uk.m0nom.jena.vocabulary.DIRECTORY;
import uk.m0nom.jena.vocabulary.FILE;
import uk.m0nom.jena.vocabulary.PACKAGE;

public class ArchiveScanner implements Runnable {

	public static String URI_PREFIX = "http://slave.hecnet.eu";

	private final Logger logger = LogManager.getLogger(this.getClass().getName());
	private File archiveBaseFile;
	private ScanCommand command;

	private AtomicBoolean running;

	private boolean complete;
	private ScanResults results;

    private ScanContext context;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");

	// RDF stuff
	private Model model;

	private final FileScanner fileScanner = new FileScanner();
	private final DirectoryScanner directoryScanner = new DirectoryScanner();

	// private DirectoryScanner directoryScanner = new DirectoryScanner(model);

	public ArchiveScanner() {
		running = new AtomicBoolean(false);
	}

	public void initialise(ScanWebSocket socket) {
		Archive archive = new Archive(command.getArchiveName(), command.getArchiveBase(), FileType.REPOSITORY);

		results = new ScanResults(archive);
		results.setFilesRead(0L);
		results.setDirectoriesRead(0L);
		results.setSuccess(false);

		context = new ScanContext();
		context.setSocket(socket);

		model = ModelFactory.createDefaultModel();
		model.setNsPrefix("archive", ARCHIVE.getURI());
		model.setNsPrefix("package", PACKAGE.getURI());
		model.setNsPrefix("file", FILE.getURI());
		model.setNsPrefix("directory", DIRECTORY.getURI());
        LexicalRdf lexicalRdf = new LexicalRdf(model);
		model.setNsPrefix("lexical", LexicalRdf.getUri());

		fileScanner.setLexicalRdf(lexicalRdf);
		archiveBaseFile = new File(command.getArchiveBase());
		if (archiveBaseFile.exists() && archiveBaseFile.isDirectory()) {
			complete = false;
		}
	}

	private String persistModel(String name) {
		String now = sdf.format(new Date());
		String format = command.getFormat();
		String extension = "xml";
		if ("N-TRIPLE".equals(format) || "TURTLE".equals(format)) {
			extension = "txt";
		}
		String filename = String.format("model-%s-%s.%s", name, now, extension);
		if (command.isPersist()) {
			BufferedOutputStream out = null;
			try {
				out = new BufferedOutputStream(Files.newOutputStream(Paths.get(filename)));
				model.write(out, command.getFormat());
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return filename;
	}

	public void run() {
		running.set(true);
		Resource archiveBaseFileResource = model.createResource(ARCHIVE.getURI());
		archiveBaseFileResource.addProperty(ARCHIVE.NAME, command.getArchiveName());
		recursiveDescentFileScan(null, archiveBaseFileResource, archiveBaseFile);

		logger.info("Scan results:");
		logger.info(results);

		context.getSocket().sendMessage(
				new ScanFeedback(ScanFeedback.ScanStatus.PERSISTING, "", "", results.getDirectoriesRead(), results.getFilesRead()));

		String modelFilename = persistModel(archiveBaseFile.getName());

		// This is the scan complete or halted
		ScanFeedback feedback = new ScanFeedback(ScanFeedback.ScanStatus.COMPLETE, "", modelFilename, results.getDirectoriesRead(),
				results.getFilesRead());
		if (command.isUpdateIntervalOnCompletion()) {
			feedback.setPieData(results.getFileTypes());
		}
		context.getSocket().sendMessage(feedback);
		stop();
		complete = true;
	}

	private void recursiveDescentFileScan(File parent, Resource parentResource, File current) {
		// External termination
		if (!running.get()) {
			return;
		} else if (current == null) {
			// Recursion termination condition
			return;
		} else if (current.isDirectory()) {
			// Add this file as a resource property to the parent resource
			Resource directoryResource = processDirectory(current);
			parentResource.addProperty(DIRECTORY.FILE, directoryResource);

			for (File file : current.listFiles()) {
				// Descend into child files
				recursiveDescentFileScan(current, directoryResource, file);
			}
			String parentPath = (parent != null) ? parent.getAbsolutePath() : "";
			ScanFeedback feedback = new ScanFeedback(ScanFeedback.ScanStatus.DIRECTORY, parentPath, current.getAbsolutePath(),
					results.getDirectoriesRead(), results.getFilesRead());
			if (command.isUpdateIntervalOnDirectoryScan()) {
				feedback.setPieData(results.getFileTypes());
			}
			// Push update via websocket
			context.getSocket().sendMessage(feedback);
		} else if (current.isFile()) {
			// Add this file as a resource property to the parent resource
			parentResource.addProperty(FILE.FILE, processFile(current));
			ScanFeedback feedback = new ScanFeedback(ScanFeedback.ScanStatus.FILE, parent.getAbsolutePath(), current.getAbsolutePath(),
					results.getDirectoriesRead(), results.getFilesRead());
			if (command.isUpdateIntervalOnFileScan()) {
				feedback.setPieData(results.getFileTypes());
			}
			// Push update via websocket
			context.getSocket().sendMessage(feedback);
		}
	}

	private Resource processFile(File file) {
		Resource resource = model.createResource(URI_PREFIX + file.getAbsolutePath());
		String fileType = fileScanner.scanFileForProperties(resource, file);
		results.getFileTypes().bumpOrCreateFileType(fileType);
		results.bumpFilesRead();
		return resource;
	}

	private Resource processDirectory(File directory) {
		Resource resource = model.createResource(URI_PREFIX + directory.getAbsolutePath());
		resource.addProperty(DIRECTORY.BASENAME, directory.getName());
		resource.addProperty(DIRECTORY.PATHNAME, directory.getAbsolutePath());
		directoryScanner.scanFileForProperties(resource, directory);
		results.bumpDirectoriesRead();
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
