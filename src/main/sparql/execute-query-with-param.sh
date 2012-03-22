#!/bin/bash
#
# execute a query against the semantic-scanner model
#
#
#set -x

SEMANTIC_SCANNER=/usr/local/projects/semantic-scanner
RDF_FILE=model.xml
#
# Inject parameter into query
QUERY=$1
PARAM=$2

if [ $# != 2 ]
then
	echo "Usage: execute-query-with-params.sh query.sparql param"
	exit 0;
fi

sed -e "s#\$PARAM#$PARAM#g" $QUERY > query.sparql
sparql --query query.sparql --data $SEMANTIC_SCANNER/$RDF_FILE 
