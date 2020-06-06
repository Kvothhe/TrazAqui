import java.util.Map;

public class Utilizador
{
    private String CodUtilizador;
    private String Nome;
    private Location GPS;
    
    public Utilizador(){
        this.CodUtilizador = "";
        this.Nome = "";
        this.GPS = new Location();
    }
    
    public Utilizador(String cod, String n, Location l){
        this.CodUtilizador = cod;
        this.Nome = n;
        this.GPS = l;
    }
    
    public Utilizador(Utilizador a){
        this.CodUtilizador = a.getCodigo();
        this.Nome = a.getNome();
        this.GPS = a.getLocation();
    }
    
    public void setCodigo(String cod){
        this.CodUtilizador = cod;
    }
    
    public void setNome(String n){
        this.Nome = n;
    }
    
    public void setGps(Location l){
        this.GPS = l.clone();
    }
    
    public String getCodigo(){
        return this.CodUtilizador;
    }
    
    public String getNome(){
        return this.Nome;
    }
    
    public Location getLocation(){
        return this.GPS.clone();
    }
    
    public Encomenda solicitarEncomenda(Loja l){
        return l.getEncomenda(this.CodUtilizador);
    }
    
    public boolean aceitarServico(Servico s,boolean b){
        if(s.getClass().equals(Voluntario.class))
            return true;
        else return b;
    }
    
    public void classificar(int d,Servico s){
        s.addClassificacao(d);
    }
    
    public Utilizador clone(){
        return new Utilizador(this);
    }
}
