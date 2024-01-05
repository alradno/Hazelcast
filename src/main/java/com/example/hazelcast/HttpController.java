package com.example.hazelcast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class HttpController {
    private final ServiceHttp service;

    @Autowired
    public HttpController(ServiceHttp service) {
        this.service = service;
    }

    @GetMapping("/search/bssMap/{key}")
    public List<String> searchByBssMap(@PathVariable String key) {
        return service.searchByBssMapService(key);
    }

    @GetMapping("/search/causainterna/{key}")
    public List<String> searchByCausaInterna(@PathVariable String key) {
        return service.searchByCausaInternaService(key);
    }

    @GetMapping("/search/ranap/{key}")
    public List<String> searchByRanap(@PathVariable String key) {
        return service.searchByRanapService(key);
    }

    @GetMapping("/search/nrn/{key}")
    public List<String> searchByNrn(@PathVariable String key) {
        return service.searchByNrnService(key);
    }

    @GetMapping("/search/operadores/{key}")
    public List<String> searchByOperadores(@PathVariable String key) {
        return service.searchByOperadoresService(key);
    }

    @GetMapping("/search/test/{key}")
    public List<String> searchByTest(@PathVariable String key) {
        return service.searchByTestService(key);
    }

}

