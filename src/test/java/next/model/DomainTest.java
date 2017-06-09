package next.model;

import next.CannotOperateException;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DomainTest {

    @Test
    public void questionCanDelete() throws CannotOperateException {
        User testUser = new User("jbee", "testPwd", "jy", "test@test");
        Question question = new Question("jbee", "test", "test");
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("jbee", "test", 0));
        answers.add(new Answer("jbee", "test", 1));
        question.delete(testUser, answers);
        assertThat(question.isDeleted(), is(true));
    }

    @Test(expected = CannotOperateException.class)
    public void questionCannotDelete() throws CannotOperateException {
        User testUser = new User("jbee", "testPwd", "jy", "test@test");
        Question question = new Question("jbee", "test", "test");
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("jbe", "test", 0));
        answers.add(new Answer("jbee", "test", 1));
        question.delete(testUser, answers);
    }

    @Test
    public void answerCanDelete() throws CannotOperateException {
        User testUser = new User("jbee", "testPwd", "jy", "test@test");
        Answer answer = new Answer("jbee", "test", 0);
        answer.delete(testUser);
    }

    @Test(expected = CannotOperateException.class)
    public void answerCannotDelete() throws CannotOperateException {
        User testUser = new User("jbe", "testPwd", "jy", "test@test");
        Answer answer = new Answer("jbee", "test", 0);
        answer.delete(testUser);
        assertThat(answer.isDeleted(), is(true));
    }
}
