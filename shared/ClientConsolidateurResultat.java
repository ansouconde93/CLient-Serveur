import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ClientConsolidateurResultat extends UnicastRemoteObject implements IProduit2Matrices, Serializable {
    private float[][] resultatConsoliderPartiel;

    public ClientConsolidateurResultat() throws RemoteException {}

    public float[][] getResultatConsoliderPartiel() {
        return resultatConsoliderPartiel;
    }

    public void setResultatConsoliderPartiel(float[][] resultatConsoliderPartiel) {
        this.resultatConsoliderPartiel = resultatConsoliderPartiel;
    }

    @Override
    public void rappelMoiLeResultat(float[][] resultatPartiel) throws RemoteException {
        resultatConsoliderPartiel = resultatPartiel;
    }
}
