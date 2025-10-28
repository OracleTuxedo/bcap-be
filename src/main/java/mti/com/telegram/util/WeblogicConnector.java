package mti.com.telegram.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import weblogic.wtc.gwt.TuxedoConnection;
import weblogic.wtc.gwt.TuxedoConnectionFactory;
import weblogic.wtc.jatmi.ApplicationToMonitorInterface;
import weblogic.wtc.jatmi.Reply;
import weblogic.wtc.jatmi.TPException;
import weblogic.wtc.jatmi.TPReplyException;
import weblogic.wtc.jatmi.TypedBuffer;
import weblogic.wtc.jatmi.TypedCArray;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.transaction.TransactionManager;
import javax.transaction.RollbackException;

/**
 * Api to Weblogic Connector
 */
public class WeblogicConnector {

    private static final Logger log = LogManager.getLogger(InterfaceTelegram.class);

    /// TODO Add exception related to WebClient connection
    public static byte[] connectTuxedo(byte[] request) throws ServletException, IOException, Exception {

        // byte[] output = directConnectTuxedo(request);

        /// For Development only
        byte[] output = throughWeblogic(request);

        return (output != null) ? output : new byte[0];
    }

    private static byte[] throughWeblogic(byte[] request) throws ServletException, IOException, Exception {
        log.info("WeblogicConnector.throughWeblogic");
        log.info("request [{}]", new String(request, StandardCharsets.UTF_8));
        // TODO: Configuration to application.properties
        // String baseUrlWeblogic = "http://localhost:7011/bcap";
        String baseUrlWeblogic = "https://0967c38ee258.ngrok-free.app/bcap";
        String url = baseUrlWeblogic + "/message/forward/weblogic";

        // Set headers to indicate plain text content
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setAccept(List.of(MediaType.APPLICATION_OCTET_STREAM));

        // Create the HTTP entity with the plain text body and headers
        HttpEntity<byte[]> requestEntity = new HttpEntity<byte[]>(request, headers);

        // Send the request and receive a response
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<byte[]> response = restTemplate.exchange(url, HttpMethod.POST,
                requestEntity, byte[].class);

        // Return the response body
        byte[] output = response.getBody();

        return output;
    }

    private static byte[] directConnectTuxedo(byte[] var0) throws ServletException, IOException, Exception {
        log.info("WeblogicConnector.directConnectTuxedo");
        TuxedoConnection var3 = null;

        byte[] var20 = new byte[0];
        try {
            InitialContext var1 = new InitialContext();
            TuxedoConnectionFactory var2 = (TuxedoConnectionFactory) var1.lookup("tuxedo.services.TuxedoConnection");
            var3 = var2.getTuxedoConnection();

            log.info("Request[" + var0.length + "] : [" + new String(var0) + "]");

            TypedCArray var4 = new TypedCArray();
            var4.carray = var0;
            var4.setSendSize(var0.length);
            Reply var6 = var3.tpcall("SLCFPROXY", var4, 0);
            TypedCArray var5 = (TypedCArray) var6.getReplyBuffer();
            var20 = var5.carray;

            log.info("Response[" + var20.length + "] : [" + new String(var20) + "]");

        } catch (NamingException var15) {
            log.info("Could not get TuxedoConnectionFactory : NamingException:\n" + var15.getMessage());
            log.info(var15.getMessage());
            // throw var15;
        } catch (TPReplyException var16) {
            log.info("tpcall threw TPReplyExcption " + var16);
            log.info(var16.getMessage());
            // throw var16;
        } catch (TPException var17) {
            log.info("tpcall threw TPException " + var17);
            log.info(var17.getMessage());
            // throw var17;
        } catch (Exception var18) {
            log.info("tpcall threw exception: " + var18);
            log.info(var18.getMessage());
            // throw var18;
        } finally {
            var3.tpterm();
        }

        return var20;
    }

    private static byte[] callTuxedoTypedCArray(byte[] input) throws ServletException, IOException {

        byte[] output = new byte[0];
        log.info("Initialize All Variable");
        try {
            log.info("InitialContext");
            javax.naming.Context myContext = new InitialContext();

            log.info("TransactionManager");
            TransactionManager tm = (javax.transaction.TransactionManager) myContext
                    .lookup("javax.transaction.TransactionManager");

            // Begin Transaction
            log.info("TransactionManager");
            tm.begin();

            log.info("TuxedoConnectionFactory");
            TuxedoConnectionFactory tuxConFactory = (TuxedoConnectionFactory) myContext
                    .lookup("tuxedo.services.TuxedoConnection");

            log.info("TuxedoConnection");
            TuxedoConnection myTux = tuxConFactory.getTuxedoConnection();

            log.info("TypedCArray Initialization");
            TypedCArray dataInput = new TypedCArray();
            dataInput.carray = input;
            dataInput.setSendSize(input.length);

            log.info("Reply SLCFPROXY");
            Reply reply = myTux.tpcall("SLCFPROXY", (TypedBuffer) dataInput, ApplicationToMonitorInterface.TPNOTRAN);

            log.info("TypedCArray Response");
            TypedCArray dataResponse = (TypedCArray) reply.getReplyBuffer();

            log.info("TypedCArray Response to byte[] ");
            output = dataResponse.carray;

            log.info("myTux.tpterm() tm.commit()");
            myTux.tpterm();
            tm.commit();

        } catch (NamingException ne) {
            log.info("ERROR: Naming Exception looking up JNDI: " + ne);

        } catch (RollbackException re) {
            log.info("ERROR: TRANSACTION ROLLED BACK: " + re);

        } catch (TPReplyException var16) {
            log.info("TPReplyException:" + var16);

            try {
                throw var16;
            } catch (TPReplyException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (TPException te) {
            log.info("ERROR: tpcall failed: TpException: " + te);

        } catch (Exception e) {
            log.info("ERROR: Exception: " + e);

        } finally {
            System.out.println("Output:");
            System.out.println(new String(output, StandardCharsets.UTF_8));
            // log.info(new String(output, StandardCharsets.UTF_8));
        }

        return output;
    }
}
