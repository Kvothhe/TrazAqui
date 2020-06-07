import java.io.*;
import java.nio.file.Files;
import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.*;
import java.util.stream.Collectors;
import java.util.Scanner;

public class TrazAqui
{
    Map<String,Utilizador> utilizadores;
    Map<String,Loja> lojas;
    Map<String,Servico> servicos;
    
    public TrazAqui(){
        this.utilizadores = new HashMap<>();
        this.lojas = new HashMap<>();
        this.servicos = new HashMap<>();
    }
    
    public TrazAqui(Map<String,Utilizador> ut,Map<String,Loja> lj,Map<String,Servico> sv){
        setUtilizadores(ut);
        setLojas(lj);
        setServicos(sv);
    }
    
    public TrazAqui(TrazAqui t){
        setUtilizadores(t.getUtilizadoresC());
        setLojas(t.getLojasC());
        setServicos(t.getServicosC());
    }
    
    public void setUtilizadores(Map<String,Utilizador> utils){
        this.utilizadores = new HashMap<>();
        utils.entrySet().forEach(e->this.utilizadores.put(e.getKey(),
                    e.getValue().clone()));
    }
    
    public void setLojas(Map<String,Loja> lojs){
        this.lojas = new HashMap<>();
        lojs.entrySet().forEach(e->this.lojas.put(e.getKey(),
                    e.getValue().clone()));
    }
    
    public void setServicos(Map<String,Servico> srvs){
        this.servicos = new HashMap<>();
        srvs.entrySet().forEach(e->this.servicos.put(e.getKey(),
                    e.getValue().clone()));
    }
    
    public Map<String,Utilizador> getUtilizadoresC(){
        Map<String,Utilizador> res = new HashMap<>();
        for(Map.Entry<String,Utilizador> e : this.utilizadores.entrySet())
            res.put(e.getKey(),e.getValue().clone());
        return res;    
    }
    
    public Map<String,Loja> getLojasC(){
        Map<String,Loja> res = new HashMap<>();
        for(Map.Entry<String,Loja> e : this.lojas.entrySet())
            res.put(e.getKey(),e.getValue().clone());
        return res;    
    }
    
    public Map<String,Servico> getServicosC(){
        Map<String,Servico> res = new HashMap<>();
        for(Map.Entry<String,Servico> e : this.servicos.entrySet())
            res.put(e.getKey(),e.getValue().clone());
        return res;    
    }
    
    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("Utilizadores: ").append(this.utilizadores).append("\n").
        append("Lojas: ").append(this.lojas).append("\n").append("Servicos :").
        append(this.servicos);
        return s.toString();
    }
    
    public TrazAqui clone(){
        return new TrazAqui(this);
    }
    
    public void adicionaUti(Utilizador u) throws ExisteUtilizadorException{
        if(this.utilizadores.containsKey(u.getCodigo()))
            throw new ExisteUtilizadorException(u.getCodigo());
        else this.utilizadores.put(u.getCodigo(),u.clone());    
    }
    
    public void adicionaLoj(Loja l) throws ExisteLojaException{
        if(!this.lojas.containsKey(l.getCodLoja()))
            throw new ExisteLojaException(l.getCodLoja());
        else this.lojas.put(l.getCodLoja(),l.clone());
    }
    
    public void adicionaSer(Servico s) throws ExisteServicoException{
        if(!this.servicos.containsKey(s.getCodigo()))
            throw new ExisteServicoException(s.getCodigo());
        else this.servicos.put(s.getCodigo(),s.clone());    
    }
    
    public static void main(String args[]){
        TrazAqui app = new TrazAqui();
        //Utilizador u1 = new Utilizador("u48","Francisco",new Location(-97.28862,59.067047));
        Location tl = new Location(-97.84033,59.35376);
        
        EmpresaV TORRESTIR = new EmpresaV("t43","TORRESTIR - TRANSPORTES NACIONAIS E INTERNACIONAIS",tl,189.0,"212420781",0.5);
        TORRESTIR.setVelocidademed(20.0);
        
        Menu menu = new Menu();
        menu.initMenu();

        //System.out.println(tl.distanceTo(new Location(-97.28862,59.067047)));
    }
    /*
    public TrazAqui clone(){
        return new TrazAqui(this);
    }*/
}
