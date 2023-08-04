package aisl.ksensor.filtermanager.common.transfer.http.service;

import reactor.core.publisher.Mono;

import java.util.Map;

public interface HttpRequestNonSSL {

    Mono<String> httpPostRequest(String uri, String body, Map<String, String> headers);

    Mono<String> httpGetRequest(String uri, Map<String, String> headers);

}
