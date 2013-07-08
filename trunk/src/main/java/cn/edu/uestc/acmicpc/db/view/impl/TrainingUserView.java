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

package cn.edu.uestc.acmicpc.db.view.impl;

import cn.edu.uestc.acmicpc.db.entity.TrainingUser;
import cn.edu.uestc.acmicpc.db.view.base.View;
import cn.edu.uestc.acmicpc.util.Global;
import cn.edu.uestc.acmicpc.util.annotation.Ignore;

/**
 * Description
 *
 * @author <a href="mailto:muziriyun@gmail.com">mzry1992</a>
 */
public class TrainingUserView extends View<TrainingUser> {

    private Integer rank;
    private Integer trainingUserId;
    private String name;
    private Boolean allow;
    private Double rating;
    private Double volatility;
    private Integer type;
    private String typeName;
    private String userName;
    private String userEmail;
    private Double ratingVary;
    private Double volatilityVary;
    private Integer competitions;

    public Integer getRank() {
        return rank;
    }

    @Ignore
    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getCompetitions() {
        return competitions;
    }

    public void setCompetitions(Integer competitions) {
        this.competitions = competitions;
    }

    public Double getVolatilityVary() {
        return volatilityVary;
    }

    public void setVolatilityVary(Double volatilityVary) {
        this.volatilityVary = volatilityVary;
    }

    public Double getRatingVary() {
        return ratingVary;
    }

    public void setRatingVary(Double ratingVary) {
        this.ratingVary = ratingVary;
    }

    public Integer getTrainingUserId() {
        return trainingUserId;
    }

    public void setTrainingUserId(Integer trainingUserId) {
        this.trainingUserId = trainingUserId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getAllow() {
        return allow;
    }

    public void setAllow(Boolean allow) {
        this.allow = allow;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getVolatility() {
        return volatility;
    }

    public void setVolatility(Double volatility) {
        this.volatility = volatility;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    @Ignore
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUserName() {
        return userName;
    }

    @Ignore
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    @Ignore
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Fetch data from entity.
     *
     * @param trainingUser specific entity
     */
    public TrainingUserView(TrainingUser trainingUser) {
        super(trainingUser);
        setUserName(trainingUser.getUserByUserId().getUserName());
        setUserEmail(trainingUser.getUserByUserId().getEmail());
        setTypeName(Global.TrainingUserType.values()[trainingUser.getType()].getDescription());
    }
}
