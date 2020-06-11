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
    private Location loc;
    private boolean disponibilidade;
    private boolean medicamentos;
    private Encomenda enc;
    
    public Voluntario(){
        super();
        this.codigo = "";
        this.classificacao = 0.0;
        this.loc = new Location();
        this.uti = 0;
        this.raio = 0.0;
        this.velocidademed = 0.0;
        this.disponibilidade = true;
        this.medicamentos = false;
    }
    
    public Voluntario(List<Encomenda> list,String email, String password,String cod,String n,Location l,double r,Encomenda en){
        super(n,email, password, list, cod);
        this.codigo = cod;
        this.classificacao = 0.0;
        this.loc = l;
        this.raio = r;
        this.velocidademed = 0;
        this.disponibilidade = true;
        this.medicamentos = false;
        this.uti = 0;
        this.enc = en;
    }
    
    public Voluntario(Voluntario v){
        super(v);
        //completar
        this.enc = v.getEncomendap();
    }

    public Location getLoc(){
        return this.loc;
    }
    
    public void setLoc(Location l){
        this.loc = l;
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
    
    public void addClassificacao(int i){
        double sum = 0;
        this.uti++;
        sum = this.classificacao + ((i - this.classificacao)/uti);
        setClassificacao(sum);
    }

    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        Voluntario v = (Voluntario) o;
        return super.equals(o) && this.enc.equals(v.getEncomenda());
    }
    
    public String toString(){      
        return super.toString() + "\nDisponibilidade: " + this.disponibilidade  + "\n" +  this.enc.toString();
    }
    
    public Voluntario clone() {return new Voluntario(this);}
    
}
