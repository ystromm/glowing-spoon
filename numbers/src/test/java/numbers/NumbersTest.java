package numbers;

import com.github.ystromm.kodtest.numbers.Numbers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class NumbersTest {

    private final Random random = new Random();
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void getValue_with_negative_number_should_throw() {
        expectedException.expect(IllegalArgumentException.class);
        Numbers.getValue(-1);
    }

    @Test
    public void getValue_with_0_should_throw() {
        expectedException.expect(IllegalArgumentException.class);
        Numbers.getValue(0);
    }

    @Test
    public void getValue_with_1_should_return_1() {
        assertThat(Numbers.getValue(1), equalTo(1));
    }

    @Test
    public void getValue_with_2_should_return_1() {
        assertThat(Numbers.getValue(2), equalTo(1));
    }

    @Test
    public void getValue_with_6_should_return_8() {
        assertThat(Numbers.getValue(6), equalTo(8));
    }

    @Test
    public void getValue_with_8_should_return_21() {
        assertThat(Numbers.getValue(8), equalTo(21));
    }

    @Test
    public void getValue_with_9_should_return_34() {
        assertThat(Numbers.getValue(9), equalTo(34));
    }

    @Test
    public void getValue_random_number_shold_return_sum_of_previous() {
        final int r = random.nextInt(20) + 2;
        assertThat(Numbers.getValue(r), equalTo(Numbers.getValue(r-1)+Numbers.getValue(r-2)));
    }
}