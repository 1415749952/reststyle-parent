CREATE DATABASE `reststyle`  DEFAULT CHARACTER SET utf8mb4 ;


-- reststyle.account definition

CREATE TABLE `account` (
                           `id` varchar(255) NOT NULL COMMENT '唯一标识符id',
                           `username` varchar(255) NOT NULL COMMENT '登录名',
                           `password` varchar(255) NOT NULL COMMENT '登录密码',
                           `name` varchar(255) NOT NULL COMMENT '姓名',
                           `telephone` varchar(255) NOT NULL COMMENT '电话',
                           `email` varchar(255) NOT NULL COMMENT '邮箱',
                           `create_time` datetime NOT NULL COMMENT '数据创建时间',
                           `update_time` datetime DEFAULT NULL COMMENT '数据最后更新时间',
                           `state` varchar(255) NOT NULL COMMENT '状态',
                           `is_account_non_expired` int(1) DEFAULT NULL COMMENT '帐户未过期',
                           `is_account_non_locked` int(1) DEFAULT NULL COMMENT '帐户未锁定',
                           `is_credentials_non_expired` int(1) DEFAULT NULL COMMENT '凭证未过期',
                           `is_enabled` int(1) DEFAULT NULL COMMENT '账号已启用',
                           PRIMARY KEY (`id`) USING BTREE,
                           UNIQUE KEY `user_name` (`username`) USING BTREE,
                           UNIQUE KEY `telephone` (`telephone`) USING BTREE,
                           UNIQUE KEY `email` (`email`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户表';


-- reststyle.authority definition

CREATE TABLE `authority` (
                             `id` varchar(255) NOT NULL COMMENT '唯一标识符id',
                             `authority` varchar(255) NOT NULL COMMENT '权限标识符',
                             `authority_name` varchar(255) NOT NULL COMMENT '权限名称',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='权限表';


-- reststyle.menu definition

CREATE TABLE `menu` (
                        `id` varchar(255) NOT NULL COMMENT '菜单id',
                        `menu_name` varchar(255) NOT NULL COMMENT '菜单名称',
                        `menu_url` varchar(255) DEFAULT NULL COMMENT '菜单url',
                        `menu_icon` varchar(255) NOT NULL COMMENT '菜单图标',
                        `menu_type` varchar(255) NOT NULL COMMENT '菜单类型：top_menu, main_menu, admin_menu',
                        `menu_score` int(11) NOT NULL COMMENT '菜单分数，越大排序越前',
                        `parent_id` varchar(255) NOT NULL COMMENT '父级菜单id',
                        `create_time` datetime NOT NULL COMMENT '创建时间',
                        `update_time` datetime DEFAULT NULL COMMENT '更新时间',
                        `state` varchar(255) NOT NULL COMMENT '菜单状态',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='菜单表';


-- reststyle.persistent_logins definition

CREATE TABLE `persistent_logins` (
                                     `username` varchar(64) NOT NULL,
                                     `series` varchar(64) NOT NULL,
                                     `token` varchar(64) NOT NULL,
                                     `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


-- reststyle.`role` definition

CREATE TABLE `role` (
                        `id` varchar(11) NOT NULL COMMENT '唯一标识符id',
                        `role_name` varchar(255) NOT NULL COMMENT '角色名',
                        `role_describe` varchar(255) DEFAULT NULL COMMENT '角色描述',
                        `create_time` datetime NOT NULL COMMENT '角色创建时间',
                        `update_time` datetime DEFAULT NULL COMMENT '角色最后更新时间',
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色表';


-- reststyle.account_role definition

CREATE TABLE `account_role` (
                                `id` varchar(255) NOT NULL COMMENT '唯一标识符id',
                                `account_id` varchar(255) NOT NULL COMMENT '用户id',
                                `role_id` varchar(255) NOT NULL COMMENT '角色id',
                                PRIMARY KEY (`id`) USING BTREE,
                                KEY `account_id` (`account_id`) USING BTREE,
                                KEY `role_id` (`role_id`) USING BTREE,
                                CONSTRAINT `account_role_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
                                CONSTRAINT `account_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';


-- reststyle.exception_log definition

CREATE TABLE `exception_log` (
                                 `id` varchar(255) NOT NULL,
                                 `request_param` varchar(255) DEFAULT NULL COMMENT '请求参数',
                                 `method_name` varchar(255) DEFAULT NULL COMMENT '请求方法名',
                                 `exception_name` varchar(255) DEFAULT NULL COMMENT '异常名称',
                                 `exception_message` varchar(255) DEFAULT NULL COMMENT '异常信息',
                                 `account_id` varchar(255) DEFAULT NULL COMMENT '操作人id',
                                 `url` varchar(255) DEFAULT NULL COMMENT '操作URI',
                                 `ip` varchar(255) DEFAULT NULL COMMENT '操作员IP',
                                 `occur_time` datetime DEFAULT NULL COMMENT '异常发生时间',
                                 `version` varchar(255) DEFAULT NULL COMMENT '系统版本',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 KEY `account_id` (`account_id`) USING BTREE,
                                 CONSTRAINT `exception_log_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='异常日志表';


-- reststyle.operation_log definition

CREATE TABLE `operation_log` (
                                 `id` varchar(255) NOT NULL,
                                 `method_name` varchar(255) DEFAULT NULL COMMENT '请求方法名',
                                 `request_param` varchar(255) DEFAULT NULL COMMENT '请求参数',
                                 `response_param` varchar(255) DEFAULT NULL COMMENT '返回结果',
                                 `account_id` varchar(255) DEFAULT NULL COMMENT '操作人id',
                                 `url` varchar(255) DEFAULT NULL COMMENT '操作URI',
                                 `ip` varchar(255) DEFAULT NULL COMMENT '操作员IP',
                                 `request_time` datetime DEFAULT NULL COMMENT '请求发生时间',
                                 `version` varchar(255) DEFAULT NULL COMMENT '系统版本',
                                 `detail` varchar(255) DEFAULT NULL COMMENT '方法描述(这个方法是干什么的)',
                                 `level` int(11) DEFAULT NULL COMMENT '日志等级:自己定，此处分为1-9',
                                 `operation_type` varchar(255) DEFAULT NULL COMMENT '操作类型(enum):主要是select,insert,update,delete',
                                 `operation_unit` varchar(255) NOT NULL COMMENT '被操作的对象被操作的对象(此处使用enum):可以是任何对象，如表名(user)，或者是工具(redis)',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 KEY `account_id` (`account_id`) USING BTREE,
                                 CONSTRAINT `operation_log_ibfk_1` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='操作日志表';


-- reststyle.role_authority definition

CREATE TABLE `role_authority` (
                                  `id` varchar(255) NOT NULL COMMENT '唯一标识符id',
                                  `role_id` varchar(255) NOT NULL COMMENT '角色id',
                                  `authority_id` varchar(255) NOT NULL COMMENT '菜单id',
                                  PRIMARY KEY (`id`) USING BTREE,
                                  KEY `role_id` (`role_id`) USING BTREE,
                                  KEY `authority_id` (`authority_id`) USING BTREE,
                                  CONSTRAINT `role_authority_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                                  CONSTRAINT `role_authority_ibfk_2` FOREIGN KEY (`authority_id`) REFERENCES `authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色权限表';


-- reststyle.role_menu definition

CREATE TABLE `role_menu` (
                             `id` varchar(255) NOT NULL,
                             `role_id` varchar(255) DEFAULT NULL COMMENT '角色id',
                             `menu_id` varchar(255) DEFAULT NULL COMMENT '角色拥有菜单的id',
                             PRIMARY KEY (`id`) USING BTREE,
                             KEY `role_id` (`role_id`) USING BTREE,
                             KEY `menu_id` (`menu_id`) USING BTREE,
                             CONSTRAINT `role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`),
                             CONSTRAINT `role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `menu` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

