package br.com.cartoes.pagamentos.gateway.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "transacao")
public class TransacaoEntity {

    @Id
    private String nsu;

    private Double valor;

    private Double liquido;

    private String bandeira;

    private String modalidade;

    private Date horario;

    private LocalDate disponivel;

}
