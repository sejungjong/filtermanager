package aisl.ksensor.filtermanager.common.transfer.http.service.impl;

import aisl.ksensor.filtermanager.common.transfer.http.service.HttpRequestNonSSL;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.Map;

@Service
@Slf4j
public class HttpRequestNonSSLImpl implements HttpRequestNonSSL {

    private final WebClient webClient;

    public HttpRequestNonSSLImpl() {
        HttpClient httpClient = HttpClient.create()
                .secure(t -> t.sslContext(SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)));


        this.webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
    }

    public Mono<String> httpPostRequest(String uri, String body, Map<String, String> headers) {
        WebClient.RequestHeadersSpec<?> request = webClient.post()
                .uri(uri)
                .bodyValue(body);

        headers.forEach(request::header);

        return request.retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> httpGetRequest(String uri, Map<String, String> headers) {
        WebClient.RequestHeadersSpec<?> request = webClient.get()
                .uri(uri);

        headers.forEach(request::header);

        return request.retrieve()
                .bodyToMono(String.class);
    }
}
