package eu.hecnet.apps.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;

import eu.hecnet.apps.query.form.QueryOptions;
import eu.hecnet.jena.vms.LexicalRdf;
import eu.hecnet.jena.vocabulary.FILE;

public class QueryExecutor {
	private static final Logger logger = LoggerFactory.getLogger(QueryExecutor.class);

	public QueryExecutor() {

	}

	public QueryResults executeQuery(QueryOptions queryOptions) {
		logger.info("Loading model: " + queryOptions.getModel());
		Model model = FileManager.get().loadModel(queryOptions.getModel());

		String queryString = constructQuery(queryOptions);
		logger.info("Executing query:\n" + queryString);

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		QueryResults queryResults = new QueryResults(queryString, queryOptions);

		String fileUri = "";
		String lexical = "";
		try {
			ResultSet results = qexec.execSelect();
			for (; results.hasNext();) {
				QuerySolution soln = results.nextSolution();
				RDFNode n = soln.get("uri");
				if (n.isResource()) {
					Resource r = (Resource) n;
					fileUri = r.getProperty(FILE.PATHNAME).getString();
					queryResults.addResult(fileUri, " ");
				}
			}
		} catch (Exception e) {
			logger.error("Error extracting results from query", e);
		} finally {
			qexec.close();
		}

		return queryResults;
	}

	public String constructQuery(QueryOptions queryOptions) {
		StringBuffer sb = new StringBuffer();
		sb.append("\n");
		sb.append("PREFIX file:	<http://hecnet.eu/file-rdf/1.0#>\n");
		sb.append("SELECT ?uri ?lexical\n");
		sb.append("WHERE {\n");
		sb.append("         ?uri file:CONTENT_TYPE \"" + queryOptions.getType() + "\" . \n");
		sb.append("         ?uri file:LEXICAL_USE \"" + LexicalRdf.getUri() + queryOptions.getLexical() + "\" . \n");
		sb.append("         ?url file:LEXICAL_USE ?lexical . \n");
		sb.append("}");

		String query = sb.toString();
		return query;
	}
}
