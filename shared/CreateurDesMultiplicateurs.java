import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CreateurDesMultiplicateurs extends UnicastRemoteObject implements IServeurSuperManager, Serializable {

    public CreateurDesMultiplicateurs() throws RemoteException{}

    @Override
    public IMultiplicateurMatrice generateurDesObjetsDeMultiplicateur(int nombreExemplaire) throws RemoteException {
        return new MultiplicateurMatrice();
    }

    @Override
    public IConsolidateurResultat generateurObjetsConsolidateur() throws RemoteException {
        return null;
    }
}
