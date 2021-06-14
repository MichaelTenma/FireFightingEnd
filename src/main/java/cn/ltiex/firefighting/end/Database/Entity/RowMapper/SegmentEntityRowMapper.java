package cn.ltiex.firefighting.end.Database.Entity.RowMapper;

import cn.ltiex.firefighting.end.Database.Entity.SegmentEntity;
import cn.ltiex.firefighting.end.Database.Entity.VertexEntity;
import org.postgis.LineString;
import org.postgis.Point;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SegmentEntityRowMapper implements RowMapper<SegmentEntity> {
    @Override
    public SegmentEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        Double cost = resultSet.getDouble("cost");
        Double speed = 0.0 + resultSet.getLong("maxspeed");
        Object objLineString = resultSet.getObject("geom");
        LineString segment = new LineString(objLineString.toString());

        return new SegmentEntity(segment, speed, cost);
    }
}
