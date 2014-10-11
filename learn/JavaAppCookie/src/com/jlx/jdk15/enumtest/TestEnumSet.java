package com.jlx.jdk15.enumtest;

import java.util.EnumSet;

public class TestEnumSet {
	public enum ColorFeature {
		RED, BLUE, GREEN, YELLOW, BLACK
	};

	/**
	 * 测试TestEnumSet类的用法
	 * 
	 * @param args
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		EnumSet allFeatures = EnumSet.allOf(ColorFeature.class);
		EnumSet warmColorFeatures = EnumSet.of(ColorFeature.RED,
				ColorFeature.YELLOW);
		EnumSet non_warmColorFeatures = EnumSet.complementOf(warmColorFeatures);
		EnumSet notBlack = EnumSet.range(ColorFeature.RED, ColorFeature.YELLOW);

		for (ColorFeature cf : ColorFeature.values()) {
			if(warmColorFeatures.contains(cf)) {
				System.out.println("warmColor " + cf.name());
			}
			if(non_warmColorFeatures.contains(cf)) {
				System.out.println("non_WarmColor " + cf.name());
			}
		}
	}
}
