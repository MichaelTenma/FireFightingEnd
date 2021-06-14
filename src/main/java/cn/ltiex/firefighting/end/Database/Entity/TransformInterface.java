package cn.ltiex.firefighting.end.Database.Entity;

public interface TransformInterface<From, To> {
    To transform(From from);
}
