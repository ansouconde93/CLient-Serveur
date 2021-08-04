import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;
import java.rmi.registry.LocateRegistry;
import java.rmi.RMISecurityManager;
import java.rmi.server.RMIClassLoader;
import java.util.Properties;
import java.rmi.Remote;
import java.lang.reflect.Constructor;

public class ProjetClient {

    public ProjetClient(){
	Scanner in = new Scanner(System.in);
        int nombreLigne1=0,nombreColonne=0,nombreColonne2 =0;
	try{
		do{
			System.out.println("Donner le nombre de ligne de la matrice 1");
			nombreLigne1= in.nextInt();
			System.out.println("Donner le nombre de colonne de la matrice 1");
			nombreColonne = in.nextInt();
			System.out.println("Donner le nombre de la colonne de la matrice 2");
			nombreColonne2= in.nextInt();
			System.out.println("\n");
		}while(nombreLigne1<1 || nombreColonne<1 || nombreColonne2 <1);

	}catch (Exception e){
		System.out.println("\n\tAttention ! Vous n'avez pas donné un entier donc nous arretons le système.\n");
		System.exit(0);
        }
        try {
		
        	//lecture des matrice
        	float[][] matrice1 = saisirLesMatrices(nombreLigne1,nombreColonne);
        	float[][] matrice2 = saisirLesMatrices(nombreColonne,nombreColonne2);
		//Instancition du gestionnaire de securité		
		System.setSecurityManager(new SecurityManager());
		//Chargement de la classe CLient et son instanciation
		Properties p = System.getProperties();
		String url = p.getProperty("java.rmi.server.codebase");
		Class client = RMIClassLoader.loadClass(url,"Client");
		Constructor[] clientContructors =client.getConstructors();
		clientContructors[0].newInstance(matrice1,matrice2);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //saisi d'une matrice par le random()
    private float[][] saisirLesMatrices(int nombreLingne,int nombreColonne) throws RemoteException {
        float[][] matrice = new float[nombreLingne][nombreColonne];
        for (int i = 0; i < nombreLingne; i++) {
            for (int j = 0; j < nombreColonne; j++) {
                matrice[i][j] = (float) Math.random()*100;
            }
        }
        return matrice;
    }

    public static void main(String[] args) {
	//Demarage du client dynamique
	new ProjetClient();        
    }
}
