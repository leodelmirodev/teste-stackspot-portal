package br.com.leodelmiro.springbase.samples.products

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class NovoProdutoControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun `should register a new product`() {
        // scenario
        val request = NewProductRequest(
            "iPad Mini",
            "iPad Mini 6a",
            3500.99
        )

        // action and validation
        mockMvc.perform(
            post("/api/v1/products")
                .contentType(APPLICATION_JSON)
                .content(toJson(request))
                .header(ACCEPT_LANGUAGE, "en")
        )
            .andExpect(status().isCreated())

    }

    @Test
    fun `should not register a new product when invalid parameters`() {
        // scenario
        val request = NewProductRequest(
            "",
            "a".repeat(2001),
            -0.01
        );

        // action and validation
        mockMvc.perform(
            post("/api/v1/products")
                .contentType(APPLICATION_JSON)
                .content(toJson(request))
                .header(ACCEPT_LANGUAGE, "en")
        )
            .andExpect(status().isBadRequest())
        ;
    }

    /**
     * Converts an object payload to {@code String} in JSON format
     */
    fun toJson(payload: Any): String {
        return mapper.writeValueAsString(payload);
    }

}