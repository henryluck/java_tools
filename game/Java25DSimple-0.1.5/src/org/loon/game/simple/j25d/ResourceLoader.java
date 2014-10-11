package org.loon.game.simple.j25d;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.WeakHashMap;


/**
 * <p>
 * Title: LoonFramework
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2007
 * </p>
 * <p>
 * Company: LoonFramework
 * </p>
 * 
 * @author chenpeng
 * @email：ceponline@yahoo.com.cn
 * @version 0.1
 */
public abstract class ResourceLoader {

	final static private ClassLoader classLoader = Thread.currentThread()
			.getContextClassLoader();

	final static private WeakHashMap lazyMap = new WeakHashMap(100);

	public static void clearLazy() {
		lazyMap.clear();
	}

	public void finalize() {
		clearLazy();
	}

	final static public byte[] getHttpStream(final String uri) {
		URL url;
		try {
			url = new URL(uri);
		} catch (Exception e) {
			return null;
		}
		InputStream is = null;
		try {
			is = url.openStream();
		} catch (Exception e) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] arrayByte = null;
		try {
			arrayByte = new byte[2048];
			int read;
			while ((read = is.read(arrayByte)) >= 0) {
				byteArrayOutputStream.write(arrayByte, 0, read);
			}
			arrayByte = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			return null;
		} finally {
			try {
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
					byteArrayOutputStream = null;
				}
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}

		return arrayByte;
	}

	public static InputStream getResourceToInputStream(final String fileName) {
		return new ByteArrayInputStream(getResource(fileName));
	}

	public static boolean isExists(String fileName) {
		return new File(fileName).exists();
	}

	public static byte[] getResource(final String fileName) {
		String innerName = fileName;
		String keyName = innerName.replaceAll(" ", "").toLowerCase();
		byte[] result = (byte[]) lazyMap.get(keyName);
		if (result == null) {
			BufferedInputStream bufferedInput = null;
			boolean canInner = innerName.startsWith(".")
					|| innerName.startsWith("/");
			if (!isExists(innerName) && !canInner) {
				innerName = ("/" + innerName).intern();
				canInner = true;
			}
			if (canInner) {
				if (innerName.startsWith(".")) {
					innerName = innerName.substring(1, innerName.length());
				}
				innerName = LSystem.replaceIgnoreCase(innerName, "\\", "/");
				innerName = innerName.substring(1, innerName.length());
			} else {
				if (innerName.startsWith("\\")) {
					innerName = innerName.substring(1, innerName.length());
				}
			}
			if (!canInner) {
				try {
					bufferedInput = new BufferedInputStream(
							new FileInputStream(new File(innerName)));
				} catch (FileNotFoundException ex) {
					throw new RuntimeException(ex);
				}
			} else {
				bufferedInput = new BufferedInputStream(classLoader
						.getResourceAsStream(innerName));
			}
			lazyMap.put(keyName, result = getDataSource(bufferedInput));
		}
		return result;
	}

	final static private byte[] getDataSource(InputStream is) {
		if (is == null) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] arrayByte = new byte[8192];
		try {
			int read;
			while ((read = is.read(arrayByte)) >= 0) {
				byteArrayOutputStream.write(arrayByte, 0, read);
			}
			arrayByte = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
					byteArrayOutputStream = null;
				}
				if (is != null) {
					is.close();
					is = null;
				}
			} catch (IOException e) {
			}
		}
		return arrayByte;
	}

}
