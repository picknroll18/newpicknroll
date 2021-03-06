package com.picknroll.web.service.mybatis;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.picknroll.web.dao.AppDao;
import com.picknroll.web.dao.MemberDao;
import com.picknroll.web.dao.MemberRoleDao;
import com.picknroll.web.entity.App;
import com.picknroll.web.entity.Member;
import com.picknroll.web.entity.MemberRole;
import com.picknroll.web.service.HomeService;
import com.picknroll.web.util.ParameterUtil;

@Service
public class MybatisHomeService implements HomeService {

	@Autowired
	private MemberRoleDao memberRoleDao;
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private AppDao appDao;

	@Override
	public String getDefaultRoleName(String memberId) {

		List<MemberRole> list = memberRoleDao.getList(ParameterUtil.mapping("memberId", memberId));
		String roleName = "ROLE_CLIENT";
		for (MemberRole role : list) {
			if (role.getDefaultRole())
				roleName = role.getRoleName();
		}
		return roleName;
	}

	@Override
	public Member getMember(String id) {
		return memberDao.get(ParameterUtil.mapping("id", id));
	}

	@Override
	public List<App> getAppListByMemberId(Map<String, String> params) {
		return appDao.getListByMemberId(params);
	}

}