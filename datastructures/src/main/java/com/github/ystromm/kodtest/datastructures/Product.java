package com.github.ystromm.kodtest.datastructures;

import com.google.common.collect.ImmutableList;
import lombok.Builder;
import lombok.NonNull;
import lombok.Singular;
import lombok.Value;

import java.util.Collection;

@Builder
@Value
public class Product implements Describable {

    @NonNull
    private final String name;
    @Singular
    @NonNull
    final Collection<Describable> describables;

    @Override
    public void describeTo(Description description) {
        description.addDescription(name);
        description.startGroup();
        describables.forEach(describable -> describable.describeTo(description));
        description.endGroup();

    }
}
