import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Loja extends Account
{
    private String nomeLoja;
    private String codLoja;
    private Location GPS;
    private boolean sinal;
    private Map<String,Encomenda> encomendas;
    
    public Loja(){
        super();
        this.codLoja = "";
        this.GPS = new Location(0.0,0.0);
        this.sinal = false;
        this.encomendas = new HashMap<>();
    }
    
    public Loja(String em,String pa,List<Encomenda> re,
                String n,String c,Location loc){
        super(n,em,pa,re, c);
        this.nomeLoja = n;
        this.codLoja = c;
        this.GPS = loc;
        this.sinal = false;
        this.encomendas = new HashMap<>();
    }
    
    public Loja(Loja l){
        super(l);
        this.codLoja = l.getCodLoja();
        this.GPS = l.getLocalizacao();
        this.sinal = l.getSinal();
        this.encomendas = l.getEncomendas();
    }
    
    public String getCodLoja(){return this.codLoja;}
    
    public boolean getSinal(){return this.sinal;}
        
    public Location getLocalizacao(){return this.GPS.clone();}
    
    public Encomenda getEncomenda(String cod){
        return this.encomendas.get(cod).clone();
    }
    
    public Map<String,Encomenda> getEncomendas(){
        Map<String,Encomenda> res = new HashMap<>();
        
        for(Map.Entry<String,Encomenda> entry : this.encomendas.entrySet()){
            res.put(entry.getKey(),entry.getValue().clone());
        }
        return res;
    }
    
    public void setEncomendas(Map<String, Encomenda> enc){
        this.encomendas = new HashMap<>();
        enc.values().forEach(a -> this.encomendas.put(a.getCliente(),a.clone()));
    }
    
    public boolean setPronto(Encomenda e){
        if(this.encomendas.containsValue(e))
            return true;
        else
            return false;
    }
    
    public void insereEncomenda(Encomenda e){
        this.encomendas.put(e.getCliente(),e.clone());
    }
    
    public void removeEncomenda(String nome){
        this.encomendas.remove(nome);
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Loja:").append(this.nomeLoja + "\n")
        .append("Codigo: ").append(this.codLoja).append("\n")
                            .append(this.GPS);                            
        return super.toString() + sb.toString();                    
    }
    
    public boolean equals(Object o){
        if(o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Loja l = (Loja) o;
        return this.codLoja == l.getCodLoja() 
               && this.GPS.equals(l.getLocalizacao());
    }
    
    public Loja clone(){
        return new Loja(this);
    }
}
