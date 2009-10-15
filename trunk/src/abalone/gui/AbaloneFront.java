package abalone.gui;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Map;

import abalone.gamelogic.GameLogic;
import abalone.gamelogic.StandardAbaloneLogic;
import abalone.gamestate.GameState;
import abalone.model.Board;
import abalone.model.HumanPlayer;
import abalone.model.Player;

import com.trolltech.qt.QVariant;
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;
import com.trolltech.qt.webkit.*;

public class AbaloneFront extends QMainWindow
{
	public Signal0 newGame = new Signal0();
	
	private GameState state;
	
	private QMenu game;
	private QMenu view;
	private QMenu settings;
	private QMenu help;
	
	private QAction newAct;
	private QAction loadAct;
	private QAction saveAct;
	private QAction saveAsAct;
	private QAction networkgameAct;
	private QAction undoMoveAct;
	private QAction resignAct;
	private QAction quitAct;
	
	private QAction fullscreenAct;
	private QAction showLogsAct;
	private QAction showStatisticsAct;
	private QAction preferencesAct;
	private QAction playerSettingsAct;
	
	private QAction aboutAbaloneAct;
	private QAction reportProblemAct;
	private QAction aboutAct;
	
	private BoardWidget boardWidget;
	private GameInfoWidget gameInfoWidget;

	
	public AbaloneFront(GameState state)
	{
		this.state=state;
		QMenuBar menuBar = new QMenuBar();
		setMenuBar(menuBar);
		setWindowIcon(new QIcon("classpath:abalone/gui/Icons/logo.png"));
		setWindowTitle("Abalone-game");
		setMinimumSize(300,300);
		
		MainWidget game = new MainWidget();
		setCentralWidget(game);
		
		try
		{
			createActions();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		createMenus();
		//createStatusBar();	
	}
	
	public void newGame()
	{
		newGame.emit();
	}
	
	public void aboutAbalone()
	{
		QWebView view = new QWebView();
		view.setBaseSize(800, 600);
		view.setWindowTitle("About Abalone");
        view.load(new QUrl("http://en.wikipedia.org/wiki/Abalone_(board_game)"));
        view.show();
	}
	
	public void reportProblem()
	{
		QWebView view = new QWebView();
		view.setBaseSize(800, 600);
		view.setWindowTitle("IssueTracker");
        view.load(new QUrl("http://code.google.com/p/abalone-game/issues/list"));
        view.show();
	}
	public void about()
	{
		QMessageBox.about(this,
							tr("About Abalone-game"),
							tr("Game made by a group of students at DKE " +
							"@ university Maastricht"));
	}
	
	//public void aboutQt()
	//{
	//	aboutQt(this);
	//}
	
	private void createActions()
	{
		newAct = new QAction(new QIcon("classpath:abalone/gui/Icons/new.png"),tr("&New Game"), this);
		newAct.setShortcut(new QKeySequence(tr("Ctrl+N")));
		newAct.setStatusTip(tr("Create a new Game"));
		newAct.triggered.connect(this, "newGame()");
		
		loadAct = new QAction(new QIcon("classpath:abalone/gui/Icons/open.png"),tr("&Load"), this);
		loadAct.setShortcut(tr("Ctrl+L"));
		loadAct.setStatusTip(tr("Opens a saved game"));
		//openAct.triggered.connect(this, "open()");
		
		saveAct = new QAction(new QIcon("classpath:abalone/gui/Icons/save.png"),tr("&Save"), this);
		saveAct.setShortcut(tr("Ctrl+S"));
		saveAct.setStatusTip(tr("Saves a game"));
		//saveAct.triggered.connect(this, "open()");
		
		saveAsAct = new QAction(new QIcon("classpath:abalone/gui/Icons/saveAs.png"),tr("Save &As"), this);
		saveAsAct.setShortcut(tr("Ctrl+A"));
		saveAsAct.setStatusTip(tr("Saves a game under a new name"));
		
		networkgameAct = new QAction(new QIcon("classpath:abalone/gui/Icons/networkGame.png"),tr("Network Game"), this);
		networkgameAct.setStatusTip(tr("Play a game over the network"));
		
		undoMoveAct = new QAction(new QIcon("classpath:abalone/gui/Icons/undo.png"),tr("Undo move"), this);
		undoMoveAct.setShortcut(tr("Ctrl+Z"));
		undoMoveAct.setStatusTip(tr("1 step back"));
		
		resignAct = new QAction(new QIcon("classpath:abalone/gui/Icons/resign.png"),tr("&Resign"), this);
		resignAct.setShortcut(tr("Ctrl+R"));
		resignAct.setStatusTip(tr("Resign this game"));
		
		quitAct = new QAction(new QIcon("classpath:abalone/gui/Icons/exit.png"),tr("&Quit"), this);
		quitAct.setShortcut(tr("Ctrl+Q"));
		quitAct.setStatusTip(tr("Quits the game"));
		quitAct.triggered.connect(this, "close()");
		
		fullscreenAct = new QAction(tr("Fullscreen"), this);
		fullscreenAct.setStatusTip(tr("View the game fullscreen"));
		
		showLogsAct = new QAction(tr("Show Logs"), this);
		showLogsAct.setStatusTip(tr("Shows a log of the game"));
		
		showStatisticsAct = new QAction(tr("Show Statistics"), this);
		showStatisticsAct.setStatusTip(tr("Shows the statistics of the game"));
		
		preferencesAct = new QAction(tr("Preferences"), this);
		preferencesAct.setStatusTip(tr("Preferences menu"));
		
		aboutAbaloneAct = new QAction(tr("About Abalone"), this);
		aboutAbaloneAct.setStatusTip(tr("Link to abalone wiki"));
		aboutAbaloneAct.triggered.connect(this, "aboutAbalone()");
		
		reportProblemAct = new QAction(tr("Report Problem"), this);
		reportProblemAct.setStatusTip(tr("Link to our issuetracker"));
		reportProblemAct.triggered.connect(this, "reportProblem()");
		
		aboutAct = new QAction(tr("About"), this);
		aboutAct.setStatusTip(tr("About this game"));
		aboutAct.triggered.connect(this, "about()");
	}
	
	private void createMenus()
	{
		game = menuBar().addMenu(tr("Game"));
		game.addAction(newAct);
		game.addAction(loadAct);
		game.addAction(saveAct);
		game.addAction(saveAsAct);
		game.addSeparator();
		game.addAction(networkgameAct);
		game.addSeparator();
		game.addAction(undoMoveAct);
		game.addAction(resignAct);
		game.addSeparator();
		game.addAction(quitAct);
		
		view = menuBar().addMenu(tr("View"));
		view.addAction(fullscreenAct);
		view.addAction(showLogsAct);
		view.addAction(showStatisticsAct);
		
		settings = menuBar().addMenu(tr("Settings")); 
		settings.addAction(preferencesAct);
		settings.addAction(playerSettingsAct);
		
		help = menuBar().addMenu(tr("Help"));
		help.addAction(aboutAbaloneAct);
		help.addAction(reportProblemAct);
		help.addAction(aboutAct);
	}
	
	/**
	 * This is a wrapper Widget for the main parts of the window, 
	 * added to make parts of the window dockable (statistics for 
	 * instance) and some parts not (the ones in this widget)
	 */
	class MainWidget extends QWidget
	{

		public MainWidget()
		{
			QHBoxLayout leftRight = new QHBoxLayout();
			boardWidget = new BoardWidget(state);
			leftRight.addWidget(boardWidget);
			leftRight.addSpacing(20);
			gameInfoWidget = new GameInfoWidget();
			leftRight.addWidget(gameInfoWidget);

			setLayout(leftRight);
		}
	}

	public BoardWidget getBoardWidget()
	{
		return boardWidget;
	}

	public void updateFront()
	{
		updateFront(this.state);
	}
	
	public void updateFront(GameState state)
	{
		boardWidget.updateBoard(state);
		
		List<Player> players = state.getPlayers();
		Map<Player, Integer> marblesLost = state.getMarblesRemoved();
		int i = 0;
		int[] points = new int[2];
		Integer j;
		for(Player player : players)
		{
			if(i > 2)
			{
				System.out.println("More than 2 players, sir!");
				return;
			}
			j = marblesLost.get(player);
			points[i] = j.intValue();
			i++;
		}
		//System.out.println("Player 1 lost " + points[0] + " Player 2 lost " + points[1]);
		gameInfoWidget.updateGameInfo(points[0], points[1]);
	}
	

}