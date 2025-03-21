package com.backend.config;
//JSON类型处理器
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * MyBatis JSON类型转换处理器
 * 功能：实现数据库JSON类型与Java集合类型的双向转换
 * 适用场景：处理MySQL JSON类型字段（tags和selectedQuestion字段）
 *
 * @param <> 要转换的Java类型
 */
@MappedTypes({List.class}) // 声明处理的Java类型
public class MyBatisJsonTypeHandler extends BaseTypeHandler<List<?>> {
    // Jackson JSON处理器（线程安全）
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * 将Java List转换为JSON字符串存入数据库
     * @param ps PreparedStatement对象
     * @param i 参数位置
     * @param parameter 要转换的List对象
     * @param jdbcType JDBC类型
     */
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i,
                                    List<?> parameter, JdbcType jdbcType) throws SQLException {
        try {
            // 序列化List为JSON字符串
            ps.setString(i, mapper.writeValueAsString(parameter));
        } catch (Exception e) {
            throw new SQLException("JSON序列化失败", e);
        }
    }

    /**
     * 从结果集获取JSON字符串并转换为List
     * @param rs 结果集
     * @param columnName 列名
     * @return 转换后的List对象
     */
    @Override
    public List<?> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parseJson(rs.getString(columnName));
    }

    // 其他结果集获取方法（重载）
    @Override
    public List<?> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parseJson(rs.getString(columnIndex));
    }

    @Override
    public List<?> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parseJson(cs.getString(columnIndex));
    }

    /**
     * 解析JSON字符串为List对象
     * @param json 数据库存储的JSON字符串
     * @return 解析后的List，解析失败返回null
     */
    private List<?> parseJson(String json) {
        try {
            // 使用TypeReference处理泛型类型
            return mapper.readValue(json, new TypeReference<List<?>>() {});
        } catch (Exception e) {
            // 日志记录建议：此处可添加日志输出
            return null;
        }
    }
}
