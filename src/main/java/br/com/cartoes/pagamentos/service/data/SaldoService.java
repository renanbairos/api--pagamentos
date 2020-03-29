package br.com.cartoes.pagamentos.service.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaldoService {

    private BigDecimal disponivel;

    private BigDecimal receber;

}
