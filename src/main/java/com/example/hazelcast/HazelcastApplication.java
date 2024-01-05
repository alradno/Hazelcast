package com.example.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.*;

@SpringBootApplication
public class HazelcastApplication {

    static HazelcastInstance HAZELCAST;
    static final String BSSMAP = "bss-map";
    static final String CAUSAINTERNA_MAP = "causainterna-map";
    static final String RANAP_MAP = "ranap-map";
    static final String NRN_MAP = "nrn-map";
    static final String OPERADORES_MAP = "operadores-map";
    static final String TEST_MAP = "test-map";
    static final Properties PROPS = new Properties();

    public static void main(String[] args) throws IOException {
        SpringApplication.run(HazelcastApplication.class, args);

        // Load properties from file located in args[0]
        try (InputStream input = new FileInputStream(args[0])) {
            PROPS.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        HAZELCAST = new HazelcastConfig().hazelcastServerInstance();

        IMap<String, List<String>> bssMap = HAZELCAST.getMap(BSSMAP);
        IMap<String, List<String>> causainternaMap = HAZELCAST.getMap(CAUSAINTERNA_MAP);
        IMap<String, List<String>> ranapMap = HAZELCAST.getMap(RANAP_MAP);
        IMap<String, List<String>> nrnMap = HAZELCAST.getMap(NRN_MAP);
        IMap<String, List<String>> operadoresMap = HAZELCAST.getMap(OPERADORES_MAP);
        IMap<String, List<String>> testMap = HAZELCAST.getMap(TEST_MAP);

        List<IMap<String, List<String>>> maps = List.of(bssMap, causainternaMap, ranapMap, nrnMap, operadoresMap, testMap);

        //Carga el csv en el mapa hazelcast
        loadCsv(maps);

    }

    public static void loadCsv(List<IMap<String, List<String>>> maps) throws IOException {

        String bssmap_location = PROPS.getProperty("bssmap_file_location");
        String causainterna_location = PROPS.getProperty("causainterna_file_location");
        String ranap_location = PROPS.getProperty("ranap_file_location");
        String nrn_location = PROPS.getProperty("nrn_file_location");
        String operadores_location = PROPS.getProperty("operadores_file_location");
        String test_location = PROPS.getProperty("test_file_location");

        List<String> locations = List.of(bssmap_location, causainterna_location, ranap_location,
                                         nrn_location, operadores_location, test_location);

        int key_column = Integer.parseInt(PROPS.getProperty("key_column"));

        String line;
        int indice = 0;
        for (String location : locations){
            BufferedReader br = new BufferedReader(new FileReader(location));
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(";");
                List<String> values = List.of(columns);
                String key = columns[key_column];
                maps.get(indice).put(key, values);
            }
            infoLoadedMaps(indice);
            indice++;
            br.close();
        }
        System.out.println("Mapas cargados en hazelcast");
    }

    public static void printHazelcastMap(IMap<String, List<List<String>>> csvMap){
        for (List<List<String>> value : csvMap.values()) {
            System.out.println(value);
        }
    }

    public static void deleteQuotationMarks(String csv_comillas, String csv_sin_comillas) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(csv_comillas));
        String line;
        try (FileWriter fw = new FileWriter(csv_sin_comillas)) {
            while ((line = br.readLine()) != null) {
                line = line.replaceAll("\"", "");
                fw.write(line + "\n");
                System.out.println(line);
            }
        }
    }
    public static void infoLoadedMaps(int indice){
        switch(indice){
            case 0:
                System.out.println(BSSMAP +" cargado");
                break;
            case 1:
                System.out.println(CAUSAINTERNA_MAP +" cargado");
                break;
            case 2:
                System.out.println(RANAP_MAP +" cargado");
                break;
            case 3:
                System.out.println(NRN_MAP +" cargado");
                break;
            case 4:
                System.out.println(OPERADORES_MAP +" cargado");
                break;
            case 5:
                System.out.println(TEST_MAP +" cargado");
                break;
        }

    }

}
