package com.gashanin.fedorwildfly.service.impl;

import com.gashanin.fedorwildfly.model.ValCurs;
import com.gashanin.fedorwildfly.model.Valute;
import com.gashanin.fedorwildfly.service.abstracts.ValCursApi;
import com.gashanin.fedorwildfly.service.abstracts.ValuteService;
import lombok.NoArgsConstructor;
import okhttp3.OkHttpClient;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import java.util.List;

@NoArgsConstructor
@Service
public class ValuteServiceImpl implements ValuteService {

    private final static String API_BASE_URL = "http://www.cbr.ru/scripts/XML_daily.asp/";

    @Override
    public Valute getValuteRate(String currency, String dateValCurs) {
        try {
            ValCurs valCurs = getValCurs(dateValCurs);
            return getValute(valCurs, currency);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    };

    @Override
    public ValCurs getValCurs(String dateValCurs) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .client(httpClient.build())
                .build();

        ValCursApi service = retrofit.create(ValCursApi.class);
        Call<ValCurs> callSync = service.getValCurs(dateValCurs);
        try {
            Response<ValCurs> response = callSync.execute();
            return response.body();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public Valute getValute(ValCurs valCurs, String currency){
        List<Valute> currencies = valCurs.getValute();
        return currencies.stream().filter(c->c.getCharCode().equals(currency)).findFirst().get();
    };
}
