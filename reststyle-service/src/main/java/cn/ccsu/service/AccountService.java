package cn.ccsu.service;

import cn.ccsu.condition.AccountCondition;
import cn.ccsu.dto.AccountDTO;
import cn.ccsu.vo.AccountVO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-20
 * @Time: 16:25
 */
public interface AccountService
{
    public List<AccountVO> query(AccountCondition accountCondition);

    public AccountVO queryAccountById(String id);

    public Integer creatAccount(AccountDTO accountDTO);

    public Integer updataAccount(AccountDTO accountDTO, String id);

    public Integer delete(String id);
}
