package ftp.view.listener;

import java.util.EventListener;

import ftp.view.event.ChangeDirEvent;

public interface ChangeDirListener extends EventListener {
	/**
	 * ¸Ä±äÄ¿Â¼
	 * @param e
	 */
	public void changeDir(ChangeDirEvent e);
}
