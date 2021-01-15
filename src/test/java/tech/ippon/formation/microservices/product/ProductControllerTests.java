package tech.ippon.formation.microservices.product;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.matching;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import tech.ippon.formation.microservices.product.domain.Product;
import tech.ippon.formation.microservices.product.domain.Stock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = {WireMockInitializer.class})
@AutoConfigureMockMvc
class ProductControllerTests {

  @Autowired private WireMockServer wireMockServer;

  @Autowired private MockMvc mockMvc;

  @Test
  void testGetAllTodosShouldReturnDataFromClient() throws Exception {
    ObjectMapper objectMapper = new ObjectMapper();
    this.wireMockServer.stubFor(
        WireMock.get(urlPathEqualTo("/api/stocks"))
            .withQueryParam("productId", matching(".*"))
            .willReturn(
                aResponse()
                    .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                    .withBody(objectMapper.writeValueAsString(new Stock().setStock(1)))));

    MvcResult mvcResult =
        this.mockMvc.perform(get("/api/products")).andExpect(status().isOk()).andReturn();

    List<Product> products =
        objectMapper.readValue(
            mvcResult.getResponse().getContentAsString(), new TypeReference<>() {});

    assertThat(products.get(0)).isNotNull();
    assertThat(products.get(0).getStock()).isEqualTo(1);
  }
}
