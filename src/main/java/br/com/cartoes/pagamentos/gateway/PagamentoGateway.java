package br.com.cartoes.pagamentos.gateway;

import br.com.cartoes.pagamentos.gateway.data.TransacaoEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface PagamentoGateway {

    void registrarTransacao(TransacaoEntity entity);

    List<TransacaoEntity> recuperarTransacoes();

    BigDecimal recuperarDisponivel(LocalDate dataAtual);

    BigDecimal recuperarReceber(LocalDate dataAtual);

}
