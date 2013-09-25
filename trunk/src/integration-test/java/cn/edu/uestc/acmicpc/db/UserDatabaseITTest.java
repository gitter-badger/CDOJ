package cn.edu.uestc.acmicpc.db;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.uestc.acmicpc.config.IntegrationTestContext;
import cn.edu.uestc.acmicpc.db.condition.impl.UserCondition;
import cn.edu.uestc.acmicpc.db.dao.iface.IUserDAO;
import cn.edu.uestc.acmicpc.db.entity.User;
import cn.edu.uestc.acmicpc.util.exception.AppException;
import cn.edu.uestc.acmicpc.util.exception.FieldNotUniqueException;

/**
 * Test cases for {@link User}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { IntegrationTestContext.class })
public class UserDatabaseITTest {

  @Autowired
  private IUserDAO userDAO;

  @SuppressWarnings("unchecked")
  @Test
  public void testQuery_byName() throws AppException {
    UserCondition condition = new UserCondition();
    condition.userName = "admin";
    List<User> users = (List<User>) userDAO.findAll(condition.getCondition());
    Assert.assertEquals(2, users.size());
    Assert.assertEquals("administrator", users.get(0).getUserName());
    Assert.assertEquals(Integer.valueOf(1), users.get(0).getUserId());
    Assert.assertEquals("admin", users.get(1).getUserName());
    Assert.assertEquals(Integer.valueOf(2), users.get(1).getUserId());
  }

  @SuppressWarnings("unchecked")
  @Test
  public void testQuery_byDepartmentId() throws AppException {
    UserCondition condition = new UserCondition();
    condition.endId = 5;
    condition.departmentId = 1;
    List<User> users = (List<User>) userDAO.findAll(condition.getCondition());
    Assert.assertEquals(2, users.size());
    Assert.assertEquals("administrator", users.get(0).getUserName());
    Assert.assertEquals("admin", users.get(1).getUserName());
  }

  @Test
  @Ignore("borken test for design-in problem")
  public void testDelete() throws AppException, FieldNotUniqueException {
    User user = userDAO.getEntityByUniqueField("userName", "testDeleted");
    Long oldCount = userDAO.count();
    userDAO.delete(user.getUserId());
    Long newCount = userDAO.count();
    Assert.assertEquals(oldCount - 1, newCount.longValue());
  }

  @Test
  public void testUserCondition_byStartIdAndEndId() throws AppException {
    UserCondition condition = new UserCondition();
    condition.startId = 2;
    condition.endId = 10;
    Assert.assertEquals(Long.valueOf(2), userDAO.count(condition.getCondition()));
  }
}