package br.com.cartoes.pagamentos.entrypoint;

import br.com.cartoes.pagamentos.entrypoint.converter.TransacaoEntrypointConverter;
import br.com.cartoes.pagamentos.entrypoint.data.TransacaoEntrypoint;
import br.com.cartoes.pagamentos.service.PagamentoService;
import br.com.cartoes.pagamentos.util.enums.BandeiraEnum;
import br.com.cartoes.pagamentos.util.enums.ModalidadeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;
    private final TransacaoEntrypointConverter transacaoEntrypointConverter;

    @PostMapping("/pagamento")
    public ResponseEntity pagar(@RequestBody @Valid TransacaoEntrypoint transacaoEntrypoint) throws Exception {

        ResponseEntity response = new ResponseEntity(HttpStatus.OK);

        if(!this.validarRequisicao(transacaoEntrypoint)) {
            response = new ResponseEntity(erros, HttpStatus.BAD_REQUEST);
        }

        this.pagamentoService.pagar(
                this.transacaoEntrypointConverter.toService(transacaoEntrypoint));

        return response;
    }

    private List<String> erros;
    private boolean validarRequisicao(TransacaoEntrypoint transacaoEntrypoint) {

        this.erros = new ArrayList<>();

        boolean retorno = true;

        if(!BandeiraEnum.contains(transacaoEntrypoint.getBandeira())) {
            this.erros.add("Bandeira não permitida. Bandeiras permitidas: VISA, MASTERCARD, ELO, AMEX.");
        }

        if(!ModalidadeEnum.contains(transacaoEntrypoint.getModalidade())) {
            this.erros.add("Modalidade inexistente. Modalidades permitidas: credito, debito");
        }

        if (!this.erros.isEmpty()) {
            retorno = false;
        }

        return retorno;

    }
}
