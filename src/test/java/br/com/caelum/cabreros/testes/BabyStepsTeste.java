package br.com.caelum.cabreros.testes;
import br.com.caelum.cabreros.BabySteps;
import org.junit.Test;

import static org.junit.Assert.*;

public class BabyStepsTeste {

    @Test
    public void testarSoma(){
        BabySteps step = new BabySteps();
        assertEquals(4, step.soma(2,2));
    }

}
