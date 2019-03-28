# CXF WebService Proxy example

### Introduction

An example which proxies a real web service by a Camel application using the camel-cxf component

### Build
You will need to compile this example first:

	mvn compile

### Run

To run the example type:

	mvn camel:run -Dmaven.test.skip=true

The proxied webservice is located at

	http://localhost:${proxy.port}/camel-example-cxf-proxy/webservices/incident

<http://localhost:9080/camel-example-cxf-proxy/webservices/incident>

The real webservice is located at

	http://localhost:${real.port}/real-webservice

<http://localhost:9081/real-webservice>

The webservice WSDL is exposed at:

	http://localhost:${proxy.port}/camel-example-cxf-proxy/webservices/incident?wsdl

<http://localhost:9080/camel-example-cxf-proxy/webservices/incident?wsdl>

Because we use dynamic port numbers, you have to check the console to get the used one.
To stop the example hit <kbd>ctrl</kbd>+<kbd>c</kbd>

To make a SOAP call open soapUI or another SOAP query tool and create a new
project w/WSDL of <http://localhost:${proxy.port}/camel-example-cxf-proxy/webservices/incident?wsdl>.
Then make SOAP requests of this format:

	<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:rep="http://reportincident.example.camel.apache.org">
	<soapenv:Header/>
	<soapenv:Body>
		<rep:inputReportIncident>
			<incidentId>12345</incidentId>
			<incidentDate>2019-03-22</incidentDate>
			<givenName>Me</givenName>
			<familyName>Myself</familyName>
			<summary>problem with xsi namesopace</summary>
			<details>ns not copied, if streaming allowed</details>
			<email>nobody@nowhere.com</email>
			<phone>+49 12 23456</phone>
			<homeAddress xsi:type="rep:USAddressType" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance">
				<Line1>234 Lancaseter Av</Line1>
				<Line2>SmallsVille</Line2>
				<State>Florida</State>
				<Zipcode>34543</Zipcode>
			</homeAddress>
		</rep:inputReportIncident>
	</soapenv:Body>
</soapenv:Envelope>

### Configuration

You can change `${proxy.port}` and `${real.port}` via configuration file `src/main/resources/incident.properties`


### Forum, Help, etc

If you hit an problems please let us know on the Camel Forums
  <http://camel.apache.org/discussion-forums.html>

Please help us make Apache Camel better - we appreciate any feedback you may
have.  Enjoy!


The Camel riders!
