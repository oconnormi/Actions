package org.codice.testify.actions.jetty;

import org.codice.testify.objects.AllObjects;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@RunWith(JUnit4.class)
public class TestJettyServerAction {
    JettyServerAction jettyServerAction = new JettyServerAction();

    @Test
    public void testStartServer() throws InterruptedException {
        String directory = System.getProperty("user.dir") + "/JettyServerAction/src/test/resources/foo";
        String host = "localhost:8181";
        String s = "|START|test.server==" + host + "/foo==" + directory;

        jettyServerAction.executeAction(s);

        assert(AllObjects.getObject("test.server") != null);

    }
}
