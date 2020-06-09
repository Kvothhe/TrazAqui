import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;
import java.io.Serializable;

public class Account implements Comparable<Account>,Serializable{

    private String email;
    private String password;
    private String name;
    private String cod;
    private List<Encomenda> registo;

    public Account(){
	   this.email = null;
	   this.password = null;
	   this.name = null;
       this.cod = null;
	   this.registo = new ArrayList<>();
    }

    public Account(String n,String email, String password,List<Encomenda> tvl, String cod){
        this.email = email;
        this.password = password;
        this.name = n;
        this.cod = cod;
        this.registo = tvl.stream().map(Encomenda::clone).
                        collect(Collectors.toCollection(ArrayList::new));
    }

    public Account (Account a){
        this.email = a.getEmail();
        this.password = a.getPassword();
        this.name = a.getNome();
	    this.cod = a.getCod();
        this.registo = a.getEncomenda();
    }
    
    public void setNome(String n){
        this.name = n;
    }
    
    public String getNome(){
        return this.name;
    }

    public String getEmail(){
            return this.email;
    }

    public String getPassword(){
            return this.password;
    }

    public List<Encomenda> getEncomenda(){
   
            return this.registo.stream()
                           .map(t->t.clone())
                           .collect(Collectors.toList());
    }

    public void setEmail (String e){
        this.email = e;
    }

    public void setPassword (String pw){
        this.password = pw;
    }

    public void setEncomenda(List<Encomenda> nTL){

        this.registo = nTL.stream()
                          .map(Encomenda::clone)
                          .collect(Collectors.toCollection(ArrayList::new)); 
    }	

    public Account clone(){
	       return new Account(this);
    }

    public boolean equals(Object o){
        if (o==this) return true;
        if ((o==null) || (o.getClass()!=this.getClass())) return false;
        Account a = (Account)o;
        return (this.email.equals(a.getEmail()) && 
        this.password.equals(a.getPassword()));
    }

    public String toString(){
        StringBuilder r = new StringBuilder();
        r.append("Nome: ").append(this.name).append("\n");
        r.append("Email: ").append(this.email).append("\n");
        r.append("Password: ").append(this.password).append("\n");
	    r.append("Encomendas: ").append(this.registo.toString());
	return r.toString();
    }

    public List<Encomenda> getEncomendaBetween(LocalDate init, LocalDate end){
    	return this.registo.stream()
    	       .filter(t->t.getDataentrega().isAfter(init) 
    	       && t.getDataentrega().isBefore(end))
               .collect(Collectors.toList()); 
    }

    public void addEncomenda(Encomenda t){
    	this.registo.add(t.clone());
    }
    
    public int qtsClientes(){
        return this.registo.size();
    }
    
    public int compareTo (Account a){
         return this.email.compareTo(a.getEmail());
    }

    public String getCod()
    {
        return this.cod;
    }
}
