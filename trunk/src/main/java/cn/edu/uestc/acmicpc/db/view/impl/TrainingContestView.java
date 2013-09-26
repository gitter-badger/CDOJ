package cn.edu.uestc.acmicpc.db.view.impl;

import java.util.List;

import cn.edu.uestc.acmicpc.db.entity.TrainingContest;
import cn.edu.uestc.acmicpc.db.view.base.View;

/**
 * Description
 *
 * @author <a href="mailto:muziriyun@gmail.com">mzry1992</a>
 */
public class TrainingContestView extends View<TrainingContest> {

  private Integer trainingContestId;
  private String title;
  private Boolean isPersonal;
  private List<TrainingStatusView> trainingStatusViewList;
  private Integer type;
  private String typeName;

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public String getTypeName() {
    return typeName;
  }

  public void setTypeName(String typeName) {
    this.typeName = typeName;
  }

  public Integer getTrainingContestId() {
    return trainingContestId;
  }

  public void setTrainingContestId(Integer trainingContestId) {
    this.trainingContestId = trainingContestId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Boolean getIsPersonal() {
    return isPersonal;
  }

  public void setIsPersonal(Boolean personal) {
    isPersonal = personal;
  }

  public List<TrainingStatusView> getTrainingStatusViewList() {
    return trainingStatusViewList;
  }

  public void setTrainingStatusViewList(List<TrainingStatusView> trainingStatusViewList) {
    this.trainingStatusViewList = trainingStatusViewList;
  }

  @Deprecated
  public TrainingContestView(TrainingContest trainingContest) {
    // TODO(mzry1992): use dto transfer.
    super(trainingContest);
//    List<TrainingStatus> trainingStatusList =
//        (List<TrainingStatus>) trainingContest.getTrainingStatusesByTrainingContestId();
//    Collections.sort(trainingStatusList, new Comparator<TrainingStatus>() {
//
//      @Override
//      public int compare(TrainingStatus a, TrainingStatus b) {
//        return a.getRank().compareTo(b.getRank());
//      }
//    });
//    trainingStatusViewList = new LinkedList<>();
//    for (TrainingStatus trainingStatus : trainingStatusList)
//      trainingStatusViewList.add(new TrainingStatusView(trainingStatus));
//    setTypeName(Global.TrainingContestType.values()[trainingContest.getType()].getDescription());
  }
}
