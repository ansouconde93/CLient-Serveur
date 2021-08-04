
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MultiplicateurMatrice extends UnicastRemoteObject implements IMultiplicateurMatrice, Serializable {
    public MultiplicateurMatrice() throws RemoteException{
    }
    @Override
    public void multipliezMoiCesMatrices(IProduit2Matrices iProduit2Matrices,float[][] matrice1,float[][] matrice2) throws RemoteException {
        float[][] resultat = new float[matrice1.length][matrice2[0].length];
        for (int i = 0; i < matrice1.length; i++) {
            for (int j = 0; j < matrice2[0].length; j++) {
                resultat[i][j]=0;
                for (int k = 0; k < matrice1[0].length; k++) {
                    resultat[i][j] += matrice1[i][k]*matrice2[k][j];
                }
            }
        }
        iProduit2Matrices.rappelMoiLeResultat(resultat); //exécution de callback
    }
}
