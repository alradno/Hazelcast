package com.example.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.example.hazelcast.HazelcastApplication.BSSMAP;
import static com.example.hazelcast.HazelcastApplication.CAUSAINTERNA_MAP;
import static com.example.hazelcast.HazelcastApplication.RANAP_MAP;
import static com.example.hazelcast.HazelcastApplication.NRN_MAP;
import static com.example.hazelcast.HazelcastApplication.OPERADORES_MAP;
import static com.example.hazelcast.HazelcastApplication.TEST_MAP;

@Service
public class ServiceHttp {

    private final IMap<String, List<String>> bssmap;
    private final IMap<String, List<String>> causainternaMap;
    private final IMap<String, List<String>> ranapMap;
    private final IMap<String, List<String>> nrnMap;
    private final IMap<String, List<String>> operadoresMap;
    private final IMap<String, List<String>> testMap;

    @Autowired
    public ServiceHttp(HazelcastInstance hazelcastInstance) {
        bssmap = hazelcastInstance.getMap(BSSMAP);
        causainternaMap = hazelcastInstance.getMap(CAUSAINTERNA_MAP);
        ranapMap = hazelcastInstance.getMap(RANAP_MAP);
        nrnMap = hazelcastInstance.getMap(NRN_MAP);
        operadoresMap = hazelcastInstance.getMap(OPERADORES_MAP);
        testMap = hazelcastInstance.getMap(TEST_MAP);
    }

    public List<String> searchByBssMapService(String key) {
        List<String> values = bssmap.get(key);
        if (values == null) {
            //System.out.println("Clave no encontrada");
            values = List.of("NULL");
            return values;
        }
        else {
            //System.out.println("Clave encontrada:");
            //System.out.println(values);
            return values;
        }
    }

    public List<String> searchByCausaInternaService(String key) {
        List<String> values = causainternaMap.get(key);
        if (values == null) {
            //System.out.println("Clave no encontrada");
            values = List.of("NULL");
            return values;
        }
        else {
            //System.out.println("Clave encontrada:");
            //System.out.println(values);
            return values;
        }
    }

    public List<String> searchByRanapService(String key) {
        List<String> values = ranapMap.get(key);
        if (values == null) {
            //System.out.println("Clave no encontrada");
            values = List.of("NULL");
            return values;
        }
        else {
            //System.out.println("Clave encontrada:");
            //System.out.println(values);
            return values;
        }
    }

    public List<String> searchByNrnService(String key) {
        List<String> values = nrnMap.get(key);
        if (values == null) {
            //System.out.println("Clave no encontrada");
            values = List.of("NULL");
            return values;
        }
        else {
            //System.out.println("Clave encontrada:");
            //System.out.println(values);
            return values;
        }
    }

    public List<String> searchByOperadoresService(String key) {
        List<String> values = operadoresMap.get(key);
        if (values == null) {
            //System.out.println("Clave no encontrada");
            //values = List.of("NULL");
            return values;
        }
        else {
            //System.out.println("Clave encontrada:");
            //System.out.println(values);
            return values;
        }
    }

    public List<String> searchByTestService(String key) {
        List<String> values = testMap.get(key);
        if (values == null) {
            //System.out.println("Clave no encontrada");
            values = List.of("NULL");
            return values;
        }
        else {
            //System.out.println("Clave encontrada:");
            //System.out.println(values);
            return values;
        }
    }

}
