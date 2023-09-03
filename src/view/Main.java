package view;

import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		RedesController redesController = new RedesController();
		String url = "www.google.com.br";
		
		redesController.ip();
		redesController.ping(url);

	}

}
