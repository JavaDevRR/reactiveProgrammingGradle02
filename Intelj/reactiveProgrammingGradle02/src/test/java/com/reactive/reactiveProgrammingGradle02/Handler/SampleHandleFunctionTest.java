package com.reactive.reactiveProgrammingGradle02.Handler;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class SampleHandleFunctionTest {

    @Autowired
    public WebTestClient webTestClient;

    public WebTestClient getWebTestClient() {
        return webTestClient;
    }

    public void setWebTestClient(WebTestClient webTestClient) {
        this.webTestClient = webTestClient;
    }
    @Test
    public void  flux_approc1(){

        Flux<Integer> intFlux = webTestClient.get().uri("/functional/flux")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .returnResult(Integer.class).getResponseBody();

        StepVerifier.create(intFlux).expectNext(1,2,3,4).verifyComplete();

    }




}
