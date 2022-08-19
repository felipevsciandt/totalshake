package armazem;

import ingredientes.Ingrediente;

import java.util.List;
import java.util.TreeMap;

public class Armazem {
    private TreeMap<Ingrediente, Integer> estoque;

    public Armazem(){
        this.estoque= new TreeMap<>();
    }

    public void cadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        if (!estoque.containsKey(ingrediente)) {
            estoque.put(ingrediente, 0);
        } else {
            throw new IllegalArgumentException("Ingrediente já cadastrado.");
        }
    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {

    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        if (estoque.containsKey(ingrediente)) {
            estoque.put(ingrediente, quantidade);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado");
        }
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {

    }

    public int consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {
        if (estoque.containsKey(ingrediente)) {
            return estoque.get(ingrediente);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado");
        }
    }



}
