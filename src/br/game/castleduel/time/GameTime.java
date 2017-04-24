package br.game.castleduel.time;

public class GameTime {
	protected static final int FPS_MIN = 20;
	protected static final int FRAME_LIMIT = 60 * 60 * 5;
	protected static final int FRAME_PLAYER = 13;
	protected static final int FRAME_GOLD = FRAME_PLAYER * 6;
	
	private int fps = 60;
	private int frameTime = 1000 / fps;
	private int currentFrame = 0;
	private long currentFrameFinishTime;
	private long sleepTime;
	
	public GameTime(int fps) {
		if (fps >= FPS_MIN) {
			this.fps = fps;
			frameTime = 1000 / fps;
		}
	}
	
	public boolean canContinue() {
		return currentFrame < FRAME_LIMIT;
	}

	public void runWithSleep(final FixedTimeRunnable runnable) {
		computeCurrentFrameTime();
		runnable.runWithFixedTime();
		sleep();
		nextFrame();
	}
	
	protected void computeCurrentFrameTime() {
		currentFrameFinishTime = System.currentTimeMillis() + frameTime;
	}
	
	protected void sleep() {
		sleepTime = currentFrameFinishTime - System.currentTimeMillis();
		if (sleepTime > 0) {
			try { Thread.sleep(sleepTime); }
			catch (InterruptedException e) {}
		}
	}

	public void nextFrame() {
		currentFrame++;
	}
	
	public boolean canPlayersPlay() {
		return currentFrame % FRAME_PLAYER == 0;
	}
	
	public boolean canReceiveGold() {
		return currentFrame % FRAME_GOLD == 0;
	}

	public int getFramesLeft() {
		return (FRAME_LIMIT - currentFrame) / 100;
	}
}
