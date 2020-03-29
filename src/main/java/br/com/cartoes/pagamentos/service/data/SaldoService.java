package br.com.cartoes.pagamentos.service.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaldoService {

    private Double disponivel;

    private Double receber;

}
