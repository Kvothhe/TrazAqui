import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.Duration;

public abstract class Servico
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
    private List<Encomenda> registo;
    
    public Servico(){
        this.nome = "";
        this.codigo = "";
        this.classificacao = 0.0;
        this.uti = 0;
        this.raio = 0.0;
        this.velocidademed = 0.0;
        this.medicamentos = false;
        this.localizacao = new Location(0.0,0.0);
        this.registo = new ArrayList<>();
    }
    
    public Servico(String cod,String n,Location l,double r){
        this.codigo = cod;
        this.nome = n;
        this.classificacao = 0;
        this.raio = r;
        this.velocidademed = 0;
        this.disponibilidade = false;
        this.medicamentos = false;
        this.localizacao = l;
        this.uti = 0;
        this.registo = new ArrayList <>();
    }
    
    
    public Servico(Servico s){
        this.codigo = s.getCodigo();
        this.nome = s.getNome();
        this.classificacao = s.getClassificacao();
        this.raio = s.getRaio();
        this.velocidademed = s.getVelocidademed();
        this.disponibilidade = s.getDisponibilidade();
        this.medicamentos = s.getMedicamentos();
        this.localizacao = s.getLocalizacao();
        this.registo = s.getRegisto();
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
        
    public List<Encomenda> getRegisto(){
        return registo.stream().map(Encomenda::clone).collect(Collectors.toList());
    }
    
    public void addClassificacao(int i){
        double sum = this.classificacao + ((i - this.classificacao)/uti);
        setClassificacao(sum);
    }
    
    public void setRegisto(List<Encomenda> p){
        this.registo = new ArrayList<>();
        p.forEach(s -> {this.registo.add(s.clone());});
    }
    
    public abstract Servico clone();
    
    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        Servico s = (Servico) o;
        
        return this.codigo.equals(s.getCodigo()) && 
        this.nome.equals(s.getNome()) && 
        this.classificacao == s.getClassificacao() &&
        this.raio == s.getRaio() && this.velocidademed == s.getVelocidademed() && 
        this.disponibilidade == s.getDisponibilidade() && 
        this.medicamentos == s.getMedicamentos() &&
        this.localizacao.equals(s.getLocalizacao()) && 
        this.registo.equals(s.getRegisto()) && this.uti == s.getUtilizador();
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append(this.codigo).append("\n").append(this.nome).append("\n").
        append(this.localizacao).append("\n").append(this.raio);
        
        return sb.toString();
    }    
}
