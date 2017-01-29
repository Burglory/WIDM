package com.gynt.widm.core.util;

import javax.swing.JTree;

public class JTreeUtil {

	public static void expandAllNodes(JTree tree) {
	    int j = tree.getRowCount();
	    int i = 0;
	    while(i < j) {
	        tree.expandRow(i);
	        i += 1;
	        j = tree.getRowCount();
	    }
	}
	
}
