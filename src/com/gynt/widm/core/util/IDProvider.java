package com.gynt.widm.core.util;

import java.util.UUID;

public class IDProvider {

	public static String provide() {
		return Long.toString(Math.abs(UUID.randomUUID().getMostSignificantBits()), 32).toString();
	}

}
