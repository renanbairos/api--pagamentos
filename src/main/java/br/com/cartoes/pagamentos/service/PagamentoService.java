package br.com.cartoes.pagamentos.service;

import br.com.cartoes.pagamentos.service.data.SaldoService;
import br.com.cartoes.pagamentos.service.data.TransacaoService;

import java.time.LocalDate;
import java.util.List;

public interface PagamentoService {

    void pagar(TransacaoService transacaoService);

    List<TransacaoService> recuperarTransacoes();

    SaldoService recuperarSaldo(LocalDate dataAtual);

}
