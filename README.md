# microservice-logging-example
A project for exploring spring boot logging in the context of Microservice, PaaS deployments.

### Building
```
$ ./mvnw clean package
```

### Running
```
$ java -jar target/microservice-logging-0.0.1-SNAPSHOT.jar
```

### Example Endpoints

1. [http://localhost:8080/success]() - Notice the console output.  It is JSON-formatted, has Zipkin span and trace IDs.
2. [http://localhost:8080/exception]() - Notice the console output.  It is JSON-formatted, has Zipkin span and trace IDs, and the Java stacktrace.

JSON formatting courtesy of Logstash's Logback Encoder.  Zipkin support courtesy of Spring Cloud Sleuth.

### Functionality Demonstrated

This sample shows several things pertinent to implementing 12-factor app logging appropriate for cloud deployment.

- Logging to `sysout|syserr` (wrapped in performant async appender).  These streams can be piped to other destinations.  Furthermore, in a PaaS deployment, one can expect log aggregation of stdout/stderr.
- Log messages as events, formatted as JSON for effective processing by Splunk, ElasticSearch, Solr, etc.
- Use of correlation-ids for distributed system tracing (see Sleuth and Zipkin documentation for more details).
- Support for runtime log level changes.  This is via ootb Logback JMX integration, and spring boot actuator config.
- Support for changing log levels remotely via HTTP.  This is via spring boot actuator Jolokia configuration.  In a real system, security would be enabled.

#### Usage

1. Build and Run the app.  `./mvnw clean package; java -jar target/microservice-logging-0.0.1-SNAPSHOT.jar`
2. Load [http://localhost:8080/success]().  Notice in the console that "com.example" messages at `INFO` level are logged.
3. Change the log level for "com.example" with [http://localhost:8080/jolokia/exec/ch.qos.logback.classic:Name=default,Type=ch.qos.logback.classic.jmx.JMXConfigurator/setLoggerLevel/com.example/TRACE]().  Confirm with [http://localhost:8080/jolokia/exec/ch.qos.logback.classic:Name=default,Type=ch.qos.logback.classic.jmx.JMXConfigurator/getLoggerEffectiveLevel/com.example]().
4. Reload [http://localhost:8080/success]().  Notice in the console that "com.example" messages at `TRACE`, `DEBUG`, and `INFO` levels are all logged.

### Next Steps

- Use this as a test-harness for adding to logback's async appender functionality similar to java.util.logging's MemoryHandler to buffer lower-than-threshold-level events, such that in the case of an error, the prior X lower-level entries will also be flushed to the underlying appender.  
