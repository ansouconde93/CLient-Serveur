import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ConsolidateurResultat extends UnicastRemoteObject implements IConsolidateurResultat, Serializable {
    public ConsolidateurResultat() throws RemoteException{}
    @Override
    public void consolideMoiLeResultat(IProduit2Matrices iProduit2Matrices,float[][] resultatPartiel1, float[][] resultatPartiel2) throws RemoteException {
        float[][] resultatConsolider = new float[resultatPartiel1.length+resultatPartiel2.length][resultatPartiel1[0].length];
        int k =0;
        for (int i = 0; i < resultatPartiel1.length; i++) {
            for (int j = 0; j < resultatPartiel1[0].length; j++) {
                resultatConsolider[i][j] = resultatPartiel1[i][j];
            }
            k++;
        }
        for (int i = 0; i < resultatPartiel2.length; i++) {
            for (int j = 0; j < resultatPartiel2[0].length; j++) {
                resultatConsolider[k][j] = resultatPartiel2[i][j];
            }
            k++;
        }
        iProduit2Matrices.rappelMoiLeResultat(resultatConsolider); // exécution de callback
    }
}
