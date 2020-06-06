import java.util.List;
import java.time.LocalTime;

public class Voluntario extends Servico
{
    private Encomenda enc;
    
    public Voluntario(){
        super();
    }
    
    public Voluntario(String cod,String n,Location l,double r,Encomenda en){
        super(cod,n,l,r);
        this.enc = en;
    }
    
    public Voluntario(Voluntario v){
        super(v);
        this.enc = v.getEncomenda();
    }
    
    public Encomenda getEncomenda(){
        return this.enc.clone();
    }
    
    public void setEncomenda(Encomenda e){
        this.enc = e.clone();
    }
    
    public boolean equals(Object o){
        if(this == o) return true;
        if((o == null) || (this.getClass() != o.getClass())) return false;
        Voluntario v = (Voluntario) o;
        return super.equals(o) && this.enc.equals(v.getEncomenda());
    }
    
    public String toString(){      
        return super.toString() +  this.enc;
    }
    
    public Voluntario clone() {return new Voluntario(this);}
    
}
