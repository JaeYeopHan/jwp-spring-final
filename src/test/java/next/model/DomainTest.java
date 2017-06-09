package next.model;

import next.CannotOperateException;
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
//(expected = CannotOperateException.class)
    @Test
    public void answerCanDelete() throws CannotOperateException {
        User testUser = new User("jbee", "testPwd", "jy", "test@test");
        Answer answer = new Answer("jbee", "test", 0);
        Question question = new Question("jbee", "test", "test");
        answer.delete(testUser);
    }
}
