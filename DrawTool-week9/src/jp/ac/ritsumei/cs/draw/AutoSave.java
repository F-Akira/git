
package jp.ac.ritsumei.cs.draw;

/**
 * A thread that automatically saves information about figures on a given canvas.
 */
public class AutoSave extends Thread {

	/**
	 * The constant value of time period for auto save. 
	 */
	private static final int AUTO_SAVE_TIME_PERIOD = 1000 * 60;  // 1 minute
    
	private boolean isRunning;
	
    /**
     * A canvas on which a figure is drawn.
     */
    private DrawCanvas canvas;
    
    /**
     * Creates a thread that automatically saves information about figures on a given canvas.
     * @param canvas the canvas on which a figure is drawn
     */
    AutoSave(DrawCanvas canvas) {
        this.canvas = canvas;
    }
    
    /**
     * Terminates this thread.
     */
    public void terminate() {
    	isRunning = false;
    }
    
    /**
     * Runs this thread.
     */
    public void run() {
    	isRunning = true;
    	
    	while (isRunning) {
    		try {
    			Thread.sleep(AUTO_SAVE_TIME_PERIOD);
    		} catch (InterruptedException e) {}
    		canvas.autoSave();
    	}
    }
}