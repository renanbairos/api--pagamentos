package br.com.cartoes.pagamentos.entrypoint.data.converter;

import br.com.cartoes.pagamentos.entrypoint.data.TransacaoEntrypoint;
import br.com.cartoes.pagamentos.service.data.TransacaoService;

public interface TransacaoEntrypointConverter {

    TransacaoService toService(TransacaoEntrypoint transacaoEntrypoint);

}
