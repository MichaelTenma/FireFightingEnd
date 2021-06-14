package cn.ltiex.firefighting.end.Database.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.postgis.LineString;

@Data
public class SegmentEntity {
    private LineString segment;
    private Double speed;
    private Double cost;

    public SegmentEntity(){}

    public SegmentEntity(LineString segment, Double speed, Double cost) {
        this.segment = segment;
        this.speed = speed;
        this.cost = cost;
    }
}
