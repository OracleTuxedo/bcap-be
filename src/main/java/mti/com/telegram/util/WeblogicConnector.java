package mti.com.telegram.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import weblogic.wtc.gwt.TuxedoConnection;
import weblogic.wtc.gwt.TuxedoConnectionFactory;
import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedCArray;

import java.io.IOException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

/**
 * Api to Weblogic Connector
 */
public class WeblogicConnector {

    private static final Logger log = LogManager.getLogger(WeblogicConnector.class);

    /// TODO Add exception related to WebClient connection
    public static byte[] connectTuxedo(byte[] request) throws ServletException, IOException, Exception {

        byte[] output = directConnectTuxedo(request);

        return (output != null) ? output : new byte[0];
    }

    private static byte[] directConnectTuxedo(byte[] var0) throws ServletException, IOException, Exception {
        log.info("WeblogicConnector.directConnectTuxedo");
        TuxedoConnection var3 = null;

        byte[] var20 = new byte[0];
        try {
            InitialContext var1 = new InitialContext();
            TuxedoConnectionFactory var2 = (TuxedoConnectionFactory) var1.lookup("tuxedo.services.TuxedoConnection");
            var3 = var2.getTuxedoConnection();

            log.info("Tuxedo request size [{}] bytes", var0.length);

            TypedCArray var4 = new TypedCArray();
            var4.carray = var0;
            var4.setSendSize(var0.length);
            Reply var6 = var3.tpcall("SLCFPROXY", var4, 0);
            TypedCArray var5 = (TypedCArray) var6.getReplyBuffer();
            var20 = var5.carray;

            log.info("Tuxedo response size [{}] bytes", var20.length);

        } catch (NamingException var15) {
            log.error("Could not get TuxedoConnectionFactory: NamingException: {}", var15.getMessage());
            throw new Exception("Tuxedo connection failed", var15);
        } catch (TPReplyException var16) {
            log.error("tpcall threw TPReplyException: {}", var16.getMessage());
            throw new Exception("Tuxedo call failed", var16);
        } catch (TPException var17) {
            log.error("tpcall threw TPException: {}", var17.getMessage());
            throw new Exception("Tuxedo call failed", var17);
        } catch (Exception var18) {
            log.error("tpcall threw exception: {}", var18.getMessage());
            throw var18;
        } finally {
            if (var3 != null) {
                var3.tpterm();
            }
        }

        return var20;
    }
}
