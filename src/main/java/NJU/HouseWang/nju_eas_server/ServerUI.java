package NJU.HouseWang.nju_eas_server;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MenuDetectListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

import NJU.HouseWang.nju_eas_server.data.AuthorityManager;
import NJU.HouseWang.nju_eas_server.net.Server;

public class ServerUI {
	private static boolean serverStatus = false;
	private Server server = null;
	private AuthorityManager am = null;
	private Thread t = null;
	private MenuItem statusItem = null;
	private Label statusLabel = null;
	private TrayItem trayItem = null;

	public ServerUI() {
		Display display = new Display();

		// 禁用掉了最大化按钮
		final Shell shell = new Shell(display, SWT.SHELL_TRIM ^ SWT.MAX);
		shell.setText("NJU_EAS_Server");

		// 取系统中预置的图标，省得测试运行时还得加个图标文件
		shell.setImage(display.getSystemImage(SWT.ICON_INFORMATION));

		// 构造系统栏控件
		final Tray tray = display.getSystemTray();
		trayItem = new TrayItem(tray, SWT.NONE);

		// 程序启动时，窗口是显示的，所以系统栏图标隐藏
		trayItem.setVisible(true);
		trayItem.setToolTipText("NJU_EAS_Server");
		// 显示状态的标签
		statusLabel = new Label(shell, SWT.PUSH);
		statusLabel.setText("服务器当前状态：" + getServerStatus());
		statusLabel.setFont(new Font(display, "微软雅黑", 14, 0));
		statusLabel.setBounds(60, 30, 400, 40);

		// 控制服务器开关的按钮
		Button switchButton = new Button(shell, SWT.PUSH);
		switchButton.setText("开/关服务器");
		switchButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				switchServerStatus();
			}
		});
		switchButton.setBounds(80, 85, 150, 40);

		trayItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				toggleDisplay(shell, tray);
			}
		});

		final Menu trayMenu = new Menu(shell, SWT.POP_UP);
		statusItem = new MenuItem(trayMenu, SWT.PUSH);
		statusItem.setText("当前状态：" + getServerStatus());

		MenuItem switchItem = new MenuItem(trayMenu, SWT.PUSH);
		switchItem.setText("开启/关闭服务器(&o)");

		// 切换服务器状态
		switchItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				switchServerStatus();
			}
		});

		new MenuItem(trayMenu, SWT.SEPARATOR);

		MenuItem showMenuItem = new MenuItem(trayMenu, SWT.PUSH);
		showMenuItem.setText("显示窗口(&s)");

		// 显示窗口，并隐藏系统栏中的图标
		showMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				toggleDisplay(shell, tray);
			}
		});

		trayMenu.setDefaultItem(statusItem);

		// 系统栏中的退出菜单，程序只能通过这个菜单退出
		MenuItem exitMenuItem = new MenuItem(trayMenu, SWT.PUSH);
		exitMenuItem.setText("退出程序(&q)");

		exitMenuItem.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				server.shutdown();
				am.shutdown();
				serverStatus = false;
				t.interrupt();
				shell.dispose();
				System.exit(0);
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
		shell.open();
		shell.setSize(320, 200);
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

	/**
	 * 显示服务器状态
	 * 
	 */
	private String getServerStatus() {
		if (serverStatus) {
			return "开启";
		} else {
			return "关闭";
		}
	}

	/**
	 * 切换服务器状态
	 * 
	 */
	public void switchServerStatus() {
		if (!serverStatus) {
			t = new Thread(new Runnable() {
				public void run() {
					server = new Server();
					am = AuthorityManager.getInstance();
					try {
						am.run();
						server.start();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			});
			t.start();
			serverStatus = true;
			statusItem.setText("当前状态：" + getServerStatus());
			statusLabel.setText("服务器当前状态：" + getServerStatus());
			trayItem.setToolTipText("NJU_EAS_Server正在运行...");
		} else {
			server.shutdown();
			am.shutdown();
			serverStatus = false;
			t.interrupt();
			statusItem.setText("当前状态：" + getServerStatus());
			statusLabel.setText("服务器当前状态：" + getServerStatus());
			trayItem.setToolTipText("NJU_EAS_Server停止运行");
		}

	}
}
