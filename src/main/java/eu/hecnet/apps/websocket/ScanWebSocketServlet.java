package eu.hecnet.apps.websocket;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.WebSocket;
import org.eclipse.jetty.websocket.WebSocketServlet;

public class ScanWebSocketServlet extends WebSocketServlet {

	private static final long serialVersionUID = 1L;

	public final Set<ScanWebSocket> scanStreams = new CopyOnWriteArraySet<ScanWebSocket>();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}

	public WebSocket doWebSocketConnect(HttpServletRequest request,
			String protocol) {
		return new ScanWebSocket(scanStreams);
	}
}
