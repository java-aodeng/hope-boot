import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* ${classInfo.classComment}
*
* Created by xuxueli on '${.now?string('yyyy-MM-dd HH:mm:ss')}'.
*/
@Service
public class ${classInfo.className}ServiceImpl implements ${classInfo.className}Service {

	@Resource
	private ${classInfo.className}Dao ${classInfo.className?uncap_first}Dao;

	/**
    * 新增
    */
	@Override
	public ReturnT<String> insert(${classInfo.className} ${classInfo.className?uncap_first}) {

		// valid
		if (${classInfo.className?uncap_first} == null) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, "必要参数缺失");
        }

		${classInfo.className?uncap_first}Dao.insert(${classInfo.className?uncap_first});
        return ReturnT.SUCCESS;
	}

	/**
	* 删除
	*/
	@Override
	public ReturnT<String> delete(int id) {
		int ret = ${classInfo.className?uncap_first}Dao.delete(id);
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* 更新
	*/
	@Override
	public ReturnT<String> update(${classInfo.className} ${classInfo.className?uncap_first}) {
		int ret = ${classInfo.className?uncap_first}Dao.update(${classInfo.className?uncap_first});
		return ret>0?ReturnT.SUCCESS:ReturnT.FAIL;
	}

	/**
	* Load查询
	*/
	@Override
	public ${classInfo.className} load(int id) {
		return ${classInfo.className?uncap_first}Dao.load(id);
	}

	/**
	* 分页查询
	*/
	@Override
	public Map<String,Object> pageList(int offset, int pagesize) {

		List<${classInfo.className}> pageList = ${classInfo.className?uncap_first}Dao.pageList(offset, pagesize);
		int totalCount = ${classInfo.className?uncap_first}Dao.pageListCount(offset, pagesize);

		// result
		Map<String, Object> result = new HashMap<String, Object>();
		maps.put("pageList", pageList);
		maps.put("totalCount", totalCount);

		return result;
	}

}
