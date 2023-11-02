package xyz.moonlightpanel.nativeapp.workflows;

public abstract class Workflow {
    abstract void run();

    public static void trigger(Workflow w){
        w.run();
    }

    public static final Workflow STARTUP = new StartupWorkflow();
    public static final Workflow LOGIN_RESPONSE = new LoginResponseWorkflow();
    public static final Workflow REGISTER_RESPONSE = new RegisterResponseWorkflow();
}
