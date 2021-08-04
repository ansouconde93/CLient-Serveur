import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
	//Declaration de variables partagées
    public Produit2Matrices[] resultatPartiels;

    public Client(float[][] matrice1,float[][] matrice2){

        resultatPartiels = new Produit2Matrices[matrice1.length];
	try {
            //recuperation des stubs
            IServeurSuperManager iServeurFactoryManager = (IServeurSuperManager) Naming.lookup("rmi://localhost:1099/multiplieur");
            IServeurSuperManager iServeurSuperManager = (IServeurSuperManager) Naming.lookup("rmi://localhost:1099/consolidateur");
	    //Demarrage des threads de calcule
            // chaque calucle une ligne de la matrice1 * la matrice2
            ThreadCalcule[] tc = new ThreadCalcule[matrice1.length];
            for (int i = 0; i < matrice1.length ; i++) {
                float[][] sousMatrice = new float[1][matrice1[0].length];
                sousMatrice[0] =matrice1[i];
                tc[i] = new ThreadCalcule(i, sousMatrice, matrice2,iServeurFactoryManager);
                tc[i].start();
            }
            //Demarrage du trhead de consolidation.
            // Ce thread consolidera tous les resultat issues des threads de calcul
            ThreadConsolidation tdc = new ThreadConsolidation(iServeurSuperManager,matrice1.length,matrice2[0].length, tc);
            tdc.start();
	    tdc.join();
            System.exit(0);
        }catch (Exception e){
            e.printStackTrace();
        }    
    }

//procedure de print matrice
    public void printMatrice(float[][] m){
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                System.out.print("\t"+m[i][j]+"     ");
            }
            System.out.println("");
        }
        System.out.println("\n");
    }

    //Thread assurant le calcul parallele
    class ThreadCalcule extends Thread{
        public int monNumero;
        IServeurSuperManager iServeurSuperManager;
        float[][] mat1;
        float[][] mat2;
        public ThreadCalcule(int numero,float[][] matr1, float[][] matr2, IServeurSuperManager iServeurSuperManager) throws RemoteException {
            monNumero = numero;
            mat1 = matr1;
            mat2 = matr2;
            this.iServeurSuperManager = iServeurSuperManager;
        }
        @Override
        public void run() {
            try {
                Produit2Matrices p2m= new Produit2Matrices();
                IMultiplicateurMatrice iMultiplicateurMatrice = iServeurSuperManager.generateurDesObjetsDeMultiplicateur(monNumero);
                iMultiplicateurMatrice.multipliezMoiCesMatrices(p2m,mat1,mat2);
                resultatPartiels[monNumero]=p2m;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
	//Thread assurant la consolidation du resultat
    class ThreadConsolidation extends Thread{
        IServeurSuperManager iServeurSuperManager;
        float[][] resultat;
        ThreadCalcule[] threadCalcule;
        public ThreadConsolidation(IServeurSuperManager iss,int ligne, int colonne,ThreadCalcule[] tc){
            iServeurSuperManager = iss;
            resultat = new float[ligne][colonne];
            threadCalcule = tc;
        }

        @Override
        public void run() {
            try {
                ClientConsolidateurResultat ccr = new ClientConsolidateurResultat();
                IConsolidateurResultat iConsolidateurResultat = iServeurSuperManager.generateurObjetsConsolidateur();
                float[][] res;
                for (int i = 0; i < threadCalcule.length ; i++) {
                    threadCalcule[i].join();
                }
                res = resultatPartiels[0].getResultatPartiel();
                int taille = resultat.length;	
                for (int i = 0;i < taille; i++){
                    iConsolidateurResultat.consolideMoiLeResultat(ccr,res,resultatPartiels[i].getResultatPartiel());
                    res = ccr.getResultatConsoliderPartiel();
                }
                resultat = res;
            } catch (Exception e) {
                e.printStackTrace();
            }
            printMatrice(resultat);
        }
    }
}
