package br.com.caelum.leilao.dominio;

public class Avaliador {

    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double mediaDeTodos = Double.NEGATIVE_INFINITY;

    public void avalia(Leilao leilao) {
        Integer qtdLeiloes = leilao.getLances().size();
        Double somalances = 0.0;
        for(Lance lance : leilao.getLances()) {
            if(lance.getValor() > maiorDeTodos) {
                maiorDeTodos = lance.getValor();
            }
            if(lance.getValor() < menorDeTodos) {
                menorDeTodos = lance.getValor();
            }
            somalances += lance.getValor();
        }
        mediaDeTodos = somalances/qtdLeiloes;

    }

    public double getMaiorLance() {
        return maiorDeTodos;
    }

    public double getMenorLance(){
        return menorDeTodos;
    }

    public double getMedialances(){
        return mediaDeTodos;
    }

}
