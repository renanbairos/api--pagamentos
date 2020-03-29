package br.com.cartoes.pagamentos.entrypoint;

import br.com.cartoes.pagamentos.entrypoint.data.TransacaoEntrypoint;
import br.com.cartoes.pagamentos.entrypoint.data.converter.TransacaoEntrypointConverter;
import br.com.cartoes.pagamentos.service.PagamentoService;
import br.com.cartoes.pagamentos.service.data.SaldoService;
import br.com.cartoes.pagamentos.service.data.TransacaoService;
import br.com.cartoes.pagamentos.util.enums.BandeiraEnum;
import br.com.cartoes.pagamentos.util.enums.ModalidadeEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
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

    @GetMapping("/pagamentos")
    public @ResponseBody ResponseEntity<List<TransacaoService>> recuperarPagamentos() throws Exception {

        List<TransacaoService> transacaoServiceList = pagamentoService.recuperarTransacoes();

        ResponseEntity<List<TransacaoService>> response = new ResponseEntity<>(transacaoServiceList, HttpStatus.OK);

        return response;
    }

    @GetMapping("/saldo")
    public @ResponseBody ResponseEntity<SaldoService> recuperarSaldo() throws Exception {

        SaldoService saldoService = pagamentoService.recuperarSaldo(LocalDate.now());

        ResponseEntity<SaldoService> response = new ResponseEntity<>(saldoService, HttpStatus.OK);

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
