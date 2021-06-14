package cn.ltiex.firefighting.end.Database.DAO;

import cn.ltiex.firefighting.end.Database.Entity.RouteEntity;
import cn.ltiex.firefighting.end.Database.Entity.VertexEntity;

public interface RouteDAO {
    /**
     * 寻找两点之间的最优路径
     * @param start 起始点
     * @param end 终止点
     * @return k条路径，且每一个Feature都是一个线段带有属性
     */
    RouteEntity findBestRoute(VertexEntity start, VertexEntity end);
}
