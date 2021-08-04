import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Produit2Matrices extends UnicastRemoteObject implements IProduit2Matrices, Serializable {
    private float[][] resultatPartiel;
    //constructeur par defaut
    public Produit2Matrices() throws RemoteException {}

    public float[][] getResultatPartiel() {
        return resultatPartiel;
    }

    public void setResultatPartiel(float[][] resultatPartiel) {
        this.resultatPartiel = resultatPartiel;
    }

    @Override
    public void rappelMoiLeResultat(float[][] resultatPartiel) throws RemoteException {
        this.resultatPartiel = resultatPartiel;
    }
}
