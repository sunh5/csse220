
public interface Temporal {
	/**
	 * Show how much time has passed and the object should update its
	 * status in time
	 */
	void timePassed();
	/**
	 * Show and tell whether the life of object is over
	 */
	void die();
	/**
	 * Sets whether this object should be paused. When paused, the object
	 * shouldn't change state as timePassed() is called.
	 * 
	 * @param isPaused
	 *            pause the object if true
	 */
	void setIsPaused(boolean isPaused);

	/**
	 * Returns the status of object.
	 * 
	 * @return paused status
	 */
	boolean getIsPaused();
}
