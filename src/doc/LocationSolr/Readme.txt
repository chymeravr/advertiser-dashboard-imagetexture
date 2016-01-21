This document is meant to resume the work done on location suggestor....

1. Current work done on solr-5.4.0 and on windows platform.
2. To start the solr server (embedded jetty)
	E:\<path-to-solrhome> bin\solr start
3. To create a core with a configuration
	E:\<path-to-solrhome> bin\solr create -c <core-name> -d <path to conf directory>
4. To post (add document/create index etc) for help:
	E:\<path-to-solrhome> java -jar example\exampledocs\post.jar --help
5. To create index of a json file
	E:\<path-to-solrhome> java -Dtype=application/json -Dc=<core-name> -jar example\exampledocs\post.jar <json filename with complete address>
6. To open query window (once) http://localhost:8983/solr/#/location19/query
7. In query window --- india for single term query... "United States" for phrase.... Indi~ for fuzzy query
7. To build suggester http://localhost:8983/solr/location19/suggest?sugges.build=true
8. To run suggester query http://localhost:8983/solr/location19/suggest?suggest.q=karnatak

9. The places to consider are solrconfig.xml and schema.xml. In schema.xml only change field according to the structure of json (and if required corresponding fieldtype). Remember to give right field to uniqueKey tag.
	In solrconfig.xml places to consider are suggest and spell. In suggest <str name="field">name</str> name is the field that will be suggested. <str name="weightField">price</str> is to be put at the same level as 'field' (just mentioned).
10. Currently couldn't figure out how to get other json data fields with the suggester result.