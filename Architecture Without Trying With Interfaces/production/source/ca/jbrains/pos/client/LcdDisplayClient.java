package ca.jbrains.pos.client;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Scanner;

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
		while (true) {
			LcdDisplayClient lcdDisplayClient = new LcdDisplayClient();
			PrintWriter clientWriter = lcdDisplayClient.clientWriter;
			Sale sale = new Sale(InMemoryCatalog
					.with(new HashMap<String, Price>() {
						{
							put("078787987173", Price.lira(100));
							put("043100631309", Price.lira(200));
							put("5449000108425", Price.lira(300));
						}
					}), new TextDisplay(clientWriter));

			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			sale.onBarcode(input);
			lcdDisplayClient.close();
		}
	}
}
