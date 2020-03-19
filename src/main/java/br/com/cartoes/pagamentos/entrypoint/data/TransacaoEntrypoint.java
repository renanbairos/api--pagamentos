package br.com.cartoes.pagamentos.entrypoint.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoEntrypoint {


    @NotBlank(message = "O número sequencial é obrigatório.")
    private String nsu;

    @NotNull(message = "O valor bruto da transação é obrigatório.")
    @DecimalMin(value = "0.0", message = "O valor bruto da transação deve ser maior que 0.")
    private Double valor;

    @NotBlank(message = "A bandeira do cartão do consumidor é obrigatória.")
    private String bandeira;

    @NotBlank(message = "A modalidade do pagamento é obrigatória.")
    private String modalidade;

    @NotNull(message = "O horário da transação é obrigatório.")
    private Date horario;
}
