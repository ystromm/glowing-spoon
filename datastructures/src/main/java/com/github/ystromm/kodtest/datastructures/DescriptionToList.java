package com.github.ystromm.kodtest.datastructures;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(staticName = "descriptionToList")
public class DescriptionToList implements Description {
    private final ImmutableList.Builder<String> builder = ImmutableList.builder();
    private String prefix = "";

    public List<String> getDescriptions() {
        return builder.build();
    }

    @Override
    public void startGroup() {
        prefix = prefix + "  ";
    }

    @Override
    public void addDescription(String description) {
        builder.add(prefix + description);

    }

    @Override
    public void endGroup() {
        prefix = prefix.substring(2);
    }
}
