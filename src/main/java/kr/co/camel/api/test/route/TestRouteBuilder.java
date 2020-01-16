package kr.co.camel.api.test.route;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpCommonComponent;
import org.apache.camel.spi.annotations.Component;
import org.springframework.beans.factory.annotation.Autowired;

import kr.co.camel.CamelHelper;
import kr.co.camel.api.test.TestProcessor;




@Component("testRouteBuilder")
public class TestRouteBuilder extends RouteBuilder {

	
	public TestRouteBuilder() {

		System.out.println("테스트 라우터 빌더 시작");
	}
	
	
	@Autowired
	private TestProcessor testProcessor;
	
	
	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		
		ProducerTemplate camelTemplate = getContext().createProducerTemplate();

		CamelHelper.getInstance().setHttpCamelTemplate(camelTemplate);

		//endpoint 설정
		from("jetty:http://0.0.0.0:9999"+"/testApi")
		  .routeId("HTTP_TEST_API")
		  .process(testProcessor)
		;
	}

}
