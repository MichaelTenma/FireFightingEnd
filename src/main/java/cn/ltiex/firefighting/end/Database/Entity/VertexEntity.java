package cn.ltiex.firefighting.end.Database.Entity;

import org.postgis.Point;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VertexEntity {
    private Long id;
    private Point point;
}
