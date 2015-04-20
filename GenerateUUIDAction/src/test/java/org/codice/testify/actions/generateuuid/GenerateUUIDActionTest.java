package org.codice.testify.actions.generateuuid;

import org.codice.testify.actions.generateuuid.GenerateUUIDAction;
import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class GenerateUUIDActionTest {


    GenerateUUIDAction generateUUIDAction = new GenerateUUIDAction();

    @Test
    public void testGenerateUUIDWithProperty() {

        String uuid = null;
        TestProperties testProperties = new TestProperties();
        AllObjects.setObject("testProperties", testProperties);
        generateUUIDAction.executeAction("uuid.property");

        uuid = testProperties.getFirstValue("uuid.property");

        assert(!uuid.isEmpty());
    }

    @Test
    public void testGenerateUUIDWithoutProperty()
    {
        String uuid = null;
        TestProperties testProperties = new TestProperties();
        AllObjects.setObject("testProperties", testProperties);
        generateUUIDAction.executeAction("");

        uuid = testProperties.getFirstValue("testify.uuid");

        assert(!uuid.isEmpty());
    }
}
