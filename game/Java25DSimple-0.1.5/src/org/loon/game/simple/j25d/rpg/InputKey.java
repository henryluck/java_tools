package org.loon.game.simple.j25d.rpg;

public class InputKey {

	public static final int NORMAL = 0;

	public static final int DETECT_INITIAL_PRESS_ONLY = 1;

	private static final int STATE_RELEASED = 0;

	private static final int STATE_PRESSED = 1;

	private static final int STATE_WAITING_FOR_RELEASE = 2;

	private int mode;

	private int amount;

	private int state;

	public InputKey() {
		this(NORMAL);
	}

	public InputKey(int mode) {
		this.mode = mode;
		reset();
	}

	public void reset() {
		state = STATE_RELEASED;
		amount = 0;
	}

	public void press() {
		if (state != STATE_WAITING_FOR_RELEASE) {
			amount++;
			state = STATE_PRESSED;
		}
	}

	public void release() {
		state = STATE_RELEASED;
	}

	public boolean isPressed() {
		if (amount != 0) {
			if (state == STATE_RELEASED) {
				amount = 0;
			} else if (mode == DETECT_INITIAL_PRESS_ONLY) {
				state = STATE_WAITING_FOR_RELEASE;
				amount = 0;
			}
			return true;
		}
		return false;
	}
}
