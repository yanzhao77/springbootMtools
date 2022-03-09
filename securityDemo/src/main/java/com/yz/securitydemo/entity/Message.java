package com.yz.securitydemo.entity;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Message {

	private String id;

	private String mqName;
	private String msgData;
	private Integer msgProducer;
	private Integer msgConsumer;
	private Integer msgLevel;
	private Integer mqLevel;
	private Integer status;
	private Date createtime;
	private Integer msgType;

}
