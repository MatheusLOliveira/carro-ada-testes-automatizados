package model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarroTest {
    // F.I.R.S.T - Principios
    // F - Fast
    // I - Isolado/Independente
    // R - Repetable
    // S - Self-validating
    // T - Oportuno (TDD)

    @Test
    public void deveCriarUmCarroComTodosOsCampos() {

        Carro carro = new Carro("Preto", "BMW", "X1", 350);

        assertAll("Testando atributos do carro",
                () -> assertEquals("Preto", carro.getCor()),
                () -> assertEquals("BMW", carro.getMarca()),
                () -> assertEquals("X1", carro.getModelo()),
                () -> assertEquals(350, carro.getVelocidadeMaxima())
        );

    }

    @Test
    public void deveIniciarDesligado() {

        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)// Then (Então)
        assertFalse(carro.getLigado());

    }


    @Test
    public void deveLigarCorretamente() {

        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        carro.ligar();

        // Then (Então)
        assertTrue(carro.getLigado());
    }

    @Test
    public void deveRetornarUmAvisoSeJaEstiverLigado() {

        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        carro.ligar();
        carro.ligar();

        // Then (Então)
        assertTrue(carro.getLigado(), "O carro não deveria ser ligado novamente");

    }

    @Test
    public void deveDesligarCorretamente() {

        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        carro.ligar();
        carro.desligar();

        // Then (Então)
        assertFalse(carro.getLigado());

    }

    @Test
    public void deveRetornarUmAvisoSeDesligarComVelocidade() throws Exception {

        // Given (Dado)
        Carro carro = new Carro("Preto", "BMW", "X1", 350);

        // When (Quando)
        carro.ligar();
        carro.acelerar(20);
        carro.desligar();

        // Then (Então)
        assertTrue(carro.getLigado(), "O carro deve estar totalmente parado para desligar");

    }

    @Test
    public void deveRetornarUmAvisoSeJaEstiverDesligado() {

        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando)
        carro.desligar();

        // Then (Então)
        assertFalse(carro.getLigado(), "O carro já está desligado");

    }

    @Test
    public void deveIniciarComVelocidadeZero() {

        // Given (Dado)
        Carro carro = new Carro();

        // When (Quando) // Then (Então)
        assertEquals((Integer) 0, carro.getVelocidadeAtual());
    }

    @Test
    public void deveAcelerarCorretamente() throws Exception {

        // Given (Dado)
        Carro carro = new Carro("Preto", "BMW", "X1", 350);

        // When (Quando)
        carro.ligar();
        carro.acelerar(10);

        // Then (Então)
        assertEquals((Integer) 10, carro.getVelocidadeAtual());
    }

    @Test
    public void naoDeveUltrapassarAVelocidadeMaxima() throws Exception {

        // Given
        Carro carro = new Carro("Preto", "BMW", "X1", 200);

        // When
        carro.ligar();
        carro.acelerar(100);
        carro.acelerar(100);
        carro.acelerar(100);

        // Then
        assertEquals((Integer) 200, carro.getVelocidadeAtual(), "O carro deve estar totalmente parado para desligar");
    }

    @Test
    public void naoDeveTerVelocidadeInferiorAZero() throws Exception {

        // Given
        Carro carro = new Carro("Preto", "BMW", "X1", 350);
        carro.ligar();
        carro.acelerar(100);

        // When
        carro.frear(50);
        carro.frear(51);

        // Then
        assertEquals((Integer) 0, carro.getVelocidadeAtual());
    }

    @Test
    public void naoDeveFrearUmCarroDesligado() throws Exception {

        // Given
        Carro carro = new Carro("Preto", "BMW", "X1", 350);

        // When
        carro.frear(50);

        // Then
        assertEquals((Integer) 0, carro.getVelocidadeAtual());

    }

    @Test
    public void deveLancarExceptionEmCasoDeAceleracaoNegativa() {

        // Given
        Carro carro = new Carro();
        carro.ligar();

        // When // Then
        Throwable throwable =                               // runnable
                Assertions.assertThrows(Exception.class, () -> carro.acelerar(-10));

        Assertions.assertEquals("A aceleracao não pode ser menor que zero!", throwable.getMessage());

    }

    @Test
    public void deveLancarExceptionEmCasoDeAceleracaoComCarroDesligado() {

        // Given
        Carro carro = new Carro();

        // When
        Throwable throwable =
                Assertions.assertThrows(Exception.class, () -> carro.acelerar(-10));

        // Then
        Assertions.assertEquals("A aceleracao não pode ocorrer com o carro desligado!", throwable.getMessage());

    }

    @Test
    public void deveTrancarUmCarro() {

        // Given
        Carro carro = new Carro();

        // When
        carro.destrancar();
        carro.trancar();

        // Then
        assertTrue(carro.getTrancado());

    }

    @Test
    public void aoTrancarUmCarroJaTrancadoDeveRetornarUmAviso() {

        // Given
        Carro carro = new Carro();
        carro.ligar();

        // When
        carro.trancar();
        carro.trancar();

        // Then
        assertTrue(carro.getTrancado(), "Ja esta trancado");

    }

    @Test
    public void deveDestrancarUmCarro() {

        // Given
        Carro carro = new Carro();

        // When
        carro.destrancar();

        // Then
        assertFalse(carro.getTrancado());

    }

    @Test
    public void aoDestrancarUmCarroJaDestrancadoDeveRetornarUmAviso() {

        // Given
        Carro carro = new Carro();
        carro.ligar();

        // When
        carro.destrancar();
        carro.destrancar();

        // Then
        assertFalse(carro.getTrancado(), "Ja esta destrancado");

    }

    // TODO: Achar um método melhor para realizar o teste do método equals
    @Test
    public void deveCompararSeAsIntanciasSaoIguais() {

        // Given
        Carro carro1 = new Carro("Preto", "BMW", "X1", 350);
        Carro carro2 = new Carro("Branco", "BMW", "X1", 350);

        // When
        boolean comparacaoDosCarros = carro1.equals(carro2);

        // Then
        assertFalse(comparacaoDosCarros);

    }

    @Test
    public void deveAlterarACorDoCarro() {

        // Given
        Carro carro = new Carro("Preto", "BMW", "X1", 350);

        // When
        carro.setCor("Amarelo");

        // Then
        assertEquals("Amarelo", carro.getCor());

    }

    @Test
    public void deveMostrarEstadoAtualDeUmCarro() {

        // Given
        Carro carro = new Carro("Preto", "BMW", "X1", 350);

        // When
        carro.ligar();

        // Then
        assertEquals("Carro{" +
                        "cor='" + "Preto" + '\'' +
                        ", marca='" + "BMW" + '\'' +
                        ", modelo='" + "X1" + '\'' +
                        ", ligado=" + true +
                        ", velocidadeAtual=" + 0 +
                        ", velocidadeMaxima=" + 350 +
                        ", trancado=" + true +
                        '}',
                carro.toString());

    }
}
