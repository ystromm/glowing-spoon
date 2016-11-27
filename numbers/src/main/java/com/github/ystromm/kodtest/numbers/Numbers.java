package com.github.ystromm.kodtest.numbers;

import com.google.common.base.Preconditions;

public class Numbers {
    public static int getValue(int i) {
        Preconditions.checkArgument(i > 0, "Arg should be larger than zero: " + i);
        switch (i) {
            case 1:
            case 2:
                return 1;
            default:
                return getValue(i-2) + getValue(i-1);
        }
    }
}
