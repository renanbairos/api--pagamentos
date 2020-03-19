package br.com.cartoes.pagamentos.service;

import br.com.cartoes.pagamentos.service.data.TransacaoService;

public interface PagamentoService {

    void pagar(TransacaoService transacaoService);

}
