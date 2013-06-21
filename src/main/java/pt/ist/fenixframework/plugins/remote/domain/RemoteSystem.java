package pt.ist.fenixframework.plugins.remote.domain;

import pt.ist.fenixWebFramework.services.Service;
import pt.ist.fenixframework.pstm.PersistentRoot;

public class RemoteSystem extends RemoteSystem_Base {

    private static RemoteSystem instance = null;

    private RemoteSystem() {
        super();
        RemoteSystem root = PersistentRoot.getRoot(RemoteSystem.class.getName());
        if (root != null && root != this) {
            throw new Error("Trying to create a 2nd instance of RemoteSystemRoot! There can only be one!");
        }
    }

    @Service
    public static synchronized void init() {
        if (instance == null) {
            instance = PersistentRoot.getRoot(RemoteSystem.class.getName());
            if (instance == null) {
                instance = new RemoteSystem();
                PersistentRoot.addRoot(RemoteSystem.class.getName(), instance);
            }
        }
    }

    public static RemoteSystem getInstance() {
        if (instance == null) {
            init();
        }
        return instance;
    }
}
