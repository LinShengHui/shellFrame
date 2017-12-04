package handler;

import java.sql.ResultSet;

/**
 * Created by Administrator on 2017/12/1.
 */
public interface ResultSetHandler<T> {
    T handler(ResultSet resultSet) throws Exception;

}
