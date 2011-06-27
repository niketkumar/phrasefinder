export CLASSPATH=$CLASSPATH:~/.m2/repository/org/apache/lucene/lucene-core/3.2.0/lucene-core-3.2.0.jar
export CLASSPATH=$CLASSPATH:target/phrasefinder-1.0.jar

java com.activesphere.poc.phrasefinder.Main src/main/resources/idioms.csv $1