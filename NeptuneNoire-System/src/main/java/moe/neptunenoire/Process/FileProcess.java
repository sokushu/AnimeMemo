package moe.neptunenoire.Process;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author M
 *
 */
public class FileProcess {

	/**
	 *
	 */
	private LinkedList<String> filePaths = new LinkedList<>();

	/**
	 *
	 */
	private StringBuilder sb = new StringBuilder();

	/**
	 *
	 * @param path
	 */
	public FileProcess(String path) {
		filePaths.addLast(path);
	}

	/**
	 *
	 * @param path
	 */
	public void appPath(String path) {
		filePaths.addLast(sb.append(privateGetFilePath()).append(path).toString());
	}

	/**
	 *
	 */
	public void deletePath() {
		filePaths.set(filePaths.size()-1, null);
	}

	/**
	 *
	 * @param Path
	 */
	public void setPath(String Path) {
		filePaths.addLast(Path);
	}

	/**
	 *
	 * @param path
	 */
	public void setPath(String[] path) {
		for (String string : path) {
			filePaths.add(string);
		}
	}

	public void setPath(Collection<? extends String> c) {
		filePaths.addAll(c);
	}

	/**
	 *
	 * @return
	 */
	public boolean mkdirs() {
		List<Boolean> flag = new ArrayList<Boolean>();
		filePaths.forEach(filePath->{
			flag.add(new File(filePath).mkdirs());
		});
		return !flag.contains(false);
	}

	/**
	 *
	 * @return
	 */
	private String privateGetFilePath() {
		return filePaths.getLast();
	}

	/**
	 *
	 * @param file
	 * @param deleteMyself
	 * @return
	 */
	private boolean delete(File file, boolean deleteMyself) {
		List<Boolean> flag = new ArrayList<Boolean>();
		if (!file.exists()) return true;
		for (File var : file.listFiles()) {
			if (var.isDirectory()) {
				delete(var, deleteMyself);
			}
			flag.add(var.delete());
		}
		return !flag.contains(false);
	}

	/**
	 *
	 * @param deleteMyself
	 * @return
	 */
	public boolean delete(boolean deleteMyself) {
		List<Boolean> flag = new ArrayList<Boolean>();
		filePaths.forEach(filePath->{
			flag.add(delete(new File(filePath), deleteMyself));
		});
		return !flag.contains(false);
	}

}
