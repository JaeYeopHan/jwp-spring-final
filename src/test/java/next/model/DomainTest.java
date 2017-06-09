package next.model;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DomainTest {

    @Test
    public void questionSameUser() {
        Question question = new Question("jbee", "test", "test");
        User testUser = new User("jbee", "testPwd", "jy", "test@test");
        assertThat(question.isSameUser(testUser), is(true));
    }

    @Test
    public void answerCanDelete() {
        Answer answer = new Answer("jbee", "test", 0);
        Question question = new Question("jbee", "test", "test");
        assertThat(answer.canDelete(question), is(false));
    }
}
