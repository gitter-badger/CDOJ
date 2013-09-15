/*
 *
 *  * cdoj, UESTC ACMICPC Online Judge
 *  * Copyright (c) 2013 fish <@link lyhypacm@gmail.com>,
 *  * 	mzry1992 <@link muziriyun@gmail.com>
 *  *
 *  * This program is free software; you can redistribute it and/or
 *  * modify it under the terms of the GNU General Public License
 *  * as published by the Free Software Foundation; either version 2
 *  * of the License, or (at your option) any later version.
 *  *
 *  * This program is distributed in the hope that it will be useful,
 *  * but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  * GNU General Public License for more details.
 *  *
 *  * You should have received a copy of the GNU General Public License
 *  * along with this program; if not, write to the Free Software
 *  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package cn.edu.uestc.acmicpc.oj.action.admin;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.edu.uestc.acmicpc.db.condition.base.Condition;
import cn.edu.uestc.acmicpc.db.condition.impl.UserCondition;
import cn.edu.uestc.acmicpc.db.dao.iface.IDepartmentDAO;
import cn.edu.uestc.acmicpc.db.dto.impl.UserDTO;
import cn.edu.uestc.acmicpc.db.entity.User;
import cn.edu.uestc.acmicpc.db.view.impl.UserView;
import cn.edu.uestc.acmicpc.ioc.condition.UserConditionAware;
import cn.edu.uestc.acmicpc.ioc.dao.DepartmentDAOAware;
import cn.edu.uestc.acmicpc.ioc.dto.UserDTOAware;
import cn.edu.uestc.acmicpc.oj.action.BaseAction;
import cn.edu.uestc.acmicpc.oj.view.PageInfo;
import cn.edu.uestc.acmicpc.util.ArrayUtil;
import cn.edu.uestc.acmicpc.util.Global;
import cn.edu.uestc.acmicpc.util.annotation.LoginPermit;
import cn.edu.uestc.acmicpc.util.exception.AppException;

import com.opensymphony.xwork2.validator.annotations.CustomValidator;
import com.opensymphony.xwork2.validator.annotations.FieldExpressionValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.StringLengthFieldValidator;
import com.opensymphony.xwork2.validator.annotations.ValidationParameter;
import com.opensymphony.xwork2.validator.annotations.Validations;

/**
 * action for edit user information.
 */
@Controller
@LoginPermit(value = Global.AuthenticationType.ADMIN)
public class AdminUserAction extends BaseAction implements DepartmentDAOAware, UserConditionAware,
    UserDTOAware {

  /**
	 *
	 */
  private static final long serialVersionUID = -5440622655890452651L;

  /**
   * return the user.jsp for base view
   *
   * @return SUCCESS
   */
  @SkipValidation
  public String toUserList() {
    return SUCCESS;
  }

  /**
   * department dao, use for get a department entity by id.
   */
  @Autowired
  private IDepartmentDAO departmentDAO;

  /**
   * Conditions for user search.
   */
  @Autowired
  private UserCondition userCondition;

  /**
   * Setter of userCondition for Ioc.
   *
   * @param userCondition newly userCondition
   */
  @Override
  public void setUserCondition(UserCondition userCondition) {
    this.userCondition = userCondition;
  }

  @Override
  public UserCondition getUserCondition() {
    return userCondition;
  }

  /**
   * Search action.
   * <p/>
   * Find all records by conditions and return them as a list in JSON, and the condition set will
   * set in JSON named "condition".
   * <p/>
   * <strong>JSON output</strong>:
   * <ul>
   * <li>
   * For success: {"result":"ok", "pageInfo":<strong>PageInfo object</strong>, "condition",
   * <strong>UserCondition entity</strong>, "userList":<strong>query result</strong>}</li>
   * <li>
   * For error: {"result":"error", "error_msg":<strong>error message</strong>}</li>
   * </ul>
   *
   * @return <strong>JSON</strong> signal
   */
  @SuppressWarnings("unchecked")
  @SkipValidation
  public String toSearch() {
    try {
      Condition condition = userCondition.getCondition();
      Long count = userService.getDAO().count(userCondition.getCondition());
      PageInfo pageInfo = buildPageInfo(count, RECORD_PER_PAGE, "", null);
      condition.setCurrentPage(pageInfo.getCurrentPage());
      condition.setCountPerPage(RECORD_PER_PAGE);
      List<User> userList = (List<User>) userService.getDAO().findAll(condition);
      List<UserView> userViewList = new ArrayList<>();
      for (User user : userList)
        userViewList.add(new UserView(user));
      json.put("pageInfo", pageInfo.getHtmlString());
      json.put("result", "ok");
      json.put("userList", userViewList);
    } catch (AppException e) {
      json.put("result", "error");
    } catch (Exception e) {
      json.put("result", "error");
      e.printStackTrace();
      json.put("error_msg", "Unknown exception occurred.");
    }
    return JSON;
  }

  /**
   * User database transform object entity.
   */
  @Autowired
  private UserDTO userDTO;

  /**
   * To edit user entity.
   * <p/>
   * <strong>JSON output</strong>:
   * <ul>
   * <li>
   * For success: {"result":"ok"}</li>
   * <li>
   * For error: {"result":"error", "error_msg":<strong>error message</strong>}</li>
   * </ul>
   *
   * @return <strong>JSON</strong> signal
   */
  @Validations(
      requiredStrings = {
          @RequiredStringValidator(fieldName = "userDTO.school", key = "error.school.validation"),
          @RequiredStringValidator(fieldName = "userDTO.studentId",
              key = "error.studentId.validation"),
          @RequiredStringValidator(fieldName = "userDTO.nickName",
              key = "error.nickName.validation") },
      stringLengthFields = {
          @StringLengthFieldValidator(fieldName = "userDTO.school",
              key = "error.school.validation", minLength = "1", maxLength = "100", trim = false),
          @StringLengthFieldValidator(fieldName = "userDTO.studentId",
              key = "error.studentId.validation", minLength = "1", maxLength = "20", trim = false) },
      customValidators = { @CustomValidator(type = "regex", fieldName = "userDTO.nickName",
          key = "error.nickName.validation", parameters = {
              @ValidationParameter(name = "expression", value = "\\b^[^\\s]{2,20}$\\b"),
              @ValidationParameter(name = "trim", value = "false") }) }, fieldExpressions = {
          @FieldExpressionValidator(fieldName = "userDTO.departmentId",
              expression = "userDTO.departmentId in global.departmentList.{departmentId}",
              key = "error.department.validation"),
          @FieldExpressionValidator(fieldName = "userDTO.type",
              expression = "userDTO.type in global.authenticationTypeList.{ordinal()}",
              key = "error.type.validation") })
  public
      String toEdit() {
    try {
      User user = userService.getUserByUserId(userDTO.getUserId());
      if (user == null) {
        throw new AppException("No such user!");
      }
      userDTO.setDepartment(departmentDAO.get(userDTO.getDepartmentId()));
      userDTO.updateEntity(user);
      userService.updateUser(user);
      json.put("result", "ok");
    } catch (AppException e) {
      json.put("result", "error");
      json.put("error_msg", e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
      json.put("result", "error");
      json.put("error_msg", "Unknown exception occurred.");
    }
    return JSON;
  }

  /**
   * Action to operate multiple users.
   * <p/>
   * <strong>JSON output</strong>:
   * <ul>
   * <li>
   * For success: {"result":"ok", "msg":<strong>successful message</strong>}</li>
   * <li>
   * For error: {"result":"error", "error_msg":<strong>error message</strong>}</li>
   * </ul>
   *
   * @return <strong>JSON</strong> signal.
   */
  @SkipValidation
  public String toOperatorUser() {
    try {
      int count = 0, total = 0;
      Integer[] ids = ArrayUtil.parseIntArray(getHttpParameter("id"));
      String method = getHttpParameter("method");
      for (Integer id : ids)
        if (id != null) {
          ++total;
          try {
            if ("delete".equals(method)) {
              userService.getDAO().delete(userService.getUserByUserId(id));
            }
            ++count;
          } catch (AppException ignored) {
          }
        }
      json.put("result", "ok");
      String message = "";
      if ("delete".equals(message))
        message = String.format("%d total, %d deleted.", total, count);
      json.put("msg", message);
    } catch (Exception e) {
      json.put("result", "error");
      json.put("error_msg", "Unknown exception occurred.");
    }
    return JSON;
  }

  @Override
  public UserDTO getUserDTO() {
    return userDTO;
  }

  @Override
  public void setUserDTO(UserDTO userDTO) {
    this.userDTO = userDTO;
  }

  @Override
  public void setDepartmentDAO(IDepartmentDAO departmentDAO) {
    this.departmentDAO = departmentDAO;
  }
}
