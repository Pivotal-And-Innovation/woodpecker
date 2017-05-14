package com.woodpecker.entity;

import lombok.*;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 用户信息模型类
 * 实验结论：这@Builder有点玄乎啊，测试运行时报错。。。1、实际参数列表和形式参数列表长度不同。。。2、找不到builder()符号引用。。。
 *
 * @author Relaxier
 * @since 2017年03月30日
 */
@SuppressWarnings("all")
@Data
//@Builder
@Accessors(chain = true)
@RequiredArgsConstructor(staticName = "of")
public class WoodUser {
    /**
     * 记录ID
     */
    private int id;
    /**
     * 用户UUID
     */
    @NonNull
    private String uuid;
    /**
     * 用户名字
     */
    private String name;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 身份证号
     */
    private String idNum;
    /**
     * 用户密码
     */
    private String password;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 该用户状态：1-可用 0-禁用
     */
    private byte status;
    /**
     * 用户更新时间
     */
    private Date updateTime;

    /**
     * 以下为标准构建器模式：运用于参数太多，且很多参数都可选的情况下，比重载构造器和java bean模式好太多
     */
    public static class Builder {
        private int id;
        private String uuid;
        private String name;
        private String nickname;
        private String idNum;
        private String password;
        private String phone;
        private String email;
        private byte status;
        private Date updateTime;
        /**
         * 必需参数
         */
        public Builder(String uuid, String idNum) {
            this.uuid = uuid;
            this.idNum = idNum;
        }
        /**
         * 以下均为可选参数
         */
        public Builder name(String name) {
            this.name = name;
            return this;
        }
        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }
        public Builder password(String password) {
            this.password = password;
            return this;
        }
        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }
        public Builder email(String email) {
            this.email = email;
            return this;
        }
        public Builder status(byte status) {
            this.status = status;
            return this;
        }
        public Builder updateTime(Date updateTime) {
            this.updateTime = updateTime;
            return this;
        }

        public WoodUser build() {
            return new WoodUser(this);
        }
    }

    private WoodUser(Builder builder) {
        this.uuid = builder.uuid;
        this.name = builder.name;
        this.nickname = builder.nickname;
        this.idNum = builder.idNum;
        this.password = builder.password;
        this.phone = builder.phone;
        this.email = builder.email;
        this.status = builder.status;
        this.updateTime = builder.updateTime;
    }

    public static void main(String[] args) {
        // 验证链式调用
        WoodUser info = new WoodUser("dyeDha").setEmail("422624843@qq.com").setName("Relaxier").setNickname("就是帅！");
        System.out.println(info.toString());

        // 验证必要带有必须参数的构造器
        WoodUser userInfo = WoodUser.of("dyeDha").setIdNum("38438784649856486745");
        System.out.println(userInfo.toString());

        // 验证构建器模式-测试不通过
//        WoodUser woodUserInfo = WoodUser.builder().password("343543463").name("Relaxier").build();
//        System.out.println(woodUserInfo.toString());

        // 验证自己写的标准的构建器模式
        WoodUser info1 = new WoodUser.Builder("thy", "2323243546577878979").name("Relaxier").email("422624843@qq.com").build();
        System.out.println(info1.toString());
    }

}
