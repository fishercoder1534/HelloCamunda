package com.fishercoder.camunda;

import org.camunda.bpm.engine.test.junit5.ProcessEngineExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.test.Deployment;

@ExtendWith(ProcessEngineExtension.class)
public class ChargeCardWorkerTest {
    // provide a field where the process engine gets injected
    // This is injected via camunda.cfg.xml which isn't fully working yet, here just serves as a half-working example
    // You could use a Java class annotated with @Configuration to inject this bean as well
    ProcessEngine processEngine;

    @Test
    @Deployment
    public void extensionUsageExample() {
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByKey("extensionUsage");

        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().singleResult();
//        assertThat(task.getName()).isEqualTo("My Task");
//
//        taskService.complete(task.getId());
//        assertThat(runtimeService.createProcessInstanceQuery().count()).isEqualTo(0);
    }
}
