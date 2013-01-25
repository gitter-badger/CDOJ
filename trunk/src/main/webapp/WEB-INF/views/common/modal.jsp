<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib prefix="cdoj" uri="/WEB-INF/cdoj.tld" %>
<%--
  ~ /*
  ~  * cdoj, UESTC ACMICPC Online Judge
  ~  * Copyright (c) 2013 fish <@link lyhypacm@gmail.com>,
  ~  * 	mzry1992 <@link muziriyun@gmail.com>
  ~  *
  ~  * This program is free software; you can redistribute it and/or
  ~  * modify it under the terms of the GNU General Public License
  ~  * as published by the Free Software Foundation; either version 2
  ~  * of the License, or (at your option) any later version.
  ~  *
  ~  * This program is distributed in the hope that it will be useful,
  ~  * but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~  * GNU General Public License for more details.
  ~  *
  ~  * You should have received a copy of the GNU General Public License
  ~  * along with this program; if not, write to the Free Software
  ~  * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
  ~  */
  --%>

<%--
  Created by IntelliJ IDEA.
  User: mzry1992
  Date: 13-1-25
  Time: 下午11:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<s:if test="#session.userName == null">
    <!-- Login Modal -->
    <div id="loginModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="loginModal" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="loginModalLabel">Login</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal">
                <fieldset>`
                    <s:textfield name="userName"
                                 maxLength="24"
                                 cssClass="span4"
                                 label="User Name"
                                 theme="bootstrap"/>
                    <s:password name="password"
                                maxLength="20"
                                cssClass="span4"
                                label="Password"
                                theme="bootstrap"/>
                </fieldset>
            </form>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Close</a>
            <a href="#" class="btn btn-primary">Login</a>
        </div>
    </div>

    <!-- Register Modal -->
    <div id="registerModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="registerModal" aria-hidden="true">
        <div class="modal-header">
            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
            <h3 id="registerModalLabel">Register</h3>
        </div>
        <div class="modal-body">
            <form class="form-horizontal">
                <fieldset>`
                    <s:textfield name="userDTO.userName"
                                 maxLength="24"
                                 cssClass="span4"
                                 label="User Name"
                                 theme="bootstrap"/>
                    <s:password name="userDTO.password"
                                maxLength="20"
                                cssClass="span4"
                                label="Password"
                                theme="bootstrap"/>
                    <s:password name="userDTO.passwordRepeat"
                                maxLength="20"
                                cssClass="span4"
                                label="Repeat your password"
                                theme="bootstrap"/>
                    <s:textfield name="userDTO.nickName"
                                 maxLength="20"
                                 cssClass="span4"
                                 label="Nick name"
                                 theme="bootstrap"/>
                    <s:textfield name="userDTO.email"
                                 maxLength="100"
                                 cssClass="span4"
                                 label="Email"
                                 theme="bootstrap"/>
                    <s:textfield name="userDTO.school"
                                 maxLength="50"
                                 cssClass="span4"
                                 value="UESTC"
                                 label="School"
                                 theme="bootstrap"/>
                    <s:select name="userDTO.departmentId"
                              list="global.departmentList"
                              listKey="departmentId"
                              listValue="name"
                              cssClass="span4"
                              label="Department"
                              theme="bootstrap"/>
                    <s:textfield name="userDTO.studentId"
                                 maxLength="20"
                                 cssClass="span4"
                                 label="Student ID"
                                 theme="bootstrap"/>
                </fieldset>
            </form>
        </div>
        <div class="modal-footer">
            <a href="#" class="btn" data-dismiss="modal" aria-hidden="true">Close</a>
            <a href="#" class="btn btn-primary">Register</a>
        </div>
    </div>
</s:if>
</body>
</html>