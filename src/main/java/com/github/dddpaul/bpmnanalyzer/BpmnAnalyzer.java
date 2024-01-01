package com.github.dddpaul.bpmnanalyzer;

import io.camunda.zeebe.model.bpmn.Bpmn;
import io.camunda.zeebe.model.bpmn.BpmnModelInstance;
import io.camunda.zeebe.model.bpmn.instance.FlowElement;
import io.camunda.zeebe.model.bpmn.instance.ServiceTask;
import io.camunda.zeebe.model.bpmn.instance.zeebe.ZeebeTaskDefinition;
import org.camunda.bpm.model.xml.type.attribute.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

@Component
@ConditionalOnProperty(value = "app.analyzer.enabled", havingValue = "true")
public class BpmnAnalyzer {

    private static final Logger log = LoggerFactory.getLogger(BpmnAnalyzer.class);

    @Value("${app.analyzer.process-file}")
    private String processFileName;

    public void start() throws IOException {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream(processFileName)) {
            BpmnModelInstance instance = Bpmn.readModelFromStream(stream);
            Collection<ServiceTask> tasks = instance.getModelElementsByType(ServiceTask.class);
            List<?> types = tasks.stream()
                    .map(task -> task.getSingleExtensionElement(ZeebeTaskDefinition.class))
                    .map(ZeebeTaskDefinition::getType)
                    .toList();
            log.info("BPMN schema contains tasks: {}", types);
        }
    }
}
