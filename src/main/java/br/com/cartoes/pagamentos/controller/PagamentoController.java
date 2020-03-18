package br.com.cartoes.pagamentos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PagamentoController {

    @PostMapping("/debito")
    public ResponseEntity pagarComDebito() {

        return null;
    }
}
