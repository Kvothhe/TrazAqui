import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDate;

public class Encomenda
{
    private String referencia;
    private String cliente;
    private String fornecedor;
    private double peso;
    private boolean med;
    private double classificacao;
    private double preco;
    private LocalDate dataentrega;
    private LocalDate databusca;
    private List<LinhaEncomenda> produtos;
    
    public Encomenda(){
        this.referencia = "n\f";
        this.fornecedor = "n\f";
        this.cliente = "n\f";
        this.peso = 0;
        this.med = false;
        this.preco = 0;
        this.produtos = new ArrayList<LinhaEncomenda>();
        this.dataentrega = LocalDate.now();
        this.databusca = LocalDate.now();
    }
    
    public Encomenda(String r,String f,String c,double p,
                        LocalDate n,LocalDate o,List<LinhaEncomenda> l){
        this.referencia = r;
        this.fornecedor = f;
        this.cliente = c;
        this.peso = p;
        this.med = false;
        this.dataentrega = n;
        this.databusca = o;
        setProdutos(l);
    }
    
    public Encomenda(Encomenda e){
        this.referencia = e.getReferencia();
        this.fornecedor = e.getFornecedor();
        this.cliente = e.getCliente();
        this.peso = e.getPeso();
        this.produtos = e.getProdutos();
        this.dataentrega = e.getDataentrega();
    }
    
    public boolean equals(Object obj){
        if(obj==this) return true;
        if(obj==null || obj.getClass()!=this.getClass()) return false;
        Encomenda e = (Encomenda) obj;
        return this.fornecedor.equals(e.getFornecedor()) && 
               this.cliente.equals(e.getCliente()) &&
               this.peso == e.getPeso() && this.med == e.getMed() && 
               this.produtos.equals(e.getProdutos()) && 
               this.classificacao == e.getClassificacao() &&
               this.referencia.equals(e.getReferencia()) &&
               this.dataentrega.equals(e.getDataentrega());
    }
    
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.referencia + "\n" + this.fornecedor + "\n" + 
                        this.cliente + "\n" + this.peso 
                        + "\n" + this.med + "\n" + 
                        this.produtos.toString() + 
                        this.classificacao + "\n" + this.dataentrega
                        + "\n" + this.databusca);
        return sb.toString();                
    }
    
    public String getReferencia(){
        return this.referencia;
    }
    
    public double getClassificacao(){
        return this.classificacao;
    }
    
    public String getFornecedor(){
        return this.fornecedor;
    }
    
    public String getCliente(){
        return this.cliente;
    }
    
    public double getPeso(){
        return this.peso;
    }
    
    public boolean getMed(){
        return this.med;
    }
    
    public LocalDate getDataentrega(){
        return this.dataentrega;
    }
    
    public LocalDate getDatabusca(){
        return this.databusca;
    }
    
    public void setDataentrega(LocalDate t){
        this.dataentrega = t;
    }
    
    public void setDatabusca(LocalDate p){
        this.databusca = p;
    }
    
    public void setProdutos(List<LinhaEncomenda> p){
        this.produtos = new ArrayList<>();
        p.forEach(s -> {this.produtos.add(s.clone());});
    }
    
    public List<LinhaEncomenda> getProdutos(){
        return this.produtos.stream().
        map(LinhaEncomenda::clone).collect(Collectors.toList());    
    }
    
    public Encomenda clone(){
        return new Encomenda(this);
    }
    
    public void addProduto(LinhaEncomenda p){
        this.produtos.add(p);
    }
    
    public void removeProduto(LinhaEncomenda p){
        this.produtos.remove(p);
    }
}
