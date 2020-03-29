package br.com.cartoes.pagamentos.service.data.converter;

import br.com.cartoes.pagamentos.gateway.data.TransacaoEntity;
import br.com.cartoes.pagamentos.service.data.TransacaoService;

import java.util.List;

public interface TransacaoServiceConverter {

    TransacaoEntity toEntity(TransacaoService transacaoService);

    List<TransacaoService> fromEntityToService(List<TransacaoEntity> transacaoEntity);

}
