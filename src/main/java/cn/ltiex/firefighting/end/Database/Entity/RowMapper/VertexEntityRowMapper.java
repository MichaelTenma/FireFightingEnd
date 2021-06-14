package cn.ltiex.firefighting.end.Database.Entity.RowMapper;

import cn.ltiex.firefighting.end.Database.Entity.VertexEntity;
import org.postgis.Point;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VertexEntityRowMapper implements RowMapper<VertexEntity> {
    @Override
    public VertexEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        Long id = resultSet.getLong("id");
        Object objPoint = resultSet.getObject("point");
        Point point = new Point(objPoint.toString());
        VertexEntity vertexEntity = new VertexEntity(id, point);
        return vertexEntity;
    }
}
