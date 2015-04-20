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

import org.codice.testify.actions.Action;
import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestifyLogger;
import org.codice.testify.objects.TestProperties;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The AddKeyStoreAction class is a Testify Action service for setting keyStore properties as system properties
 */
public class AddKeyStoreAction implements BundleActivator, Action {

    @Override
    public void executeAction(String s) {

        TestifyLogger.debug("Running AddKeyStoreAction", this.getClass().getSimpleName());

        //Grab the test properties
        TestProperties testProperties = (TestProperties) AllObjects.getObject("testProperties");

        //If the keyStore property is set in the config file, set it as a system property
        if (testProperties != null && testProperties.propertyExists("testify.keyStore")) {
            TestifyLogger.info("Found keystore, adding to system", this.getClass().getSimpleName());
            System.setProperty("javax.net.ssl.keyStore", testProperties.getFirstValue("testify.keyStore"));
        } else {
            TestifyLogger.error("testify.keyStore property is not set", this.getClass().getSimpleName());
        }

        //If the keyStore password property is set in the config file, set it as a system property
        if (testProperties != null && testProperties.propertyExists("testify.storePass")) {
            TestifyLogger.info("Found storepass, adding to system", this.getClass().getSimpleName());
            System.setProperty("javax.net.ssl.keyStorePassword", testProperties.getFirstValue("testify.storePass"));
        } else {
            TestifyLogger.error("testify.storePass property is not set", this.getClass().getSimpleName());
        }

    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {

        //Register the AddKeyStore service
        bundleContext.registerService(Action.class.getName(), new AddKeyStoreAction(), null);

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}