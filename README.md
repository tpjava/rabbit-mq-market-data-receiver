# rabbit-mq-market-data-receiver

Receiver of market-data using rabbit-mq

To build from root folder run:

mvn clean install

Then to run from root run:

mvn exec:java -Dexec.args="ERICSSON"

You can add a list of one to many stocks separated by spaces which will update prices when published.

This will run a receiver which will listen on the ERICSSON channel for price updates, randomly generated by the market-data-publisher:

See here:

https://github.com/tpjava/rabbit-mq-market-data-generator
