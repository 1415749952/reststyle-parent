package cn.ccsu.vo;

import cn.ccsu.response.GeneralViews;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-05-20
 * @Time: 15:06
 */
@Data
public class AccountVO
{
    /**
     * 不在列表中展示createTime和updateTime，返回列表JSON中就没有createTime和updateTime
     */
    public interface AccountListView extends GeneralViews.RestView
    {
    }

    public interface AccountDetailView extends AccountListView
    {
    }


    @JsonView(AccountListView.class)
    private String id;

    @JsonView(AccountListView.class)
    private String username;

    @JsonView(AccountDetailView.class)
    private String password;

    @JsonView(AccountListView.class)
    private String name;

    @JsonView(AccountListView.class)
    private String telephone;

    @JsonView(AccountListView.class)
    private String email;

    @JsonView(AccountDetailView.class)
    private Date createTime;

    @JsonView(AccountDetailView.class)
    private Date modifyTime;

    @JsonView(AccountDetailView.class)
    private String delFlag;
}
