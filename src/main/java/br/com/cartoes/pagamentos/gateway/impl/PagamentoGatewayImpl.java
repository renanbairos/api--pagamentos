package br.com.cartoes.pagamentos.gateway.impl;

import br.com.cartoes.pagamentos.gateway.PagamentoGateway;
import br.com.cartoes.pagamentos.gateway.data.TransacaoEntity;
import br.com.cartoes.pagamentos.gateway.data.repository.TransacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
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

    @Override
    public BigDecimal recuperarDisponivel(LocalDate dataAtual) {
        return transacaoRepository.findSomaLiquidoByDisponivelBefore(dataAtual);
    }

    @Override
    public BigDecimal recuperarReceber(LocalDate dataAtual) {
        return transacaoRepository.findSomaLiquidoByDisponivelAfter(dataAtual);
    }


}
