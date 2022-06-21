package com.gashanin.fedorwildfly.controller;

import com.gashanin.fedorwildfly.model.Valute;
import com.gashanin.fedorwildfly.service.impl.ValuteServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/valcurs")
public class ValCursController {

    private final ValuteServiceImpl valuteService;

    @GetMapping("/get/{currency}")
    public ResponseEntity<Valute> getCurs(@PathVariable("currency") String currency,
                                                @RequestParam( value="date", required = false) String year) {
        Valute valute = valuteService.getValuteRate(currency, year);
        return ResponseEntity.ok().body(valute);
    }
}
