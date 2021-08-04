import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IConsolidateurResultat extends Remote {
    public void consolideMoiLeResultat(IProduit2Matrices iProduit2Matrices,float[][] resultatPartiel1, float[][] resultatPartiel2) throws RemoteException;
}
