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
        int quantidade = armazem.consultarQuantidadeDoIngredienteEmEstoque(baseSorvete);
        assertEquals(0, quantidade);

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> armazem.cadastrarIngredienteEmEstoque(baseSorvete)
        );
        assertTrue(thrown.getMessage().contains("Ingrediente já cadastrado"));
    }

    @Test
    void testDescadastrarIngredienteEmEstoque() {
    }

    @Test
    void testAdicionarQuantidadeDoIngredienteEmEstoque() {
    }

    @Test
    void testReduzirQuantidadeDoIngredienteEmEstoque() {
    }

    @Test
    void testConsultarQuantidadeDoIngredienteEmEstoque() {
        armazem.cadastrarIngredienteEmEstoque(baseSorvete);
        armazem.adicionarQuantidadeDoIngredienteEmEstoque(baseSorvete, 1);
        assertEquals(1,armazem.consultarQuantidadeDoIngredienteEmEstoque(baseSorvete));

    }
}