package br.com.cartoes.pagamentos.service.data;

import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoService {

    private String nsu;

    private Double valor;

    private Double liquido;

    private String bandeira;

    private String modalidade;

    private Date horario;

    private LocalDate disponivel;

}