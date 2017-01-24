package com.gynt.widm.io;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.util.zip.ZipOutputStream;

import com.sun.nio.zipfs.ZipFileSystem;
import com.sun.nio.zipfs.ZipPath;

public class GameFileContext {

	private ZipFileSystem filesystem;
	private final Method flush;
	private ZipPath root;
	private File file;

	private GameFileContext(ZipFileSystem f, File f2) throws IOException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		filesystem = f;
		flush = filesystem.getClass().getDeclaredMethod("sync");
		flush.setAccessible(true);
		root = filesystem.getPath("/game/");
		file = f2;
	}

	public void reload() throws URISyntaxException, IOException {
		URI n = new URI("jar:" + file.toURI());
		if(file.exists()) {
			filesystem = (ZipFileSystem) FileSystems.getFileSystem(n);
		} else {
			filesystem = (ZipFileSystem) FileSystems.newFileSystem(n, null);
		}
	}

	public void storeAndReload() throws IOException, URISyntaxException {
		if(filesystem!=null) {
			if(filesystem.isOpen()) {
				filesystem.close();
			}
		}
		URI n = new URI("jar:" + file.toURI());
		System.out.println(n.toString());
		Map<String, String> env = new HashMap<String, String>();
		env.put("create", "true");
		filesystem = (ZipFileSystem) FileSystems.newFileSystem(n, env);
	}

	public static GameFileContext newInterface(File file) throws IOException, URISyntaxException, NoSuchMethodException,
			SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (!file.exists()) {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(file));
			// zos.putNextEntry(new ZipEntry("game"));
			zos.close();
		}
		Map<String, String> env = new HashMap<String, String>();
		env.put("create", "true");
		URI n = new URI("jar:" + file.toURI());
		FileSystem filesystem = FileSystems.newFileSystem(n, env);
		if (!Files.exists(filesystem.getPath("game"))) {
			Files.createDirectory(filesystem.getPath("game"));
			Files.createDirectory(filesystem.getPath("game").resolve("rounds"));
			Files.createDirectory(filesystem.getPath("game").resolve("resources"));
		}
		return new GameFileContext((ZipFileSystem) filesystem, file);
	}

	public Path getRoot() {
		return root;
	}

	public void save(File file, Path p) throws IOException, URISyntaxException {
		if(!filesystem.isOpen()) reload();
		Files.copy(file.toPath(), p, StandardCopyOption.REPLACE_EXISTING);
		storeAndReload();
	}

	public void save(byte[] data, String filename) throws IOException, URISyntaxException {
		save(data, root.resolve(filename));
	}

	public void save(byte[] data, Path p) throws IOException, URISyntaxException {
		if(!filesystem.isOpen()) reload();
		if (Files.exists(p, LinkOption.NOFOLLOW_LINKS)) {
			Files.copy(new ByteArrayInputStream(data), p, StandardCopyOption.REPLACE_EXISTING);
		} else {
			Files.write(p, data);
		}
		storeAndReload();
	}

	public byte[] load(String filename) throws IOException, URISyntaxException {
		if(!filesystem.isOpen()) reload();
		return load(root.resolve(filename));
	}

	public byte[] load(Path p) throws IOException, URISyntaxException {
		if(!filesystem.isOpen()) reload();
		return Files.readAllBytes(p);
	}

	public void delete(String filename) throws IOException {
		delete(filesystem.getPath(filename));
	}

	public void delete(Path p) throws IOException {
		Files.delete(p);
	}

	public void close() throws IOException {
		filesystem.close();
	}

	public void flush() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		flush.invoke(filesystem);
	}

	public static void main(String[] args)
			throws IOException, URISyntaxException, NoSuchFieldException, SecurityException, NoSuchMethodException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Debug
		GameFileContext fi = newInterface(new File("newgame.zip"));
		fi.save("bye world!".getBytes(), "test.txt");
		fi.flush();
	}

}
