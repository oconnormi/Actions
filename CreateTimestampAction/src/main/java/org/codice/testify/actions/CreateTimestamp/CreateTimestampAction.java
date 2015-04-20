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

package org.codice.testify.actions.CreateTimestamp;

import org.codice.testify.actions.Action;
import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestifyLogger;
import org.codice.testify.objects.TestProperties;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * The CreateTimestampAction class is a Testify Action service for setting a timestamp property using the current date/time and a user provided timezone
 */
public class CreateTimestampAction implements BundleActivator, Action {

    @Override
    public void executeAction(String s) {

        TestifyLogger.debug("Running CreateTimestampAction", this.getClass().getSimpleName());

        //Grab the test properties
        TestProperties testProperties = (TestProperties) AllObjects.getObject("testProperties");

        //Check that the time zone matches one of the supported IDs
        List<String> zones = Arrays.asList(TimeZone.getAvailableIDs());
        if (!zones.contains(s)) {
            TestifyLogger.error("CreateTimestampAction does not support the provided time zone: " + s, this.getClass().getSimpleName());

        } else {

            //Get the current time stamp using the time zone provided in the action info
            TimeZone timeZone = TimeZone.getTimeZone(s);
            DateFormat baseTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            baseTime.setTimeZone(timeZone);
            String currentTime = baseTime.format(new Date());

            //Store the timestamp in test properties under the name testify.timestamp.{code}
            testProperties.addProperty("testify.timestamp." + s.toLowerCase(), currentTime);

            //Store the properties back into AllObjects
            AllObjects.setObject("testProperties", testProperties);

        }
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {

        //Register the CreateTimestamp service
        bundleContext.registerService(Action.class.getName(), new CreateTimestampAction(), null);

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}