/* This file is part of the DualRpcServer libraries
 Copyright (c) 2004-2005, Vick Perry

 This library is free software; you can redistribute it and/or
 modify it under the terms of the BSD license. See the contents
 of the file LICENSE.txt for more details.
 */
package br.ufsc.inf.leobr.servidor;


public class ServidorMain {

	public static void main(String[] args) {
		try {
			ServerLauncher sl = new ServerLauncher();
			sl.start();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(0);
		}
	}
}

class ServerLauncher extends Thread {
	public void run() {
		Server server = new Server();
		try {
			server.listen();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
