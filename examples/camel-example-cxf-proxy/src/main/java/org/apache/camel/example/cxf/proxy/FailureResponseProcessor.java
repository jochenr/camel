/**
 *
 */
package org.apache.camel.example.cxf.proxy;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;


import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.apache.camel.component.cxf.CxfPayload;
import org.apache.camel.component.cxf.converter.CachedCxfPayload;

/**
 * @author Jochen Riedlinger
 *
 */
public class FailureResponseProcessor implements Processor {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@Override
	public void process(Exchange exchange) throws Exception {

		Exception ex = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Exception.class);

		Message inMessage = exchange.getIn();
		Object bodyObject = inMessage.getBody();

		if (bodyObject instanceof CachedCxfPayload) {
			CachedCxfPayload cachedCxfPayload = (CachedCxfPayload) bodyObject;
			List bodySources = cachedCxfPayload.getBodySources();

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

			cachedCxfPayload.writeTo(byteArrayOutputStream);

			byte[] bodyAsBytes = byteArrayOutputStream.toByteArray();

			inMessage.setBody(bodyAsBytes);

			String stringExtractedBody = new String(bodyAsBytes, StandardCharsets.UTF_8.name());
			System.out.println("This was the original BODY that caused the fault:\n" + stringExtractedBody);
		}

//		if (bodyObject instanceof CxfPayload) {
//
//			String strBody = inMessage.getBody(String.class);
//			System.out.println(strBody);
//
//		}
	}

}
