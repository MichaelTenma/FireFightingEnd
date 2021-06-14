package cn.ltiex.firefighting.end.Database.DAO.Impl;

import cn.ltiex.firefighting.end.Database.DAO.VertexDAO;
import cn.ltiex.firefighting.end.Database.Entity.RowMapper.VertexEntityRowMapper;
import cn.ltiex.firefighting.end.Database.Entity.VertexEntity;
import org.postgis.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class VertexDAOImpl implements VertexDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VertexDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public VertexEntity findNearestVertex(Point point) {
        String queryString = "SELECT \n" +
                "\tid as id, the_geom as point\n" +
                "FROM roads_vertices_pgr as vertices\n" +
                "ORDER BY vertices.the_geom <-> ST_Transform(?::geometry, 3857)\n" +
                "ASC LIMIT 1;";

        return this.jdbcTemplate.queryForObject(
                queryString, new VertexEntityRowMapper(),
                point.toString()
        );
    }
}
