import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Set;
import java.util.Map;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.Collectors;
import java.io.Serializable;

public class StateManager implements Serializable{
    private Map<String,Account> users;
    //Se adicionarmos um value que é o estado da encomanda? Para já estou so a colocar aqui as aceites
    private List<String> encAceites;

    public StateManager(){
        this.users = new HashMap<>();
        this.encAceites = new ArrayList<>();
    }
    
    //Adicionar as encomendas aceites?
    public StateManager(Map<String,Account> usersL){
        this.users = usersL.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    public Map<String, Account> getUsers(){
        return this.users.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    public void setUsers(Map<String, Account> nC){
        this.users = nC.entrySet().stream().collect(Collectors.toMap(e->e.getKey(), e->e.getValue().clone()));
    }

    public void addUser(Account nUser){
        this.users.put(nUser.getEmail(), nUser);
    }

    public List<Account> getUserList(){
        return this.users.values().stream().map(Account::clone).collect(Collectors.toList());
    }
    
    public Account getuserL(String n,List<Account> lo){
        boolean encontrado = false;
        Iterator<Account> it = lo.iterator();
        Account a = new Account();
        
        while(it.hasNext() && !encontrado){
            a = it.next();
            if(a.getNome().equals(n)){
                encontrado = true;
            }
        }
        return a;
    }
    
    public void getemails(){
        Set<String> keys = users.keySet();
        for(String key: keys){
            System.out.println(key);
        }
    }

    public boolean userExists(String uEmail){
        return this.users.containsKey(uEmail);
    }

    public void updateUser(Account nInfo){
        this.users.remove(nInfo.getEmail());
        this.users.put(nInfo.getEmail(), nInfo);
    }

    public Account getUser(String mail){
        return this.users.get(mail);
    }

    public Account getUserByCode(String code)
    {
        Account conta = null;

        for(Map.Entry<String,Account> entry: this.users.entrySet())
        {
            conta = entry.getValue();

            if(conta.getCod() == code)
                break;
        }

        return conta;
    }

    public String strType(int type)
    {
        String ret = "";

        switch(type)
        {
            case 1:
                ret = "u";
                break;
            case 2:
                ret = "v";
                break;
            case 3:
                ret = "t";
                break;
            case 4:
                ret = "l";
                break;
        }

        return ret;
    }

    //Isto deve estar pouco eficiente mas n vejo outra maneira de fazer com o trabalho asssim estruturado
    public String minValueMaisUm(int type)
    {
        String s = "";
        int aux = 0;

        for(Map.Entry<String,Account> entry : this.users.entrySet())
        {
            String key = entry.getValue().getCod();

            if(type == 1 && key.charAt(0) == 'u')
                aux = Integer.parseInt(key.substring(1,key.length()));

            if(type == 2 && key.charAt(0) == 'v')
                aux = Integer.parseInt(key.substring(1,key.length()));
            
            if(type == 3 && key.charAt(0) == 't')
                aux = Integer.parseInt(key.substring(1,key.length()));

            if(type == 4 && key.charAt(0) == 'l')
                aux = Integer.parseInt(key.substring(1,key.length()));

        }
        aux++;

        return (strType(type) + String.valueOf(aux));
    }

    public void addEcoAceite(String str)
    {
        this.encAceites.add(str);
    }
}
