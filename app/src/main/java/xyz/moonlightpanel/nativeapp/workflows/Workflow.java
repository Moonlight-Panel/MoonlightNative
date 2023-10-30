package xyz.moonlightpanel.nativeapp.workflows;

public abstract class Workflow {
    abstract void run();

    public static void trigger(Workflow w){
        w.run();
    }

    public static final Workflow STARTUP = new StartupWorkflow();
}
