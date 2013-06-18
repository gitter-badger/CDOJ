/*
 * cdoj, UESTC ACMICPC Online Judge
 *
 * Copyright (c) 2013 fish <@link lyhypacm@gmail.com>,
 * mzry1992 <@link muziriyun@gmail.com>
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

/**
 * Javascript for user admin page.
 *
 * @author <a href="mailto:muziriyun@gmail.com">mzry1992</a>
 * @version 1
 */

/**
 * current search condition
 */
var currentCondition;

/**
 * current user list
 */
var userList;

function getUserName(email, userName) {
    var html = $('<td style="text-align: left;">' +
        '<img id="usersAvatar" email="' + email + '"/>' +
        '<a href="/user/center/' + userName + '">' + userName + '</a>' +
    '</td>');
    return html;
}

function getNickName(nickName, type) {
    var html = $('<td>' + nickName + '</td>');
    if (type != 0)
        html.addClass('userName-type' + type);
    return html;
}
/**
 * refresh the user list
 * @param condition
 */
function refreshUserList(condition) {
    $.post('/user/search', condition, function (data) {
        if (data.result == "error") {
            alert(data.error_msg);
            return;
        }

        //pagination
        $('#pageInfo').empty();
        $('#pageInfo').append(data.pageInfo);
        $('#pageInfo').find('a').click(function (e) {
            if ($(this).attr('href') == null)
                return false;
            currentCondition.currentPage = $(this).attr("href");
            refreshUserList(currentCondition);
            return false;
        });

        userList = data.userList;
        var tbody = $('#userList');
        // remove old user list
        tbody.find('tr').remove();
        // put user list
        $.each(userList, function (index, value) {
            var html = $('<tr></tr>');
            html.append('<td>' + value.userId + '</td>');
            html.append(getUserName(value.email, value.userName));
            html.append(getNickName(value.nickName, value.type));
            html.append('<td>' + value.school + '</td>');
            html.append('<td class="cdoj-time">' + value.lastLogin+ '</td>');
            html.append('<td>' + value.solved + '</td>');
            html.append('<td>' + value.tried + '</td>');
            tbody.append(html);
        });

        // get userList avatars
        $('img#usersAvatar').setAvatar({
            size: 37,
            image: 'http://www.acm.uestc.edu.cn/images/akari_small.jpg'
        });

        // format time style
        $('.cdoj-time').formatTimeStyle();

    });
}

function changeOrder(field) {
    if (currentCondition["userCondition.orderFields"] == field)
        currentCondition["userCondition.orderAsc"] = (currentCondition["userCondition.orderAsc"] == "true" ? "false" : "true");
    else {
        currentCondition["userCondition.orderFields"] = field;
        currentCondition["userCondition.orderAsc"] = "false";
    }
    refreshUserList(currentCondition);
}

$(document).ready(function () {

    $('#userCondition_departmentId').prepend('<option value="-1">All</option>');
    $('#userCondition_departmentId').attr("value", -1);
    $('#userCondition_type').prepend('<option value="-1">All</option>');
    $('#userCondition_type').attr("value", -1);

    currentCondition = {
        "currentPage": null,
        "userCondition.startId": undefined,
        "userCondition.endId": undefined,
        "userCondition.userName": undefined,
        "userCondition.type": undefined,
        "userCondition.school": undefined,
        "userCondition.departmentId": undefined,
        "userCondition.orderFields": "solved,tried,id",
        "userCondition.orderAsc": "false,false,true"
    };

    $('input#search').setButton({
        callback: function () {
            currentCondition = $('#userCondition').getFormData();
            currentCondition.currentPage = 1;
            refreshUserList(currentCondition);
            $('#TabMenu a:first').tab('show');
        }
    });

    $('input#reset').setButton({
        callback: function () {
            $('#userCondition').resetFormData();
        }
    });


    $.each($('.orderButton'), function(){
        var field = $(this).attr('field');
        $(this).setButton({
            callback: function(){
                changeOrder(field);
            }
        });
    });

    refreshUserList(currentCondition);
});