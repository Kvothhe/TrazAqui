import java.util.Scanner;
import java.util.Map;

public class Menu
{
	public void initMenu()
	{
		TrazAqui app = new TrazAqui();

		Scanner input = new Scanner(System.in);
        int menu = 0;
        while(true)
        {
            System.out.println("-------------MENU-------------");
            System.out.println("1. Registar Utilizador");
            System.out.println("2. Ver Utilizadores");
            System.out.println("3. Registar Cliente");
            System.out.println("4. Sair");
            menu = input.nextInt();
            input.nextLine();

            if(menu == 1)
            {
            	//Codigo automatico
                System.out.println("Código Utilizador: ");
                String cod = input.nextLine();

                System.out.println("Nome Utilizador: ");
                String nome = input.nextLine();
                
                System.out.println("Latitude Utilizador: ");
                double lat = input.nextDouble();

                System.out.println("Longitude Utilizador: ");
                double longi = input.nextDouble();
                Location local = new Location(lat,longi);

                Utilizador util = new Utilizador(cod, nome, local);
                try
                {
                	app.adicionaUti(util);
            	}
            	catch(Exception e){
            		System.out.println(e);
            	};
            }
            if(menu == 2)
            {
            	for(Map.Entry<String,Utilizador> entry : app.getUtilizadoresC().entrySet())
            		System.out.println(entry.getKey());
            }
            if(menu == 4)
            	break;
        }
	}
}