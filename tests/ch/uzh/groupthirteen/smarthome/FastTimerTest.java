package ch.uzh.groupthirteen.smarthome;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FastTimerTest {

    private FastTimer tenMinuteTimer;

    @BeforeEach
    void setUp() {
        tenMinuteTimer = new FastTimer(10);
    }

    @Test
    void testZeroTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            tenMinuteTimer = new FastTimer(0);
        });
    }

    @Test
    void testNegativeTime() {
        assertThrows(IllegalArgumentException.class, () -> {
            tenMinuteTimer = new FastTimer(-1);
        });
    }

    @Test
    void testStartTwice() {
        tenMinuteTimer.start();
        assertThrows(FastTimer.TimerAlreadyUsedException.class, tenMinuteTimer::start);
    }

    @Test
    void testStartStartAndStop() {
        tenMinuteTimer.start();
        tenMinuteTimer.stop();
    }

    @Test
    void testStopTwice() {
        tenMinuteTimer.start();
        tenMinuteTimer.stop();
        assertThrows(FastTimer.TimerAlreadyStoppedException.class, tenMinuteTimer::stop);
    }

    @Test
    void testStartAfterStop() {
        tenMinuteTimer.start();
        tenMinuteTimer.stop();
        assertThrows(FastTimer.TimerAlreadyUsedException.class, tenMinuteTimer::start);
    }

    @Test
    void testStartAfterTimeOut() throws InterruptedException {
        FastTimer zeroTimer = new FastTimer(1);
        zeroTimer.start();
        Thread.sleep(1000);
        assertThrows(FastTimer.TimerAlreadyUsedException.class, zeroTimer::start);
    }

    @Test
    void testStopAfterTimeOut() throws InterruptedException {
        FastTimer zeroTimer = new FastTimer(1);
        zeroTimer.start();
        Thread.sleep(1000);
        assertThrows(FastTimer.TimerAlreadyStoppedException.class, zeroTimer::stop);
    }

    @Test
    void testStartStopResetStartStop() {
        tenMinuteTimer.start();
        tenMinuteTimer.stop();
        tenMinuteTimer.reset();
        tenMinuteTimer.start();
        tenMinuteTimer.stop();
    }

    @Test
    void testTimeLeftNotStarted() {
        assertEquals(10, tenMinuteTimer.timeLeft());
    }

    @Test
    void testTimeLeftAfterOneMinute() throws InterruptedException {
        tenMinuteTimer.start();
        Thread.sleep(1000);
        assertEquals(9, tenMinuteTimer.timeLeft());
    }

    @Test
    void testTimeLeftAfterTimeOut() throws InterruptedException {
        FastTimer zeroTimer = new FastTimer(1);
        zeroTimer.start();
        Thread.sleep(1000);
        assertEquals(0, zeroTimer.timeLeft());
    }

    @Test
    void testResume() throws InterruptedException {
        tenMinuteTimer.start();
        Thread.sleep(1000);
        tenMinuteTimer.stop();
        Thread.sleep(1000);
        tenMinuteTimer.resume();
        Thread.sleep(1000);
        assertEquals(8, tenMinuteTimer.timeLeft());
    }

}