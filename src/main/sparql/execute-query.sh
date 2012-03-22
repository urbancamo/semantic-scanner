#!/bin/bash
#
# execute a query against the semantic-scanner model
#
#
SEMANTIC_SCANNER=/usr/local/projects/semantic-scanner
RDF_FILE=model.xml
#
sparql --query $1 --data $SEMANTIC_SCANNER/$RDF_FILE 
