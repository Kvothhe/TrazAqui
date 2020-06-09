import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class EmpresaV extends Servico
{
    private List<Encomenda> enc;
    private String nif;
    private static double taxakm;
    
    public EmpresaV(){
        super();
        this.enc = new ArrayList<>();
        this.nif = "";
        this.taxakm = 0;
    }
    
    public EmpresaV(List<Encomenda> list, String email, String password, String cod,String n,Location l,double r,
    String ni,double txak){
        super(list, email, password, cod,n,l,r);
        this.enc = new ArrayList<>();
        this.nif = ni;
        this.taxakm = txak;
    }
    
    public EmpresaV(EmpresaV emp){
        super(emp);
        this.enc = emp.getEncomendas();
        this.nif = emp.getNif();
        this.taxakm = emp.getTaxakm();
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
        return super.toString() + this.nif + "\n" + this.taxakm;
    }
    
    public EmpresaV clone(){
        return new EmpresaV(this);
    }
}
