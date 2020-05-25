package cn.ccsu.service.business;

import cn.ccsu.condition.AccountCondition;
import cn.ccsu.dto.AccountDTO;
import cn.ccsu.utils.requestUtils.PageParam;
import cn.ccsu.vo.AccountVO;
import com.github.pagehelper.PageInfo;

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
    public PageInfo<AccountVO> query(AccountCondition accountCondition, PageParam pageParam);

    public AccountVO queryAccountById(String id);

    public Integer creatAccount(AccountDTO accountDTO);

    public Integer updataAccount(AccountDTO accountDTO, String id);

    public Integer delete(String id);
}
