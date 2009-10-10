package ca.jbrains.pos.client;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;

import ca.jbrains.pos.test.TextDisplay;
import ca.jbrains.pos.test.Price;
import ca.jbrains.pos.test.Sale;
import ca.jbrains.pos.test.InMemoryCatalogTest.InMemoryCatalog;

public class LcdDisplayClient {
	private Socket client;
	public PrintWriter clientWriter;

	public LcdDisplayClient() {
		try {
			client = new Socket("mel.local", 20000);
			clientWriter = new PrintWriter(client.getOutputStream(), true);
		} catch (Exception wrapped) {
			throw new RuntimeException(
					"Could not initialize LCD display client", wrapped);
		}
	}

	public void display(String text) {
		try {
			clientWriter.print(text + System.getProperty("line.separator"));
			clientWriter.flush();
		} catch (Exception wrapped) {
			throw new RuntimeException("Could not display text", wrapped);
		}
	}

	public void close() {
		try {
			client.close();
		} catch (Exception wrapped) {
			throw new RuntimeException("Could not close connection", wrapped);
		}
	}

	public static void main(String... args) throws Exception {
		Sale sale = new Sale(InMemoryCatalog.with(new HashMap<String, Price>() {
			{
				put("12345", Price.lira(100));
			}
		}), new TextDisplay(new LcdDisplayClient().clientWriter));
		sale.onBarcode("12345");
		sale.onBarcode("23456");
	}
}
