package moe.neptunenoire.web.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Calendar;

/**
 *
 * @author M
 *
 */
public class SimpleLogger  {

	private final String ERROR = " | ERROR | ";

	private final String INFO = " | INFO | ";

	private boolean saveToFile = false;

	private File logFile = null;

	private BufferedWriter bw = null;

	private FileWriter fw = null;

	/**
	 *
	 */
	public SimpleLogger() {}

	/**
	 * PATH:<br>
	 * linux:/home/log/<br>
	 * windows:D:/log/<br>
	 * @param logSaveToFile_Path
	 */
	public SimpleLogger(String logSaveToFile_Path) {
		this();
		if (isNull(logSaveToFile_Path) == false) {
			logFile = new File(logSaveToFile_Path + "log_" + getNowTime() + ".log");
			if (!logFile.exists()) {
				try {
					logFile.createNewFile();
					fw = new FileWriter(logFile);
				} catch (IOException e) {
					saveToFile = false;
					info("====================================================");
					e.printStackTrace();
					info("====================================================");
				}
			}
			saveToFile = true;
		}
	}

	/**
	 *
	 * @param logSaveToFile_Path
	 * @return
	 */
	private boolean isNull(String logSaveToFile_Path) {
		return (logSaveToFile_Path == null || logSaveToFile_Path.length() == 0);
	}

	/**
	 *
	 * @param info
	 */
	public void info(String info) {

		String log = getNowTime()  + INFO + info;

		System.out.println(log);
		if (saveToFile == true) {
			saveToFile(log);
		}
	}

	/**
	 *
	 * @param e
	 */
	public void error(Throwable e) {
		StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw, true);
        e.printStackTrace(pw);
        pw.flush();
        sw.flush();

        error("!!!!!!!!!!!!!!!!!==========ERROR==========!!!!!!!!!!!!!!!!!");
        errorNeko();
        error(sw.toString());
        error("!!!!!!!!!!!!!!!!!========ERROR END========!!!!!!!!!!!!!!!!!");
	}

	private void errorNeko() {

		error("　　　　　／＞　 フ　　ERROR！？！！");
		error("　　　　　| 　_　 _|　　ERROR!!!");
		error("　 　　　／`ミ _x 彡　　");
		error("　　 　 /　　　 ❤ |　　ERROR!!!");
		error("　　　 /　 ヽ　　 ﾉ　　ERROR!!!");
		error("　／￣|　　 |　|　|　　");
		error("　| (￣ヽ＿_ヽ_)_)　　ERROR！！！");

	}

	/**
	 *
	 * @param error
	 */
	public void error(String error) {

		String log = getNowTime() + ERROR + error;

		System.err.println(log);
		if (saveToFile == true) {
			saveToFile(log);
		}
	}

	/**
	 *
	 * @return
	 */
	private String getNowTime() {
		Calendar time = Calendar.getInstance();

		StringBuilder sb = new StringBuilder();

		sb.append(time.get(Calendar.YEAR)+"年");
		sb.append((time.get(Calendar.MONTH) + 1) +"月");
		sb.append(time.get(Calendar.DAY_OF_MONTH)+"日");
		sb.append(time.get(Calendar.HOUR_OF_DAY)+"点");
		sb.append(time.get(Calendar.MINUTE)+"分");
		sb.append(time.get(Calendar.SECOND)+"秒");
		sb.append(".");
		sb.append(time.get(Calendar.MILLISECOND));

		return sb.toString();
	}

	/**
	 *
	 * @param log
	 */
	private void saveToFile(String log) {
		bw = new BufferedWriter(fw);
		try {
			bw.write(log);
			bw.newLine();
			bw.flush();
		} catch (IOException e) {
			saveToFile = false;
			info("====================================================");
			e.printStackTrace();
			info("====================================================");
		}
	}

	/**
	 *
	 */
	public void closeLog() {
		if (bw != null) {
			try {
				bw.close();
			} catch (IOException e) {
				saveToFile = false;
				info("====================================================");
				e.printStackTrace();
				info("====================================================");
			}
		}
		if (fw != null) {
			try {
				fw.close();
			} catch (IOException e) {
				saveToFile = false;
				info("====================================================");
				e.printStackTrace();
				info("====================================================");
			}
		}
	}
}

