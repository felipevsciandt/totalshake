package armazem;

import ingredientes.*;
import armazem.Armazem;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArmazemTest extends Armazem {

    static Armazem armazem;
    static Base baseSorvete;

    @BeforeAll
    void setupBase() {
        baseSorvete = new Base(TipoBase.Sorvete);
    }

    @BeforeEach
    void setup() {
        armazem = new Armazem();
        baseSorvete = new Base(TipoBase.Sorvete);
    }

    @Test
    void testCadastrarIngredienteEmEstoque() {
        armazem.cadastrarIngredienteEmEstoque(baseSorvete);
        int quantidadeEmEstoque = armazem.consultarQuantidadeDoIngredienteEmEstoque(baseSorvete);

        assertEquals(0, quantidadeEmEstoque);

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> armazem.cadastrarIngredienteEmEstoque(baseSorvete)
        );
        assertTrue(thrown.getMessage().contains("Ingrediente já cadastrado"));
    }

    @Test
    void testDescadastrarIngredienteEmEstoque() {
        armazem.cadastrarIngredienteEmEstoque(baseSorvete);
        armazem.descadastrarIngredienteEmEstoque(baseSorvete);

        assertFalse(armazem.existeIngrediente(baseSorvete));

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> armazem.descadastrarIngredienteEmEstoque(baseSorvete)
        );
        assertTrue(thrown.getMessage().contains("Ingrediente não encontrado"));

    }

    @Test
    void testAdicionarQuantidadeDoIngredienteEmEstoque() {
        armazem.cadastrarIngredienteEmEstoque(baseSorvete);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, 3);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, 2);

        assertEquals(5, armazem.consultarQuantidadeDoIngredienteEmEstoque(baseSorvete));

        IllegalArgumentException thrownError = assertThrows(
                IllegalArgumentException.class,
                () -> armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, -10)
        );
        assertTrue(thrownError.getMessage().contains("Quantidade invalida"));

        armazem.descadastrarIngredienteEmEstoque(baseSorvete);
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, 3)
        );
        assertTrue(thrown.getMessage().contains("Ingrediente não encontrado"));

    }

    @Test
    void testReduzirQuantidadeDoIngredienteEmEstoque() {
        // REDUZINDO QTDE
        armazem.cadastrarIngredienteEmEstoque(baseSorvete);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, 5);
        armazem.reduzirQuantidadeDoIngredienteEmEstoque(baseSorvete, 2);
        assertEquals(3, armazem.consultarQuantidadeDoIngredienteEmEstoque(baseSorvete));

        // QTDE = 0
        armazem.reduzirQuantidadeDoIngredienteEmEstoque(baseSorvete, 3);
        assertFalse(existeIngrediente(baseSorvete));

        // QTDE INVALIDA
        armazem.cadastrarIngredienteEmEstoque(baseSorvete);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, 5);
        System.out.println(armazem.consultarQuantidadeDoIngredienteEmEstoque(baseSorvete));
        IllegalArgumentException thrownNegativeStock = assertThrows(
                IllegalArgumentException.class,
                () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(baseSorvete, 7)
        );
        assertTrue(thrownNegativeStock.getMessage().contains("Quantidade inválida"));

        // Sem cadastro
        armazem.descadastrarIngredienteEmEstoque(baseSorvete);
        IllegalArgumentException thrownIngredientNotFound = assertThrows(
                IllegalArgumentException.class,
                () -> armazem.reduzirQuantidadeDoIngredienteEmEstoque(baseSorvete, 2)
        );
        assertTrue(thrownIngredientNotFound.getMessage().contains("Ingrediente não encontrado"));

    }

    @Test
    void testConsultarQuantidadeDoIngredienteEmEstoque() {
        armazem.cadastrarIngredienteEmEstoque(baseSorvete);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, 1);
        assertEquals(1,armazem.consultarQuantidadeDoIngredienteEmEstoque(baseSorvete));

        armazem.descadastrarIngredienteEmEstoque(baseSorvete);

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> armazem.consultarQuantidadeDoIngredienteEmEstoque(baseSorvete)
        );
        assertTrue(thrown.getMessage().contains("Ingrediente não encontrado"));

    }
}