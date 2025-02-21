package cn.yizhimcqiu.util;

import org.apache.logging.log4j.Logger;

public class ExceptionHandler {
    private static final Logger LOGGER = ILogManager.getLogger();
    public static boolean crashOnThrow = true;
    public static final TaskListBuilder CRASH_TASKS = new TaskListBuilder();

    /**
     * Use {@code safe(() -> {your code here})} to deal with possible crashes in the task.<br>
     * If {@code ExceptionHandler.crashOnThrow}, it will execute all the tasks in {@code ExceptionHandler.CRASH_TASKS} and set the timeout time to 1 second.
     * @param task The task you want to execute
     */
    public static void safe(Task task) {
        try {
            task.execute();
        } catch (Throwable t) {
            if (!crashOnThrow) {
                t.printStackTrace();
                return;
            }
            LOGGER.error("Meteor Shower crashed D:", t);
            Thread crashTaskThread = new Thread(() -> CRASH_TASKS.add(() -> System.exit(1)).execute());
            crashTaskThread.start();
            try {
                Thread.sleep(1000);
                if (crashTaskThread.isAlive()) {
                    LOGGER.error("The crash tasks taken too long!");
                    LOGGER.error("will directly kill the process");
                    System.exit(1);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
