/**
 * 
 */
package org.jlx.tools;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

/**
 * 扫描器，控制
 * 
 * @author jianglx
 * 
 */
public class Scanner {
	/* 线程池 */
	private ExecutorService exeservice = ThreadPoolService
			.getExecutorInstance();

	/* 排除的文件名称列表 */
	public static List<String> expFileNameList = new ArrayList<String>();

	/* 排除的目录名称列表 */
	public static List<String> expDirNameList = new ArrayList<String>();

	static {
		expFileNameList.add("Thumbs.db");
		expFileNameList.add("desktop.ini");
		expFileNameList.add("_desktop.ini");
		expFileNameList.add("~WRL");
		expFileNameList.add("~$");
		expFileNameList.add(".log");
		expFileNameList.add(".xml");
	}

	static {
		expDirNameList.add("java");
		expDirNameList.add("Java");
		expDirNameList.add("Bin");
		expDirNameList.add("bin");
		expDirNameList.add("jdk");
	}

	/**
	 * 每个文件夹作为一个单独的线程
	 * 
	 * @param file
	 * @return
	 */
	public List<URI> listFile(File file) {
		List<URI> result = new ArrayList<URI>();
		List<Future<List<URI>>> taskList = new ArrayList<Future<List<URI>>>();
		File[] fl = file.listFiles();
		try {
			for (File f : fl) {
				taskList.add(exeservice.submit(new DirChecker(f)));
			}

			for (int i = 0; i < taskList.size(); i++) {
				Future<List<URI>> fs = taskList.get(i);
				// get() blocks until completion:
				List<URI> list = fs.get();

				if (list != null) {
					result.addAll(list);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		exeservice.shutdownNow();
		return result;
	}

	/**
	 * 
	 * 
	 * @author jianglx
	 * 
	 */
	class DirChecker implements Callable<List<URI>> {
		private File file;

		public DirChecker(File f) {
			this.file = f;
		}

		public List<URI> call() throws Exception {
			//System.out.println("扫描目录:" + file.getAbsolutePath());
			return list(file);
		}

		/**
		 * 扫描目录里面的内容
		 * 
		 * @param file
		 * @return
		 */
		public List<URI> list(File file) {
			List<URI> result = new ArrayList<URI>();
			File[] fl = file.listFiles(new FNFilter());
			if (fl == null) {
				return result;
			}
			for (File f : fl) {
				if (f.isDirectory()) {
					result.addAll(list(f));
				} else {
					//System.out.println(f.toURI().toString().substring(28));
					System.out.println(f.toURI().toString());
					result.add(f.toURI());
				}
			}
			return result;
		}
	}

	/**
	 * 文件名过滤器
	 * 
	 * @author jianglx
	 * 
	 */
	class FNFilter implements FilenameFilter {

		public boolean accept(File dir, String name) {
			// 先过滤路径
			for (String dirname : expDirNameList) {
				if (dir.getAbsolutePath().indexOf(dirname) != -1) {
					return false;
				}
			}
			// 过滤名字
			for (String filename : expFileNameList) {
				if (name.indexOf(filename) != -1) {
					return false;
				}
			}
			return true;
		}

	}

}
