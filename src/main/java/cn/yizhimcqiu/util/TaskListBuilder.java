package cn.yizhimcqiu.util;

import java.util.ArrayList;
import java.util.List;

public class TaskListBuilder {
    private final List<Task> tasks = new ArrayList<>();

    public TaskListBuilder add(Task task) {
        tasks.add(task);
        return this;
    }

    public void execute() {
        ExceptionHandler.safe(() -> tasks.forEach(Task::execute));
    }
}