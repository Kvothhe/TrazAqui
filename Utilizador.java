import java.util.Map;
import java.util.List;

public class Utilizador extends Account
{
    private String CodUtilizador;
    
    public Utilizador(){
        super();
        this.CodUtilizador = "";
    }
    
    public Utilizador(String n,String em,String pass,List<Encomenda> en,String cod
    ,Location l){
        super(n,em,pass,en,cod,l);
        this.CodUtilizador = cod;
    }
    
    public Utilizador(Utilizador a){
        super(a);
        this.CodUtilizador = a.getCodigo();
    }
    
    public void setCodigo(String cod){
        this.CodUtilizador = cod;
    }
    
    public String getCodigo(){
        return this.CodUtilizador;
    }
    
    public Encomenda solicitarEncomenda(Loja l){
        return l.getEncomenda(this.CodUtilizador);
    }
    
    public Utilizador clone(){
        return new Utilizador(this);
    }
}
