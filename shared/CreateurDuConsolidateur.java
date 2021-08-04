import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CreateurDuConsolidateur extends UnicastRemoteObject implements IServeurSuperManager, Serializable {
    public CreateurDuConsolidateur() throws RemoteException{}

    @Override
    public IMultiplicateurMatrice generateurDesObjetsDeMultiplicateur(int nombreExemplaire) throws RemoteException {
        return null;
    }

    @Override
    public IConsolidateurResultat generateurObjetsConsolidateur() throws RemoteException {
        return new ConsolidateurResultat();
    }
}
