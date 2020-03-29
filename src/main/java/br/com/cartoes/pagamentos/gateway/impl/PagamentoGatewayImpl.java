package br.com.cartoes.pagamentos.gateway.impl;

import br.com.cartoes.pagamentos.gateway.PagamentoGateway;
import br.com.cartoes.pagamentos.gateway.data.repository.TransacaoRepository;
import br.com.cartoes.pagamentos.gateway.data.TransacaoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PagamentoGatewayImpl implements PagamentoGateway {

    private final TransacaoRepository transacaoRepository;

    @Override
    public void registrarTransacao(TransacaoEntity entity) {
        transacaoRepository.save(entity);
    }

    @Override
    public List<TransacaoEntity> recuperarTransacoes() {
        return transacaoRepository.findAll();
    }


}
