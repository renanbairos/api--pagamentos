package br.com.cartoes.pagamentos.util.enums;

public enum BandeiraEnum {
    
    VISA,
    MASTERCARD,
    ELO,
    AMEX;

    public static boolean contains(String bandeira) {

        for (BandeiraEnum bandeiraEnum : BandeiraEnum.values()) {
            if (bandeiraEnum.name().equals(bandeira)) {
                return true;
            }
        }

        return false;
    }
}
