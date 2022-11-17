package com.aninfo.service;

import com.aninfo.model.Recurso;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Service
public class RecursoService {

    public Recurso findById(Long legajo) {
        for (Recurso recurso : getRecursos()) {
            if (Objects.equals(recurso.getId(), legajo)) return recurso;
        }

        return null;
    }

    public List<Recurso> getRecursos() {
        ObjectMapper mapper = new ObjectMapper();

        TypeReference<List<Recurso>> ref = new TypeReference<>() {};

        URL url;
        try {
            url = new URL("https://anypoint.mulesoft.com/mocking/api/v1/sources/exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/recursos-psa/1.0.0/m/api/recursos");
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            return mapper.readValue(url, ref);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
