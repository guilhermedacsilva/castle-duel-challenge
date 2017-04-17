package br.game.castleduel;

import br.game.castleduel.exception.PlayerException;
import br.game.castleduel.gui.GuiInterface;
import br.game.castleduel.gui.NormalGui;
import br.game.castleduel.gui.ServerGui;
import br.game.castleduel.player.PlayerFacade;
import br.game.castleduel.player.PlayerInfo;
import br.game.castleduel.time.FixedTimeRunnable;
import br.game.castleduel.time.GameTime;

public class Game implements FixedTimeRunnable {
	protected GameTime time;
	protected Battleground battleground;
	protected PlayerFacade players;
	protected GuiInterface gui;
	protected int playerWonNumber = -1;

	public void play(boolean isServer, int fps) {
		loadPlayers();
		loadGameLogic(isServer, fps);
		runGameLoop(isServer);
		finish();
	}
	
	protected void loadPlayers() {
		try {
			players = new PlayerFacade();
		} catch (PlayerException e) {
			playerWonNumber = e.player != 1 ? 1 : 2;
		}
	}
	
	protected void loadGameLogic(boolean isServer, int fps) {
		time = new GameTime(fps);
		gui = isServer ? new ServerGui() : new NormalGui();
		battleground = new Battleground(gui);
	}
	
	protected void runGameLoop(boolean isServer) {
		if (playerWonNumber == -1) {
			if (isServer) {
				runGameLoopServer();
			} else {
				runGameLoopNormal();
			}
		}
	}
	
	private void runGameLoopServer() {
		while (!battleground.isFinished() && time.canContinue()) {
			runBattle();
			time.nextFrame();
		}
	}

	private void runGameLoopNormal() {
		while (!battleground.isFinished() && time.canContinue()) {
			time.runWithSleep(this);
		}
	}
	
	@Override
	public void runWithFixedTime() {
		runBattle();
		gui.updateGame(time.getFramesLeft());
	}

	private void runBattle() {
		if (time.canPlayersPlay()) {
			runPlayers();
		}
		if (time.canReceiveGold()) {
			battleground.gainGold();
		}
		battleground.executeBattle();
	}
	
	private void runPlayers() {
		for (int i = 0; i < 2; i++) {
			final PlayerInfo info = battleground.getPlayerInfo(i);
			final int unitIndex = players.callPlay(info);
			battleground.addUnitFromPlayer(unitIndex, i);	
		}
	}

	private void finish() {
		if (playerWonNumber == -1) {
			PlayerInfo player0 = battleground.getPlayerInfo(0);			
			if (player0.castle > player0.castleEnemy) {
				playerWonNumber = 1;
			} else if (player0.castle < player0.castleEnemy) {
				playerWonNumber = 2;
			} else {
				playerWonNumber = 3;
			}
		}
		gui.setPlayerWon(playerWonNumber);
		gui.updateGame(0);
	}

}
