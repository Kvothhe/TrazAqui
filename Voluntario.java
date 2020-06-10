import java.util.List;
import java.time.LocalTime;

public class Voluntario extends Account
{
    private String codigo;
    private String nome;
    private double classificacao;
    private int uti;
    private double raio;
    private double velocidademed;
    private boolean disponibilidade;
    private boolean medicamentos;
    private Location localizacao;
    private Encomenda enc;
    
    public Voluntario(){
        super();
        this.nome = "";
        this.codigo = "";
        this.classificacao = 0.0;
        this.uti = 0;
        this.raio = 0.0;
        this.velocidademed = 0.0;
        this.medicamentos = false;
        this.localizacao = new Location(0.0,0.0);
    }
    
    public Voluntario(List<Encomenda> list,String email, String password,String cod,String n,Location l,double r,Encomenda en){
        super(n,email, password, list, cod);
        this.codigo = cod;
        this.nome = n;
        this.classificacao = 0;
        this.raio = r;
        this.velocidademed = 0;
        this.disponibilidade = false;
        this.medicamentos = false;
        this.localizacao = l;
        this.uti = 0;
        this.enc = en;
    }
    
    public Voluntario(Voluntario v){
        super(v);
        this.enc = v.getEncomendap();
    }
    
    public Encomenda getEncomendap(){
        return this.enc.clone();
    }
    
    public void setEncomenda(Encomenda e){
        this.enc = e.clone();
    }
    
public void setVelocidademed(double l){
        this.velocidademed = l;
    }
    
    public void setDisponibilidade(Boolean b){
        this.disponibilidade = b;
    }
    
    public int getUtilizador(){
        return this.uti;
    }
    
    public void setUtilizador(int u){
        this.uti = u;
    }
    
    public String getCodigo(){
        return this.codigo;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public void setClassificacao(double d){
        this.classificacao = d;
    }
    
    public double getClassificacao(){
        return this.classificacao;
    }
    
    public double getRaio(){
        return this.raio;
    }
    
    public double getVelocidademed(){
        return this.velocidademed;
    }
    
    public boolean getDisponibilidade(){
        return this.disponibilidade;
    }
    
    public boolean getMedicamentos(){
        return this.medicamentos;
    }
    
    public Location getLocalizacao(){
        return this.localizacao;
    }
    
    public void addClassificacao(int i){
        double sum = this.classificacao + ((i - this.classificacao)/uti);
        setClassificacao(sum);
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        Voluntario v = (Voluntario) o;
        return super.equals(o) && this.enc.equals(v.getEncomenda());
    }
    
    public String toString(){      
        return super.toString() +  this.enc.toString();
    }
    
    public Voluntario clone() {return new Voluntario(this);}
    
}
