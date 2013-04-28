<%--
  ~ cdoj, UESTC ACMICPC Online Judge
  ~
  ~ Copyright (c) 2013 fish <@link lyhypacm@gmail.com>,
  ~ mzry1992 <@link muziriyun@gmail.com>
  ~
  ~ This program is free software; you can redistribute it and/or
  ~ modify it under the terms of the GNU General Public License
  ~ as published by the Free Software Foundation; either version 2
  ~ of the License, or (at your option) any later version.
  ~
  ~ This program is distributed in the hope that it will be useful,
  ~ but WITHOUT ANY WARRANTY; without even the implied warranty of
  ~ MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  ~ GNU General Public License for more details.
  ~
  ~ You should have received a copy of the GNU General Public License
  ~ along with this program; if not, write to the Free Software
  ~ Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
  --%>

<%--
 User center page

 @author <a href="mailto:muziriyun@gmail.com">mzry1992</a>
 @version 1
--%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/page" prefix="page" %>
<%@ taglib prefix="cdoj" uri="/WEB-INF/cdoj.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:property value="targetUser.userName"/></title>
</head>
<body>
<div id="userInfoWrap" class="row">
    <div id="userInfoLeft" class="span7">
        <div id="userInfo">
            <dl class="dl-userInfo">
                <dt>Nick name</dt>
                <dd><s:property value="targetUser.nickName"/></dd>
                <dt>School</dt>
                <dd><s:property value="targetUser.school"/></dd>
                <dt>Department</dt>
                <dd><s:property value="targetUser.department"/></dd>
                <dt>Student ID</dt>
                <dd><s:property value="targetUser.studentId"/></dd>
                <dt>Email</dt>
                <dd><s:property value="targetUser.email"/></dd>
                <dt>Last login</dt>
                <dd><s:property value="targetUser.lastLogin"/></dd>
                <dt>User type</dt>
                <dd><s:property value="global.authenticationTypeList[targetUser.type]"/></dd>
            </dl>
        </div>
    </div>
    <div id="userInfoRight" class="span3">
        <div id="userInfoSummary">
            <img id="userAvatar-large" email="<s:property value="targetUser.email"/>" type="avatar" size="200"/>
            <span class="userName-type<s:property value="targetUser.type"/>"><h4><s:property value="targetUser.userName"/></h4></span>
        </div>
    </div>
    <div class="span10">
        <div id="userSolvedList">

        </div>
    </div>
</div>
</body>
</html>