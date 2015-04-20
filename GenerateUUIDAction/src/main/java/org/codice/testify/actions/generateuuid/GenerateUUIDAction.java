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

package org.codice.testify.actions.generateuuid;

import org.codice.testify.actions.Action;
import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestifyLogger;
import org.codice.testify.objects.TestProperties;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.UUID;

/**
 * The GenerateUUIDAction class is a Testify Action service for generating a uuid value
 */
public class GenerateUUIDAction implements BundleActivator, Action {

    @Override
    public void executeAction(String s) {

        TestifyLogger.debug("Running GenerateUUIDAction", this.getClass().getSimpleName());

        //Grab the test properties
        TestProperties testProperties = (TestProperties) AllObjects.getObject("testProperties");

        if (s != null) {
            if (s.length() < 1) {
                TestifyLogger.debug("no property specified, defaulting to testify.uuid", this.getClass().getSimpleName());
                s = "testify.uuid";
            }
            else {
                TestifyLogger.debug("UUID Property: " + s, this.getClass().getSimpleName());
            }
        }
        else {
            TestifyLogger.debug("Assertion String was null, defaulting to testify.uuid", this.getClass().getSimpleName());
            s = "testify.uuid";
        }
        String uuid = generateUUID();

        // Add nonce to testProperties
        TestifyLogger.debug("Storing uuid value: " + uuid + " in property: " + s, this.getClass().getSimpleName());
        testProperties.addProperty(s, uuid);
        AllObjects.setObject("testProperties", testProperties);
    }

    private String generateUUID() {
        // Generate UUID value
        TestifyLogger.debug("Generating UUID", this.getClass().getSimpleName());
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {

        //Register the AddKeyStore service
        bundleContext.registerService(Action.class.getName(), new GenerateUUIDAction(), null);

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
