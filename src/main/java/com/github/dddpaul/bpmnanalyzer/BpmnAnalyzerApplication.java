package com.github.dddpaul.bpmnanalyzer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class BpmnAnalyzerApplication implements ApplicationRunner {

    @Autowired(required = false)
    private BpmnAnalyzer analyzer;

    @Value("${app.analyzer.enabled:true}")
    private boolean analyzerEnabled;

    public static void main(String[] args) {
        SpringApplication.run(BpmnAnalyzerApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws IOException {
        if (analyzerEnabled && analyzer != null) {
            analyzer.start();
        }
    }
}
