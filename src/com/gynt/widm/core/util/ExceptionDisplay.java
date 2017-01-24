package com.gynt.widm.core.util;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.JOptionPane;

public class ExceptionDisplay {

	public static void raiseNewExceptionDisplay(Exception e) {
		raiseNewExceptionDisplay(e, "");
	}

	public static void raiseNewExceptionDisplay(Exception e, String message) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.flush();
		JOptionPane.showMessageDialog(null, "An error occurred: "+message+" \n\n Java StackTrace: \n\n" + sw.toString(), "Fatal error", JOptionPane.ERROR_MESSAGE);
	}

}
