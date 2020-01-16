package kr.co.camel;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.model.ProcessorDefinition;
import org.apache.camel.model.RouteDefinition;
import org.apache.camel.model.SplitDefinition;

/**
 * <pre>
 * kr.co.rentacar.integration.common.util
 *    |_ CamelHelper.java
 *
 * </pre>
 * @Author 	: kangnaru
 * @Date 	: 2017. 7. 12. 오전 10:42:47
 * @Comment	:
 * @Version :
 * @See     :
  *
 * <pre>
 *
 * 개정이력(Modification Information)
 *
 * 수정일      수정자   수정내용
 * --------------------------------------------------
 * 2018. 3. 23.    jdh8088
 *
 * </pre>
 */
public class CamelHelper {

	private static CamelHelper instance = null;

	// Interface API Camel Producer Template
	private static ProducerTemplate camelTmplRmi = null;
	// Interface API Camel Producer Template
	private static ProducerTemplate camelTmplHttp = null;
	// Interface API Camel Producer Template
	private static ProducerTemplate camelTmplNetty = null;
	// Interface API Camel Producer Template
	private static ProducerTemplate camelTmplJigsaw = null;
	// Interface Schedule Camel Producer Template
	private static ProducerTemplate camelTmplSchedule = null;

	/**
	 * INTERFACE Server의 HA 구성을 위해 Active-StandBy 간의 HandShaking을 통해 Active Mode를 결정한다.
	 */
	private static boolean INF_SERVER_MODE_ACTIVE = true;

	protected CamelHelper() {}

	static {
		if (instance == null) {
			instance = new CamelHelper();
			INF_SERVER_MODE_ACTIVE = true;
		}
	}

	public static CamelHelper getInstance() {
		return instance;
	}

	public ProducerTemplate getRmiCamelTemplate() {
		return camelTmplRmi;
	}
	public void setRmiCamelTemplate(ProducerTemplate template) {
		camelTmplRmi = template;
	}

	public ProducerTemplate getHttpCamelTemplate() {
		return camelTmplHttp;
	}
	public void setHttpCamelTemplate(ProducerTemplate template) {
		camelTmplHttp = template;
	}

	public ProducerTemplate getNettyCamelTemplate() { return camelTmplNetty; }
	public void setNettyCamelTemplate(ProducerTemplate camelTmplNetty) {
		CamelHelper.camelTmplNetty = camelTmplNetty;
	}

	public ProducerTemplate getCamelTmplJigsaw() {
		return camelTmplJigsaw;
	}
	public void setCamelTmplJigsaw(ProducerTemplate template) {
		camelTmplJigsaw = template;
	}

	public ProducerTemplate getIfScheduleCamelTemplate() {
		return camelTmplSchedule;
	}
	public void setIfScheduleCamelTemplate(ProducerTemplate template) {
		camelTmplSchedule = template;
	}

	// Interface server 상태값 얻어오기 및 셋팅 (active mode & standby mode)
	public boolean isINF_SERVER_MODE_ACTIVE() {
		return INF_SERVER_MODE_ACTIVE;
	}
	public void setINF_SERVER_MODE_ACTIVE() {
		INF_SERVER_MODE_ACTIVE = true;
	}
	public void setINF_SERVER_MODE_STANDBY() {
		INF_SERVER_MODE_ACTIVE = false;
	}

	/*
	 * public static ThreadPoolExecutor getThreadPoolExecutor(CamelContext
	 * camelContext){
	 * 
	 * SplitDefinition splitDefinition = getSplitter(camelContext);
	 * ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor)
	 * splitDefinition.getExecutorService(); if (threadPoolExecutor == null) {
	 * threadPoolExecutor =
	 * camelContext.getRegistry().lookupByNameAndType(splitDefinition.
	 * getExecutorServiceRef(), ThreadPoolExecutor.class); } return
	 * threadPoolExecutor; }
	 */

	/*
	 * public static SplitDefinition getSplitter(CamelContext camelContext) {
	 * SplitDefinition result = null; List<RouteDefinition> routeDefinitions =
	 * camelContext.getRouteDefinitions(); for (RouteDefinition routeType :
	 * routeDefinitions) { result = firstSplitterType(routeType.getOutputs()); if
	 * (result != null) { break; } } return result; }
	 */

    private static SplitDefinition firstSplitterType(List<ProcessorDefinition<?>> outputs) {
        SplitDefinition result = null;

        for (ProcessorDefinition<?> processorType : outputs) {
            if (processorType instanceof SplitDefinition) {
                result = (SplitDefinition) processorType;
            } else {
                result = firstSplitterType(processorType.getOutputs());
            }
            if (result != null) {
                break;
            }
        }
        return result;
    }
}
