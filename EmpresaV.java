import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EmpresaV extends Account
{
    private String codigo;
    private String nome;
    private double classificacao;
    private int uti;
    private double raio;
    private double velocidademed;
    private boolean disponibilidade;
    private boolean medicamentos;
    private Location loc;
    private List<Encomenda> enc;
    private String nif;
    private static double taxakm;
    
    public EmpresaV(){
        super();
        this.nome = "";
        this.codigo = "";
        this.classificacao = 0.0;
        this.loc = new Location();
        this.uti = 0;
        this.raio = 0.0;
        this.velocidademed = 0.0;
        this.medicamentos = false;
        this.enc = new ArrayList<>();
        this.nif = "";
        this.taxakm = 0;
    }
    
    public EmpresaV(List<Encomenda> list, String email, String password, String cod,String n,Location l,double r,
    String ni,double txak){
        //super(list, email, password, cod,n,l,r);
        super(n,email, password, list, cod);
        this.loc =  l;
        this.codigo = cod;
        this.nome = n;
        this.classificacao = 0;
        this.raio = r;
        this.velocidademed = 0;
        this.disponibilidade = true;
        this.medicamentos = false;
        this.uti = 0;
        this.enc = new ArrayList<>();
        this.nif = ni;
        this.taxakm = txak;
    }
    
    public EmpresaV(EmpresaV emp){
        super(emp);
        this.codigo = emp.getCodigo();
        this.nome = emp.getNome();
        this.classificacao = emp.getClassificacao();
        this.raio = emp.getRaio();
        this.velocidademed = emp.getVelocidademed();
        this.disponibilidade = emp.getDisponibilidade();
        this.medicamentos = emp.getMedicamentos();
        this.enc = emp.getEncomendas();
        this.nif = emp.getNif();
        this.taxakm = emp.getTaxakm();
    }
    
    public Location getLoc(){
        return this.loc;
    }
    
    public void setLoc(Location l){
        this.loc = l;
    }
    
    public List<Encomenda> getEncomendas(){
        return this.enc.stream().map(Encomenda::clone).collect(Collectors.toList());
    }
    
    public String getNif(){
        return this.nif;
    }
    
    public double getTaxakm(){
        return this.taxakm;
    }
    
    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        EmpresaV emp = (EmpresaV) o;
        return super.equals(o) && this.enc.equals(emp.getEncomendas()) &&
        this.nif == emp.getNif() && this.taxakm == emp.getTaxakm();
    }
    
    public String toString(){
        return super.toString() + "\nNIF: " + this.nif + "\nTaxa: " + this.taxakm + "\nDisp: " + this.disponibilidade;
    }
    
    public EmpresaV clone(){
        return new EmpresaV(this);
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
    
    public void addClassificacao(int i){
        double sum = this.classificacao + ((i - this.classificacao)/uti);
        setClassificacao(sum);
    }
}
