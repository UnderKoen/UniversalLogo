package nl.underkoen.universallogo;

/**
 * Created by Under_Koen on 03/04/2018.
 */
public abstract class AnimatedProgram implements Program {
    private boolean animationActive = false;
    private Thread animation;

    /**
     * @return whenever the animation is active
     */
    public final boolean isAnimationActive() {
        return animationActive;
    }

    /**
     * Starts the animation with 0.1 sec delay
     */
    public final void startAnimation() {
        startAnimation(100);
    }

    /**
     * Starts the animation
     * @param delay the delay between frames
     */
    public final void startAnimation(long delay) {
        if (animationActive) return;
        animationActive = true;
        animation = new Thread(() -> {
            while (isAnimationActive()) {
                getCanvas().redraw();
                sleep(delay);
            }
        });
        animation.start();
    }

    /**
     * Stops the animation
     */
    public final void stopAnimation() {
        if (!animationActive) return;
        animationActive = false;
        animation.interrupt();
    }

    /**
     * @param millis how long to sleep
     */
    public final void sleep(long millis) {
        if (!animationActive) return;
        if (millis <= 0) return;
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //Ignoring this error because this would trigger everytime you use stopAnimation()
        }
    }

    /**
     * Should run every <code>delay</code> from {@link #startAnimation(long)}
     */
    public abstract void drawAnimation();
}
