package maas.bcap.security;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.util.StreamUtils;

public class CachedBodyHttpServletRequest extends HttpServletRequestWrapper {

    private static final int MAX_BODY_SIZE = 1024 * 1024; // 1 MB

    private final byte[] cachedBody;

    public CachedBodyHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        int contentLength = request.getContentLength();
        if (contentLength > MAX_BODY_SIZE) {
            throw new IOException("Request body too large: " + contentLength + " bytes (max " + MAX_BODY_SIZE + ")");
        }
        this.cachedBody = StreamUtils.copyToByteArray(request.getInputStream());
        if (cachedBody.length > MAX_BODY_SIZE) {
            throw new IOException("Request body too large: " + cachedBody.length + " bytes (max " + MAX_BODY_SIZE + ")");
        }
    }

    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(cachedBody);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener readListener) {
                // not needed
            }

            @Override
            public int read() {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    public byte[] getCachedBody() {
        return cachedBody;
    }
}
