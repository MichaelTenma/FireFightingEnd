package cn.ltiex.firefighting.end.Database.DAO.Impl;

import cn.ltiex.firefighting.end.Database.DAO.RouteDAO;
import cn.ltiex.firefighting.end.Database.Entity.RouteEntity;
import cn.ltiex.firefighting.end.Database.Entity.RowMapper.SegmentEntityRowMapper;
import cn.ltiex.firefighting.end.Database.Entity.SegmentEntity;
import cn.ltiex.firefighting.end.Database.Entity.VertexEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RouteDAOImpl implements RouteDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public RouteDAOImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RouteEntity findBestRoute(VertexEntity start, VertexEntity end) {
        String queryString = "WITH route AS (\n" +
                "\tSELECT * FROM pgr_KSP(\n" +
                "\t\t'SELECT gid as id, source, target, cost, reverse_cost FROM roads',\n" +
                "\t\t?, ?, ?,\n" +
                "\t\tdirected:=true\n" +
                "\t)\n" +
                ")\n" +
                "SELECT \n" +
                "\troute.path_id as path_id, " +
                "\troute.path_seq as path_seq, " +
                "\troute.cost as cost, " +
                "\troads.maxspeed as maxspeed, " +
                "\troads.geom as geom\n" +
                "FROM route, roads WHERE route.edge = roads.gid\n" +
                "ORDER BY path_id ASC, path_seq ASC;";

        List<SegmentEntity> segmentEntityList = this.jdbcTemplate.query(
                queryString, new SegmentEntityRowMapper(),
                start.getId(), end.getId(), 1
        );
        RouteEntity routeEntity = new RouteEntity();
        return routeEntity.transform(segmentEntityList);
    }
}
