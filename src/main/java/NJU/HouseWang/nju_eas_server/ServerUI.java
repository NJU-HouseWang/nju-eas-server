package NJU.HouseWang.nju_eas_server;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

public class ServerUI {
	public ServerUI() {
		Display display = new Display();

		// 禁用掉了最大化按钮
		final Shell shell = new Shell(display, SWT.SHELL_TRIM ^ SWT.MAX);
		shell.setText("NJU_EAS_Server");

		// 取系统中预置的图标，省得测试运行时还得加个图标文件
		shell.setImage(display.getSystemImage(SWT.ICON_INFORMATION));

		// 构造系统栏控件
		final Tray tray = display.getSystemTray();
		final TrayItem trayItem = new TrayItem(tray, SWT.NONE);

		// 程序启动时，窗口是显示的，所以系统栏图标隐藏
		trayItem.setVisible(true);
		trayItem.setToolTipText("NJU_EAS_Server");

		trayItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				toggleDisplay(shell, tray);
			}
		});

		final Menu trayMenu = new Menu(shell, SWT.POP_UP);
		MenuItem statusItem = new MenuItem(trayMenu, SWT.PUSH);
		statusItem.setText(getServerStatus());

		MenuItem showMenuItem = new MenuItem(trayMenu, SWT.PUSH);
		showMenuItem.setText("显示窗口(&s)");

		// 显示窗口，并隐藏系统栏中的图标
		showMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				toggleDisplay(shell, tray);
			}
		});

		trayMenu.setDefaultItem(statusItem);

		new MenuItem(trayMenu, SWT.SEPARATOR);

		// 系统栏中的退出菜单，程序只能通过这个菜单退出
		MenuItem exitMenuItem = new MenuItem(trayMenu, SWT.PUSH);
		exitMenuItem.setText("退出程序(&q)");

		exitMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				shell.dispose();
			}
		});

		// 在系统栏图标点击鼠标右键时的事件，弹出系统栏菜单
		trayItem.addMenuDetectListener(new MenuDetectListener() {
			public void menuDetected(MenuDetectEvent e) {
				trayMenu.setVisible(true);
			}
		});

		trayItem.setImage(shell.getImage());

		// 注册窗口事件监听器
		shell.addShellListener(new ShellAdapter() {

			// 点击窗口最小化按钮时，窗口隐藏，系统栏显示图标
			public void shellIconified(ShellEvent e) {
				toggleDisplay(shell, tray);
			}

			// 点击窗口关闭按钮时，并不终止程序，而时隐藏窗口，同时系统栏显示图标
			public void shellClosed(ShellEvent e) {
				e.doit = false; // 消耗掉原本系统来处理的事件
				toggleDisplay(shell, tray);
			}
		});

		shell.setSize(320, 240);
		center(shell);
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * 窗口是可见状态时，则隐藏窗口，同时把系统栏中图标删除 窗口是隐藏状态时，则显示窗口，并且在系统栏中显示图标
	 * 
	 * @param shell
	 *            窗口
	 * @param tray
	 *            系统栏图标控件
	 */
	private void toggleDisplay(Shell shell, Tray tray) {
		try {
			shell.setVisible(!shell.isVisible());
			if (shell.getVisible()) {
				shell.setMinimized(false);
				shell.setActive();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 窗口居中显示
	 * 
	 * @param shell
	 *            要显示的窗口
	 */
	private void center(Shell shell) {
		Monitor monitor = shell.getMonitor();
		Rectangle bounds = monitor.getBounds();
		Rectangle rect = shell.getBounds();
		int x = bounds.x + (bounds.width - rect.width) / 2;
		int y = bounds.y + (bounds.height - rect.height) / 2;
		shell.setLocation(x, y);
	}

	private String getServerStatus() {
		return "null";
	}
}
