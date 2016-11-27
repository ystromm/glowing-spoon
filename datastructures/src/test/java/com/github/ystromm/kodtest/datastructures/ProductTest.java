package com.github.ystromm.kodtest.datastructures;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import javax.sound.midi.SysexMessage;

import static com.github.ystromm.kodtest.datastructures.DescriptionToList.descriptionToList;
import static java.util.Arrays.asList;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class ProductTest {

    private static final String GROUP_1 = "Attributes 1";
    private static final String GROUP_2 = "Attributes 2";
    private static final String GROUP_3 = "Attributes 3";
    private static final String NAME_1 = "Attribute 1";
    private static final String VALUE_1 = "Value 1";
    private static final String NAME_2 = "Attribute 2";
    private static final String VALUE_2 = "Value 2";
    private static final String NAME_3 = "Attribute 3";
    private static final String VALUE_3 = "Value 3";
    private static final String NAME_4 = "Attribute 4";
    private static final String VALUE_4 = "Value 4";
    private static final String NAME_5 = "Attribute 5";
    private static final String VALUE_5 = "Value 5";
    private static final Attribute ATTRIBUTE_1 = Attribute.builder().name(NAME_1).value(VALUE_1).build();
    private static final Attribute ATTRIBUTE_2 = Attribute.builder().name(NAME_2).value(VALUE_2).build();
    private static final Attribute ATTRIBUTE_3 = Attribute.builder().name(NAME_3).value(VALUE_3).build();
    private static final Attribute ATTRIBUTE_4 = Attribute.builder().name(NAME_4).value(VALUE_4).build();
    private static final Attribute ATTRIBUTE_5 = Attribute.builder().name(NAME_5).value(VALUE_5).build();
    public static final String PRODUCT_ATTRIBUTES = "Product attributes";
    private final DescriptionToList descriptionToList = descriptionToList();

    @Rule
    public TestName testName = new TestName();

    @After
    public void print() {
        System.out.println(testName.getMethodName());
        descriptionToList.getDescriptions().forEach(description -> System.out.println(description));
    }

    @Test
    public void getDescription_with_empty_product_should_return_empty() {
        emptyProduct(PRODUCT_ATTRIBUTES).describeTo(descriptionToList);
        assertThat(descriptionToList.getDescriptions(), contains(PRODUCT_ATTRIBUTES));
    }

    @Test
    public void getDescripton_with_attribute_should_return_a_description() {
        product(PRODUCT_ATTRIBUTES, ATTRIBUTE_1).describeTo(descriptionToList);
        assertThat(descriptionToList.getDescriptions(),
                contains(PRODUCT_ATTRIBUTES, "  " + NAME_1 + ": " + VALUE_1));
    }

    @Test
    public void getDescripton_with_attributes_should_return_descriptions() {
        product(PRODUCT_ATTRIBUTES, ATTRIBUTE_1, ATTRIBUTE_2).describeTo(descriptionToList);
        assertThat(descriptionToList.getDescriptions(),
                contains(PRODUCT_ATTRIBUTES,
                        "  " + NAME_1 + ": " + VALUE_1,
                        "  " + NAME_2 + ": " + VALUE_2));
    }

    @Test
    public void getDescripton_with_productgroup_should_return_a_description() {
        product(PRODUCT_ATTRIBUTES, emptyProduct(GROUP_1)).describeTo(descriptionToList);
        assertThat(descriptionToList.getDescriptions(),
                contains(PRODUCT_ATTRIBUTES, "  " + GROUP_1));
    }

    @Test
    public void getDescripton_with_productgroups_should_return_descriptions() {
        product(PRODUCT_ATTRIBUTES, emptyProduct(GROUP_1), emptyProduct(GROUP_2)).describeTo(descriptionToList);
        assertThat(descriptionToList.getDescriptions(),
                contains(PRODUCT_ATTRIBUTES,
                        "  " + GROUP_1,
                        "  " + GROUP_2));
    }

    @Test
    public void getDescripton_with_productgroups_and_attribute_should_return_descriptions() {
        product(PRODUCT_ATTRIBUTES, emptyProduct(GROUP_1),
                ATTRIBUTE_1, emptyProduct(GROUP_2)).describeTo(descriptionToList);
        assertThat(descriptionToList.getDescriptions(),
                contains(PRODUCT_ATTRIBUTES,
                        "  " + GROUP_1,
                        "  " + NAME_1 + ": " + VALUE_1,
                        "  " + GROUP_2));
    }

    @Test
    public void getDescripton_with_productgroups_with_attributes_should_return_descriptions() {
        product(PRODUCT_ATTRIBUTES,
                product(GROUP_1, ATTRIBUTE_1),
                product(GROUP_2, ATTRIBUTE_2)).describeTo(descriptionToList);
        assertThat(descriptionToList.getDescriptions(),
                contains(PRODUCT_ATTRIBUTES,
                        "  " + GROUP_1,
                        "    " + NAME_1 + ": " + VALUE_1,
                        "  " + GROUP_2,
                        "    " + NAME_2 + ": " + VALUE_2
                ));
    }

    @Test
    public void getDescripton_with_attributes_and_productgroups_with_attributes_should_return_descriptions() {
        product(PRODUCT_ATTRIBUTES,
                product(GROUP_1,
                        ATTRIBUTE_1,
                        ATTRIBUTE_2),
                ATTRIBUTE_3,
                product(GROUP_2,
                        product(GROUP_3,
                                ATTRIBUTE_4)),
                        ATTRIBUTE_5).describeTo(descriptionToList);
        assertThat(descriptionToList.getDescriptions(),
                contains(PRODUCT_ATTRIBUTES,
                        "  " + GROUP_1,
                        "    " + NAME_1 + ": " + VALUE_1,
                        "    " + NAME_2 + ": " + VALUE_2,
                        "  " + NAME_3 + ": " + VALUE_3,
                        "  " + GROUP_2,
                        "    " + GROUP_3,
                        "      " + NAME_4 + ": " + VALUE_4,
                        "  " + NAME_5 + ": " + VALUE_5
                ));
    }


    private static Product product(String name, Describable... describables) {
        return Product.builder().name(name).describables(asList(describables)).build();
    }

    private static Product emptyProduct(String name) {
        return Product.builder().name(name).build();
    }
}