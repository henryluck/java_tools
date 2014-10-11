package com.jlx.console;

public class EchoArgs {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length == 0 ||
			      (args.length == 1 && args[0].equals("-help")))
			  {
			    System.out.println("\nUsage:  java EchoArgs uri");
			    System.out.println("   where uri is the URI of the XML " + 
			                       "document you want to print.");
			    System.out.println("   Sample:  java EchoArgs sonnet.xml");
			    System.out.println("\nParses an XML document, then writes " + 
			                       "the DOM tree to the console.");
			    System.exit(1);
			  }
		System.out.println("The args is right!");

	}

}
