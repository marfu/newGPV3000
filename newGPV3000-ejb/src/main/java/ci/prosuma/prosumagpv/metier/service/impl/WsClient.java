/**
 * 
 */
package ci.prosuma.prosumagpv.metier.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ci.prosuma.prosumagpv.entity.GenCodeDTO;


/**
 * @author ayepi
 *
 */
public class WsClient {

	final static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
	static CloseableHttpClient wsClient;
	static HttpGet getRequest;
	static HttpPost postRequest;
	static CloseableHttpResponse getResponse;
	

	static Logger LOGGER = LoggerFactory.getLogger(WsClient.class);

	static {
		cm.setMaxTotal(200);
		cm.setDefaultMaxPerRoute(20);
		wsClient = HttpClients.custom().setConnectionManager(cm).build();
	}


	
	public static String sendDataToOperator(String url, List<GenCodeDTO> listImei) throws ClientProtocolException, IOException {
		LOGGER.info("###  URL  :  "+url);
		postRequest = new HttpPost(url);
		ObjectMapper mapper = new ObjectMapper();
		LOGGER.info("   IMEI LIST   " + listImei.toString());
		postRequest.setHeader("Accept", "application/json");
        StringEntity input = new StringEntity(mapper.writeValueAsString(listImei));
        input.setContentType("application/json");
        postRequest.setEntity(input);
        getResponse = wsClient.execute(postRequest);
        String rep;
		try (BufferedReader br = new BufferedReader(new InputStreamReader((getResponse.getEntity().getContent())))) {
			rep = br.readLine();
		}
		LOGGER.info("###  sendDataToOperator response  : "+ rep);
		return rep;
	}
	
}
