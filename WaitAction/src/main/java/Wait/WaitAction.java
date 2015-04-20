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

package Wait;

import org.codice.testify.actions.Action;
import org.codice.testify.objects.TestifyLogger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 * The WaitAction class is a Testify Action service that causes Testify to wait for a given period of time
 */
public class WaitAction implements BundleActivator, Action {

    @Override
    public void executeAction(String s) {

        TestifyLogger.debug("Running WaitAction", this.getClass().getSimpleName());

        //Have the program wait for the period of time set in the action info
        try {
            int time = Integer.parseInt(s); //action info should be in milliseconds
            TestifyLogger.debug("Waiting for " + time + " milliseconds", this.getClass().getSimpleName());
            Thread.sleep(time);
        } catch (Exception e) {
            TestifyLogger.error(e.getMessage(), this.getClass().getSimpleName());
        }
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {

        //Register the Wait service
        bundleContext.registerService(Action.class.getName(), new WaitAction(), null);

    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}