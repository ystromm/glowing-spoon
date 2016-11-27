package com.github.ystromm.kodtest.datastructures;


import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
public class Attribute implements Describable {
    @NonNull
    private final String name;
    @NonNull
    private final Object value;

    @Override
    public void describeTo(Description description) {
        description.addDescription(name + ": " + value);
    }
}
