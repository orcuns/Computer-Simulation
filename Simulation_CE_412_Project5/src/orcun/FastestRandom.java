/**
 * @author Matthew Jackson - Original Author
 * @author David Arayan (DrHalfway) - Modifications to code
 * @version 1.0 - Used as part of HalfWay Game Engine Math Library
 */
public class Random {
    private long seed;

    /**
     * Default Constructor which generates the seed based on system time.
     */
    public Random() {
        seed = System.nanoTime();
    }

    /**
     * @param seed The value to start the random number generator.
     *             A seed value of 0 generates a random seed based on system time
     *             which is the same as the default constructor.
     */
    public Random(long seed) {
        if (seed == 0) {
            seed = System.nanoTime();
        }
        this.seed = seed;
    }

    /**
     * Uses an XORShift to produce a 63 bit random number.
     * Java's variables are all signed so the 64th bit is lost.
     * Period is 2^64 - 1 (thanks Roquen)
     *
     * @return A pseudo-random 63 bit number
     */
    private long rand() {
        seed ^= (seed << 13);
        seed ^= (seed >>> 7);
        seed ^= (seed << 17);
        return Math.abs(seed - 1);
//        return seed;
    }

    /**
     * Allows the user to assign a min and max value for the numbers
     * they want returned.
     *
     * @param min Minimum value to be returned (Can be negative)
     * @param max Maximum value to be returned (Can be negative)
     * @return A long random value between min and max
     */
    public long rand(long min, long max) {
        if (min > max) {
            return rand(max, min);
        }
        if (min == max) {
            return min;
        }
        return (rand() % (max + 1 - min)) + min;
    }

    /**
     * A floating point random number generator.
     *
     * @param min The minimum floating point value
     * @param max The maximum floating point value
     * @param dev The number of decimal places you want returned.
     *            dev = 10 will return 1 decimal place.
     *            dev = 100 will return 2 decimal places.
     * @return returns random floating point value between min and max.
     */
    public float randf(float min, float max, int dev) {
        if (min == max) {
            return min;
        }
        return ((float) rand((int) (min * dev), (int) (max * dev)) / dev);
    }

    /**
     * Returns the current seed for the random number generator
     */
    public long getSeed() {
        return seed;
    }

    /**
     * Sets the current seed to seed
     */
    public void setSeed(final long seed) {
        this.seed = seed;
    }
}
