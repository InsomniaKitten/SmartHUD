package net.sleeplessdev.smarthud.util.interpolation;

public interface Interpolator {
    Interpolator LINEAR = i -> i;

    /**
     * Interpolate a time across a function defined by the implementor.
     * @param time A time value, must be 0..1
     * @return The interpolated value. Can be thought of as 'y' where time is 'x'.
     */
    float interpolate(final float time);

    default float interpolate(final float min, final float max, final float x) {
        return interpolate((x - min) / (max - min));
    }
}
