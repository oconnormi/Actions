package org.codice.testify.actions.generatenonce;

import org.codice.testify.actions.generatenonce.GenerateNonceAction;
import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GenerateNonceActionTest {


    GenerateNonceAction generateNonceAction = new GenerateNonceAction();

    @Test
    public void testGenerateNonceWithProperty() {

        String nonce = null;
        TestProperties testProperties = new TestProperties();
        AllObjects.setObject("testProperties", testProperties);
        generateNonceAction.executeAction("nonce.property");

        nonce = testProperties.getFirstValue("nonce.property");

        assert(!nonce.isEmpty());
    }

    @Test
    public void testGenerateNonceWithoutProperty()
    {
        String nonce = null;
        TestProperties testProperties = new TestProperties();
        AllObjects.setObject("testProperties", testProperties);
        generateNonceAction.executeAction("");

        nonce = testProperties.getFirstValue("testify.nonce");

        assert(!nonce.isEmpty());
    }
}
