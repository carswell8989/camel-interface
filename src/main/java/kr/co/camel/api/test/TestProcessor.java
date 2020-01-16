package kr.co.camel.api.test;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.spi.annotations.Component;

import kr.co.camel.HttpOnline;

@HttpOnline
@Component("testProcessor")
public class TestProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		String result = "hello world";
		
		exchange.getIn().setBody(result);
		
	}

}
