package cn.ltiex.firefighting.end.Controller;

import cn.ltiex.firefighting.end.Database.DAO.RouteDAO;
import cn.ltiex.firefighting.end.Database.DAO.VertexDAO;
import cn.ltiex.firefighting.end.Database.Entity.RouteEntity;
import cn.ltiex.firefighting.end.Database.Entity.SegmentEntity;
import cn.ltiex.firefighting.end.Database.Entity.VertexEntity;
import cn.ltiex.firefighting.end.Response.BasicResponseEntity;
import cn.ltiex.firefighting.end.Response.ResponseCode;
import org.postgis.LineString;
import org.postgis.MultiLineString;
import org.postgis.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;


@RestController
@RequestMapping(value = "/route", produces = "application/json")
public class RouteController {

    private final VertexDAO vertexDAO;
    private final RouteDAO routeDAO;

    @Autowired
    public RouteController(VertexDAO vertexDAO, RouteDAO routeDAO) {
        this.vertexDAO = vertexDAO;
        this.routeDAO = routeDAO;
    }

    @GetMapping("/getBestRoute")
    public BasicResponseEntity<RouteEntity> getBestRoute(
        double x1, double y1, double x2, double y2, int srid
    ){
        Point startPoint = new Point(x1, y1);
        Point endPoint = new Point(x2, y2);
        startPoint.setSrid(srid);endPoint.setSrid(srid);

        /* 得到最近的路径点 */
        VertexEntity startVertexEntity = this.vertexDAO.findNearestVertex(startPoint);
        VertexEntity endVertexEntity = this.vertexDAO.findNearestVertex(endPoint);
        RouteEntity routeEntity = this.routeDAO.findBestRoute(startVertexEntity, endVertexEntity);

        return new BasicResponseEntity<>(ResponseCode.SUCCESS, routeEntity);
    }

    @GetMapping("/generateServiceAreaBySecond")
    public BasicResponseEntity<MultiLineString> generateServiceAreaBySecond(
        double x1, double y1,int srid, double serviceTimeInSecond
    ){
        Point startPoint = new Point(x1, y1);
        startPoint.setSrid(srid);
        VertexEntity startVertexEntity = this.vertexDAO.findNearestVertex(startPoint);

        List<SegmentEntity> segmentEntityList = this.routeDAO.generateServiceAreaBySecond(
            new VertexEntity[]{startVertexEntity}, serviceTimeInSecond
        );

        LineString[] lineStringList = new LineString[segmentEntityList.size()];
        {
            int i = 0;
            for(SegmentEntity e:segmentEntityList){
                lineStringList[i] = e.getSegment();
                i++;
            }
        }
        MultiLineString multiLineString = new MultiLineString(lineStringList);
//        segmentEntityList.forEach(e -> {
//            e.setSpeed(null);
//            e.setCost(null);
//        });
        return new BasicResponseEntity<>(ResponseCode.SUCCESS, multiLineString);
    }
}
