package br.com.cartoes.pagamentos.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModalidadeEnum {

    DEBITO("debito"),
    CREDITO("credito");

    private String modalidade;

    public static boolean contains(String modalidade) {

        for (ModalidadeEnum modalidadeEnum : ModalidadeEnum.values()) {
            if (modalidadeEnum.getModalidade().equals(modalidade)) {
                return true;
            }
        }

        return false;
    }
}
