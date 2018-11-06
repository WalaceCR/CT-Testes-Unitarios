package br.com.caelum.leilao.testes;

import br.com.caelum.leilao.dominio.*;
import org.junit.*;
import java.util.List;
import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.*;

public class AvaliadorTeste {


    private Avaliador leiloeiro;
    private Usuario joao;
    private Usuario jose;
    private Usuario maria;


    @BeforeClass
    public static void startClasse(){
        System.out.println("Inicia a classe");
    }


    @AfterClass
    public static void terminateClasse(){
        System.out.println("Termina a classe");
    }

    @Before
    public void criaAvaliador(){
        this.leiloeiro = new Avaliador();
        this.joao = new Usuario("Joao");
        this.jose = new Usuario("José");
        this.maria = new Usuario("Maria");
        System.out.println("Inicializando o teste");
    }



    @After
    public void finalizaTeste(){
        System.out.println("Finalizando o teste");
    }


    @Test
    public void verificandoOsLeiloesemOrdemCrescente() {
        //Cenário: 3 lances em ordem crescente

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,250.0));
        leilao.propoe(new Lance(jose,300.0));
        leilao.propoe(new Lance(maria,400.0));

        //execução da ação
        leiloeiro.avalia(leilao);

        //validação dos resultados
        // comparando a saida com o esperado
        double maiorEsperado = 400;
        double menorEsperado = 250;
        double mediaEsperada = 316.6;

        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        assertEquals(mediaEsperada, leiloeiro.getMedialances(), 0.1);

    }

    @Test
    public void verificandoOsLeiloesemOrdemCrescenteOutrosValores() {
        //Cenário: 3 lances em ordem crescente

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,2500.0));
        leilao.propoe(new Lance(jose,3000.0));
        leilao.propoe(new Lance(maria,4000.0));

        //execução da ação
        leiloeiro.avalia(leilao);

        //validação dos resultados
        // comparando a saida com o esperado
        double maiorEsperado = 4000;
        double menorEsperado = 2500;


        assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);


    }


    @Test
    public void deveEntenderLeilaoComApenasUmLance() {

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,1000.0));

        leiloeiro.avalia(leilao);

        assertEquals(1000, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(1000, leiloeiro.getMenorLance(), 0.0001);
    }



    @Test
    public void deveEncontrarOsTresMaioresLances() {

        Leilao criadorDeLeilao = new CriadorDeLeilao().para("Playstation 3 Novo")
                .lance(joao, 100.0)
                .lance(maria, 200.0)
                .lance(joao, 300.0)
                .lance(maria, 400.0)
                .constroi()
                ;


        leiloeiro.avalia(criadorDeLeilao);

        List<Lance> maiores = leiloeiro.getTresMaiores();

        assertEquals(3, maiores.size());
        assertEquals(400, maiores.get(0).getValor(), 0.00001);
        assertEquals(300, maiores.get(1).getValor(), 0.00001);
        assertEquals(200, maiores.get(2).getValor(), 0.00001);
    }


    @Test
    public void deveEntenderLeilaoComLancesEmOrdemRandomica() {

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,450.0));
        leilao.propoe(new Lance(joao,120.0));
        leilao.propoe(new Lance(maria,700.0));
        leilao.propoe(new Lance(joao,630.0));
        leilao.propoe(new Lance(maria,230.0));

        leiloeiro.avalia(leilao);

        assertEquals(700.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(120.0, leiloeiro.getMenorLance(), 0.0001);
    }

    @Test
    public void deveEntenderLeilaoComLancesEmOrdemDecrescente() {

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,400.0));
        leilao.propoe(new Lance(maria,300.0));
        leilao.propoe(new Lance(joao,200.0));
        leilao.propoe(new Lance(maria,100.0));

        leiloeiro.avalia(leilao);

        assertEquals(400.0, leiloeiro.getMaiorLance(), 0.0001);
        assertEquals(100.0, leiloeiro.getMenorLance(), 0.0001);
    }


    @Test
    public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
        Leilao leilao = new CriadorDeLeilao().para("Playstation 3 Novo").constroi();
        leiloeiro.avalia(leilao);
    }

}
