package cn.ltiex.firefighting.end.Database.DAO;

import cn.ltiex.firefighting.end.Database.Entity.VertexEntity;
import org.postgis.Point;

public interface VertexDAO {
    VertexEntity findNearestVertex(Point point);
}
