package pedido;

import ingredientes.Adicional;
import produto.TipoTamanho;

import java.util.ArrayList;
import java.util.List;

public class Pedido{

    private int id;
    private  ArrayList<ItemPedido> itens;
    private Cliente cliente;

    public Pedido(int id, ArrayList<ItemPedido> itens,Cliente cliente){
        this.id = id;
        this.itens=itens;
        this.cliente=cliente;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public int getId(){
        return this.id;
    }

    public Cliente getCliente(){
        return this.cliente;
    }

    public double calcularTotal(Cardapio cardapio){
        double total= 0;
        for (ItemPedido itemPedido : itens) {
            var base = itemPedido.getShake().getBase();
            var precoBase = cardapio.buscarPreco(base);
            var tamanho = itemPedido.getShake().getTipoTamanho();

            if (tamanho == TipoTamanho.P) {
                total += precoBase * itemPedido.getQuantidade();
            } else if (tamanho == TipoTamanho.M) {
                total += (precoBase * 1.3) * itemPedido.getQuantidade();
            } else {
                total += (precoBase * 1.5) * itemPedido.getQuantidade();
            }
        }
        return total+= this.calcularAdicionais(cardapio);
    }

    public double calcularAdicionais(Cardapio cardapio) {
        double total = 0;
        for (ItemPedido itemPedido : itens) {
            List<Adicional> adicionais = itemPedido.getShake().getAdicionais();
            for (Adicional adicional : adicionais) {
                total += cardapio.buscarPreco(adicional) * itemPedido.getQuantidade();
            }
        }
        return total;
    }

    public void adicionarItemPedido(ItemPedido itemPedidoAdicionado){
        //TODO
    }

    public boolean removeItemPedido(ItemPedido itemPedidoRemovido) {
        //substitua o true por uma condição
        if (true) {
            //TODO
        } else {
            throw new IllegalArgumentException("Item nao existe no pedido.");
        }
        return false;
    }

    @Override
    public String toString() {
        return this.itens + " - " + this.cliente;
    }
}
