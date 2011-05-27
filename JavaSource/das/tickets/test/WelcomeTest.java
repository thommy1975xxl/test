package das.tickets.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import org.jboss.jsfunit.jsfsession.JSFServerSession;
import org.jboss.jsfunit.jsfsession.JSFSession;

public class WelcomeTest extends org.apache.cactus.ServletTestCase {

	public static Test suite() {
		return new TestSuite(WelcomeTest.class);
	}

	public void testInitialPage() throws Exception {
		JSFSession jsfSession = new JSFSession("/pages/welcome.xhtml");
		JSFServerSession jsfServerSession = jsfSession.getJSFServerSession();
		assertEquals("/pages/welcome.xhtml",
				jsfServerSession.getCurrentViewID());
	}
}
