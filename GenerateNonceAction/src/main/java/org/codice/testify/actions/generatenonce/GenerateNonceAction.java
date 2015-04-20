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

package org.codice.testify.actions.generatenonce;

import org.codice.testify.actions.Action;
import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestifyLogger;
import org.codice.testify.objects.TestProperties;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The GenerateNonceAction class is a Testify Action service for generating a nonce value
 */
public class GenerateNonceAction implements BundleActivator, Action {

    @Override
    public void executeAction(String s) {

        TestifyLogger.debug("Running GenerateNonceAction", this.getClass().getSimpleName());

        //Grab the test properties
        TestProperties testProperties = (TestProperties) AllObjects.getObject("testProperties");

        if (s != null) {
            if (s.length() < 1) {
                TestifyLogger.debug("no property specified, defaulting to testify.nonce", this.getClass().getSimpleName());
                s = "testify.nonce";
            }
            else {
                TestifyLogger.debug("Nonce Property: " + s, this.getClass().getSimpleName());
            }
        }
        else {
            TestifyLogger.debug("Assertion String was null, defaulting to testify.nonce", this.getClass().getSimpleName());
            s = "testify.nonce";
        }
        String nonce = generateNonce();

        // Add nonce to testProperties
        TestifyLogger.debug("Storing nonce value: " + nonce + " in property: " + s, this.getClass().getSimpleName());
        testProperties.addProperty(s, nonce);
        AllObjects.setObject("testProperties", testProperties);
    }

    private String generateNonce() {
        // Generate Nonce value
        TestifyLogger.debug("Generating nonce using current time in milliseconds", this.getClass().getSimpleName());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String dateInString = "22-01-2015 10:20:56";
        Date date = null;
        try {
            date = sdf.parse(dateInString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        String nonce = String.valueOf(calendar.getTimeInMillis());
        return nonce;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {

        //Register the AddKeyStore service
        bundleContext.registerService(Action.class.getName(), new GenerateNonceAction(), null);

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
