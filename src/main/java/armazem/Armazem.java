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
        if (!existeIngrediente(ingrediente)) {
            estoque.put(ingrediente, 0);
        } else {
            throw new IllegalArgumentException("Ingrediente já cadastrado.");
        }
    }

    public void descadastrarIngredienteEmEstoque(Ingrediente ingrediente) {
        if (existeIngrediente(ingrediente)) {
            estoque.remove(ingrediente);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado");
        }
    }

    public void adicionarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        if (existeIngrediente(ingrediente) && quantidadeASerAlteradaIngredienteValida(quantidade)) {
            int estoqueAtual = consultarQuantidadeDoIngredienteEmEstoque(ingrediente);
            estoque.put(ingrediente, quantidade + estoqueAtual);
        } else if (!quantidadeASerAlteradaIngredienteValida(quantidade)) {
            throw new IllegalArgumentException("Quantidade invalida");
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado");
        }
    }

    public void reduzirQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente, Integer quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade inválida");
        } else {
            if (estoque.containsKey(ingrediente)) {
                var estoqueAtual = consultarQuantidadeDoIngredienteEmEstoque(ingrediente) - quantidade;
                if (estoqueAtual < 0) {
                    throw new IllegalArgumentException("Quantidade inválida");
                } else if (estoqueAtual == 0) {
                    estoque.remove(ingrediente);
                } else if (estoqueAtual > 0) {
                    estoque.replace(ingrediente, estoque.get(ingrediente) - quantidade);
                }
            } else {
                throw new IllegalArgumentException("Ingrediente não encontrado");
            }
        }

    }

    public int consultarQuantidadeDoIngredienteEmEstoque(Ingrediente ingrediente) {
        if (existeIngrediente(ingrediente)) {
            return estoque.get(ingrediente);
        } else {
            throw new IllegalArgumentException("Ingrediente não encontrado");
        }
    }

    public boolean existeIngrediente(Ingrediente ingrediente) {
        if (estoque.containsKey(ingrediente)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean quantidadeASerAlteradaIngredienteValida(int quantidade) {
        return quantidade > 0;
    }


}
