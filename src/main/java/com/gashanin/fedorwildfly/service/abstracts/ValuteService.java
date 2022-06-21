package com.gashanin.fedorwildfly.service.abstracts;

import com.gashanin.fedorwildfly.model.ValCurs;
import com.gashanin.fedorwildfly.model.Valute;

public interface ValuteService<T> {

    ValCurs getValCurs(String dateValCurs);

    Valute getValute(ValCurs valCurs, String currency);

    Valute getValuteRate(String currency, String dateValCurs);

}
