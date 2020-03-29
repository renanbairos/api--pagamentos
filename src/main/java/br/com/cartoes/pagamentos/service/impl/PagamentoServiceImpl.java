package br.com.cartoes.pagamentos.service.impl;

import br.com.cartoes.pagamentos.gateway.PagamentoGateway;
import br.com.cartoes.pagamentos.service.PagamentoService;
import br.com.cartoes.pagamentos.service.data.SaldoService;
import br.com.cartoes.pagamentos.service.data.TransacaoService;
import br.com.cartoes.pagamentos.service.data.converter.TransacaoServiceConverter;
import br.com.cartoes.pagamentos.util.enums.ModalidadeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagamentoServiceImpl implements PagamentoService {

    private final TransacaoServiceConverter transacaoServiceConverter;
    private final PagamentoGateway pagamentoGateway;

    public void pagar(TransacaoService transacaoService) {

        transacaoService.setLiquido(this.recuperarValorLiquido(transacaoService.getValor(), transacaoService.getModalidade()));
        transacaoService.setDisponivel(this.recuperarDataRecebimento(transacaoService.getHorario(), transacaoService.getModalidade()));

        pagamentoGateway.registrarTransacao(transacaoServiceConverter.toEntity(transacaoService));

    }

    public List<TransacaoService> recuperarTransacoes() {

        return transacaoServiceConverter.fromEntityToService(pagamentoGateway.recuperarTransacoes());

    }

    public SaldoService recuperarSaldo(LocalDate dataAtual) {

        BigDecimal disponivel = pagamentoGateway.recuperarDisponivel(dataAtual);
        BigDecimal receber = pagamentoGateway.recuperarReceber(dataAtual);

        BigDecimal casoNulo = new BigDecimal(0.00).setScale(2);

        return SaldoService
                .builder()
                .disponivel(disponivel != null ? disponivel : casoNulo)
                .receber(receber != null ? receber : casoNulo)
                .build();

    }

    private Double recuperarValorLiquido(Double valor, String modalidade) {

        Double liquido = valor;

        if (ModalidadeEnum.DEBITO.getModalidade().equals(modalidade)) {

            liquido -= valor * .02;

        } else {

            liquido -= valor * .03;

        }

        liquido = new BigDecimal(liquido).setScale(2, RoundingMode.HALF_UP).doubleValue();

        return liquido;
    }

    private LocalDate recuperarDataRecebimento(Date horario, String modalidade) {

        Calendar diaReceberPagamentoCalendar = Calendar.getInstance();
        diaReceberPagamentoCalendar.setTime(horario);

        if(ModalidadeEnum.DEBITO.getModalidade().equals(modalidade)) {

            diaReceberPagamentoCalendar.add(Calendar.DATE, 1);

            if (diaReceberPagamentoCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {

                diaReceberPagamentoCalendar.add(Calendar.DATE, 2);

            } else if (diaReceberPagamentoCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                diaReceberPagamentoCalendar.add(Calendar.DATE, 1);

            }

        } else {

            diaReceberPagamentoCalendar.add(Calendar.DATE, 30);

            if (diaReceberPagamentoCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {

                diaReceberPagamentoCalendar.add(Calendar.DATE, 2);

            } else if (diaReceberPagamentoCalendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {

                diaReceberPagamentoCalendar.add(Calendar.DATE, 1);

            }

        }

        LocalDate diaReceberPagamento =
                LocalDateTime.ofInstant(diaReceberPagamentoCalendar.toInstant(), diaReceberPagamentoCalendar.getTimeZone().toZoneId())
                        .toLocalDate();

        return diaReceberPagamento;
    }

}
