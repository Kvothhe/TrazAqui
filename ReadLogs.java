import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadLogs
{
	public void read(StateManager state)
	{
		try
		{
      		File file = new File("logs.txt");
      		Scanner myReader = new Scanner(file);
      		
      		while (myReader.hasNextLine()) 
      		{
        		String data = myReader.nextLine();

        		String[] parts = data.split(",");

        		if(parts[0].charAt(0) == 'U')
        		{
        			String userCod = parts[0].substring(11,parts[0].length());
        			state.addUser(new Utilizador(parts[1],(userCod+"@email.pt"),userCod,new ArrayList<Encomenda>(),
        			userCod, new Location(Double.valueOf(parts[2]),Double.valueOf(parts[3]))));
        		}

        		if(parts[0].charAt(0) == 'V')
        		{
        			String volCod = parts[0].substring(11,parts[0].length());
        			state.addUser(new Voluntario(new ArrayList<Encomenda>(), (volCod+"@email.pt"), volCod, volCod,
        			parts[1], new Location(Double.valueOf(parts[2]),Double.valueOf(parts[3])), Double.valueOf(parts[4]), new Encomenda()));
        		}

        		if(parts[0].charAt(0) == 'T')
        		{
        			String transCod = parts[0].substring(15,parts[0].length());
        			state.addUser(new EmpresaV(new ArrayList<Encomenda>(), (transCod+"@email.pt"), transCod, transCod,
        			parts[1], new Location(Double.valueOf(parts[2]),Double.valueOf(parts[3])), Double.valueOf(parts[5]), parts[4], Double.valueOf(parts[6])));
        		}

        		if(parts[0].charAt(0) == 'L')
        		{
        			String lojaCod = parts[0].substring(5,parts[0].length());
        			state.addUser(new Loja((lojaCod+"@email.pt"), lojaCod, new ArrayList<Encomenda>(), parts[1],
        			lojaCod, new Location(Double.valueOf(parts[2]),Double.valueOf(parts[3]))));
        		}

      		}
      		myReader.close();
    	} 
    	catch (FileNotFoundException e) 
    	{
      		System.out.println("Ficheiro logs.txt inexistente");
      		e.printStackTrace();
    	}
	}
}