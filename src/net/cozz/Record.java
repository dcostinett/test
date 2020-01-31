package net.cozz;

import java.util.logging.Logger;

/**
 * Created by costd035 on 12/17/14.
 */
public class Record {
    private static final Logger LOGGER = Logger.getLogger(Record.class.getName());

    public Record() {

    }

    public static class InnerTestClass001 implements Comparable<InnerTestClass001> {
        private static final Logger LOGGER = Logger.getLogger(InnerTestClass001.class.getName());

        public InnerTestClass001() {
            LOGGER.info("constructor");
        }


        @Override
        public int compareTo(InnerTestClass001 o) {
            return 0;
        }
    }
}
