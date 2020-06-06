import java.util.List;

public class Empresa1 extends Servico
{
    private Encomenda enc;
    private String nif;
    private static double taxakm;
    
    public Empresa1(){
        super();
        this.nif = "";
        this.taxakm = 0.0;
    }
    
    public Empresa1(String cod,String n,Location l,double r,
    String ni,double txak){
        super(cod,n,l,r);
        this.enc = new Encomenda();
        this.nif = ni;
        this.taxakm = txak;
    }
    
    public Empresa1(Empresa1 emp){
        super(emp);
        this.nif = emp.getNif();
        this.enc = emp.getEncomenda();
        this.taxakm = emp.getTaxakm();
    }
    
    public String getNif(){
        return this.nif;
    }
    
    public Encomenda getEncomenda(){
        return this.enc.clone();
    }
    
    public double getTaxakm(){
        return this.taxakm;
    }
    
    public double setCusto(double d){
        return this.enc.getPeso() * d;
    }
    
    public double getTotaldistancia(Location df,Location dc){
        double total = this.getLocalizacao().distanceTo(df) + df.distanceTo(dc);
        return total;
    }
    
    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        Empresa1 emp = (Empresa1) o;
        return super.equals(o) && this.enc.equals(emp.getEncomenda()) &&
        this.nif == emp.getNif() && this.taxakm == emp.getTaxakm();
    }
    
    public String toString(){
        return super.toString() + this.nif + "\n" + this.taxakm;
    }
    
    public Empresa1 clone(){
        return new Empresa1(this);
    }
}
