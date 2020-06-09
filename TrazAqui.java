import java.util.Scanner;
import java.util.ArrayList;
import java.util.TreeMap;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TrazAqui
{
    private StateManager curState;
    private Account curUser;
    private Menu appMenu;
    private static int count=0;
    
    public static void main(String[] args){
        TrazAqui mApp = null;
        boolean recover=false, success=false;
        
        try
        {
            mApp = new TrazAqui();
            Scanner input = new Scanner(System.in);
            //Isto não é intuitivo 
            System.out.print("Recuperar um estado antigo?");
            recover = input.nextBoolean();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        
        if(recover)
        {
            try
            {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Estado"));
                mApp.curState = (StateManager)ois.readObject();
                ois.close();
            }
            catch(Exception e)
            {
                System.out.println("Não está carregado! (" + e.getMessage() + ")");
            }   
        } 
    
        mApp.run(false);
    }
    
    public void run(boolean loggedIn)
    {    
        do
        {
            if(!loggedIn)
                loggedIn = this.menuActions();
            /*
            else
            { 
                if(this.curUser instanceof Client)
                    loggedIn = this.userActions();
                else loggedIn = this.driverActions();
            }
            */   
        }while(this.appMenu.getOpt() != 0);
    }
    
    public void save(){
       try{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Estado"));
            oos.writeObject(this.curState);
            oos.flush();
            oos.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private TrazAqui() throws TooManyInstancesException{
        if(count == 0){
            String[] mOps = {"Login", "Registar", "Top 10 utilizadores", "Top 10 empresas", "Read Logs"};
            String[] uOps = {"Inserir Pedido", "Classificar Serviço", "Logout"};
            String[] lOps = {"Inserir informação","Logout"};
            String[] eOps = {"Total faturado","Logout"};
            this.curState = new StateManager();
            this.curUser = null;
            this.appMenu = new Menu(uOps,lOps,eOps,mOps);
        }else throw new TooManyInstancesException();
    }

    
    public boolean menuActions(){  
        Account aux;
        boolean login=false;

        this.appMenu.mMenu();
        switch(this.appMenu.getOpt()){
            case 1:
            /*
                try{
                    aux = login();
                    login = true;
                    this.curUser = aux;
                    System.out.println("Login aceite");
                }catch(WrongPasswordException | UserNotFoundException e){
                    System.out.println(e.getMessage());
                }*/
                break;
            case 2:
                try{
                    aux = register();
                    this.curState.addUser(aux);
                    System.out.println("Registo com sucesso!\nCodigo atribuido: " + aux.getCod());
                }catch(DuplicateRegistrationException e){
                    System.out.println("Utilizador com email "+e.getMessage()+" já existe");
                }
                break;
            case 3:
                //System.out.println(top10Uti());
                break;
            case 4:
                //System.out.println(top5Drivers());
                break;
            case 5:
                ReadLogs readlogs = new ReadLogs(); 
                readlogs.read(this.curState);
                break;
            case 0:
                save();
                System.out.println("A sair...");
                break;
        }

        return login;
    }

    public String atribuirCod(int type)
    {
        return this.curState.minValueMaisUm(type);
    }

    public Location askLocation()
    {
        Scanner input = new Scanner(System.in);
        
        System.out.println("Latitude: ");
        double lat = input.nextDouble();
    
        System.out.println("Longitude: ");
        double longi = input.nextDouble();

        return new Location(lat, longi);
    }

    public double askRaio()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Raio:");
        return input.nextDouble();
    }

    public double askTaxa()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Taxa:");
        return input.nextDouble();
    }
    
    public String askNif()
    {
        Scanner input = new Scanner(System.in);
        System.out.println("NIF:");
        return input.nextLine();
    }

    public Account register () throws DuplicateRegistrationException{
        Scanner input = new Scanner(System.in);
        Account ret   = null;
        String nome   = null, email = null, password = null;
        double aux    = 0.f;
        boolean enter = false, success = false;
        int type = 0;
        char c;
        
        while(!success){
            try
            {
                System.out.print("Nome: ");
                nome = input.nextLine();

                System.out.print("Email: ");
                email = input.nextLine();
                
                System.out.print("Password: ");
                password = input.nextLine();
                
                System.out.println("Escolha o tipo de conta:"); 
                System.out.println("1-Utilizador\n2-Voluntário\n3-Transportadora\n4-Loja");
                while(type < 1 || type > 4)
                {
                    type = input.nextInt();
                    if(type < 1 || type > 4)
                        System.out.println("Opção inválida, escolha de novo");
                }
                success = true;
            }
            catch(Exception e)
            {
		        System.out.println("Input error ("+e.getMessage() + ") please try again");
                input.nextLine();
            }
        }

        if(this.curState.userExists(email)) throw new DuplicateRegistrationException(email);
	    
        if(type == 1)
            ret = new Utilizador(nome, email, password, new ArrayList<Encomenda>(), atribuirCod(type), askLocation());
        
        //adicionar se estão disponiveis ou não para encomendas, setDisponi...
        if(type == 2)
            ret = new Voluntario(new ArrayList<Encomenda>(), email, password, atribuirCod(type), nome,  askLocation(), askRaio(), new Encomenda());
        
        if(type == 3)
            ret = new EmpresaV(new ArrayList<Encomenda>(), email, password, atribuirCod(type), nome,  askLocation(), askRaio(), askNif(), askTaxa());

        if(type == 4)
            ret = new Loja(email, password, new ArrayList<Encomenda>(), nome, atribuirCod(type), askLocation());
    
	    return ret; 
    }
    /*
    public String top10Uti(){
        StringBuilder sb = new StringBuilder();
        List<String> aux = this.curState.getUsers().values().stream()
                               .filter(a->a.getClass().getSimpleName().equals("Utilizador"))
                               .sorted(new UsarComparator())
                               .limit(10)
                               .map(a->a.getNome())
                               .collect(Collectors.toList());
        int i=1;
        for(String st : aux){
            sb.append(i).append("- ").append(st).append("\n");
            i++;
        }

        return sb.toString();
    }*/
}