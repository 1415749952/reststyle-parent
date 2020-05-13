package cn.ccsu.response;


import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:API 统一返回状态码
 * <p>
 * <p>
 * 200 – 服务器成功返回网页 404 – 请求的网页不存在 503 – 服务不可用
 * 1xx（临时响应）
 * 表示临时响应并需要请求者继续执行操作的状态代码。
 * <p>
 * 代码   说明
 * 100   （继续） 请求者应当继续提出请求。 服务器返回此代码表示已收到请求的第一部分，正在等待其余部分。
 * 101   （切换协议） 请求者已要求服务器切换协议，服务器已确认并准备切换。
 * <p>
 * 2xx （成功）
 * 表示成功处理了请求的状态代码。
 * <p>
 * 代码   说明
 * 200   （成功）  服务器已成功处理了请求。 通常，这表示服务器提供了请求的网页。
 * 201   （已创建）  请求成功并且服务器创建了新的资源。
 * 202   （已接受）  服务器已接受请求，但尚未处理。
 * 203   （非授权信息）  服务器已成功处理了请求，但返回的信息可能来自另一来源。
 * 204   （无内容）  服务器成功处理了请求，但没有返回任何内容。
 * 205   （重置内容） 服务器成功处理了请求，但没有返回任何内容。
 * 206   （部分内容）  服务器成功处理了部分 GET 请求。
 * <p>
 * 3xx （重定向）
 * 表示要完成请求，需要进一步操作。 通常，这些状态代码用来重定向。
 * <p>
 * 代码   说明
 * 300   （多种选择）  针对请求，服务器可执行多种操作。 服务器可根据请求者 (user agent) 选择一项操作，或提供操作列表供请求者选择。
 * 301   （永久移动）  请求的网页已永久移动到新位置。 服务器返回此响应（对 GET 或 HEAD 请求的响应）时，会自动将请求者转到新位置。
 * 302   （临时移动）  服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。
 * 303   （查看其他位置） 请求者应当对不同的位置使用单独的 GET 请求来检索响应时，服务器返回此代码。
 * 304   （未修改） 自从上次请求后，请求的网页未修改过。 服务器返回此响应时，不会返回网页内容。
 * 305   （使用代理） 请求者只能使用代理访问请求的网页。 如果服务器返回此响应，还表示请求者应使用代理。
 * 307   （临时重定向）  服务器目前从不同位置的网页响应请求，但请求者应继续使用原有位置来进行以后的请求。
 * <p>
 * 4xx（请求错误）
 * 这些状态代码表示请求可能出错，妨碍了服务器的处理。
 * <p>
 * 代码   说明
 * 400   （错误请求） 服务器不理解请求的语法。
 * 401   （未授权） 请求要求身份验证。 对于需要登录的网页，服务器可能返回此响应。
 * 403   （禁止） 服务器拒绝请求。
 * 404   （未找到） 服务器找不到请求的网页。
 * 405   （方法禁用） 禁用请求中指定的方法。
 * 406   （不接受） 无法使用请求的内容特性响应请求的网页。
 * 407   （需要代理授权） 此状态代码与 401（未授权）类似，但指定请求者应当授权使用代理。
 * 408   （请求超时）  服务器等候请求时发生超时。
 * 409   （冲突）  服务器在完成请求时发生冲突。 服务器必须在响应中包含有关冲突的信息。
 * 410   （已删除）  如果请求的资源已永久删除，服务器就会返回此响应。
 * 411   （需要有效长度） 服务器不接受不含有效内容长度标头字段的请求。
 * 412   （未满足前提条件） 服务器未满足请求者在请求中设置的其中一个前提条件。
 * 413   （请求实体过大） 服务器无法处理请求，因为请求实体过大，超出服务器的处理能力。
 * 414   （请求的 URI 过长） 请求的 URI（通常为网址）过长，服务器无法处理。
 * 415   （不支持的媒体类型） 请求的格式不受请求页面的支持。
 * 416   （请求范围不符合要求） 如果页面无法提供请求的范围，则服务器会返回此状态代码。
 * 417   （未满足期望值） 服务器未满足”期望”请求标头字段的要求。
 * <p>
 * 5xx（服务器错误）
 * 这些状态代码表示服务器在尝试处理请求时发生内部错误。 这些错误可能是服务器本身的错误，而不是请求出错。
 * <p>
 * 代码   说明
 * 500   （服务器内部错误）  服务器遇到错误，无法完成请求。
 * 501   （尚未实施） 服务器不具备完成请求的功能。 例如，服务器无法识别请求方法时可能会返回此代码。
 * 502   （错误网关） 服务器作为网关或代理，从上游服务器收到无效响应。
 * 503   （服务不可用） 服务器目前无法使用（由于超载或停机维护）。 通常，这只是暂时状态。
 * 504   （网关超时）  服务器作为网关或代理，但是没有及时从上游服务器收到请求。
 * 505   （HTTP 版本不受支持） 服务器不支持请求中所用的 HTTP 协议版本。
 * <p>
 * <p>
 * <p>
 * RFC 6585 最近刚刚发布，该文档描述了 4 个新的 HTTP 状态码。
 * <p>
 * HTTP 协议还在变化？是的，HTTP 协议一直在演变，新的状态码对于开发 REST 服务或者说是基于 HTTP 的服务非常有用，下面我们为你详细介绍这四个新的状态码以及是否应该使用。
 * <p>
 * 428 Precondition Required (要求先决条件)
 * 先决条件是客户端发送 HTTP 请求时，如果想要请求能成功必须满足一些预设的条件。
 * <p>
 * 一个好的例子就是 If-None-Match 头，经常在 GET 请求中使用，如果指定了 If-None-Match ，那么客户端只在响应中的 ETag 改变后才会重新接收回应。
 * <p>
 * 先决条件的另外一个例子就是 If-Match 头，这个一般用在 PUT 请求上用于指示只更新没被改变的资源，这在多个客户端使用 HTTP 服务时用来防止彼此间不会覆盖相同内容。
 * <p>
 * 当服务器端使用 428 Precondition Required 状态码时，表示客户端必须发送上述的请求头才能执行请求，这个方法为服务器提供一种有效的方法来阻止 'lost update' 问题。
 * <p>
 * 429 Too Many Requests (太多请求)
 * 当你需要限制客户端请求某个服务数量时，该状态码就很有用，也就是请求速度限制。
 * <p>
 * 在此之前，有一些类似的状态码，例如 '509 Bandwidth Limit Exceeded'. Twitter 使用 420 （这不是HTTP定义的状态码）
 * <p>
 * 如果你希望限制客户端对服务的请求数，可使用 429 状态码，同时包含一个 Retry-After 响应头用于告诉客户端多长时间后可以再次请求服务。
 * <p>
 * 431 Request Header Fields Too Large (请求头字段太大)
 * 某些情况下，客户端发送 HTTP 请求头会变得很大，那么服务器可发送 431 Request Header Fields Too Large 来指明该问题。
 * <p>
 * 我不太清楚为什么没有 430 状态码，而是直接从 429 跳到 431，我尝试搜索但没有结果。唯一的猜测是 430 Forbidden 跟 403 Forbidden 太像了，为了避免混淆才这么做的，天知道！
 * <p>
 * 511 Network Authentication Required (要求网络认证)
 * 对我来说这个状态码很有趣，如果你在开发一个 HTTP 服务器，你不一定需要处理该状态码，但如果你在编写 HTTP 客户端，那这个状态码就非常重要。
 *
 * @version 1.0
 * @author: TheFei
 * @Date: 2020-02-05
 * @Time: 0:18
 */

public enum ResultCode
{
    /**
     * 成功状态码
     */
    OK(200, String.format("操作成功(%s)", HttpStatus.OK.getReasonPhrase())),

    FORBIDDEN(403, String.format("操作成功(%s)", HttpStatus.FORBIDDEN.getReasonPhrase())),
    /**
     * 404 Web 服务器找不到您所请求的文件或脚本。请检查URL 以确保路径正确。
     */
    NOT_FOUND(404, String.format("哎呀，无法找到这个资源啦(%s)", HttpStatus.NOT_FOUND.getReasonPhrase())),

    /**
     * 405 对于请求所标识的资源，不允许使用请求行中所指定的方法。请确保为所请求的资源设置了正确的 MIME 类型。
     */
    METHOD_NOT_ALLOWED(405, String.format("请换个姿势操作试试(%s)", HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase())),

    /**
     * 415 Unsupported Media Type
     */
    UNSUPPORTED_MEDIA_TYPE(415, String.format("呀，不支持该媒体类型(%s)", HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())),

    /**
     * 系统异常 500 服务器的内部错误
     */
    INTERNAL_SERVER_ERROR(500, "程序出现错误"),




    //参数错误：10001-19999
    /**
     * 校验参数错误
     */
    PARAM_IS_INVALID(10001, "校验参数错误"),

    /**
     * 用户错误：20001-29999
     */
    USER_NOT_LOGGED_IN(20001, "用户未登录"),

    USER_LOGIN_FAILURE(20002, "用户登陆失败"),

    /**
     * 业务错误：30001-39999
     */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "某业务出现问题"),

    /**
     * 系统错误：40001-49999
     */
    SYSTEM_INNER_ERROR(40001, "系统繁忙，请稍后重试"),

    /**
     * 数据错误：50001-599999
     */
    RESULE_DATA_NONE(50001, "数据未找到"),

    DATA_IS_WRONG(50002, "数据有误"),

    DATA_ALREADY_EXISTED(50003, "数据已存在"),

    DATA_HAS_BEEN_DELETED(50004, "数据已被删除"),

    /**
     * 接口错误：60001-69999
     */
    INTERFACE_INNER_INVOKE_ERROR(60001, "内部系统接口调用异常"),

    INTERFACE_OUTTER_INVOKE_ERROR(60002, "外部系统接口调用异常"),

    INTERFACE_FORBID_VISIT(60003, "该接口禁止访问"),

    INTERFACE_ADDRESS_INVALID(60004, "接口地址无效"),

    INTERFACE_REQUEST_TIMEOUT(60005, "接口请求超时"),

    INTERFACE_EXCEED_LOAD(60006, "接口负载过高"),



    //权限错误：70001-79999
    /**
     * 70001无访问权限
     */
    PERMISSION_NO_ACCESS(70001, "无访问权限"),

//不可知的错误：80001-89999
    /**
     *  80001未知错误
     */
    UNKNOWN_ERROR(80001, "未知错误"),
//不可知的错误：90001-99999
    /**
     *  90001 form表单重复提交错误
     */
    FORM_SAME_SUBMIT(9001,"form表单重复提交错误")

    ;




    /**
     * 状态码
     */
    private Integer code;
    /**
     * 请求失败返回的提示信息，给前端进行页面展示的信息
     */
    private String message;

    ResultCode(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    public Integer getCode()
    {
        return code;
    }

    public void setCode(Integer code)
    {
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    //校验重复的code值
    public static void main(String[] args)
    {
        ResultCode[] ApiResultCodes = ResultCode.values();
        List<Integer> codeList = new ArrayList<>();
        for (ResultCode ApiResultCode : ApiResultCodes)
        {
            if (codeList.contains(ApiResultCode.code))
            {
                System.out.println(ApiResultCode.code);
            }
            else
            {
                codeList.add(ApiResultCode.getCode());
            }
        }
    }
}
