package ftp.view.event;

import java.util.EventObject;

public class ChangeDirEvent extends EventObject {

	private String targetPath;//目标目录
	
	/**
	 * 
	 * @param source
	 * @param path 目标目录
	 */
	public ChangeDirEvent(Object source, String path) {
		super(source);
		this.targetPath = path;
	}

	public String getTargetPath() {
		return targetPath;
	}

}
