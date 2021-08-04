import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IMultiplicateurMatrice extends Remote {
    public void multipliezMoiCesMatrices(IProduit2Matrices iProduit2Matrices,float[][] matrice1,float[][] matrice2) throws RemoteException;
}
