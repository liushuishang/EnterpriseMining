package enterprise.mining.data.utils;

import org.apache.commons.collections4.MapUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by  yuananyun on 2017/8/27.
 */
public class EMapUtils {
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 获取实体对象Id
     *
     * @param param
     * @return
     */
    public static String getId(Map<String, Object> param, String field) {
        return String.valueOf(MapUtils.getLongValue(param, field));
    }

    /**
     * 获取属性Map
     *
     * @param param
     * @return
     */
    public static Map<String, Object> getProperties(Map<String, Object> param) {
        return (Map<String, Object>) MapUtils.getMap(param, "properties");
    }

    /**
     * 获取map中的String字段值
     *
     * @param param
     * @param field
     * @return
     */
    public static String getString(Map<String, Object> param, String field) {
        return MapUtils.getString(param, field, "").trim();
    }

    public static Integer getInteger(Map param, String field) {
        return MapUtils.getInteger(param, field);
    }

    public static String getDateTime(Map param, String field) {
        try {
            Date date = new Date(MapUtils.getLong(param, field));
            return simpleDateFormat.format(date);
        } catch (Exception ex) {
            return "缺失";
        }
    }
}
