/*
 * Copyright 2015 Codice Foundation
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package AddKeyStore;

import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class AddKeyStoreActionTest {

    //Set objects
    AddKeyStoreAction addKeyStoreAction = new AddKeyStoreAction();

    @Test
    public void testNoTestPropertiesSet() {

        System.setProperty("javax.net.ssl.keyStore", "null");
        System.setProperty("javax.net.ssl.keyStorePassword", "null");
        TestProperties testProperties = null;
        AllObjects.setObject("testProperties", testProperties);
        addKeyStoreAction.executeAction(null);
        assert ( System.getProperty("javax.net.ssl.keyStore").equals("null") );
        assert ( System.getProperty("javax.net.ssl.keyStorePassword").equals("null") );

    }

    @Test
    public void testNoKeyStorePropertiesSet() {

        System.setProperty("javax.net.ssl.keyStore", "null");
        System.setProperty("javax.net.ssl.keyStorePassword", "null");
        TestProperties testProperties = new TestProperties();
        testProperties.addProperty("Something", "Something");
        AllObjects.setObject("testProperties", testProperties);
        addKeyStoreAction.executeAction(null);
        assert ( System.getProperty("javax.net.ssl.keyStore").equals("null") );
        assert ( System.getProperty("javax.net.ssl.keyStorePassword").equals("null") );

    }

    @Test
    public void testKeyStoreNoPass() {

        System.setProperty("javax.net.ssl.keyStore", "null");
        System.setProperty("javax.net.ssl.keyStorePassword", "null");
        TestProperties testProperties = new TestProperties();
        testProperties.addProperty("testify.keyStore", "keystore");
        AllObjects.setObject("testProperties", testProperties);
        addKeyStoreAction.executeAction(null);
        assert ( System.getProperty("javax.net.ssl.keyStore").equals("keystore") );
        assert ( System.getProperty("javax.net.ssl.keyStorePassword").equals("null") );

    }

    @Test
    public void testPassNoKeyStore() {

        System.setProperty("javax.net.ssl.keyStore", "null");
        System.setProperty("javax.net.ssl.keyStorePassword", "null");
        TestProperties testProperties = new TestProperties();
        testProperties.addProperty("testify.storePass", "storepass");
        AllObjects.setObject("testProperties", testProperties);
        addKeyStoreAction.executeAction(null);
        assert ( System.getProperty("javax.net.ssl.keyStore").equals("null") );
        assert ( System.getProperty("javax.net.ssl.keyStorePassword").equals("storepass") );

    }

    @Test
    public void testKeyStoreAndPass() {

        System.setProperty("javax.net.ssl.keyStore", "null");
        System.setProperty("javax.net.ssl.keyStorePassword", "null");
        TestProperties testProperties = new TestProperties();
        testProperties.addProperty("testify.keyStore", "keystore");
        testProperties.addProperty("testify.storePass", "storepass");
        AllObjects.setObject("testProperties", testProperties);
        addKeyStoreAction.executeAction(null);
        assert ( System.getProperty("javax.net.ssl.keyStore").equals("keystore") );
        assert ( System.getProperty("javax.net.ssl.keyStorePassword").equals("storepass") );

    }

}