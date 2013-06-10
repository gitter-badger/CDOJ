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

/**
 * Subnav
 *
 * @author <a href="mailto:muziriyun@gmail.com">mzry1992</a>
 * @version 1
 */

$(document).ready(function () {
    // fix sub nav on scroll
    var $win = $(window)
        , $nav = $('.subnav')
        , navTop = $('.subnav').length && $('.subnav').offset().top - 40
        , isFixed = 0;

    processScroll();

    $win.on('scroll', processScroll);

    function processScroll() {
        var scrollTop = $win.scrollTop();
        if (scrollTop >= navTop && !isFixed) {
            isFixed = 1;
            $nav.addClass('subnav-fixed');
            $('.subnav-content').css('margin-top', $nav.height());
        } else if (scrollTop <= navTop && isFixed) {
            isFixed = 0;
            $nav.removeClass('subnav-fixed');
            $('.subnav-content').css('margin-top', 0);
        }
    }
});
