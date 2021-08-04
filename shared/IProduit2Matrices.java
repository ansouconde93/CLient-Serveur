import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IProduit2Matrices extends Remote {
    public void rappelMoiLeResultat(float[][] resultatPartiel) throws RemoteException;
}
