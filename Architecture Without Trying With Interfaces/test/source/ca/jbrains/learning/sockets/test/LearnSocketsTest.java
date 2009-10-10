package ca.jbrains.learning.sockets.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.junit.Assert;
import org.junit.Test;

public class LearnSocketsTest {
	private Socket client;
	private PrintWriter serverCanvas;
	private BufferedReader clientReader;
	private ServerSocket serverSocket;
	private Socket server;
	private Thread clientListens;
	private Thread serverSpeaks;

	@Test
	public void clientServerPair() throws Exception {
		serverSocket = new ServerSocket(8888, 5, InetAddress.getLocalHost());

		Writer clientReceivedCanvas = new StringWriter();
		final PrintWriter clientReceivedData = new PrintWriter(
				clientReceivedCanvas, true);
		
		serverSpeaks = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					server = serverSocket.accept();
					serverCanvas = new PrintWriter(server.getOutputStream(),
							true);
	
					serverCanvas.println("762");
					serverCanvas.println("stop");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "server speaks");

		clientListens = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					client = new Socket(InetAddress.getLocalHost(), 8888);
					clientReader = new BufferedReader(new InputStreamReader(
							client.getInputStream()));

					while (true) {
						String line = clientReader.readLine();
						if (line == null || "stop".equals(line))
							break;
						clientReceivedData.println(line);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, "client listens");
		
		serverSpeaks.start();
		clientListens.start();

		serverSpeaks.join();
		clientListens.join();
		Assert.assertEquals("762" + System.getProperty("line.separator"), clientReceivedCanvas.toString());
	}
}
