package com.github.dddpaul.bpmnanalyzer;

import io.camunda.zeebe.model.bpmn.Bpmn;
import io.camunda.zeebe.model.bpmn.BpmnModelInstance;
import io.camunda.zeebe.model.bpmn.instance.BaseElement;
import io.camunda.zeebe.model.bpmn.instance.ServiceTask;
import io.camunda.zeebe.model.bpmn.instance.Process;
import io.camunda.zeebe.model.bpmn.instance.zeebe.ZeebeTaskDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import picocli.CommandLine;
import picocli.CommandLine.Option;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.List;

public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Option(names = {"-p", "--process"}, description = "BPMN process file name")
    private String processFileName = "";

    @Option(names = {"-h", "--help"}, description = "Display a help message", usageHelp = true)
    private boolean help = false;

    public void start() throws IOException {
        try (InputStream stream = !"".equals(processFileName) ? Files.newInputStream(Path.of(processFileName)) : System.in) {
            BpmnModelInstance instance = Bpmn.readModelFromStream(stream);
            Collection<Process> processes = instance.getDocumentElement().getChildElementsByType(Process.class);
            String processId = processes.stream()
                    .findFirst()
                    .map(BaseElement::getId)
                    .orElseThrow();
            Collection<ServiceTask> tasks = instance.getModelElementsByType(ServiceTask.class);
            List<?> types = tasks.stream()
                    .map(task -> task.getSingleExtensionElement(ZeebeTaskDefinition.class))
                    .map(ZeebeTaskDefinition::getType)
                    .toList();
            System.out.printf("process: %s, tasks: %s\n", processId, types);
        }
    }

    public static void main(String[] args) throws IOException {
        Application app = CommandLine.populateCommand(new Application(), args);
        if (app.help) {
            CommandLine.usage(app, System.err);
            return;
        }
        app.start();
    }
}
