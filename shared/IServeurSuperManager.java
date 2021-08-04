import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServeurSuperManager extends Remote{
    public IMultiplicateurMatrice generateurDesObjetsDeMultiplicateur(int nombreExemplaire) throws RemoteException;
    public IConsolidateurResultat generateurObjetsConsolidateur() throws RemoteException;
}
