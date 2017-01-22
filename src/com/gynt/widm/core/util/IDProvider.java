package com.gynt.widm.core.util;

import java.util.UUID;

public class IDProvider {

	public static String provide() {
		return UUID.randomUUID().toString();
	}

}
