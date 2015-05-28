package org.codice.testify.actions.jetty;

import org.codice.testify.actions.Action;
import org.codice.testify.objects.AllObjects;
import org.codice.testify.objects.TestifyLogger;
import org.codice.testify.objects.TestProperties;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;

/**
 * The JettyServerAction class is a Testify Action service for starting and stopping a jetty server within testify
 * @see org.codice.testify.actions.Action
 */
public class JettyServerAction implements BundleActivator, Action {

    private final int expectedActionElements = 3;
    private final String START_OPTION_TEXT = "|START|";
    private final String START_OPERATION_TEXT = "START";
    private final String STOP_OPTION_TEXT = "|STOP|";
    private final String STOP_OPERATION_TEXT = "STOP";
    final TestProperties testProperties = (TestProperties) AllObjects.getObject("testProperties");

    @Override
    public void executeAction(String s) {

        String property = null;
        String host = null;
        int port;
        String context = null;
        String directory = null;
        String operation = null;

        TestifyLogger.debug("Running " + this.getClass().getSimpleName(), this.getClass().getSimpleName());

        if (s != null) {
            // Check for start option
            if (s.startsWith(START_OPTION_TEXT)) {
                s = s.substring(START_OPTION_TEXT.length(), s.length());
                operation = START_OPERATION_TEXT;
            }
            else if (s.startsWith(STOP_OPTION_TEXT)) {
                s = s.substring(START_OPTION_TEXT.length(), s.length());
                operation = STOP_OPERATION_TEXT;
            }
            else {
                TestifyLogger.warn("No operation specified, defaulting to: " + START_OPERATION_TEXT, this.getClass().getSimpleName());
                operation = START_OPERATION_TEXT;
            }
            // Split action info by "=="
            TestifyLogger.debug(s, this.getClass().getSimpleName());
            String[] actionElements = s.split("==");

            //If there are not enough action elements, then produce an error
            if (actionElements.length >= expectedActionElements) {
                property = actionElements[0];
                host = actionElements[1].substring(0, actionElements[1].indexOf(":"));
                port = Integer.parseInt(actionElements[1].substring(actionElements[1].indexOf(":") + 1, actionElements[1].indexOf("/")));
                context = actionElements[1].substring(actionElements[1].indexOf("/"), actionElements[1].length());
                directory = actionElements[2];

                TestifyLogger.debug("Starting server at host: " + host + ", port: " + port + ", directory: " + directory, this.getClass().getSimpleName());

                Server server = new Server(port);
                ResourceHandler resource_handler = new ResourceHandler();
                resource_handler.setDirectoriesListed(true);
                resource_handler.setWelcomeFiles(new String[]{ "index.html" });
                resource_handler.setResourceBase(directory);
                HandlerList handlers = new HandlerList();
                handlers.setHandlers(new Handler[] { resource_handler, new DefaultHandler() });
                server.setHandler(handlers);


//                ContextHandler contextHandler = new ContextHandler();
//                contextHandler.setContextPath(context);
//                contextHandler.setResourceBase(directory);
//                contextHandler.setClassLoader(Thread.currentThread().getContextClassLoader());
//                server.setHandler(contextHandler);

                try {
                    server.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    server.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                AllObjects.setObject(property, server);
                return;
            }
            else { TestifyLogger.error("Provided Action info: " + s + " is invalid, requires " + expectedActionElements + " elements but found: " + actionElements.length, this.getClass().getSimpleName()); }
        }
        else { TestifyLogger.error("Action info must be provided for this action", this.getClass().getSimpleName()); }
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        //Register the CreateTimestamp service
        bundleContext.registerService(Action.class.getName(), new JettyServerAction(), null);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {

    }
}
