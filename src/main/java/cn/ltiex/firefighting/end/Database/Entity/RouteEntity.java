package cn.ltiex.firefighting.end.Database.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.postgis.LineString;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RouteEntity implements TransformInterface<List<SegmentEntity>, RouteEntity>{
    private List<SegmentEntity> route;
    private Double cost;/* total cost in second */

//    @Override
//    public RouteEntity transform(List<SegmentEntity> segmentEntityList) {
//        double cost = 0;
//        for(SegmentEntity segmentEntity : segmentEntityList){
//            cost += segmentEntity.getCost();
//        }
//        this.route = segmentEntityList;
//        this.cost = cost;
//        return this;
//    }

    @Override
    public RouteEntity transform(List<SegmentEntity> segmentEntityList) {
        List<SegmentEntity> routeList = new LinkedList<>();

        double cost = 0;
        double lastCost = segmentEntityList.get(0).getCost();
        Double lastSpeed = segmentEntityList.get(0).getSpeed();
        LineString lastLineString = segmentEntityList.get(0).getSegment();
        for(int i = 1;i < segmentEntityList.size();i++){
            SegmentEntity segmentEntity = segmentEntityList.get(i);
            if(segmentEntity.getSpeed().equals(lastSpeed)){
                lastLineString = lastLineString.concat(segmentEntity.getSegment());
                lastCost += segmentEntity.getCost();
            }else{
                cost += lastCost;
                routeList.add(new SegmentEntity(lastLineString, lastSpeed, lastCost));

                lastSpeed = segmentEntity.getSpeed();
                lastLineString = segmentEntity.getSegment();
                lastCost = segmentEntity.getCost();
            }
        }
        cost += lastCost;
        routeList.add(new SegmentEntity(lastLineString, lastSpeed, lastCost));

        this.route = new ArrayList<>(routeList);
        this.cost = cost;
        return this;
    }
}
