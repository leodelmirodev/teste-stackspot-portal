package br.com.leodelmiro.springbase.samples.products

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class NewProductController {

    @PostMapping("/api/v1/products")
    fun register(@RequestBody @Valid request: NewProductRequest): ResponseEntity<Any> {

        // fake business logic

        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

}