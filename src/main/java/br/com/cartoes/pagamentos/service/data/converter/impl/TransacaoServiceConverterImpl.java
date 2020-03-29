package br.com.cartoes.pagamentos.service.data.converter.impl;

import br.com.cartoes.pagamentos.gateway.data.TransacaoEntity;
import br.com.cartoes.pagamentos.service.data.TransacaoService;
import br.com.cartoes.pagamentos.service.data.converter.TransacaoServiceConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TransacaoServiceConverterImpl implements TransacaoServiceConverter {

    @Override
    public TransacaoEntity toEntity(TransacaoService transacaoService) {
        return TransacaoEntity
                .builder()
                .nsu(transacaoService.getNsu())
                .valor(transacaoService.getValor())
                .liquido(transacaoService.getLiquido())
                .bandeira(transacaoService.getBandeira())
                .modalidade(transacaoService.getModalidade())
                .horario(transacaoService.getHorario())
                .disponivel(transacaoService.getDisponivel())
                .build();
    }

    @Override
    public List<TransacaoService> fromEntityToService(List<TransacaoEntity> transacaoEntityList) {

        List<TransacaoService> retorno = new ArrayList<>();

        transacaoEntityList.forEach((transacaoEntity) -> {
            retorno.add(
                    TransacaoService
                            .builder()
                            .nsu(transacaoEntity.getNsu())
                            .valor(transacaoEntity.getValor())
                            .liquido(transacaoEntity.getLiquido())
                            .bandeira(transacaoEntity.getBandeira())
                            .modalidade(transacaoEntity.getModalidade())
                            .horario(transacaoEntity.getHorario())
                            .disponivel(transacaoEntity.getDisponivel())
                            .build()
            );
        });

        return retorno;
    }

}
