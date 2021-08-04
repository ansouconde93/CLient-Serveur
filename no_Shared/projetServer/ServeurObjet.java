import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.RMISecurityManager;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;
import java.rmi.Remote;

public class ServeurObjet {
    public static void main(String[] args) {

            System.setSecurityManager(new SecurityManager());
        try {
            	LocateRegistry.createRegistry(1099);
		System.out.println("Demarrage du serveur !!!!!!");
	     	Properties p = System.getProperties();
	     	String url = p.getProperty("java.rmi.server.codebase");
	     	Class createurDesMultiplicateurs = RMIClassLoader.loadClass(url,"CreateurDesMultiplicateurs");
	     	Class createurDuConsolidateur = RMIClassLoader.loadClass(url,"CreateurDuConsolidateur");
            	Naming.rebind("rmi://localhost:1099/multiplieur",(Remote) createurDesMultiplicateurs.newInstance());
            	Naming.rebind("rmi://localhost:1099/consolidateur",(Remote) createurDuConsolidateur.newInstance());

        }catch (Exception e){}
    }
}
