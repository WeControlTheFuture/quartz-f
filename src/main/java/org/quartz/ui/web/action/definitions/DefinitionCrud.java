package org.quartz.ui.web.action.definitions;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.quartz.ui.util.JaxbUtil;
import org.quartz.ui.web.action.base.BaseAction;
import org.quartz.ui.web.dao.ZkConfigDao;
import org.quartz.ui.web.model.JobDefinition;
import org.quartz.ui.web.model.JobSchedulingData;
import org.quartz.ui.web.model.Page;

public class DefinitionCrud extends BaseAction {
	private static final long serialVersionUID = -4495359250775449787L;
	private static final Log log = LogFactory.getLog(DefinitionCrud.class);
	JobDefinition _definition = new JobDefinition();

	private String definitionName = "";
	private String searchName;
	private ZkConfigDao zkConfigDao;

	Map paramMap;

	public String save() {

		JobDefinition def = BaseAction.getDefinitionManager().getDefinition(_definition.getName());
		if (def != null) {
			this._definition = def;

		} else {
			// save for a new
			if (paramMap != null) {
				_definition.getParameters().addAll(paramMap.values());
			}

			BaseAction.getDefinitionManager().addDefinition(_definition.getName(), _definition);
		}

		return SUCCESS;

	}

	@Override
	public String execute() {
		// assuming we are doing a new if no name supplied
		if (definitionName != null || definitionName.length() > 1) {
			// this is fine. No definition loaded(new)
			_definition = BaseAction.getDefinitionManager().getDefinition(definitionName);
		}

		return SUCCESS;
	}

	public Collection<JobSchedulingData> getDefinitions() {
		try {
			List<String> names = zkConfigDao.getAllChildrenName();
			if (StringUtils.isNotEmpty(searchName))
				names = search(names);
			Page page = new Page();
			List<JobSchedulingData> result = new ArrayList<JobSchedulingData>(page.getPageSize());
			if (CollectionUtils.isNotEmpty(names))
				for (int i = (page.getPageIndex() - 1) * page.getPageSize(); i < names.size() && i < i + 10; i++) {
					String content = zkConfigDao.getConfig(names.get(i));
					System.out.println(content);
					JobSchedulingData jobSchedulingData = JaxbUtil.unmarshal(content, JobSchedulingData.class);
					result.add(jobSchedulingData);
				}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error get definitions", e);
		}
		return new ArrayList<JobSchedulingData>();
	}

	private List<String> search(List<String> names) {
		List<String> result = new ArrayList<String>();
		for (String name : names)
			if (name.indexOf(searchName) != -1)
				result.add(name);
		return result;
	}

	public String delete() {
		BaseAction.getDefinitionManager().removeDefinition(definitionName);
		return SUCCESS;
	}

	public String list() {
		return SUCCESS;
	}

	/**
	 * @return
	 */
	public String getDefinitionName() {
		return definitionName;
	}

	/**
	 * @param string
	 */
	public void setDefinitionName(String string) {
		definitionName = string;
	}

	/**
	 * @return JobDefinition
	 */
	public JobDefinition getDefinition() {
		return _definition;
	}

	/**
	 * @param definition
	 */
	public void setDefinition(JobDefinition definition) {
		this._definition = definition;
	}

	/**
	 * @return Returns the paramMap.
	 */
	public Map getParamMap() {
		return paramMap;
	}

	/**
	 * @param paramMap The paramMap to set.
	 */
	public void setParamMap(Map paramMap) {
		this.paramMap = paramMap;
	}

	public void setZkConfigDao(ZkConfigDao zkConfigDao) {
		this.zkConfigDao = zkConfigDao;
	}

	public String getSearchName() {
		return searchName;
	}

	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}

}
