package com.github.dddpaul.bpmnanalyzer.entities;

import java.util.List;

public record BpmnInfo(String processId, List<Task> tasks) {
}
