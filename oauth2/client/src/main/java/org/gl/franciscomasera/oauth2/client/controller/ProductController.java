package org.gl.franciscomasera.oauth2.client.controller;

import org.gl.franciscomasera.oauth2.client.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;
import static org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

@RestController
@RequestMapping("/client/product")
public class ProductController {

    @Autowired
    private WebClient webClient;

    @GetMapping("list")
    public List<Product> getProducts(
            @RegisteredOAuth2AuthorizedClient("products-reg-id")OAuth2AuthorizedClient authorizedClient) {
        return webClient
                .get()
                .uri("http://localhost:8090/resource/product")
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(
                        new ParameterizedTypeReference<List<Product>>() {
                        }
                )
                .block();
    }

}
