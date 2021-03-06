package com.yeahmobi.yscheduler.variable;

import com.yeahmobi.yscheduler.common.variable.VariableContext;
import com.yeahmobi.yscheduler.model.Task;
import com.yeahmobi.yscheduler.model.TaskInstance;
import com.yeahmobi.yscheduler.model.Workflow;
import com.yeahmobi.yscheduler.model.WorkflowInstance;

public class DefaultVariableContext implements VariableContext {

    private Workflow         workflow;

    private WorkflowInstance workflowInstance;

    private TaskInstance     taskIntance;

    private Task             task;

    public DefaultVariableContext() {
    }

    public Workflow getWorkflow() {
        return this.workflow;
    }

    public void setWorkflow(Workflow workflow) {
        this.workflow = workflow;
    }

    public WorkflowInstance getWorkflowInstance() {
        return this.workflowInstance;
    }

    public void setWorkflowInstance(WorkflowInstance workflowInstance) {
        this.workflowInstance = workflowInstance;
    }

    public TaskInstance getTaskIntance() {
        return this.taskIntance;
    }

    public void setTaskIntance(TaskInstance taskIntance) {
        this.taskIntance = taskIntance;
    }

    public Task getTask() {
        return this.task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
