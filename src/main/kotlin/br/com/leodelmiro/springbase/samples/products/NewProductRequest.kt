package br.com.leodelmiro.springbase.samples.products

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size

data class NewProductRequest(
    @field:NotBlank @field:Size(max = 60) val name: String,
    @field:NotBlank @field:Size(max = 2000) val description: String,
    @field:NotNull @field:Positive val price: Double,
)