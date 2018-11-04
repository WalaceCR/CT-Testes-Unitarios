package br.com.caelum.leilao.testes;

import br.com.caelum.leilao.dominio.Avaliador;
import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import org.junit.Assert;
import org.junit.Test;

public class AvaliadorTeste {

    @Test
    public void verificandoOsLeiloesemOrdemCrescente() {
        //Cenário: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,250.0));
        leilao.propoe(new Lance(jose,300.0));
        leilao.propoe(new Lance(maria,400.0));

        //execução da ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //validação dos resultados
        // comparando a saida com o esperado
        double maiorEsperado = 400;
        double menorEsperado = 250;
        double mediaEsperada = 316.6;

        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
        Assert.assertEquals(mediaEsperada, leiloeiro.getMedialances(), 0.1);

    }

    @Test
    public void verificandoOsLeiloesemOrdemCrescenteOutrosValores() {
        //Cenário: 3 lances em ordem crescente
        Usuario joao = new Usuario("Joao");
        Usuario jose = new Usuario("José");
        Usuario maria = new Usuario("Maria");

        Leilao leilao = new Leilao("Playstation 3 Novo");

        leilao.propoe(new Lance(joao,2500.0));
        leilao.propoe(new Lance(jose,3000.0));
        leilao.propoe(new Lance(maria,4000.0));

        //execução da ação
        Avaliador leiloeiro = new Avaliador();
        leiloeiro.avalia(leilao);

        //validação dos resultados
        // comparando a saida com o esperado
        double maiorEsperado = 4000;
        double menorEsperado = 2500;


        Assert.assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
        Assert.assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);


    }




}
