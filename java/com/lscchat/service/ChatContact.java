package com.lscchat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface ChatContact {
	List<Map<String, String>> findChatContactService(Long companyId, Long depId, Long unitId, String mobileNo);
}
