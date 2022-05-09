package com.rensilver.msrelatoriocompras.service.validation;

import com.rensilver.msrelatoriocompras.entity.Compra;

import java.util.List;

public final class CompraValidation {

    public static boolean verificarSeAnoDaCompraExiste(List<Compra> compras, String ano) {
        boolean isAno = false;
        for (Compra compra : compras) {
            isAno = compra.getData().getYear() == Integer.parseInt(ano);
            if (isAno) {
                break;
            }
        }
        return isAno;
    }
}
