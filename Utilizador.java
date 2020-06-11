import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Utilizador extends Account 
{
    private String CodUtilizador;
    private Location loc;
    
    public Utilizador(){
        super();
        this.loc = new Location();
        this.CodUtilizador = "";
    }
    
    public Utilizador(String n,String em,String pass,List<Encomenda> en,String cod
    ,Location l){
        super(n,em,pass,en,cod);
        this.loc = l;
        this.CodUtilizador = cod;
    }
    
    public Utilizador(Utilizador a){
        super(a);
        this.CodUtilizador = a.getCodigo();
        this.loc = a.getLoc();
    }
    
    public Location getLoc(){
        return this.loc;
    }
    
    public void setLoc(Location l){
        this.loc = l;
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

    public Voluntario retornacloseVol(Location loc,Location l,List<Account> lis){
        Voluntario closer = null;
        double dist = Double.MAX_VALUE,temp;
        List<Voluntario> disponiveis = lis.stream()
                                       .filter(a->a.getClass().getSimpleName().equals("Voluntario"))
                                       .map(a->(Voluntario)a)
                                       .filter(a->(a.getDisponibilidade() == true)) 
                                       .collect(Collectors.toList());

        System.out.println("Disponiveis: " + disponiveis.toString());
        
        for(Voluntario v : disponiveis){
            if((temp = loc.distanceTo(l))<dist && temp <= v.getRaio()){
                closer = v;
                dist = temp;
            }
        }

        return closer;
    }
    
    public EmpresaV retornacloseEmp(Location loc,Location l,List<Account> lis){
        EmpresaV closer = null;
        double dist = Double.MAX_VALUE,temp;
        List<EmpresaV> disponiveis = lis.stream()
                                       .filter(a->a.getClass().getSimpleName().equals("EmpresaV"))
                                       .map(a->(EmpresaV)a)
                                       .filter(a->((EmpresaV)a).getDisponibilidade() == true)
                                       .collect(Collectors.toList());
        
        for(EmpresaV v : disponiveis){
            if((temp = loc.distanceTo(l))<dist || temp <= v.getRaio()){
                closer = v;
                dist = temp;
            }
        }

        //System.out.println(closer);

        return closer;
    }
    
}
