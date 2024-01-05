package com.example.hazelcast;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class HazelcastConfig {

    static final Properties PROPS = new Properties();

    @Bean
    public HazelcastInstance hazelcastServerInstance() {
        Config config = new Config();
        int puerto = Integer.parseInt(PROPS.getProperty("hazelcast.port", "5701"));
        config.getNetworkConfig().setPort(puerto);
        return Hazelcast.newHazelcastInstance(config);
    }
}

