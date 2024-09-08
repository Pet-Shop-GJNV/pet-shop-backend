package br.com.gjnv.petshop.controller;

import br.com.gjnv.petshop.util.Messages;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Tag(name = "Health Check", description = "Verifica se a API está funcionando corretamente")
public class HealthCheckController {
    @Operation(summary = "Verifica se a API está funcionando corretamente")
    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "API Ok!", content =
    @Content(schema = @Schema(example = "{\"statusCode\": 200, \"message\": \"API Ok!\"}")
    ))
    public ResponseEntity<Messages> healthTest() {
        return ResponseEntity.ok().body(new Messages(200, "API Ok!"));
    }
}
