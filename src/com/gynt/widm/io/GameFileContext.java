package com.gynt.widm.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import com.sun.nio.zipfs.ZipFileSystem;
import com.sun.nio.zipfs.ZipPath;

public class GameFileContext {

	private File file;
	private VirtualZipFile vzip;

	public static class ZipFilePath {

	}

	private static class VirtualZipFile {

		private HashMap<String, byte[]> map = new HashMap<String, byte[]>();

		public void put(String join, byte[] bytes) {
			map.put(join, bytes);
		}

		public byte[] get(String join) {
			return map.get(join);
		}

		public boolean has(String join) {
			return map.containsKey(join);
		}

		public Set<Entry<String, byte[]>> entries() {
			return map.entrySet();
		}

	}

	public GameFileContext(File f) throws ZipException, IOException {
		file = f;
		vzip=new VirtualZipFile();
	}

	public boolean exists(String...path) {
		return vzip.has(String.join("/", path));
	}

	public byte[] retrieve(String...path) {
		return vzip.get(String.join("/", path));
	}

	public void store(byte[] bytes, String... path) {
		vzip.put(String.join("/", path), bytes);
	}

	public void load() throws IOException {
		vzip.map.clear();
		ZipInputStream zis = new ZipInputStream(new FileInputStream(file));
		ZipEntry ze = zis.getNextEntry();
		while(ze!=null) {
			byte[] data = new byte[(int) ze.getSize()];
			zis.read(data);
			vzip.put(ze.getName(), data);
			ze=zis.getNextEntry();
		}
		zis.close();
	}

	public void save() throws IOException {
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file));
		for(Entry<String, byte[]> entry : vzip.entries()) {
			zos.putNextEntry(new ZipEntry(entry.getKey()));
			zos.write(entry.getValue());
			zos.closeEntry();
		}
		zos.finish();
		zos.close();
	}

//	@Deprecated
//	public byte[] coldretrieve(String... path) throws IOException {
//		ZipEntry z = zip.getEntry(String.join("/", path));
//		if(z==null) throw new RuntimeException();
//		InputStream zi = zip.getInputStream(z);
//		byte[] result = new byte[(int) z.getSize()];
//		int read = zi.read(result);
//		if(read!=z.getSize()) throw new RuntimeException();
//		return result;
//	}



}
