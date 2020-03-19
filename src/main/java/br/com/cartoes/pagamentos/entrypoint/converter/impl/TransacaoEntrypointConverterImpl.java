package br.com.cartoes.pagamentos.entrypoint.converter.impl;

import br.com.cartoes.pagamentos.entrypoint.converter.TransacaoEntrypointConverter;
import br.com.cartoes.pagamentos.entrypoint.data.TransacaoEntrypoint;
import br.com.cartoes.pagamentos.service.data.TransacaoService;
import org.springframework.stereotype.Component;

@Component
public class TransacaoEntrypointConverterImpl implements TransacaoEntrypointConverter {

    @Override
    public TransacaoService toService(TransacaoEntrypoint transacaoEntrypoint) {
        return TransacaoService
                .builder()
                .nsu(transacaoEntrypoint.getNsu())
                .valor(transacaoEntrypoint.getValor())
                .bandeira(transacaoEntrypoint.getBandeira())
                .modalidade(transacaoEntrypoint.getModalidade())
                .horario(transacaoEntrypoint.getHorario())
                .build();
    }
}
