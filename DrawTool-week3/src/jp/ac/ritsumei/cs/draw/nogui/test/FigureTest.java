/*
 * Test code to execute tests for all the figures.
 */

package jp.ac.ritsumei.cs.draw.nogui.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LineTest.class, 
                RectTest.class, 
                FilledRectTest.class,
                OvalTest.class,
                FilledOvalTest.class,
              })
public class FigureTest {
}
