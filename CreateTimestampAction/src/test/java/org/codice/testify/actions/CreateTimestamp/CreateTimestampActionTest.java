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

import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CreateTimestampActionTest {

    //Set objects
    CreateTimestampAction createTimestampAction = new CreateTimestampAction();


    @Test
    public void testBadTimeZone() {

        TestProperties testProperties = new TestProperties();
        testProperties.addProperty("Something", "Something");
        AllObjects.setObject("testProperties", testProperties);
        createTimestampAction.executeAction("ASVJF");
        TestProperties newTestProperties = (TestProperties) AllObjects.getObject("testProperties");
        assert ( !newTestProperties.propertyExists("testify.timestamp.asvjf") );

    }

    @Test
    public void testNilTimeZone() {

        TestProperties testProperties = new TestProperties();
        testProperties.addProperty("Something", "Something");
        AllObjects.setObject("testProperties", testProperties);
        createTimestampAction.executeAction(null);
        TestProperties newTestProperties = (TestProperties) AllObjects.getObject("testProperties");
        assert ( !newTestProperties.propertyExists("testify.timestamp.") );

    }

    @Test
    public void testRealTimeZone() {

        TestProperties testProperties = new TestProperties();
        testProperties.addProperty("Something", "Something");
        AllObjects.setObject("testProperties", testProperties);
        createTimestampAction.executeAction("UTC");
        TestProperties newTestProperties = (TestProperties) AllObjects.getObject("testProperties");
        assert ( newTestProperties.propertyExists("testify.timestamp.utc") );

    }

}